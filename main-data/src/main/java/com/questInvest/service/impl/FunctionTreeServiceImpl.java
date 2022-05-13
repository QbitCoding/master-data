package com.questInvest.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.questInvest.dao.FunctionTreeDao;
import com.questInvest.entity.FunctionInfo;
import com.questInvest.service.FunctionTreeService;
import com.questInvest.vo.TreeNode;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @className    : FunctionTreeServiceImpl
 * @description    : FunctionTreeService实现类
 */
@Slf4j
@Service
@MapperScan("com.questInvest.dao")
public class FunctionTreeServiceImpl implements FunctionTreeService {

    //function_tree表数据访问对象
    @Resource
    private FunctionTreeDao functionTreeDao;

    //功能树对象
    private TreeNode<FunctionInfo> functionTree = new TreeNode<FunctionInfo>();

    /**
     * @methodName        : loadData
     * @description        : 加载数据库中数据
     * @return            : 成功返回true，否则返回false。
     */
    @Override
    public boolean loadData() {

        try {
            //查询function_tree表，获取全部数据
            List<FunctionInfo> functionInfoList = functionTreeDao.selectAll();
            //要考虑数据次序不一定保证父节点已先加载的情况

            //建立节点ID与节点对象的映射表，表示节点加载过程当前已加载的节点集合
            Map<Integer, TreeNode<FunctionInfo>> nodeMap = new HashMap<Integer, TreeNode<FunctionInfo>>();

            //加锁保护，防止脏读
            synchronized (functionTree) {
                //清除数据
                functionTree.clear();
                //设置根节点
                setRootNode(functionTree);
                //先加入根节点
                nodeMap.put(functionTree.getNodeData().getNodeId(), functionTree);

                //父节点
                TreeNode<FunctionInfo> parentNode = null;

                //遍历functionInfoList，加载功能树
                for (FunctionInfo item : functionInfoList) {
                    Integer parentId = item.getParentId();

                    if (nodeMap.containsKey(parentId)) {
                        //如果父节点已加载，取得父节点对象
                        parentNode = nodeMap.get(parentId);
                        //加载树节点
                        addTreeNode(parentNode, item, nodeMap);
                    } else {
                        //如果父节点未加载，则暂时作为游离的独立节点或子树
                        //加载树节点
                        addTreeNode(null, item, nodeMap);
                    }
                }

                //处理游离的节点
                for (TreeNode<FunctionInfo> node : nodeMap.values()) {
                    if (node.getParent() == null && node.getNodeData().getNodeId() != 0) {
                        //父节点为空，且非根节点
                        //取得父节点ID
                        Integer parentId = node.getNodeData().getParentId();
                        if (nodeMap.containsKey(parentId)) {
                            //如果父节点存在，,取得父节点对象
                            parentNode = nodeMap.get(parentId);
                            //加入父节点中
                            parentNode.addChildNode(node);
                        } else {
                            //parentId对应的节点不存在，说明数据配置错误
                            log.error("FunctionTreeServiceImpl.loadData error with func_id = " + node.getNodeData().getNodeId());
                            //直接返回，也可以考虑忽略配置错误的节点数据，视业务需求而定
                            return false;
                        }
                    }
                }
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * @methodName        : getFunctionTree
     * @description        : 获取整个功能树
     * @return            : 完整的功能树
     */
    @Override
    public TreeNode<FunctionInfo> getFunctionTree() {
        return functionTree;
    }

    /**
     * @param node : 输入的功能树根节点
     * @methodName        : setRootNode
     * @description        : 设置根节点
     */
    private void setRootNode(TreeNode<FunctionInfo> node) {
        node.setParent(null);
        //创建空节点数据
        node.setNodeData(new FunctionInfo());
        //约定根节点的节点ID为0
        node.getNodeData().setFuncId(0);
        node.getNodeData().setFuncName("root");
        //根节点总是包含的
        node.setIsIncluded(1);
    }

    /**
     * @param parentNode   : 父节点
     * @param functionInfo : 功能信息对象
     * @param nodeMap      : 节点ID与节点对象的映射表
     * @methodName        : addTreeNode
     * @description        : 加入功能树节点
     */
    private void addTreeNode(TreeNode<FunctionInfo> parentNode, FunctionInfo functionInfo,
                             Map<Integer, TreeNode<FunctionInfo>> nodeMap) {
        //生成树节点
        TreeNode<FunctionInfo> treeNode = new TreeNode<FunctionInfo>();
        //设置节点数据
        treeNode.setNodeData(functionInfo);
        if (parentNode != null) {
            //父节点非空，加入父节点中
            parentNode.addChildNode(treeNode);
        }

        //加入nodeMap中
        nodeMap.put(functionInfo.getNodeId(), treeNode);
    }
}