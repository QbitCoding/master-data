package com.ddd.interfaceAdapter.rest.controller;

import com.ddd.application.command.dto.CreateResidentCommand;
import com.ddd.application.command.service.ResidentApplicationService;
import com.ddd.application.query.QueryService;
import com.ddd.common.result.Result;
import com.ddd.interfaceAdapter.rest.vo.OfficerVO;
import com.ddd.interfaceAdapter.rest.vo.PostVO;
import com.ddd.interfaceAdapter.rest.vo.ResidentVO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PeopleController {

    @Autowired
    private QueryService queryService;

    @Autowired
    private ResidentApplicationService residentApplicationService;

    @GetMapping("/people/officer/{id}")
    public OfficerVO findOfficerbyId(@PathVariable("id") Integer id) {

        return queryService.findOfficerbyId(id);
    }

    @GetMapping("/people/resident/{community_id}")
    @PreAuthorize("hasAuthority('ROLE_OFFICER')")
    public List<ResidentVO> findResidentbyCommunityIds(@PathVariable("community_id") String id) {

        return queryService.findResidentbyCommunityId(id);
    }

    @GetMapping("/people/post/associated_post")
    @PreAuthorize("hasAuthority('ROLE_OFFICER')")
    public List<PostVO> findAssociatedPost() {

        return queryService.findAssociatedPost();
    }

    /**
     * 创建居民
     *
     * @param command 参数
     * @return 居民ID
     */
    @PostMapping(path = "/resident/create_resident")
    public Result<String> createProduct(@Validated @RequestBody CreateResidentCommand command) {
        Result<String> result = residentApplicationService.createResident(command);

        System.out.println("createProducting："+ command);
        return result;
    }

}
