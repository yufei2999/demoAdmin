package com.yufei.sys.service.impl;

import com.yufei.base.BaseDao;
import com.yufei.base.BaseServiceImpl;
import com.yufei.sys.dao.SysUserDao;
import com.yufei.sys.model.SysUser;
import com.yufei.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sysUserService")
@Transactional
public class SysUserServiceImpl extends BaseServiceImpl<SysUser,String> implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;

	public BaseDao<SysUser, String> getEntityDao() {
		return this.sysUserDao;
	}


}
