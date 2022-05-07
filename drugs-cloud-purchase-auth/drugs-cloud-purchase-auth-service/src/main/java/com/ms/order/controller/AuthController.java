package com.ms.order.controller;

import com.ms.auth.entity.UserInfo;
import com.ms.auth.utils.JwtUtils;
import com.ms.common.utils.CookieUtils;
import com.ms.config.JwtProperties;
import com.ms.order.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Shuai.Ma
 * @date 2022/3/20 17:10
 */
@RestController
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录授权
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @PostMapping("accredit")
    public ResponseEntity<Void> authentication(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest request,
            HttpServletResponse response
    )
    {
        // 登录校验
        String token =  authService.authentication(username,password);
        if (StringUtils.isBlank(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        // 将token写入cookie,并指定httpOnly为true，防止通过JS获取和修改
        CookieUtils.setCookie(request, response, jwtProperties.getCookieName(),
                token, jwtProperties.getCookieMaxAge(), null, true);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 校验用户登录状态
     * @return
     */
    @GetMapping("verify")
    public ResponseEntity<UserInfo> verify(@CookieValue("LY_TOKEN") String token, HttpServletRequest request, HttpServletResponse response
    ){
        try {
            // 从token中解析token信息,通过jwt工具类使用工钥解析jwt
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, this.jwtProperties.getPublicKey());
            if(userInfo == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            //刷新jwt中的有效时间,
            token = JwtUtils.generateToken(userInfo, this.jwtProperties.getPrivateKey(), this.jwtProperties.getExpire());

            //刷新Cookie中的有效时间
            CookieUtils.setCookie(request,response,this.jwtProperties.getCookieName(),token,this.jwtProperties.getExpire() * 60);

            // 解析成功返回用户信息
            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 出现异常则，响应500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}

