package org.lanqiao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	public static ThreadLocal<Connection> theadLocal=new ThreadLocal<Connection>();
	
	public static Connection getConn()
	{
		Connection conn=theadLocal.get();
		if(conn==null)
		{
			String url="jdbc:log4jdbc:oracle:thin:@119.23.203.154:1521:orcl";
			try {
				//Class.forName("oracle.jdbc.driver.OracleDriver");
				Class.forName("net.sf.log4jdbc.DriverSpy");
				conn=DriverManager.getConnection(url, "rbac","rbac1234");
				theadLocal.set(conn);
				return conn;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	public static void closeConnection()
	{
		Connection conn=theadLocal.get();
		if(conn!=null)
		{
			try {
				if(!conn.isClosed())
				{
					conn.close();
					theadLocal.remove();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}