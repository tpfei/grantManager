package org.lanqiao.service.impl;

import org.lanqiao.bean.Role;
import org.lanqiao.dao.RoleDao;
import org.lanqiao.dao.impl.RoleDaoImpl;
import org.lanqiao.service.RoleService;
import org.lanqiao.util.PageModel;

public class RoleServiceImpl implements RoleService {

	RoleDao roleDao;
	public RoleServiceImpl()
	{
		roleDao=new RoleDaoImpl();
	}
	public PageModel<Role> getAllRole(int currentPageNo) {
		
		return roleDao.getAllRole(currentPageNo);
	}
	public Role getRoleById(String roleId) {
		return roleDao.getRoleById(roleId);
	}
	public int saveRole(Role role) {
		return roleDao.saveRole(role);
	}
	public Role getRoleGrant(String roleId) {
		return roleDao.getRoleGrant(roleId);
	}
}