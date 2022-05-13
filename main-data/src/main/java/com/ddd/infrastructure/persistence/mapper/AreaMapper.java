package com.ddd.infrastructure.persistence.mapper;

import com.ddd.infrastructure.persistence.DO.AreaDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper
public interface AreaMapper extends JpaRepository<AreaDO, String> {

    AreaDO findAreaDOByAreaId(String id);
}
