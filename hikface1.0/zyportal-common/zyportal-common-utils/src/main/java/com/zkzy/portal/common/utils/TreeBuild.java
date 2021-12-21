package com.zkzy.portal.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yupc on 2017/10/28.
 */
public class TreeBuild {

	public static <T> TreeBase<T> build(List<TreeBase<T>> nodes) {
		if(nodes == null){
			return null;
		}
		List<TreeBase<T>> topNodes = new ArrayList<TreeBase<T>>();
		for (TreeBase<T> children : nodes) {
			String pid = children.getParentId();
			if (pid == null || "".equals(pid)) {
				topNodes.add(children);
				continue;
			}
			for (TreeBase<T> parent : nodes) {
				String id = parent.getId();
				if (id != null && id.equals(pid)) {
					parent.getChildren().add(children);
					children.setParent(true);
					parent.setChildren(true);
					continue;
				}
			}
		}
		TreeBase<T> root = new TreeBase<T>();
		if (topNodes.size() == 1) {
			root = topNodes.get(0);
		} else {
			root.setId("-1");
			root.setParentId("");
			root.setParent(false);
			root.setChildren(true);
			root.setChecked(true);
			root.setChildren(topNodes);
			root.setName("顶级节点");
		}
		return root;
	}

}