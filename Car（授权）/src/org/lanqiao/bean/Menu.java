package org.lanqiao.bean;

import java.util.HashSet;
import java.util.Set;

public class Menu {

	private String menu_id;
	private String menu_name;
	private String father_menu_id;
	private Set<Fun> funs=new HashSet<Fun>();
	
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getFather_menu_id() {
		return father_menu_id;
	}
	public void setFather_menu_id(String father_menu_id) {
		this.father_menu_id = father_menu_id;
	}
	public Set<Fun> getFuns() {
		return funs;
	}
	public void setFuns(Set<Fun> funs) {
		this.funs = funs;
	}
}