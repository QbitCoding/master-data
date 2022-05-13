package com.ddd.domain.entity.People;

import com.ddd.application.command.dto.CreateResidentCommand;
import com.ddd.domain.entity.space.Area;
import com.ddd.domain.service.ResidentDomainService;
import com.ddd.infrastructure.persistence.DO.ResidentDO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
public class Resident extends Person {

    @Autowired
    private ResidentDomainService residentDomainService;

    private String residentId;

    List<String> listArea;

    public Resident(){};

    public Resident(String residentId,CreateResidentCommand createResidentCommand){
        this.setResidentId(residentId);
        this.setName(createResidentCommand.getName());
        this.setAge(createResidentCommand.getAge());
        this.setSex(createResidentCommand.getSex());
        this.setListArea(createResidentCommand.getListArea());
    }

    public Resident(ResidentDO residentDO)
    {
        this.setResidentId(residentDO.getResidentId());
        this.setName(residentDO.getName());
        this.setAge(residentDO.getAge());
        this.setSex(residentDO.getSex());

    }




}
