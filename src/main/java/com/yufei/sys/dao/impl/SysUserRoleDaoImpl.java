package com.yufei.sys.dao.impl;

import com.yufei.base.BaseDaoImpl;
import com.yufei.utils.IdGenerator;
import com.yufei.sys.dao.SysUserRoleDao;
import com.yufei.sys.model.SysUserRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository("sysUserRoleDao")
public class SysUserRoleDaoImpl extends BaseDaoImpl<SysUserRole,java.lang.String> implements SysUserRoleDao {

    @Override
    public String getIbatisSqlMapNamespace() {
        return "SysUserRole";
    }

    @Override
    protected void prepareObjectForSave(SysUserRole entity) {
        if(StringUtils.isEmpty(entity.getId())) {
            entity.setId(IdGenerator.genUUIDStr());
        }
    }


}
