package vn.edu.ptithcm.WebUIS.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service    
public class SecurityUtil {
    private final JwtEncoder jwtEncoder; //Được ghi đè trong SecurityConfiguration

    public static final String AUTHORITIES_KEY = "star";
    public static final MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS512; 

    @Value("${duong.jwt.base64-secret}")
    public String jwtBase64Secret;

    @Value("${duong.jwt.token-validity-in-seconds}")
    private long jwtTokenValidityInSeconds;

    public String generateTokenLogin(Authentication authentication) {
        Instant now = Instant.now(); 
        Instant validity = now.plus(this.jwtTokenValidityInSeconds, ChronoUnit.SECONDS); 
        // @formatter:off 
        JwtClaimsSet claims = JwtClaimsSet.builder() 
            .issuedAt(now) 
            .expiresAt(validity) 
            .subject(authentication.getName()) 
            .claim(AUTHORITIES_KEY, authentication) 
            .build(); 
        // @formatter:on
        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build(); 

        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }
}
