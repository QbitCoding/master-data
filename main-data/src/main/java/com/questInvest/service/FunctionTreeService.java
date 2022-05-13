package com.questInvest.service;

import com.questInvest.entity.FunctionInfo;
import com.questInvest.vo.TreeNode;

/**
 * @className    : FunctionTreeService
 * @description    : 功能树服务
 */
public interface FunctionTreeService {

    /**
     * @methodName        : loadData
     * @description        : 加载数据库中数据
     * @return            : 成功返回true，否则返回false。
     */
    public boolean loadData();

    /**
     * @methodName        : getFunctionTree
     * @description        : 获取整个功能树
     * @return            : 完整的功能树
     */
    public TreeNode<FunctionInfo> getFunctionTree();
}