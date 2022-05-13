package com.questInvest.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;

import com.questInvest.vo.ITreeNodeData;
import lombok.Data;

/**
 * @className    : FunctionInfo
 * @description    : 功能节点信息
 */
@Data
public class FunctionInfo implements Serializable, ITreeNodeData {
    private static final long serialVersionUID = 1L;

    //功能ID
    @Id
    @Column(name = "func_id")
    private Integer funcId;

    //功能名称
    @Column(name = "func_name")
    private String funcName;

    //父节点ID
    @Column(name = "parent_id")
    private Integer parentId;

    //功能所在层级
    @Column(name = "level")
    private Byte level;

    //显示顺序
    @Column(name = "order_no")
    private Integer orderNo;

    //访问接口url
    @Column(name = "url")
    private String url;

    //dom对象的id
    @Column(name = "dom_key")
    private String domKey;

    // ================ 接口重载 ======================

    //获取节点ID
    @Override
    public int getNodeId() {
        return funcId;
    }

    //获取节点名称
    @Override
    public String getNodeName() {
        return funcName;
    }

    //获取父节点ID
    @Override
    public int getParentId() {
        return parentId;
    }

    //对象克隆
    @Override
    public Object clone() {
        FunctionInfo obj = null;
        try {
            obj = (FunctionInfo) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public String toString() {
        return "{"
                + "funcId:" + funcId + ","
                + "funcName:" + funcName + ","
                + "parentId:" + parentId + ","
                + "level:" + level + ","
                + "orderNo:" + orderNo + ","
                + "url:" + url + ","
                + "domKey:" + domKey + ""
                + "}";
    }

}