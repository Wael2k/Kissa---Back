package PFA.project.config.utils;
import PFA.project.config.security.config.JwtConfig;
import PFA.project.dao.entity.UserInfo;
import PFA.project.enums.TypeRegisterEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;

import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class JwtUtil {


    public static String generateToken(UserInfo userInfo, JwtConfig jwtConfig) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        return Jwts.builder()
                .setSubject(userInfo.getEmail())
                .claim(jwtConfig.getAccessId(), userInfo.getId())
                .claim(jwtConfig.getRole(),userInfo.getRole().name())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Long.parseLong(jwtConfig.getExpiration())))  // in milliseconds
                .signWith(getSignInKey(jwtConfig.getSigningKey()), SignatureAlgorithm.HS256).compact();

    }
    public static String generateTokenForVerifyingMail(String customerUuid, @NotNull TypeRegisterEnum typeRegister, JwtConfig jwtConfig) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        return Jwts.builder()
                .setSubject(customerUuid)
                .claim(jwtConfig.getAccessId(), customerUuid)
                .claim(jwtConfig.getTypeRegister(),typeRegister)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 30 * 60 * 1000))  // in milliseconds
                .signWith(getSignInKey(jwtConfig.getSigningKey()), SignatureAlgorithm.HS256).compact();


    }

    public static String extractUsername(String token, String signInKey) {
        return extractClaim(token, Claims::getSubject, signInKey);
    }

    public static String extractTypeRegister(String token, String signInKey) {
        return extractAllClaims(token, signInKey).get("typeRegister").toString();
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver, String signInKey) {
        final Claims claims = extractAllClaims(token, signInKey);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token, String signInKey) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey(signInKey))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private static Key getSignInKey(String signInKey) {
        byte[] keyBytes = Decoders.BASE64.decode(signInKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static String parseToken(HttpServletRequest httpServletRequest, String jwtTokenCookieName, String signingKey) {
        String token = CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
        if (token == null) {
            return null;
        }

        String subject = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
        return subject;
    }

    public boolean isTokenValid(String token, String signingKey) {
        final String username = extractUsername(token,signingKey);
        return (username != null) && !isTokenExpired(token,signingKey);
    }

    public static boolean isTokenExpired(String token, String signingKey) {
        return extractAllClaims(token,signingKey).getExpiration().after(new Date());
    }


    public static Map<String, Object> verify(String token, String signingKey) {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(signingKey)).parseClaimsJws(token)
                .getBody();

        return new HashMap<>(claims);
    }

    public static Claims parseToken(String token, String signingKey) {
        return Jwts.parser()
                .setSigningKey(signingKey.getBytes())
                .parseClaimsJws(token.replace("", ""))
                .getBody();
    }

}
