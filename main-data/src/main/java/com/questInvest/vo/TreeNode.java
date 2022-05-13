package com.questInvest.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import lombok.Data;

/**
 * @className    : TreeNode
 * @description    : 树节点
 * @summary        : 节点数据类型，必须实现ITreeNodeData接口类的接口
 */
@Data
public class TreeNode<T extends ITreeNodeData> implements Serializable {
    private static final long serialVersionUID = 1L;

    //节点数据
    private T nodeData;

    //父节点对象
    private TreeNode<T> parent;

    //子节点
    private List<TreeNode<T>> children = new ArrayList<TreeNode<T>>();

    //是否包含在树中，1表示包含，0表示不包含，此属性为附加属性，在完整树剪枝时使用
    private Integer isIncluded = 0;

    /**
     * @param childNode : 子节点
     * @methodName    : addChildNode
     * @description    : 添加子节点
     */
    public void addChildNode(TreeNode<T> childNode) {
        childNode.setParent(this);
        children.add(childNode);
    }

    /**
     * @param childNode : 子节点
     * @methodName        : removeChildNode
     * @description        : 移除子节点，如果子节点在子节点列表中，则移除，否则无影响
     */
    public void removeChildNode(TreeNode<T> childNode) {
        children.remove(childNode);
    }

    /**
     * @methodName    : clear
     * @description    : 移除所有子节点
     */
    public void clear() {
        children.clear();
    }

    /**
     * @methodName        : getPrevSibling
     * @description        : 取得左邻节点
     * @return            : 如果当前节点为第一个节点，则返回null，否则为前一个节点
     */
    public TreeNode<T> getPrevSibling() {
        if (parent == null) {
            //如果为根节点，则返回null
            return null;
        }

        List<TreeNode<T>> siblingList = parent.getChildren();
        TreeNode<T> node = null;
        for (int i = 0; i < siblingList.size(); i++) {
            TreeNode<T> item = siblingList.get(i);
            if (item == this) {
                //找到当前节点
                if (i > 0) {
                    //当前节点不是第一个子节点
                    //取得前一个节点
                    node = siblingList.get(i - 1);
                }
                break;
            }
        }
        return node;
    }

    /**
     * @methodName        : getNextSibling
     * @description        : 取得右邻节点
     * @return            : 如果当前节点为最后一个节点，则返回null，否则为后一个节点
     */
    public TreeNode<T> getNextSibling() {
        if (parent == null) {
            //如果为根节点，则返回null
            return null;
        }

        List<TreeNode<T>> siblingList = parent.getChildren();
        TreeNode<T> node = null;
        for (int i = 0; i < siblingList.size(); i++) {
            TreeNode<T> item = siblingList.get(i);
            if (item == this) {
                //找到当前节点
                if (i < siblingList.size() - 1) {
                    //当前节点不是最后一个子节点
                    //取得后一个节点
                    node = siblingList.get(i + 1);
                }
                break;
            }
        }
        return node;
    }

    /**
     * @param nodeId : 节点ID
     * @methodName        : lookUpSubNode
     * @description        : 在当前节点及下级节点中查找指定节点ID的节点
     * @return            : 如果找到，返回对应树节点，否则返回null
     */
    public TreeNode<T> lookUpSubNode(int nodeId) {
        TreeNode<T> node = null;

        //检查当前节点
        if (nodeData.getNodeId() == nodeId) {
            node = this;
            return node;
        }

        //遍历子节点
        for (TreeNode<T> item : children) {
            node = item.lookUpSubNode(nodeId);
            if (node != null) {
                //如果节点非空，表示查找到了
                break;
            }
        }
        return node;
    }

    /**
     * @param nodeName : 节点名称
     * @methodName        : lookUpSubNode
     * @description        : 在当前节点及下级节点中查找指定节点名称的节点
     * @return            : 如果找到，返回对应树节点，否则返回null
     */
    public TreeNode<T> lookUpSubNode(String nodeName) {
        TreeNode<T> node = null;

        //检查当前节点
        if (nodeData.getNodeName().equals(nodeName)) {
            node = this;
            return node;
        }

        //遍历子节点
        for (TreeNode<T> item : children) {
            node = item.lookUpSubNode(nodeName);
            if (node != null) {
                //如果节点非空，表示查找到了
                break;
            }
        }
        return node;
    }

    /**
     * @param nodeId : 节点ID
     * @methodName        : lookUpSuperNode
     * @description        : 在当前节点及上级节点中查找指定节点ID的节点
     * @return            : 如果找到，返回对应树节点，否则返回null
     */
    public TreeNode<T> lookUpSuperNode(int nodeId) {
        TreeNode<T> node = null;

        //检查当前节点
        if (nodeData.getNodeId() == nodeId) {
            node = this;
            return node;
        }

        //查找父节点
        if (parent != null) {
            node = parent.lookUpSuperNode(nodeId);
        }

        return node;
    }

    /**
     * @param nodeName : 节点名称
     * @methodName        : lookUpSuperNode
     * @description        : 在当前节点及上级节点中查找指定节点名称的节点
     * @return            : 如果找到，返回对应树节点，否则返回null
     */
    public TreeNode<T> lookUpSuperNode(String nodeName) {
        TreeNode<T> node = null;

        //检查当前节点
        if (nodeData.getNodeName().equals(nodeName)) {
            node = this;
            return node;
        }

        //查找父节点
        if (parent != null) {
            node = parent.lookUpSuperNode(nodeName);
        }

        return node;
    }

    /**
     * @methodName        : clone
     * @description        : 复制树节点，包括所有子节点
     * @return            : 复制后的树节点
     */
    @SuppressWarnings("unchecked")
    public TreeNode<T> clone() {
        //复制当前节点数据信息
        TreeNode<T> treeNode = new TreeNode<T>();
        //节点数据
        treeNode.setNodeData((T) this.nodeData.clone());
        //是否包含
        treeNode.setIsIncluded(this.isIncluded);
        //复制所有子节点
        for (TreeNode<T> item : this.children) {
            //复制子节点
            TreeNode<T> childNode = item.clone();
            //加入子节点列表中
            treeNode.addChildNode(childNode);
        }
        return treeNode;
    }

    /**
     * @param isIncluded : 节点是否包含
     * @methodName        : setChildrenIsIncluded
     * @description        : 设置所有子节点的是否包含属性
     */
    public void setChildrenIsIncluded(Integer isIncluded) {
        //遍历所有子节点
        for (TreeNode<T> item : this.children) {
            item.setIsIncluded(isIncluded);
            //子节点的子节点
            for (TreeNode<T> subItem : item.getChildren()) {
                subItem.setChildrenIsIncluded(isIncluded);
            }
        }
    }

    /**
     * @param combineNode: 并入的节点
     * @methodName        : combineTreeNode
     * @description        : 将结构完全相同的节点合并到本节点中，合并后的节点的isIncluded属性位|操作
     */
    public void combineTreeNode(TreeNode<T> combineNode) {
        //当前节点数据的isIncluded属性，使用位|操作
        this.setIsIncluded(this.getIsIncluded() | combineNode.getIsIncluded());
        //合并子节点
        for (int i = 0; i < children.size(); i++) {
            TreeNode<T> item = children.get(i);
            TreeNode<T> combineItem = combineNode.getChildren().get(i);
            //合并子节点
            item.combineTreeNode(combineItem);
        }
    }

    /**
     * @methodName    : arrange
     * @description    : 对树进行剪枝处理，即所有isIncluded为0的节点，都被移除
     */
    public void arrange() {
        //遍历子节点列表，如果子节点的isIncluded为0，则剪枝
        //倒序遍历
        for (int i = children.size() - 1; i >= 0; i--) {
            TreeNode<T> item = children.get(i);
            if (item.getIsIncluded() == 0) {
                //不包含，需要移除
                children.remove(i);
            } else {
                //包含，当前节点不需要移除，处理其子节点列表
                item.arrange();
            }
        }
    }

    /**
     * @param nodeList : 树节点列表,入口参数为null
     * @methodName        : getNodeList
     * @description        : 获取包括自身及所有子节点的列表
     * @return            : 树节点列表
     */
    public List<TreeNode<T>> getNodeList(List<TreeNode<T>> nodeList) {

        if (nodeList == null) {
            //如果入口节点，则参数为null，需要创建
            nodeList = new ArrayList<TreeNode<T>>();
        }

        //加入自身节点
        nodeList.add(this);

        //加入所有子节点
        for (int i = 0; i < children.size(); i++) {
            TreeNode<T> childNode = children.get(i);
            childNode.getNodeList(nodeList);
        }

        return nodeList;
    }

    /**
     * @methodName        : toString
     * @description        : 重载toString方法
     * @return            : 返回序列化输出的字符串
     */
    @Override
    public String toString() {
        String sRet = "";

        //根节点的数据部分不必输出
        if (parent != null) {
            //非根节点
            //输出节点开始符号
            sRet = "{";
            //输出isIncluded值，剪枝后的树，无需输出此字段
            //sRet += ""isIncluded":" + isIncluded + ",";
            //输出当前节点数据
            sRet += "nodeData :" + nodeData.toString();
            //与前一个节点分隔
            sRet += ",";
            sRet += "children:";
        }

        //输出子节点
        //子节点列表
        sRet += "[";
        String sChild = "";
        //遍历子节点
        for (TreeNode<T> item : children) {
            //输出子节点数据
            if (sChild.equals("")) {
                sChild = item.toString();
            } else {
                sChild += "," + item.toString();
            }
        }
        sRet += sChild;
        //结束列表
        sRet += "]";
        if (parent != null) {
            //输出节点结束符号
            sRet += "}";
        }

        return sRet;
    }
}