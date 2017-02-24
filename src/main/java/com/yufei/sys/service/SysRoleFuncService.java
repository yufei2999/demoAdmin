package com.yufei.sys.service;

import com.yufei.base.BaseService;
import com.yufei.sys.model.SysRoleFunc;

import java.util.List;

public interface SysRoleFuncService extends BaseService<SysRoleFunc, String> {

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
