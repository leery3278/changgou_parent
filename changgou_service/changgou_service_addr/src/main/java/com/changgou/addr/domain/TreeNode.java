package com.changgou.addr.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @Author leery
 * @Date 2021/2/1 14:28
 */
@Data
public class TreeNode {
    /**
     * ID
     */
    protected String key;
    /**
     * 父节点ID
     */
    protected String parentKey;
    /**
     * 名称
     */
    protected String value;
    /**
     * 子节点
     */
    protected List<TreeNode> children = new ArrayList<>();
    /**
     * 添加子节点
     *
     * @param node
     */
    public void add(TreeNode node) {
        children.add(node);
    }
}
