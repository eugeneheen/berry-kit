package com.github.eugeneheen.berry.kit.security.token;

import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * JWT Token接口定义
 *
 * @author Eugene
 */
public interface IJwtToken {

    /**
     * 加密类型
     */
    public static final SignatureAlgorithm ALG_HS256 = SignatureAlgorithm.HS256;

    /**
     * JWT签发者，标准Playload声明Key
     */
    public static final String CLAIMS_STANDARD_KEY_ISS = "iss";

    /**
     * JWT所面向的用户，标准Playload声明Key
     */
    public static final String CLAIMS_STANDARD_KEY_SUB = "sub";

    /**
     * JWT接收方，标准Playload声明Key
     */
    public static final String CLAIMS_STANDARD_KEY_AUD = "aud";

    /**
     * JWT过期时间，标准Playload声明Key
     */
    public static final String CLAIMS_STANDARD_KEY_EXP = "exp";

    /**
     * JWT未到达此时间不可用，标准Playload声明Key
     */
    public static final String CLAIMS_STANDARD_KEY_NBF = "nbf";

    /**
     * JWT签发时间，标准Playload声明Key
     */
    public static final String CLAIMS_STANDARD_KEY_IAT = "iat";

    /**
     * JWT唯一身份标识，用于一次性Token，避免重放攻击，标准Playload声明Key
     */
    public static final String CLAIMS_STANDARD_KEY_JTI = "jti";

    /**
     * 用户名，私有Playload声明Key
     */
    public static final String CLAIMS_PRIVATE_KEY_USERNAME = "userName";

    /**
     * 密码，私有Playload声明Key
     */
    public static final String CLAIMS_PRIVATE_KEY_PASSWORD = "password";

    /**
     * 获取业务主键
     *
     * @return 业务主键
     */
    public String getId();

    /**
     * 获取业务类型
     *
     * @return 业务类型
     */
    public String getCategory();

    /**
     * 获取业务动作
     *
     * @return 业务动作
     */
    public String getAction();

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    public String getUserName();

    /**
     * 获取用户密码
     *
     * @return 用户密码
     */
    public String getPassword();

    /**
     * 获取过期时间
     *
     * @return 过期时间
     */
    public Date getExpiration();

    /**
     * 获取全部业务属性
     *
     * @return 全部业务属性
     */
    public Map<String, Object> getProperties();

    /**
     * 获取Token中的某个业务属性
     *
     * @param key 业务属性Key
     * @return 对象类型值
     */
    public Object getProperty(String key);

    /**
     * 获取Token中的某个业务属性
     *
     * @param key 业务属性Key
     * @return String类型值
     */
    public String getStringProperty(String key);

    /**
     * 获取Token中的某个业务属性
     *
     * @param key 业务属性Key
     * @return Long类型值
     */
    public Long getLongProperty(String key);

    /**
     * 获取Token中的某个业务属性
     *
     * @param key 业务属性Key
     * @return Integer类型值
     */
    public Integer getIntProperty(String key);

    /**
     * 获取Token中的某个业务属性
     *
     * @param key 业务属性Key
     * @return Float类型值
     */
    public Float getFloatProperty(String key);

    /**
     * 获取Token中的某个业务属性
     *
     * @param key 业务属性Key
     * @return Double类型值
     */
    public Double getDoubleProperty(String key);

    /**
     * 获取Token中的某个业务属性
     *
     * @param key 业务属性Key
     * @return Boolean类型值
     */
    public Boolean getBooleanProperty(String key);

    /**
     * 获取Token中的某个业务属性
     *
     * @param key 业务属性Key
     * @return Date类型值
     */
    public Date getDateProperty(String key);
}
