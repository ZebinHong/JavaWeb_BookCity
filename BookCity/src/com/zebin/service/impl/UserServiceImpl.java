package com.zebin.service.impl;

import com.zebin.dao.UserDao;
import com.zebin.dao.impl.UserDaoImpl;
import com.zebin.pojo.User;
import com.zebin.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private UserDaoImpl udi = new UserDaoImpl();
    @Override
    public User login(User user) {
        User user1 = null;
        user1 = udi.getUserByNameAndPwd(user.getName(), user.getPassword());
        return user1;
    }

    @Override
    public boolean regist(User user) {
        boolean b = udi.saveUser(user);
        return b;
    }

    @Override
    public boolean existsUserName(String username) {
        User userByName = null;
        userByName = udi.getUserByName(username);
        if(userByName!=null)
            return true;
        else
            return false;
    }
}
