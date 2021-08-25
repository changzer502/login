package com.example.login.dao;


import com.example.login.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {
    User getUserByNameAndPwd(@Param("name") String name, @Param("password") String password);

    User getUserByName(String name);

    int saveUser(@Param("name") String name, @Param("password") String password);
}
