package org.lanqiao.dao.impl;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lanqiao.bean.Fun;
import org.lanqiao.bean.Menu;
import org.lanqiao.bean.Role;
import org.lanqiao.bean.User;

public class LoginDaoTest{

	LoginDaoImpl loginDao;
	
	@Before
	public void setUp()
	{
		loginDao=new LoginDaoImpl();
	}
	//@Test(timeout=30000)
	@Test
	public void testLogin()
	{
		User user=loginDao.login("wangwu","ww1234");

		//��������
		for(Role role:user.getRoles())
		{
			System.out.print("�û�����"+user.getUser_name());
			System.out.println("\t��ɫ��"+role.getRole_name());
			for(Menu menu:role.getMenus())
			{
				System.out.println("һ���˵���"+menu.getMenu_name());
				for(Fun fun:menu.getFuns())
				{
					System.out.println("\tȨ�ޣ�"+fun.getFun_name());
				}
				for(Menu seMenu:menu.getChildMenus())
				{
					System.out.println("\t�����˵���"+seMenu.getMenu_name());
					for(Fun fun:seMenu.getFuns())
					{
						System.out.println("\t\tȨ�ޣ�"+fun.getFun_name());
					}
				}
			}
		}
		/*
		Assert.assertNotNull(user);
		Assert.assertEquals(expected, actual);
		Assert.assertSame(expected, actual);
		Assert.assertNotNull(object);
		Assert.assertNull(object);
		Assert.assertNotSame(expected, actual);
		Assert.assertFalse(condition);
		Assert.assertTrue(condition);
		*/
		//assertThat(reason, assertion);
	}
	@Test
	public void testAbc()
	{
		Assert.assertEquals(true, true);
	}
	@After
	public void tearDown()
	{
		
	}
}