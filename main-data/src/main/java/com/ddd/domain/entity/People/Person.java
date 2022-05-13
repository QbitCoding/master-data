package com.ddd.domain.entity.People;

import lombok.Data;

@Data
public abstract class Person {



    private String name;

    private Integer age;

    private String sex;

    public boolean isOldEnoughForMarriage(){
        switch (getSex()){
            case "男":
                return getAge()>=22;
            case "女":
                return getAge()>=20;
            default:
                throw new RuntimeException();
        }
    }

}
