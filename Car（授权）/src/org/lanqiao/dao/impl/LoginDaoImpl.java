package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.lanqiao.bean.Fun;
import org.lanqiao.bean.Menu;
import org.lanqiao.bean.Role;
import org.lanqiao.bean.User;
import org.lanqiao.dao.LoginDao;

public class LoginDaoImpl implements LoginDao {

	public User login(String username, String password) {
		User user = null;
		String url = "jdbc:oracle:thin:@119.23.203.154:1521:orcl";
		String sql = "SELECT * FROM t_user WHERE user_name=? AND user_pass=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "rbac", "rbac1234");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUser_id(rs.getString("USER_ID"));
				user.setUser_name(rs.getString("USER_NAME"));
			}
			sql = "SELECT r.role_id,r.role_name FROM t_user_role ur,t_role r WHERE ur.role_id=r.role_id AND user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUser_id());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Role role = new Role();
				role.setRole_id(rs.getString("ROLE_ID"));
				role.setRole_name(rs.getString("ROLE_NAME"));
				user.getRoles().add(role);// 设置用户和角色的关系
			}
			sql = "SELECT m.* FROM t_role_menu rm,t_menu m WHERE rm.menu_id=m.menu_id AND rm.role_id=?";
			pstmt = conn.prepareStatement(sql);
			for (Role role : user.getRoles()) {
				pstmt.setString(1, role.getRole_id());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Menu menu = new Menu();
					menu.setMenu_id(rs.getString("MENU_ID"));
					menu.setFather_menu_id(rs.getString("FATHER_MENU_ID"));
					menu.setMenu_name(rs.getString("MENU_NAME"));
					role.getMenus().add(menu);
				}
			}
			sql = "SELECT f.* FROM t_fun f,t_menu m WHERE f.menu_id=m.menu_id AND m.menu_id=?";
			pstmt = conn.prepareStatement(sql);
			for (Role role : user.getRoles()) {
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
			}
			return user;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}