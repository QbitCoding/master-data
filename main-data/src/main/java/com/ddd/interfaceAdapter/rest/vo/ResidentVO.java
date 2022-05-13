package com.ddd.interfaceAdapter.rest.vo;

import com.ddd.domain.entity.People.Resident;
import lombok.Data;

@Data
public class ResidentVO extends PersonVO {

    public ResidentVO(Resident resident) {

        this.setName(resident.getName());

        this.setAge(resident.getAge());

    //    this.setID(resident.getID());


        // return new OfficerInfo(officer.getName(),officer.getAge(),officer.getID());


    }

}
