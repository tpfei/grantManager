package org.lanqiao.util;

import java.util.List;

public class PageModel<T> {

	private List<T> list;
	private int totalPage;
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
}