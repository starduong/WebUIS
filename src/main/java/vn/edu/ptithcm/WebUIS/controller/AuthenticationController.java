package vn.edu.ptithcm.WebUIS.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Account;
import vn.edu.ptithcm.WebUIS.domain.mapper.AccountMapper;
import vn.edu.ptithcm.WebUIS.domain.request.LoginRequest;
import vn.edu.ptithcm.WebUIS.domain.request.password.ForgotPasswordRequest;
import vn.edu.ptithcm.WebUIS.domain.request.password.ResetPasswordRequest;
import vn.edu.ptithcm.WebUIS.domain.response.LoginResponse;
import vn.edu.ptithcm.WebUIS.domain.response.MessageResponse;
import vn.edu.ptithcm.WebUIS.exception.BadRequestException;
import vn.edu.ptithcm.WebUIS.exception.IdInValidException;
import vn.edu.ptithcm.WebUIS.service.AccountService;
import vn.edu.ptithcm.WebUIS.service.EmailService;
import vn.edu.ptithcm.WebUIS.util.SecurityUtil;
import vn.edu.ptithcm.WebUIS.util.annotation.ApiMessage;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final SecurityUtil securityUtil;
    private final AccountService accountService;
    private final AccountMapper accountMapper;
    private final EmailService emailService;

    @Value("${duong.jwt.refresh-token-validity-in-seconds}")
    private long jwtRefreshTokenValidityInSeconds;

    @Value("${app.frontend.url}")
    private String frontendUrl;

    @PostMapping(value = "/login")
    @ApiMessage("Login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginDTO) throws Exception {
        // input username and password
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), loginDTO.getPassword());
        // authenticate -> check username and password by LoadUserByUsername
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication); // set authentication to context
        LoginResponse responseLoginDTO = new LoginResponse();
        // Get user information based on role
        Account account = accountService.findByUsername(loginDTO.getUsername());
        if (account != null) {
            LoginResponse.UserLogin userLogin = accountMapper.convertAccountLoginToResponse(account);
            if (userLogin.getUserId() != null) {
                responseLoginDTO.setUserLogin(userLogin);
            } else {
                throw new BadRequestException("login failed");
            }
        }
        String accessToken = securityUtil.generateTokenLogin(authentication.getName(), responseLoginDTO.getUserLogin());
        responseLoginDTO.setAccessToken(accessToken);

        String refreshToken = securityUtil.generateRefreshToken(loginDTO.getUsername(), responseLoginDTO);
        accountService.updateAccountToken(loginDTO.getUsername(), refreshToken);
        // set cookies
        ResponseCookie resCookies = ResponseCookie
                .from("refresh_token", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(jwtRefreshTokenValidityInSeconds)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, resCookies.toString())
                .body(responseLoginDTO);
    }

    @GetMapping("/refresh-token")
    @ApiMessage("Refresh token")
    public ResponseEntity<LoginResponse> getRefreshToken(
            @CookieValue(name = "refresh_token", defaultValue = "abc") String refresh_token) throws IdInValidException {
        if (refresh_token.equals("abc")) {
            throw new IdInValidException("Bạn không có refresh token ở cookie");
        }
        // check valid
        Jwt decodedToken = this.securityUtil.checkValidRefreshToken(refresh_token);
        String username = decodedToken.getSubject();

        Account currentAccount = accountService.findByRefreshTokenAndUsername(refresh_token, username);
        if (currentAccount == null) {
            throw new IdInValidException("Refresh Token không hợp lệ");
        }

        // issue new token/set refresh token as cookies
        LoginResponse responseLoginDTO = new LoginResponse();
        // Get user information based on role
        Account account = accountService.findByUsername(username);
        if (account != null) {
            LoginResponse.UserLogin userLogin = accountMapper.convertAccountLoginToResponse(account);
            if (userLogin.getUserId() != null) {
                responseLoginDTO.setUserLogin(userLogin);
            } else {
                throw new BadRequestException("login failed");
            }
        }
        String accessToken = securityUtil.generateTokenLogin(username, responseLoginDTO.getUserLogin());
        responseLoginDTO.setAccessToken(accessToken);

        String newRefreshToken = securityUtil.generateRefreshToken(username, responseLoginDTO);
        accountService.updateAccountToken(username, newRefreshToken);
        // set cookies
        ResponseCookie resCookies = ResponseCookie
                .from("refresh_token", newRefreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(jwtRefreshTokenValidityInSeconds)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, resCookies.toString())
                .body(responseLoginDTO);
    }

    @PostMapping("/logout")
    @ApiMessage("Logout")
    public ResponseEntity<Void> logout() throws IdInValidException {
        String username = SecurityUtil.getCurrentUserLogin().isPresent() ? SecurityUtil.getCurrentUserLogin().get()
                : "";

        if (username.equals("")) {
            throw new IdInValidException("Access Token không hợp lệ");
        }

        // update refresh token = null
        this.accountService.updateAccountToken(username, null);

        // remove refresh token cookie
        ResponseCookie deleteSpringCookie = ResponseCookie
                .from("refresh_token", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, deleteSpringCookie.toString())
                .body(null);
    }

    /**
     * Gửi email đặt lại mật khẩu
     * 
     * @param request Request body chứa username
     * @param request
     * @return
     */
    @PostMapping("/forgot-password")
    @ApiMessage("Gửi email đặt lại mật khẩu")
    public ResponseEntity<MessageResponse> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        try {
            // Tìm tài khoản theo username
            Account account = accountService.findAccountByEmail(request.getEmail());
            if (account == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(MessageResponse.of("Không tìm thấy tài khoản với username này"));
            }

            // Tạo token đặt lại mật khẩu
            String token = securityUtil.generatePasswordResetToken(request.getEmail(), account.getId());

            // Tạo link đặt lại mật khẩu
            String resetLink = frontendUrl + "/reset-password?token=" + token;

            // Gửi email
            emailService.sendPasswordResetEmail(request.getEmail(), resetLink);

            return ResponseEntity
                    .ok(MessageResponse.of("Email đặt lại mật khẩu đã được gửi: \n" + resetLink));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MessageResponse.of("Có lỗi xảy ra khi gửi email đặt lại mật khẩu: " + e.getMessage()));
        }
    }

    /**
     * Đặt lại mật khẩu
     * 
     * @param request Request body chứa token và mật khẩu mới
     * @return
     */
    @PostMapping("/reset-password")
    @ApiMessage("Đặt lại mật khẩu")
    public ResponseEntity<MessageResponse> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        try {
            // Kiểm tra tính hợp lệ của token
            Jwt jwt = securityUtil.validatePasswordResetToken(request.getToken());

            // Lấy thông tin từ token
            // String username = jwt.getSubject();
            Long accountId = jwt.getClaim("accountId");

            if (!request.getNewPassword().equals(request.getConfirmPassword())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(MessageResponse.of("Mật khẩu mới và mật khẩu xác nhận không khớp"));
            }

            // Cập nhật mật khẩu mới
            accountService.updatePassword(accountId.intValue(), request.getNewPassword());

            return ResponseEntity.ok(MessageResponse.of("Mật khẩu đã được đặt lại thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(MessageResponse.of("Token không hợp lệ hoặc đã hết hạn: " + e.getMessage()));
        }
    }

}
