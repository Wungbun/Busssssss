package com.example.busbackend.service;

import com.example.busbackend.entity.User;
import com.example.busbackend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public List<User> findByRole(String role) {
        return userMapper.findByRole(role);
    }

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public User save(User user) {
        LocalDateTime now = LocalDateTime.now();
        if (user.getId() == null) {
            user.setCreatedAt(now);
            user.setUpdatedAt(now);
            userMapper.insert(user);
        } else {
            user.setUpdatedAt(now);
            userMapper.update(user);
        }
        return user;
    }

    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }

    public User findById(Long id) {
        return userMapper.findById(id);
    }
}