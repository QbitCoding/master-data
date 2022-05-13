package com.ddd.infrastructure.persistence.mapper;

import com.ddd.infrastructure.persistence.DO.ResidentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Mapper
public interface ResidentMapper extends JpaRepository<ResidentDO,String>{


   // ResidentDO saveAndFlush(ResidentDO residentDO);


}
