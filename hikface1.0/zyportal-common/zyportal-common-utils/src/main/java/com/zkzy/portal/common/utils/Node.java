package com.zkzy.portal.common.utils;

import java.util.List;

public class Node {

	private String id;
    private String parentId;
    private String name;  
    private String link;
    private List<Node> nodeList;

    public List<Node> getNodeList(){ return nodeList;}
    public void setNodeList(List<Node> nodeList){ this.nodeList=nodeList;}
    public String getId() {
        return id;  
    }  
    public void setId(String id) {
        this.id = id;  
    }  
    public String getParentId() {
        return parentId;  
    }  
    public void setParentId(String parentId) {
        this.parentId = parentId;  
    }  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public String getLink() {  
        return link;  
    }  
    public void setLink(String link) {  
        this.link = link;  
    }  
}
