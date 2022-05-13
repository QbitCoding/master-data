package com.ddd.infrastructure.persistence.mapper;

import com.ddd.infrastructure.persistence.DO.AreaDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AreaPostUserMapper {

    @Select("SELECT * FROM area INNER JOIN (SELECT post_area.areaId FROM post INNER JOIN post_area USING (postId) WHERE post.username = \"${username}\") t USING (areaId) group by area.areaId")
    List<AreaDO> findAreaDOByUsername(String username);

}
