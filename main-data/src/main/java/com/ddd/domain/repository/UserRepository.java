package com.ddd.domain.repository;

import com.ddd.domain.entity.People.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import org.springframework.security.core.userdetails.User;

@Repository
public interface UserRepository {
    User findOneByUsername(String username);
}