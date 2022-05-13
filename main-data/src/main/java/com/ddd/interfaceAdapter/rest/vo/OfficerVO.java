package com.ddd.interfaceAdapter.rest.vo;

import com.ddd.domain.entity.People.Officer;
import lombok.Data;

@Data
public class OfficerVO extends PersonVO {


    public OfficerVO(Officer officer) {

        this.setName(officer.getName());

        this.setAge(officer.getAge());

     //   this.setID(officer.getID());

    }

}
