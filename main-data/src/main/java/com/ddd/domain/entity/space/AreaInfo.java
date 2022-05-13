package com.ddd.domain.entity.space;

import com.questInvest.vo.ITreeNodeData;

import java.io.Serializable;

public class AreaInfo implements Serializable, ITreeNodeData {
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
