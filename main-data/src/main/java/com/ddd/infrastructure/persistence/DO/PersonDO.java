package com.ddd.infrastructure.persistence.DO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
public class PersonDO {

    @Column
    String name;

    @Column
    Integer age;

    @Column
    String sex;

    @Column
    String identityId;

    @Column
    String phone;

//    @Column
//    Integer id;
}
