package vn.edu.ptithcm.WebUIS.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.util.Base64;

import lombok.extern.slf4j.Slf4j;
import vn.edu.ptithcm.WebUIS.domain.entity.Account;
import vn.edu.ptithcm.WebUIS.domain.response.LoginResponse;
import vn.edu.ptithcm.WebUIS.service.AccountService;

@Service
@Slf4j
public class SecurityUtil {
    private final JwtEncoder jwtEncoder; // Được ghi đè trong SecurityConfiguration
    private final AccountService accountService;

    public static final MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS512;

    @Value("${duong.jwt.base64-secret}")
    public String jwtBase64Secret;

    @Value("${duong.jwt.access-token-validity-in-seconds}")
    private long jwtTokenValidityInSeconds;

    @Value("${duong.jwt.refresh-token-validity-in-seconds}")
    private long jwtRefreshTokenValidityInSeconds;

    // Use constructor injection with @Lazy to break circular dependency
    public SecurityUtil(JwtEncoder jwtEncoder, @Lazy AccountService accountService) {
        this.jwtEncoder = jwtEncoder;
        this.accountService = accountService;
    }

    /**
     * Tạo access token cho người dùng đăng nhập
     * 
     * @param username  Tên đăng nhập
     * @param userLogin Thông tin người dùng
     * @return JWT access token
     */
    public String generateTokenLogin(String username, LoginResponse.UserLogin userLogin) {
        Instant now = Instant.now();
        Instant validity = now.plus(this.jwtTokenValidityInSeconds, ChronoUnit.SECONDS);

        // Lấy thông tin vai trò của tài khoản
        Account account = accountService.findByUsername(username);
        String role = account != null ? account.getRole().getName() : "";

        // Tạo JWT claims
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(validity)
                .subject(username)
                .claim(Constants.Security.USER_KEY, userLogin)
                .claim(Constants.Security.AUTHORITIES_KEY, role)
                .build();

        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }

    /**
     * Tạo refresh token cho người dùng đăng nhập
     * 
     * @param username    Tên đăng nhập
     * @param resLoginDTO Thông tin đăng nhập
     * @return JWT refresh token
     */
    public String generateRefreshToken(String username, LoginResponse resLoginDTO) {
        Instant now = Instant.now();
        Instant validity = now.plus(this.jwtRefreshTokenValidityInSeconds, ChronoUnit.SECONDS);

        // Lấy thông tin vai trò của tài khoản
        Account account = accountService.findByUsername(username);
        String role = account != null ? account.getRole().getName() : "";

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(validity)
                .subject(username)
                .claim(Constants.Security.USER_KEY, resLoginDTO.getUserLogin())
                .claim(Constants.Security.AUTHORITIES_KEY, role)
                .build();
        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }

    /**
     * Lấy tên đăng nhập của người dùng hiện tại
     * 
     * @return Tên đăng nhập hoặc empty nếu chưa đăng nhập
     */
    public static Optional<String> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(extractPrincipal(securityContext.getAuthentication()));
    }

    /**
     * Trích xuất tên đăng nhập từ đối tượng Authentication
     */
    private static String extractPrincipal(Authentication authentication) {
        if (authentication == null) {
            return null;
        } else if (authentication.getPrincipal() instanceof UserDetails springSecurityUser) {
            return springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getSubject();
        } else if (authentication.getPrincipal() instanceof String s) {
            return s;
        }
        return null;
    }

    /**
     * Lấy JWT của người dùng hiện tại
     * 
     * @return JWT token hoặc empty nếu không có
     */
    public static Optional<String> getCurrentUserJWT() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .filter(authentication -> authentication.getCredentials() instanceof String)
                .map(authentication -> (String) authentication.getCredentials());
    }

    /**
     * Tạo SecretKey từ chuỗi base64
     * 
     * @return SecretKey để mã hóa/giải mã JWT
     */
    private SecretKey getSecretKey() {
        byte[] keyBytes = Base64.from(jwtBase64Secret).decode();
        return new SecretKeySpec(keyBytes, 0, keyBytes.length,
                JWT_ALGORITHM.getName());
    }

    /**
     * Kiểm tra tính hợp lệ của refresh token
     * 
     * @param token Refresh token cần kiểm tra
     * @return Đối tượng Jwt nếu token hợp lệ
     * @throws Exception Nếu token không hợp lệ
     */
    public Jwt checkValidRefreshToken(String token) {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(
                getSecretKey()).macAlgorithm(SecurityUtil.JWT_ALGORITHM).build();
        try {
            return jwtDecoder.decode(token);
        } catch (Exception e) {
            log.error("Refresh Token error: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Tạo token đặt lại mật khẩu
     * 
     * @param email     Email của tài khoản cần đặt lại mật khẩu
     * @param accountId ID của tài khoản
     * @return JWT token
     */
    public String generatePasswordResetToken(String email, Integer accountId) {
        Instant now = Instant.now();
        // Token hết hạn sau 15 phút
        Instant validity = now.plus(15, ChronoUnit.MINUTES);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(validity)
                .subject(email)
                .claim("accountId", accountId)
                .claim("purpose", "reset")
                .build();

        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }

    /**
     * Kiểm tra tính hợp lệ của token đặt lại mật khẩu
     * 
     * @param token Token cần kiểm tra
     * @return Đối tượng Jwt nếu token hợp lệ
     * @throws Exception Nếu token không hợp lệ hoặc không phải token đặt lại mật
     *                   khẩu
     */
    public Jwt validatePasswordResetToken(String token) {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(
                getSecretKey()).macAlgorithm(SecurityUtil.JWT_ALGORITHM).build();
        try {
            Jwt jwt = jwtDecoder.decode(token);

            // Kiểm tra purpose của token
            String purpose = jwt.getClaim("purpose");
            if (!"reset".equals(purpose)) {
                throw new IllegalArgumentException("Invalid token purpose");
            }

            return jwt;
        } catch (Exception e) {
            log.error("Password Reset Token error: {}", e.getMessage());
            throw e;
        }
    }
}
