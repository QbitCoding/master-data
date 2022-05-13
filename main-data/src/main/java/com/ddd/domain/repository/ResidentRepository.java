package com.ddd.domain.repository;

import com.ddd.domain.entity.People.Resident;
import com.ddd.domain.entity.space.Area;

import java.util.List;

public interface ResidentRepository {

    Resident findResident(Integer id);

    List<Resident> findResident(Area area);

    List<Resident> findResidentbyCommunityId(String id);

    void saveResident(Resident resident);
}
