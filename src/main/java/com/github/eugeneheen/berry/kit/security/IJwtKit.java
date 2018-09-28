package com.github.eugeneheen.berry.kit.security;

import com.github.eugeneheen.berry.kit.security.token.JwtToken;

/**
 * <p>JWT工具箱接口定义</p>
 * 定义了encode和decode方法，对JWT Token进行编码和解码操作标准
 *
 * @author Eugene
 */
public interface IJwtKit {

    /**
     * JWT Token进行编码
     *
     * @param jwtToken JWT Token对象
     * @return 编码后的JWT Token字符串
     * @throws SecurityException 安全异常
     */
    public String encode(JwtToken jwtToken) throws SecurityException;

    /**
     * JWT Token进行解码
     *
     * @param encodeJwtToken 编码后的JWT Token字符串
     * @return JWT Token对象
     * @throws SecurityException 安全异常
     */
    public JwtToken decode(String encodeJwtToken) throws SecurityException;
}
