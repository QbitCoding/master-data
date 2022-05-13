package com.ddd.infrastructure.persistence.DO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "area")
public class AreaDO {

    private Integer id;

    @Id
    private String areaId;

    private String areaName;

    private String areaType;

    private Integer treenodeId;

}
