package com.yufei.sys.service.impl;

import com.yufei.base.BaseDao;
import com.yufei.base.BaseServiceImpl;
import com.yufei.sys.dao.SysRoleDao;
import com.yufei.sys.model.SysRole;
import com.yufei.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sysRoleService")
@Transactional
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole,String> implements SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;

	public BaseDao<SysRole, String> getEntityDao() {
		return this.sysRoleDao;
	}


}
