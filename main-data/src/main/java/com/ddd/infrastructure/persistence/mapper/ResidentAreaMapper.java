package com.ddd.infrastructure.persistence.mapper;

import com.ddd.infrastructure.persistence.DO.ResidentAreaDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper
public interface ResidentAreaMapper extends JpaRepository<ResidentAreaDO,Integer> {

}
