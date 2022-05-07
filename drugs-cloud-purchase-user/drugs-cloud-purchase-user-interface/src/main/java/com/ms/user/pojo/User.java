package com.ms.user.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Shuai.Ma
 * @date 2022/3/20 15:03
 */
@Table(name = "tb_user")
@Data
public class User {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    @Length(min = 4, max = 30, message = "用户名只能在4~30位之间")
    private String username;// 用户名

    @JsonIgnore
    @Length(min = 6, max = 20, message = "密码只能在6~20位之间")
    private String password;// 密码

    private Date created;// 创建时间

}