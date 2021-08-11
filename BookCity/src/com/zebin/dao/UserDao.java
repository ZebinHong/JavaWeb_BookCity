package com.zebin.dao;

import com.zebin.pojo.User;

import java.sql.SQLException;

public abstract interface UserDao {
    /**
     * 根据name获取用户
     * @param name
     * @return 有用户则放回该用户，否则返回null
     */
    public User getUserByName(String name);

    /**
     * 根据name和password获取用户
     * @param name
     * @param password
     * @return  有用户则放回该用户，否则返回null
     */
    public User getUserByNameAndPwd(String name,String password);

    /**
     *保存用户
     * @param user
     * @return
     */
    public boolean saveUser(User user);

}
