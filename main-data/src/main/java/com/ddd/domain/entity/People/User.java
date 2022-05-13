package com.ddd.domain.entity.People;

import com.ddd.infrastructure.persistence.DO.UserDO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

//@NoArgsConstructor
@Data
public class User { //extends org.springframework.security.core.userdetails.User


    private long id;


    private String username;


    private String password;


    private String role;

    private Integer personType; //Officer resident Visitor


//    public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, authorities);
//    }

    public User() {

    }

    ;

    public User(UserDO userDO) {
        this.setUsername(userDO.getUsername());
        this.setPassword(userDO.getPassword());
        this.setRole(userDO.getRole());

    }

    ;

//    public String getRole() {
//        return this.role;
//    }
}
