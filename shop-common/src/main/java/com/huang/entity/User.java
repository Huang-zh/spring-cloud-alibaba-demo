package com.huang.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Huang.zh
 * @date 2020/8/13 14:26
 * @Description: 用户实体类
 */
@Entity(name = "shop_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //主键
    private Integer uid;
    //用户名
    private String username;
    //密码
    private String password;
    //手机号
    private String telephone;
}
