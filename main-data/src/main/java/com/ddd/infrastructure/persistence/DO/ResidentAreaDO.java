package com.ddd.infrastructure.persistence.DO;

import com.ddd.domain.entity.People.Resident;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;


@AllArgsConstructor
@Data
@Entity
@Table(name = "resident_area")
public class ResidentAreaDO {

    @Id
    @Column //
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id =1001;

    @Column(name = "residentid")
    private String residentId;

    @Column(name = "areaid")
    private String areaId;

    @Column(name = "areatype")
    private String areaType;

    public ResidentAreaDO(String residentId,String areaId){

        this.setResidentId(residentId);
        this.setAreaId(areaId);

    }

    public ResidentAreaDO(){};

}
