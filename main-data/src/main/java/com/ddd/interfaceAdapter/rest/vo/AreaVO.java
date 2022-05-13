package com.ddd.interfaceAdapter.rest.vo;

import com.ddd.domain.entity.space.Area;
import lombok.Data;

@Data
public class AreaVO {

    private String areaID;

    private String areaName;

    private String areaType;

    public AreaVO(Area area) {

        this.setAreaID(area.getAreaId());

        this.setAreaName(area.getAreaName());

        this.setAreaType(area.getAreaType());

    }
}
