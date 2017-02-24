package com.yufei.common.easyui.bean;

import com.yufei.utils.BeanUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pc on 2016-11-03.
 */
public class EasyUITreeNode {

    public static final String DEFAULT_ROOT_ID = "0";
    public static final String DEFAULT_ROOT_TEXT = "善治合同节水";
    public static final int DEFAULT_ROOTCODE_LENGTH = 3;
    public static final String DEFAULT_IS_LEAF = "isleaf";

    /**
     * 节点的 id，它对于加载远程数据很重要。
     */
    private String id;
    /**
     * 要显示的节点文本。
     */
    private String text;
    /**
     * 节点状态，'open' 或 'closed'，默认是 'open'。当设置为 'closed' 时，该节点有子节点，并且将从远程站点加载它们。
     */
    private String state = "closed";
    /**
     * 指示节点是否被选中。
     */
    private boolean checked = false;
    /**
     * 给一个节点添加的自定义属性。
     */
    private Map<String, Object> attributes;
    /**
     * 定义了一些子节点的节点数组。
     */
    private List<EasyUITreeNode> children;

    public EasyUITreeNode() {
    }

    public EasyUITreeNode(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public EasyUITreeNode(String id, String text, Object object) {
        this.id = id;
        this.text = text;
        attributes = new HashMap<>();
        BeanUtil.copy2Map(object, attributes);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public List<EasyUITreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<EasyUITreeNode> children) {
        this.children = children;
    }

}
