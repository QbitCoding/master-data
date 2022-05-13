package com.ddd.domain.entity.space;

import com.ddd.infrastructure.persistence.DO.AreaDO;
import com.questInvest.vo.TreeNode;
import lombok.Data;

@Data
public class Area {

    private String areaId;

    private String areaName;

    private String areaType;

    private TreeNode<AreaInfo> areaNode;

    public Area() {

    }

    public Area(AreaDO areaDO) {

        this.setAreaId(areaDO.getAreaId());

        this.setAreaName(areaDO.getAreaName());

        this.setAreaType(areaDO.getAreaType());

    }


}
