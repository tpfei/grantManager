package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.bean.Role;
import org.lanqiao.util.PageModel;

public interface RoleDao {

	public PageModel<Role> getAllRole(int currentPageNo);
	public Role getRoleById(String roleId);
	public int saveRole(Role role);
	public Role getRoleGrant(String roleId);
}