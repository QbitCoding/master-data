package com.ddd.domain.repository.impl;

import com.ddd.infrastructure.persistence.DO.OfficerDO;
import com.ddd.infrastructure.persistence.DO.ResidentDO;
import com.ddd.infrastructure.persistence.mapper.PeopleMapper;
import com.ddd.domain.entity.space.Area;
import com.ddd.domain.entity.People.Officer;
import com.ddd.domain.entity.People.Resident;
import com.ddd.domain.repository.PeopleRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@MapperScan("com.ddd.infrastructure.persistence.mapper")
@Repository
public class PeopleRepositoryImpl implements PeopleRepository {

    @Resource
    private PeopleMapper peopleMapper;


    @Override
    public Officer findOfficer(Integer id) {
        OfficerDO officerDO = peopleMapper.selectOfficerById(id);
        //return PersonBuilder.toOfficer(officerDO);
        return officerDO.toOfficer();
    }






}
