package com.yufei.sys.dao.impl;

import com.yufei.base.BaseDaoImpl;
import com.yufei.utils.IdGenerator;
import com.yufei.sys.dao.SysUserDao;
import com.yufei.sys.model.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository("sysUserDao")
public class SysUserDaoImpl extends BaseDaoImpl<SysUser,String> implements SysUserDao {

    @Override
    public String getIbatisSqlMapNamespace() {
        return "SysUser";
    }

    @Override
    protected void prepareObjectForSave(SysUser entity) {
        if(StringUtils.isEmpty(entity.getId())) {
            entity.setId(IdGenerator.genUUIDStr());
        }
    }


}
