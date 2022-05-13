package com.ddd.domain.entity.People;

import com.questInvest.vo.ITreeNodeData;
import lombok.Data;

import java.io.Serializable;

@Data
public class PostInfo implements Serializable, ITreeNodeData {


    @Override
    public int getNodeId() {
        return 0;
    }

    @Override
    public String getNodeName() {
        return null;
    }

    @Override
    public int getParentId() {
        return 0;
    }

    @Override
    public Object clone() {
        return null;
    }
}
