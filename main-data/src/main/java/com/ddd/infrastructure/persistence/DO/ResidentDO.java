package com.ddd.infrastructure.persistence.DO;
import com.ddd.domain.entity.People.Resident;
import lombok.Data;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Data
@Entity
@Table(name = "resident")
public class ResidentDO  {

    private Boolean oldEnoughForMarriage;
    @Column
    String name;

    @Column
    Integer age;

    @Column
    String sex;

    @Column(name = "identityid" )
    String identityId;

    @Column
    String phone;

    @Column(name = "residentid" )
    private String residentId;

    @Id
    @Column //
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public ResidentDO(Resident resident){

        this.setAge(resident.getAge());
        this.setName(resident.getName());
        this.setResidentId(resident.getResidentId());
        this.setSex(resident.getSex());
        this.oldEnoughForMarriage=resident.isOldEnoughForMarriage();
    }

    public ResidentDO(){};

//    public List<Resident> toListResident()
//    {
//
//    }


}
