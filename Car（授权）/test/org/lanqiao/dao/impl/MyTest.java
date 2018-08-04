package org.lanqiao.dao.impl;

import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;

public class MyTest {

	@Test
	public void testGetUser()
	{
		Assert.assertEquals(true, true);
	}
	@Test
	public void testUUID()
	{
		System.out.println(UUID.randomUUID().toString());
	}
}