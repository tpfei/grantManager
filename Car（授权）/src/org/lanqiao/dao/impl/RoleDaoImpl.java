package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.bean.Fun;
import org.lanqiao.bean.Menu;
import org.lanqiao.bean.Role;
import org.lanqiao.dao.RoleDao;
import org.lanqiao.util.ConnectionManager;
import org.lanqiao.util.PageModel;

public class RoleDaoImpl implements RoleDao {

	public PageModel<Role> getAllRole(int currentPageNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM (SELECT rownum rn,t.* FROM (SELECT * FROM t_role) t WHERE rownum<=?) WHERE rn>=?";
		int rowsPerPage = 3;
		int endRow = rowsPerPage * currentPageNo;
		int startRow = (currentPageNo - 1) * rowsPerPage + 1;
		int totalRows = 0;
		int totalPage = 0;
		ResultSet rs = null;
		PageModel<Role> pm = new PageModel<Role>();
		List<Role> roles = new ArrayList<Role>();
		try {
			conn = ConnectionManager.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Role role = new Role();
				role.setRole_id(rs.getString("ROLE_ID"));
				role.setRole_name(rs.getString("ROLE_NAME"));
				roles.add(role);
			}
			sql = "SELECT COUNT(*) FROM t_role";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			totalRows = rs.getInt(1);
			pm.setList(roles);
			totalPage = totalRows % rowsPerPage == 0 ? totalRows / rowsPerPage
					: totalRows / rowsPerPage + 1;
			pm.setTotalPage(totalPage);
			return pm;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionManager.closeConnection();
		}
		return null;
	}

	public Role getRoleById(String roleId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM t_role WHERE role_id=?";
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roleId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Role role = new Role();
				role.setRole_id(roleId);
				role.setRole_name(rs.getString("ROLE_NAME"));
				return role;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int saveRole(Role role) {
		Connection conn = null;
		String sql = "INSERT INTO t_role(role_id,role_name) VALUES(?,?)";
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionManager.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, role.getRole_id());
			pstmt.setString(2, role.getRole_name());
			pstmt.executeUpdate();// 保存用户数据
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Role getRoleGrant(String roleId) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT m.* FROM t_role_menu rm,t_menu m WHERE rm.menu_id=m.menu_id AND rm.role_id=?";
		try {
			conn = ConnectionManager.getConn();
			Role role = getRoleById(roleId);//如果role不为空
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roleId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setMenu_id(rs.getString("MENU_ID"));
				menu.setFather_menu_id(rs.getString("FATHER_MENU_ID"));
				menu.setMenu_name(rs.getString("MENU_NAME"));
				role.getMenus().add(menu);
			}
			sql = "SELECT f.* FROM t_fun f,t_menu m WHERE f.menu_id=m.menu_id AND m.menu_id=?";
			pstmt = conn.prepareStatement(sql);

			for (Menu menu : role.getMenus()) {
				pstmt.setString(1, menu.getMenu_id());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Fun fun = new Fun();
					fun.setFun_id(rs.getString("FUN_ID"));
					fun.setFun_name(rs.getString("FUN_NAME"));
					fun.setFun_url(rs.getString("FUN_URL"));
					fun.setMenu_id(rs.getString("MENU_ID"));
					menu.getFuns().add(fun);
				}
			}
			return role;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}