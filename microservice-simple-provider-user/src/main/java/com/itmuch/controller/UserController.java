package com.itmuch.controller;

import com.itmuch.dao.UserRepository;
import com.itmuch.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by leo on 2017/7/28.
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/{id}")
    public User findById(@PathVariable Long id){
        User user=userRepository.findOne(id);
        return user;
    }
}
