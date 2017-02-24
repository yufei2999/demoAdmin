package com.yufei.sys.service;

import com.yufei.base.BaseService;
import com.yufei.sys.model.SysFunc;

import java.util.List;

public interface SysFuncService extends BaseService<SysFunc, java.lang.String> {

    /**
     * 权限列表
     *
     * @param parentId
     * @return
     */
    List<SysFunc> findByParentId(String parentId);

    /**
     * 生成code
     *
     * @param parentId
     * @param tableName
     * @param codeColumnName
     * @return
     */
    String generateCode(String parentId, String tableName, String codeColumnName);

    /**
     * 逻辑删除
     *
     * @param parentId
     */
    void deleteFuncTree(String parentId);

    /**
     * 权限列表
     *
     * @param userId
     * @return
     */
    List<SysFunc> getUserFuncList(String userId);

}
