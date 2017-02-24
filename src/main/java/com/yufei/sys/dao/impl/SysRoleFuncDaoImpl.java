package com.yufei.sys.dao.impl;

import com.yufei.base.BaseDaoImpl;
import com.yufei.utils.IdGenerator;
import com.yufei.sys.dao.SysRoleFuncDao;
import com.yufei.sys.model.SysRoleFunc;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sysRoleFuncDao")
public class SysRoleFuncDaoImpl extends BaseDaoImpl<SysRoleFunc,String> implements SysRoleFuncDao {

    @Override
    public String getIbatisSqlMapNamespace() {
        return "SysRoleFunc";
    }

    @Override
    protected void prepareObjectForSave(SysRoleFunc entity) {
        if(StringUtils.isEmpty(entity.getId())) {
            entity.setId(IdGenerator.genUUIDStr());
        }
    }

    @Override
    public List<String> getFuncIdListbyRoleId(String roleId) {
        return getSqlSession().selectList("manual-SysRoleFunc.getFuncIdListbyRoleId", roleId);
    }

    @Override
    public void deleteFuncByRoleId(String roleId) {
        getSqlSession().delete("manual-SysRoleFunc.deleteFuncByRoleId", roleId);
    }

}
