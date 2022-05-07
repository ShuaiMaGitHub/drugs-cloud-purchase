package com.ms.user.api;

import com.ms.user.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Shuai.Ma
 * @date 2022/3/20 15:11
 */
public interface UserApi {
    @GetMapping("query")
    User queryUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password);
}
