package com.ma.auth.test;

import com.ms.auth.entity.UserInfo;
import com.ms.auth.utils.JwtUtils;
import com.ms.auth.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author Shuai.Ma
 * @date 2022/3/20 17:38
 */
public class JwtTest {
    private static final String pubKeyPath = "F:\\BiShe\\tmp\\test\\rsa.pub";

    private static final String priKeyPath = "F:\\BiShe\\tmp\\test\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTY0ODIwMTY3OX0.Ezzdx9CvlrUxtBKoPVT3QDxBMfRIeWMIqyXinqgMle5z7OkGOiUlSOqoe-yidZBUan9lDUacR43ZboDHrfqrxOepkikk4jSxIQ5gQ5XA3xCTGLQ4X9qzaTJciJ4a6ruuWlFd_ZctbuBM-Yqjj410RkeGC7BTABcwit_z5lV7fk0";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }
}
