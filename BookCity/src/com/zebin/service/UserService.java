package com.zebin.service;

import com.zebin.pojo.User;

import java.sql.SQLException;

public interface UserService {
    /**
     * 用户登录
     * @param user
     * @return 登录成功返回对象，失败返回null
     */
    public User login(User user);

    /**
     * 用户注册
     * @param user
     * @return 注册成功返回true，注册失败返回false
     */
    public boolean regist(User user);

    /**
     * 是否存在该用户
     * @param username
     * @return 存在返回true，不存在返回false
     */
    public boolean existsUserName(String username);
}
