package com.ms.user.controller;

import com.ms.user.pojo.User;
import com.ms.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

/**
 * @author Shuai.Ma
 * @date 2022/3/20 15:06
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 校验数据
     * @param data
     * @param type
     * @return
     */
    @GetMapping("/check/{data}/{type}")
    public ResponseEntity<Boolean> checkData(@PathVariable("data") String data, @PathVariable("type") Integer type){
        return  ResponseEntity.ok(userService.checkData(data,type));
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("register")
    public ResponseEntity<Void> register(@Valid User user){
        userService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("query")
    public ResponseEntity<User> queryUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password)
    {
        User user = userService.queryUser(username,password);
        return ResponseEntity.ok(user);
    }

}
