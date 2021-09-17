package com.zkzy.portal.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/*
原来的递归方法 无限层级树，会导致内存溢出 不使用了
 */
public class Tree {

	public JSONArray treeMenuList(JSONArray menuList, int parentId) {
		JSONArray childMenu = new JSONArray();
		for (Object object : menuList) {
			JSONObject jsonMenu = (JSONObject) JSONObject.toJSON(object);
			int menuId = jsonMenu.getIntValue("id");
			int pid = jsonMenu.getIntValue("parentId");
			if (parentId == pid) {
				JSONArray c_node = treeMenuList(menuList, menuId);
				jsonMenu.put("childNode", c_node);
				childMenu.add(jsonMenu);
			}
		}
		return childMenu;
	}

	public static void main(String args[]) {
		JSONArray jsonArray = new JSONArray();
		Node menu1 = new Node();
		menu1.setId("1");
		menu1.setParentId("0");
		Node menu2 = new Node();
		menu2.setId("2");
		menu2.setParentId("0");
		Node menu3 = new Node();
		menu3.setId("3");
		menu3.setParentId("2");
		Node menu4 = new Node();
		menu4.setId("4");
		menu4.setParentId("2");
		Node menu5 = new Node();
		menu5.setId("5");
		menu5.setParentId("4");
		Node menu6 = new Node();
		menu6.setId("6");
		menu6.setParentId("1");

		jsonArray.add(menu1);
		jsonArray.add(menu2);
		jsonArray.add(menu3);
		jsonArray.add(menu4);
		jsonArray.add(menu5);
		jsonArray.add(menu6);

		System.out.print(new Tree().treeMenuList(jsonArray,0));
	}
}
