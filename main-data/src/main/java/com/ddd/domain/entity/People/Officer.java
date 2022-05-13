package com.ddd.domain.entity.People;

import lombok.Data;

import java.util.List;

@Data
public class Officer extends Person {


    private Integer officerID;
    private List<Post> lp;

//    OfficerInfo setOfficerInfo(){
//        return new OfficerInfo
//    }

}
