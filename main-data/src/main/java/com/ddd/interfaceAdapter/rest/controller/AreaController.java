package com.ddd.interfaceAdapter.rest.controller;


import com.ddd.application.query.QueryService;
import com.ddd.interfaceAdapter.rest.vo.AreaVO;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AreaController {

    private QueryService queryService;

    @GetMapping("/area/permitted_area")
   // @PreAuthorize("hasAuthority('ROLE_OFFICER')")
    public List<AreaVO> findPermittedArea() {

        return queryService.findPermittedArea();

    }

    @GetMapping("/area/{id}")
    @PreAuthorize("hasAuthority('ROLE_OFFICER')")
    public AreaVO findAreaByAreaId(@PathVariable("id") String id) {
        return queryService.findAreaByAreaId(id);
    }
}
