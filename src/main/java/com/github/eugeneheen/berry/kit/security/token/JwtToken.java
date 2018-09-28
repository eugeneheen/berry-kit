package com.github.eugeneheen.berry.kit.security.token;

import io.jsonwebtoken.Claims;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT Token实现
 *
 * @author Eugene
 */
public class JwtToken extends JwtTokenAbstract implements IJwtToken {
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
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 过期时间
     */
    private Date expiration;

    /**
     * 附加业务属性
     */
    private Map<String, Object> properties;

    public JwtToken() {
        this.properties = new HashMap<>();
    }

    public JwtToken(Claims claims) {
        this.properties = new HashMap<>();
        String[] subjects = claims.getSubject().split("\\.");
        this.category = subjects[0];
        this.action = subjects[1];
        this.id = claims.getId();
        this.userName = (String) claims.get(IJwtToken.CLAIMS_PRIVATE_KEY_USERNAME);
        this.password = (String) claims.get(IJwtToken.CLAIMS_PRIVATE_KEY_PASSWORD);
        this.expiration = claims.getExpiration();

        for (String key : claims.keySet()) {
            if (key.equals(IJwtToken.CLAIMS_STANDARD_KEY_SUB) || key.equals(IJwtToken.CLAIMS_STANDARD_KEY_EXP) || key.equals(IJwtToken.CLAIMS_STANDARD_KEY_IAT) || key.equals(IJwtToken.CLAIMS_STANDARD_KEY_JTI)) {
                continue;
            }
            if (key.equalsIgnoreCase(IJwtToken.CLAIMS_PRIVATE_KEY_USERNAME) || key.equals(IJwtToken.CLAIMS_PRIVATE_KEY_PASSWORD)) {
                continue;
            }
            this.properties.put(key, claims.get(key));
        }
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getCategory() {
        return this.category;
    }

    @Override
    public String getAction() {
        return this.action;
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Date getExpiration() {
        return this.expiration;
    }

    @Override
    public Map<String, Object> getProperties() {
        return this.properties;
    }

    @Override
    public Object getProperty(String key) {
        return this.properties.get(key);
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(this.id).append(this.category).append(this.action)
                .append(this.expiration).append(this.userName).append(this.password);
        if (MapUtils.isEmpty(this.properties)) {
            for (Map.Entry<String, Object> entry : this.properties.entrySet()) {
                builder.append(entry.getKey() + "=" + entry.getValue());
            }
        }
        return builder.toHashCode();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof JwtToken == false) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        JwtToken other = (JwtToken) obj;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.id, other.getId());
        builder.append(this.category, other.getCategory());
        builder.append(this.action, other.getAction());
        builder.append(this.expiration, other.getExpiration());
        builder.append(this.userName, other.getUserName());
        builder.append(this.password, other.getPassword());
        builder.append(this.properties, other.getProperties());
        return builder.isEquals();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
