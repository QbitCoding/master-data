package com.ddd.infrastructure.persistence.DO;

import com.ddd.domain.entity.People.Officer;
import lombok.Data;

@Data
public class OfficerDO extends PersonDO {

    private Integer officerId;

    public Officer toOfficer() {
        Officer officer = new Officer();
        officer.setAge(this.age);
        officer.setName(this.name);
    //    officer.setID(this.officerId);
        return officer;
    }
}
