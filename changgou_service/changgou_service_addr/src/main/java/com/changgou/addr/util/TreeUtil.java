package com.changgou.addr.util;

import com.changgou.addr.domain.TreeNode;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @Author leery
 * @Date 2021/2/1 15:38
 */
@UtilityClass
public class TreeUtil {
    /**
     *两层循环实现建树
     *
     * @param treeNodes
     * @param root
     * @return {@link {@link java.util.List<T>}}
     * @throws
     * @Author leery
     * @Date 2021/2/1 17:33
     */
    public <T extends TreeNode> List<T> build(List<T> treeNodes,Object root) {
        List<T> trees = new ArrayList<>();

        for (T treeNode : treeNodes) {

            if(root.equals(treeNode.getParentKey())) {
                trees.add(treeNode);
            }

            for (T it : treeNodes) {
                if(it.getParentKey().equals(treeNode.getKey())) {
                    if(treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<>());
                    }
                    treeNode.add(it);
                }
            }

        }
        return trees;
    }

    /**
     * 使用递归方法建树
     *
     * @param treeNodes
     * @param root
     * @return {@link {@link List<T>}}
     * @throws
     * @Author leery
     * @Date 2021/2/2 8:57
     */
    public <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes,Object root) {
        List<T> trees = new ArrayList<T>();
        for (T treeNode : treeNodes){
            if(root.equals(treeNode.getParentKey())) {
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     *
     * 递归查找子节点
     * @param treeNode
     * @param treeNodes
     * @return {@link {@link T}}
     * @throws
     * @Author leery
     * @Date 2021/2/1 17:53
     */
    public <T extends TreeNode> T findChildren(T treeNode,List<T> treeNodes) {
        for (T it : treeNodes){
            if(treeNode.getKey().equals(it.getParentKey())) {
                if(treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }

}
