package com.ddd.domain.repository.impl;

import com.ddd.domain.entity.People.Resident;
import com.ddd.domain.entity.space.Area;
import com.ddd.domain.repository.PeopleRepository;
import com.ddd.domain.repository.ResidentRepository;
import com.ddd.infrastructure.persistence.DO.ResidentAreaDO;
import com.ddd.infrastructure.persistence.DO.ResidentDO;
import com.ddd.infrastructure.persistence.mapper.PeopleMapper;
import com.ddd.infrastructure.persistence.mapper.ResidentAreaMapper;
import com.ddd.infrastructure.persistence.mapper.ResidentMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@MapperScan("com.ddd.infrastructure.persistence.mapper")
@Repository
public class ResidentRepositoryImpl implements ResidentRepository {

    @Resource
    private ResidentMapper residentMapper;

    @Resource
    private ResidentAreaMapper residentAreaMapper;

    @Resource
    private PeopleMapper peopleMapper;

    @Override
    public Resident findResident(Integer id) {
        return null;
    }

    @Override
    public ArrayList<Resident> findResident(Area area) {
        return null;
    }

    @Override
    public List<Resident> findResidentbyCommunityId(String id) {
        List<ResidentDO> listResidentDO = peopleMapper.selectResidentbyCommunityId(id);
        List<Resident> listResident = new LinkedList<>();
        for (ResidentDO residentDO : listResidentDO) {
            listResident.add(new Resident(residentDO));
        }

        return listResident;
    }


    /*
     ** 插入表resident 和 resident-area
     */
    @Override
    public void saveResident(Resident resident) {
        ResidentDO residentDO = new ResidentDO(resident);
        printObject(residentDO);
        System.out.println("resident:"+residentMapper.saveAndFlush(residentDO));

        for(String areaId : resident.getListArea())
        {
            residentAreaMapper.save(new ResidentAreaDO(resident.getResidentId(),areaId));
        }
        System.out.println("resident "+resident.getResidentId()+ " saved");
    }

    public void printObject(Object ob){
        Field[] fields = ob.getClass().getDeclaredFields();//获取所有属性
        Arrays.stream(fields).forEach(field -> {
            //获取是否可访问
            boolean flag = field.isAccessible();
            try {
                //设置该属性总是可访问
                field.setAccessible(true);
                System.out.println("成员变量" + field.getName() + "的值为：" + field.get(ob));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            //还原可访问权限
            field.setAccessible(flag);
        });
    }



}
