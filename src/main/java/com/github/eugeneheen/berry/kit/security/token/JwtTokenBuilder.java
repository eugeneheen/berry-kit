package com.github.eugeneheen.berry.kit.security.token;

import com.github.eugeneheen.berry.kit.exception.SecurityException;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT Token构造器
 *
 * @author Eugene
 */
public class JwtTokenBuilder {
    /**
     * 业务主键
     */
    private String id;

    /**
     * 业务类别
     */
    private String category;

    /**
     * 动作
     */
    private String action;

    /**
     * 用户
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 过期时间
     */
    private long expiration = -1;

    /**
     * 附加业务属性
     */
    private Map<String, Object> properties;

    private JwtTokenBuilder() {

    }

    private JwtTokenBuilder(String id, String category, String action) throws SecurityException {
        assertNotBlank("id", id);
        assertNotBlank("category", category);
        assertNotBlank("action", action);

        this.properties = new HashMap<>();
        this.id = id;
        this.category = category;
        this.action = action;
    }

    public static JwtTokenBuilder newJwtTokenBuilder(String id, String category, String action) {
        return new JwtTokenBuilder(id, category, action);
    }

    public JwtToken build() {
        JwtToken jwtToken = new JwtToken();
        jwtToken.setId(id);
        jwtToken.setCategory(category);
        jwtToken.setAction(action);
        if (StringUtils.isNotBlank(userName)) {
            jwtToken.setUserName(userName);
        }
        if (StringUtils.isNotBlank(password)) {
            jwtToken.setPassword(password);
        }
        if (this.expiration != -1) {
            jwtToken.setExpiration(new Date(System.currentTimeMillis() + this.expiration));
        }
        jwtToken.setProperties(properties);

        return jwtToken;
    }

    public JwtTokenBuilder userName(String userName) throws SecurityException {
        assertNotBlank(IJwtToken.CLAIMS_PRIVATE_KEY_USERNAME, userName);
        this.userName = userName;
        return this;
    }

    public JwtTokenBuilder password(String password) throws SecurityException {
        assertNotBlank(IJwtToken.CLAIMS_PRIVATE_KEY_PASSWORD, password);
        this.password = password;
        return this;
    }

    public JwtTokenBuilder expiration(long expiration) {
        this.expiration = expiration;
        return this;
    }

    public JwtTokenBuilder expirationInDays(int days) {
        this.expiration = days * 24 * 3600 * 1000;
        return this;
    }

    public JwtTokenBuilder expirationInHours(int hours) {
        this.expiration = hours * 3600 * 1000;
        return this;
    }

    public JwtTokenBuilder expirationInMinutes(int minutes) {
        this.expiration = minutes * 60 * 1000;
        return this;
    }

    public JwtTokenBuilder expirationInSeconds(int seconds) {
        this.expiration = seconds * 1000;
        return this;
    }

    public JwtTokenBuilder property(String key, Object value) throws SecurityException {
        assertNotBlank("key", key);
        assertNotNull("value", value);
        this.properties.put(key, value);
        return this;
    }

    public JwtTokenBuilder properties(Map<String, Object> properties) throws SecurityException {
        assertNotNull("properties", properties);
        this.properties.putAll(properties);
        return this;
    }

    private void assertNotBlank(String key, String value) throws SecurityException {
        if (StringUtils.isBlank(value)) {
            throw new SecurityException(key + " is null");
        }
    }

    private void assertNotNull(String key, Object value) throws SecurityException {
        if (value == null) {
            throw new SecurityException(key + " is null");
        }
    }
}
