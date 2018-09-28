package com.github.eugeneheen.berry.kit.security;

import com.github.eugeneheen.berry.kit.exception.jwt.JwtTokenExpiredException;
import com.github.eugeneheen.berry.kit.exception.jwt.JwtTokenInvalidException;
import com.github.eugeneheen.berry.kit.security.token.IJwtToken;
import com.github.eugeneheen.berry.kit.security.token.JwtToken;
import com.github.eugeneheen.berry.kit.core.ResourceKit;
import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * <p>JWT工具箱实现</p>
 * 实现了encode和decode方法，提供JWT Token进行编码和解码操作<br>
 * 通过在类路径下自定义jwt.properties文件，指定jwt.secret键的值设置自定义密文
 * @author Eugene
 */
public class JwtKit implements IJwtKit {

    /**
     * 签名字符串
     */
    private static String secret;

    static {
        final String defaultSecret = "dhwl jwt token service secret";

        try (InputStream in = ResourceKit.getResourceAsStream("jwt.properties")) {
            if (in != null) {
                Properties tokenProperty = new Properties();
                tokenProperty.load(in);
                if (tokenProperty.containsKey("jwt.secret")) {
                    secret = tokenProperty.getProperty("jwt.secret");
                } else {
                    secret = defaultSecret;
                }
            } else {
                secret = defaultSecret;
            }
        } catch (IOException e) {
            throw new SecurityException("load jwt token properties error", e);
        }
    }

    @Override
    public String encode(JwtToken jwtToken) throws SecurityException {
        if (jwtToken == null) {
            throw new SecurityException("jwt token is null");
        }

        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setId(jwtToken.getId());
        jwtBuilder.setSubject(jwtToken.getCategory() + "." + jwtToken.getAction());
        long nowDate = System.currentTimeMillis();
        jwtBuilder.setIssuedAt(new Date(nowDate));

        if (MapUtils.isNotEmpty(jwtToken.getProperties())) {
            jwtBuilder.addClaims(jwtToken.getProperties());
        }
        if (jwtToken.getExpiration() != null) {
            jwtBuilder.setExpiration(jwtToken.getExpiration());
        }
        if (StringUtils.isNotBlank(jwtToken.getUserName())) {
            jwtBuilder.claim(IJwtToken.CLAIMS_PRIVATE_KEY_USERNAME, jwtToken.getUserName());
        }
        if (StringUtils.isNotBlank(jwtToken.getPassword())) {
            jwtBuilder.claim(IJwtToken.CLAIMS_PRIVATE_KEY_PASSWORD, jwtToken.getPassword());
        }
        jwtBuilder.signWith(IJwtToken.ALG_HS256, Base64.encodeBase64(JwtKit.secret.getBytes()));
        return jwtBuilder.compact();
    }

    @Override
    public JwtToken decode(String encodeJwtToken) throws SecurityException {
        if (StringUtils.isBlank(encodeJwtToken)) {
            throw new SecurityException("encode jwt token is null");
        }

        try {
            Claims claims = Jwts.parser().setSigningKey(Base64.encodeBase64(JwtKit.secret.getBytes())).parseClaimsJws(encodeJwtToken).getBody();
            return new JwtToken(claims);
        } catch (SignatureException e) {
            throw new JwtTokenInvalidException("jwt token is invalid", e);
        } catch (ExpiredJwtException e) {
            throw new JwtTokenExpiredException("jwt token has been expired", e);
        } catch (Exception e) {
            throw new SecurityException("decode jwt token error", e);
        }
    }
}
