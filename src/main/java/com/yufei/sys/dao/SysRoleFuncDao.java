package com.yufei.sys.dao;

import com.yufei.base.BaseDao;
import com.yufei.sys.model.SysRoleFunc;

import java.util.List;

public interface SysRoleFuncDao extends BaseDao<SysRoleFunc, String>{

    /**
     * 权限id列表
     *
     * @param roleId
     * @return
     */
    List<String> getFuncIdListbyRoleId(String roleId);

    /**
     * 删除权限
     *
     * @param roleId
     */
    void deleteFuncByRoleId(String roleId);

}
