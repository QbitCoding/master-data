package com.ddd.infrastructure.persistence.DO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class UserDO {

    @Id
    @Column
    private long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String role;
}
