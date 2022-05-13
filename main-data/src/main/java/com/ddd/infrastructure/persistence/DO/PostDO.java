package com.ddd.infrastructure.persistence.DO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "post")
public class PostDO {

    @Id
    private Integer postId;

    private Integer orgId;

    private String postName;

    private String userName;

}
