package com.yufei.sys.dao;

import com.yufei.base.BaseDao;
import com.yufei.sys.model.SysFunc;

import java.util.List;

public interface SysFuncDao extends BaseDao<SysFunc, java.lang.String>{

    /**
     * 权限列表
     *
     * @param parentId
     * @return
     */
    List<SysFunc> findByParentId(String parentId);

    /**
     * 根据parentId查询parentcode
     *
     * @param parentId
     * @param tableName
     * @param codeColumnName
     * @return
     */
    String findCodeByParentId(String parentId, String tableName, String codeColumnName);

    /**
     * 查询所有父级等于parentId的子集code集合
     *
     * @param parentId
     * @param tableName
     * @param codeColumnName
     * @return
     */
    List<String> findChildrenCodeListByParentId(String parentId, String tableName, String codeColumnName);

    /**
     * 获取parentId = 0 的所有code
     *
     * @param parentIdZero
     * @param tableName
     * @param codeColumnName
     * @return
     */
    List<String> findByAllCodeParentIdEQZero(String parentIdZero, String tableName, String codeColumnName);

    /**
     * 逻辑删除
     *
     * @param parentId
     */
    void deleteFuncTree(String parentId);

    /**
     * 权限列表
     *
     * @param roleIds
     * @return
     */
    List<SysFunc> getFuncListByRoleIds(String roleIds);
}
