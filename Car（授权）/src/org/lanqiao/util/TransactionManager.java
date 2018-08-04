package org.lanqiao.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager<T> implements InvocationHandler {
	
	T t;
	// �����������
	@SuppressWarnings("unchecked")
	public T createProxy(T t) {
		this.t = t;
		return (T)Proxy.newProxyInstance(t.getClass().getClassLoader(),// ͬһ���������
				t.getClass().getInterfaces(), this);
	}
	public Object invoke(Object proxy, Method method, Object[] args)// method����Ŀ�귽��
	{
		Connection conn = ConnectionManager.getConn();
		try {
			if (method.getName().startsWith("save")
					|| method.getName().startsWith("delete")) {
				conn.setAutoCommit(false);// ��������
				Object retVal = method.invoke(t, args);// ��������Ŀ�귽����saveUser��saveRole��
				conn.commit();// �ύ����
				return retVal;
			}
			else
			{
				Object retVal = method.invoke(t, args);//��ѯ
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