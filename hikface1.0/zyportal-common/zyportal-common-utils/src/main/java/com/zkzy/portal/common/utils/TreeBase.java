package com.zkzy.portal.common.utils;


import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yupc on 2017/10/28.
 */
public class TreeBase<T> {
	/**
	 * 节点ID
	 */
	private String id;
	/**
	 * 显示节点文本
	 */
	private String name;
	/**
	 * 节点状态，open closed
	 */
	private String state = "open";
	/**
	 * 节点是否被选中 true false
	 */
	private boolean checked = false;
	/**
	 * 节点属性
	 */
	private List<Map<String, Object>> attributes;
	/**
	 * 节点的子节点
	 */
	private List<TreeBase<T>> children = new ArrayList<TreeBase<T>>();

	/**
	 * 父ID
	 */
	private String parentId;
	/**
	 * 是否有父节点
	 */
	private boolean isParent = false;
	/**
	 * 是否有子节点
	 */
	private boolean isChildren = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<Map<String, Object>> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Map<String, Object>> attributes) {
		this.attributes = attributes;
	}

	public List<TreeBase<T>> getChildren() {
		return children;
	}

	public void setChildren(List<TreeBase<T>> children) {
		this.children = children;
	}

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public boolean isChildren() {
		return isChildren;
	}

	public void setChildren(boolean isChildren) {
		this.isChildren = isChildren;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public TreeBase(String id, String name, String state, boolean checked,
	            List<Map<String, Object>> attributes, List<TreeBase<T>> children,
	            boolean isParent, boolean isChildren, String parentID) {
		super();
		this.id = id;
		this.name = name;
		this.state = state;
		this.checked = checked;
		this.attributes = attributes;
		this.children = children;
		this.isParent = isParent;
		this.isChildren = isChildren;
		this.parentId = parentID;
	}

	public TreeBase() {
		super();
	}

	@Override
	public String toString() {

		return JSON.toJSONString(this);
	}

}
