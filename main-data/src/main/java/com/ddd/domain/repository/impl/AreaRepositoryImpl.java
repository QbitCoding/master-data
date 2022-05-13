package com.ddd.domain.repository.impl;

import com.ddd.infrastructure.persistence.DO.AreaDO;
import com.ddd.domain.entity.space.Area;
import com.ddd.infrastructure.persistence.mapper.AreaMapper;
import com.ddd.infrastructure.persistence.mapper.AreaPostUserMapper;
import com.ddd.domain.repository.AreaRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@MapperScan("com.ddd.infrastructure.persistence.mapper")
@Repository
public class AreaRepositoryImpl implements AreaRepository {

    @Resource
    private AreaMapper areaMapper;

    @Resource
    private AreaPostUserMapper areaPostUserMapper;

    @Override
    public List<Area> findAreaByUsername(String username) {
        List<AreaDO> areaDOList = areaPostUserMapper.findAreaDOByUsername(username);

        System.out.println(areaDOList);

        List<Area> areaList = new LinkedList<>();
        for (AreaDO areaDO : areaDOList) {
            areaList.add(new Area(areaDO));
        }

        return areaList;
    }

    @Override
    public Area findAreaByAreaId(String id) {
        AreaDO areaDO = areaMapper.findAreaDOByAreaId(id);
        return new Area(areaDO);
    }


}
