package vn.edu.ptithcm.WebUIS.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
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
import vn.edu.ptithcm.WebUIS.domain.response.LoginResponse;
import vn.edu.ptithcm.WebUIS.exception.IdInValidException;
import vn.edu.ptithcm.WebUIS.service.AccountService;
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

    @Value("${duong.jwt.refresh-token-validity-in-seconds}")
    private long jwtRefreshTokenValidityInSeconds;

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
            responseLoginDTO.setUserLogin(accountMapper.convertAccountLoginToResponse(account));
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
            responseLoginDTO.setUserLogin(accountMapper.convertAccountLoginToResponse(account));
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
}
