package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.lanqiao.bean.User;
import org.lanqiao.dao.UserDao;
import org.lanqiao.util.ConnectionManager;

public class UserDaoImpl implements UserDao {

	public int saveUser(User user) {
		Connection conn = null;
		String sql="INSERT INTO t_user(user_id,user_name) VALUES(?,?)";
		PreparedStatement pstmt=null;
		try {
			conn = ConnectionManager.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,user.getUser_id());
			pstmt.setString(2,user.getUser_name());
			pstmt.executeUpdate();//保存用户数据
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}