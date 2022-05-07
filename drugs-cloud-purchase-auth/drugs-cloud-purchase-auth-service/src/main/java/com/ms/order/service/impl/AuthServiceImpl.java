package com.ms.order.service.impl;

import com.ms.auth.entity.UserInfo;
import com.ms.auth.utils.JwtUtils;
import com.ms.client.UserClient;
import com.ms.config.JwtProperties;
import com.ms.order.service.AuthService;
import com.ms.user.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Shuai.Ma
 * @date 2022/3/20 17:11
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserClient userClient;
    @Autowired
    private JwtProperties properties;

    @Override
    public String authentication(String username, String password) {
        try {
            // 调用微服务进行查询
            User user = userClient.queryUser(username, password);
            // 如果查询为null,直接返回null
            if (user == null) {
                return null;
            }
            // 如果有查询结果,则生成token
            String token = JwtUtils.generateToken(new UserInfo(user.getId(), user.getUsername()),
                    properties.getPrivateKey(), properties.getExpire());
            return token;
        } catch (Exception e) {
            // 发生错误,记录日志
            log.error("[授权中心] 授权异常!错误为: " + e);
        }
        return null;
    }
}
