package com.yufei.sys.service.impl;

import com.yufei.base.BaseDao;
import com.yufei.base.BaseServiceImpl;
import com.yufei.sys.dao.SysUserRoleDao;
import com.yufei.sys.model.SysUserRole;
import com.yufei.sys.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sysUserRoleService")
@Transactional
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole,java.lang.String> implements SysUserRoleService {

	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	public BaseDao<SysUserRole, java.lang.String> getEntityDao() {
		return this.sysUserRoleDao;
	}


}
