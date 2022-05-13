package com.questInvest.dao;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.questInvest.entity.FunctionInfo;

/**
 * @className    : FunctionTreeDao
 * @description    : function_tree表数据访问类
 */
@Mapper
public interface FunctionTreeDao {

    //查询所有功能树表记录，按parent_id,order_no排序
    @Select("SELECT func_id,func_name,parent_id,level,order_no,url,dom_key"
            + " FROM function_tree ORDER BY parent_id,order_no")
    List<FunctionInfo> selectAll();
}