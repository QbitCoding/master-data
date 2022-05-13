package com.ddd.application.query;

import com.ddd.domain.repository.*;
import com.ddd.infrastructure.persistence.DO.PostDO;
import com.ddd.domain.entity.People.Officer;
import com.ddd.domain.entity.People.Resident;
import com.ddd.domain.entity.space.Area;
import com.ddd.interfaceAdapter.rest.vo.AreaVO;
import com.ddd.interfaceAdapter.rest.vo.OfficerVO;
import com.ddd.interfaceAdapter.rest.vo.PostVO;
import com.ddd.interfaceAdapter.rest.vo.ResidentVO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Data
@Service
public class QueryService {

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private ResidentRepository residentRepository;


    public OfficerVO findOfficerbyId(Integer id) {
        Officer officer = peopleRepository.findOfficer(id);
        //    System.out.println(officer.getName());
        return new OfficerVO(officer);
    }

    public List<PostVO> findAssociatedPost() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        List<PostDO> postDOList = postRepository.findPostDOByUserName(currentPrincipalName);
        List<PostVO> postVOList = new LinkedList<>();

        for (PostDO postDO : postDOList) {
            postVOList.add(new PostVO(postDO));
        }

        System.out.println(postVOList);

        return postVOList;


    }


    public List<ResidentVO> findResidentbyCommunityId(String id) {
        List<Resident> listResident = residentRepository.findResidentbyCommunityId(id);
        List<ResidentVO> listResidentVO = new LinkedList<>();
        for (Resident resident : listResident) {
            listResidentVO.add(new ResidentVO(resident));
        }


        return listResidentVO;

    }


    public List<AreaVO> findPermittedArea() {
        //  JwtUserDto currentUser = SecurityUtils.getCurrentUser();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        System.out.println(currentPrincipalName);

        List<Area> areaList = areaRepository.findAreaByUsername(currentPrincipalName);

        System.out.println(areaList);

        List<AreaVO> areaVOList = new LinkedList<>();

        for (Area area : areaList) {
            areaVOList.add(new AreaVO(area));
        }

        System.out.println(areaVOList);

        return areaVOList;


    }


    public AreaVO findAreaByAreaId(String id) {
        return new AreaVO(areaRepository.findAreaByAreaId(id));
    }


}
