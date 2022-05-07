package com.ms.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shuai.Ma
 * @date 2022/3/20 16:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private Long id;

    private String username;
}