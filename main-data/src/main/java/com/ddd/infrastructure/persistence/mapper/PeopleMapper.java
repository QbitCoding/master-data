package com.ddd.infrastructure.persistence.mapper;

import com.ddd.infrastructure.persistence.DO.OfficerDO;
import com.ddd.infrastructure.persistence.DO.ResidentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Mapper
public interface PeopleMapper {
    //查询所有功能树表记录，按parent_id,order_no排序

    @Select("select * from officer where id=#{id}")
    OfficerDO selectOfficerById(Integer id);

    @Select("SELECT resident.age,resident.name,resident.residentId from resident INNER JOIN  (select * from  resident_area where resident_area.areaId like \"${id}%\") x USING (residentId)")
    List<ResidentDO> selectResidentbyCommunityId(String id);



}
