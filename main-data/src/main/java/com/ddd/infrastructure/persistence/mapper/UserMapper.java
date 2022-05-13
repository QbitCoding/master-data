package com.ddd.infrastructure.persistence.mapper;

import com.ddd.infrastructure.persistence.DO.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import org.springframework.security.core.userdetails.User;

@Repository
public interface UserMapper extends JpaRepository<UserDO, Integer> {
    UserDO findOneByUsername(String username);
}