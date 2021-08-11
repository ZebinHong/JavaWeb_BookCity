package com.zebin.dao.impl;

import com.zebin.dao.UserDao;
import com.zebin.pojo.User;

import java.sql.SQLException;

public class UserDaoImpl  extends BaseDao implements UserDao{

    @Override
    public User getUserByName(String name) {
        String sql = "SELECT id,`name`,`password`,`email` from `t_user` WHERE `name`=?";
        return queryForOne(User.class, sql, name);
    }

    @Override
    public User getUserByNameAndPwd(String name, String password) {
        String sql = "select id,name,password,email from t_user where name=? and password=?";
        return queryForOne(User.class, sql, name,password);
    }

    @Override
    public boolean saveUser(User user) {
        String sql = "insert into t_user(name,password,email) values(?,?,?)";
        int update = update(sql, user.getName(), user.getPassword(), user.getEmail());
        return (update>0?true:false);
    }
}
