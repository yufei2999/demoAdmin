package com.yufei.sys.service.impl;

import com.yufei.base.BaseDao;
import com.yufei.base.BaseServiceImpl;
import com.yufei.sys.dao.SysRoleFuncDao;
import com.yufei.sys.model.SysRoleFunc;
import com.yufei.sys.service.SysRoleFuncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("sysRoleFuncService")
@Transactional
public class SysRoleFuncServiceImpl extends BaseServiceImpl<SysRoleFunc,String> implements SysRoleFuncService {

	@Autowired
	private SysRoleFuncDao sysRoleFuncDao;

	public BaseDao<SysRoleFunc, String> getEntityDao() {
		return this.sysRoleFuncDao;
	}

	@Override
	public List<String> getFuncIdListbyRoleId(String roleId) {
		return sysRoleFuncDao.getFuncIdListbyRoleId(roleId);
	}

	@Override
	public void deleteFuncByRoleId(String roleId) {
		sysRoleFuncDao.deleteFuncByRoleId(roleId);
	}

}
