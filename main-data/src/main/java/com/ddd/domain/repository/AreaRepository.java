package com.ddd.domain.repository;

import com.ddd.domain.entity.space.Area;

import java.util.List;


public interface AreaRepository {


    List<Area> findAreaByUsername(String username);

    Area findAreaByAreaId(String id);


}
