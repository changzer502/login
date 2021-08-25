package com.example.login.service;

import com.example.login.dao.UserDao;
import com.example.login.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public User getUserByNameAndPwd(String name, String password) {
        return userDao.getUserByNameAndPwd(name,password);
    }

    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    public int saveUser(String name, String password) {
        return userDao.saveUser(name,password);
    }
}
