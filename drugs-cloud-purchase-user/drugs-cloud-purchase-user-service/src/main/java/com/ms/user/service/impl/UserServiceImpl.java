package com.ms.user.service.impl;

import com.ms.common.enums.ExceptionEnum;
import com.ms.common.exception.DrugsExecption;
import com.ms.user.mapper.UserMapper;
import com.ms.user.pojo.User;
import com.ms.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @author Shuai.Ma
 * @date 2022/3/20 15:08
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
   /* @Autowired
    private JwtProperties properties;*/

    @Override
    public boolean checkData(String data, Integer type) {
        // 判断数据类型
        User record = new User();
        switch (type) {
            case 1:
                record.setUsername(data);
                break;

            default:
                throw new DrugsExecption(ExceptionEnum.INVALID_USER_DATA_TYPE);
        }
        int count = userMapper.selectCount(record);
        return count == 0;
    }


    @Override
    public void register(User user) {
        if(StringUtils.isBlank(user.getUsername())){
            throw new DrugsExecption(ExceptionEnum.USER_NAME_ERROR);
        }
        if(StringUtils.isBlank(user.getPassword())){
            throw new DrugsExecption(ExceptionEnum.USER_NAME_ERROR);
        }
        user.setId(null);
        user.setCreated(new Date());
        // 写入数据库
        userMapper.insertSelective(user);
    }

    @Override
    public User queryUser(String username, String password) {
        // 查询用户
        User record = new User();
        record.setUsername(username);
        User user = userMapper.selectOne(record);
        // 校验用户名,为空抛出异常
        if (user == null) {
            throw new DrugsExecption(ExceptionEnum.USER_NAME_ERROR);
        }
        // 校验密码,注意加上salt来校验
        if (!user.getPassword().equals(password)) {
            throw new DrugsExecption(ExceptionEnum.USER_PASSWORD_ERROR);
        }
        return user;
    }

}
