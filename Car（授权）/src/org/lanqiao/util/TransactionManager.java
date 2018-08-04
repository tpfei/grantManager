package org.lanqiao.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager<T> implements InvocationHandler {
	
	T t;
	// 创建代理对象
	@SuppressWarnings("unchecked")
	public T createProxy(T t) {
		this.t = t;
		return (T)Proxy.newProxyInstance(t.getClass().getClassLoader(),// 同一个类加载器
				t.getClass().getInterfaces(), this);
	}
	public Object invoke(Object proxy, Method method, Object[] args)// method就是目标方法
	{
		Connection conn = ConnectionManager.getConn();
		try {
			if (method.getName().startsWith("save")
					|| method.getName().startsWith("delete")) {
				conn.setAutoCommit(false);// 开启事务
				Object retVal = method.invoke(t, args);// 真正调用目标方法（saveUser、saveRole）
				conn.commit();// 提交事务
				return retVal;
			}
			else
			{
				Object retVal = method.invoke(t, args);//查询
				return retVal;
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally
		{
			ConnectionManager.closeConnection();
		}
		return null;
	}
}