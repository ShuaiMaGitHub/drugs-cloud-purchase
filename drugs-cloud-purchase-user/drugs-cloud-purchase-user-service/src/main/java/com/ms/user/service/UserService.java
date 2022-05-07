package com.ms.user.service;

import com.ms.user.pojo.User;

/**
 * @author Shuai.Ma
 * @date 2022/3/20 15:08
 */
public interface UserService {
    void register(User user);

    boolean checkData(String data, Integer type);

    User queryUser(String username, String password);

}
