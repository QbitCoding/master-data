package com.ddd.domain.repository.impl;

import com.ddd.domain.entity.People.User;
import com.ddd.domain.repository.UserRepository;
import com.ddd.infrastructure.persistence.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Repository;

@MapperScan("com.ddd.infrastructure.persistence.mapper")
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User findOneByUsername(String username) {

        return new User(userMapper.findOneByUsername(username));
    }
}
