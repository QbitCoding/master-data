package com.ddd.interfaceAdapter.rest.vo;

import com.ddd.infrastructure.persistence.DO.PostDO;
import lombok.Data;

@Data
public class PostVO {

    private Integer postId;

    private Integer orgId;

    private String postName;


    public PostVO(PostDO postDO) {

        this.orgId = postDO.getOrgId();
        this.postId = postDO.getPostId();
        this.postName = postDO.getPostName();

    }
}
