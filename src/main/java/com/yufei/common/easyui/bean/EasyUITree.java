package com.yufei.common.easyui.bean;

import com.alibaba.fastjson.JSON;
import com.yufei.utils.Reflections;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2016-11-03.
 */
public class EasyUITree {

    public static String getTreeRootJson() {
        EasyUITreeNode root = new EasyUITreeNode(EasyUITreeNode.DEFAULT_ROOT_ID, EasyUITreeNode.DEFAULT_ROOT_TEXT);
        List<EasyUITreeNode> treeList = new ArrayList<EasyUITreeNode>();
        treeList.add(root);
        return JSON.toJSONString(treeList);
    }

    public static String getTreeRootJson(String id, String text) {
        EasyUITreeNode root = new EasyUITreeNode(id, text);
        List<EasyUITreeNode> treeList = new ArrayList<EasyUITreeNode>();
        treeList.add(root);
        return JSON.toJSONString(treeList);
    }

    /**
     * 把集合转换成EasyUI支持的JSON对象集合
     *
     * @param list    当前节点的集合
     * @param idPro   节点ID属性的 KEY值
     * @param textPro 节点TEXT的 KEY 值
     * @return
     */
    public static <T> String getTreeNodeJson(List<T> list, String idPro, String textPro) {
        return getTreeNodeJson(list, idPro, textPro, null, null);
    }

    /**
     * 把集合转换成EasyUI支持的JSON对象集合
     *
     * @param list          当前节点的集合
     * @param idPro         节点ID属性的 KEY值
     * @param textPro       节点TEXT的 KEY 值
     * @param checkedIdList 树节点需要选择的ID集合
     * @return
     */
    public static <T> String getTreeNodeJson(List<T> list, String idPro, String textPro, List<String> checkedIdList) {
        return getTreeNodeJson(list, idPro, textPro, null, checkedIdList);
    }

    /**
     * 把集合转换成EasyUI支持的JSON对象集合
     *
     * @param list          当前节点的集合
     * @param idPro         节点ID属性的 KEY值
     * @param textPro       节点TEXT的 KEY 值
     * @param isleaf        当前节点是否是叶子节点的 KEY值
     * @param checkedIdList 树节点需要选择的ID集合
     * @return
     */
    public static <T> String getTreeNodeJson(List<T> list, String idPro, String textPro, String isleaf, List<String> checkedIdList) {
        if (CollectionUtils.isNotEmpty(list)) {
            List<EasyUITreeNode> treeList = new ArrayList<EasyUITreeNode>();
            for (T t : list) {
                Object id = Reflections.getFieldValue(t, idPro);
                Object text = Reflections.getFieldValue(t, textPro);
                EasyUITreeNode node = new EasyUITreeNode(id.toString(), text.toString(), t);

                //此处应该根据对象中的“是否是叶子节点”的属性来判断前端的树是否打开
                //如果是open或者空则为叶子节点
                //如果数据库中没有该类型字段此处默认都是close，否则查询消耗较大
                //node.setState("open");
                if (isleaf != null) {
                    Object isleafObj = Reflections.getFieldValue(t, isleaf);
                    if (isleafObj != null && isleafObj.toString().equals(EasyUITreeNode.DEFAULT_IS_LEAF)) {
                        node.setState("open");
                    }
                }

                //判断当前节点是否选中
                if (CollectionUtils.isNotEmpty(checkedIdList)) {
                    if (checkedIdList.contains(id)) {
                        node.setChecked(true);
                    } else {
                        node.setChecked(false);
                    }
                }

                treeList.add(node);
            }
            return JSON.toJSONString(treeList);
        }
        return null;
    }

}
