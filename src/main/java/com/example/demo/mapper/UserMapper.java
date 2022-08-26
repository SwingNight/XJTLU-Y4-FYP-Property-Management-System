package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<User> getUserList();
    User getUserById(int id);
    User getUserByAccount(String account);
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(int id);
    List<User> queryUserByAccount(String acc);

}
