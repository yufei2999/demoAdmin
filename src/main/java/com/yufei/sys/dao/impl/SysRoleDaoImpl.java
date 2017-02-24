package com.yufei.sys.dao.impl;

import com.yufei.base.BaseDaoImpl;
import com.yufei.utils.IdGenerator;
import com.yufei.sys.dao.SysRoleDao;
import com.yufei.sys.model.SysRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository("sysRoleDao")
public class SysRoleDaoImpl extends BaseDaoImpl<SysRole,String> implements SysRoleDao {

    @Override
    public String getIbatisSqlMapNamespace() {
        return "SysRole";
    }

    @Override
    protected void prepareObjectForSave(SysRole entity) {
        if(StringUtils.isEmpty(entity.getId())) {
            entity.setId(IdGenerator.genUUIDStr());
        }
    }


}
