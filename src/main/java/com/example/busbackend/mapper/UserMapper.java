package com.example.busbackend.mapper;

import com.example.busbackend.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findById(Long id);
    List<User> findAll();
    List<User> findByRole(String role);
    User findByUsername(String username); // 新增：按用户名查找
    int insert(User user);
    int update(User user);
    int deleteById(Long id);
}