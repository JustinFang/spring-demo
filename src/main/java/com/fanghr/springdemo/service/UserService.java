package com.fanghr.springdemo.service;

import com.fanghr.springdemo.mapper.UserMapper;
import com.fanghr.springdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User : fanghr
 * Date : 2019/7/20
 * Time : 8:42 PM
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserById(long id ) {
        return userMapper.getUserById(id);
    }

    public List<User> getUsersByName(String name) {
        return userMapper.getUsersByName(name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int addUser(User user) {
        return userMapper.addUser(user);
    }
}
