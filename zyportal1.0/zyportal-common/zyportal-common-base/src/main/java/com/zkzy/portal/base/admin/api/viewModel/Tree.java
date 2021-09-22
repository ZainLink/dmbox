package com.zkzy.portal.base.admin.api.viewModel;

import java.util.ArrayList;
import java.util.List;

public class Tree {

	private StringBuffer html = new StringBuffer();  
    private List<Node> nodes;  
    private List<TreeModel> TreeModel;  
    String s[]={"line-h-r","line-h-c","line-h-l","line-h-none"};
//    public Tree(List<Node> nodes){
//        this.nodes = nodes;
//    }
    
//    public String buildTree(){
//        html.append("<ul>");
//        for (Node node : nodes) {
//            Integer id = node.getId();  
//            if (node.getParentId() == null) {
//                html.append("\r\n<li id='" + id + "'>" + node.getName()+ "</li>");  
//                build(node);  
//            }  
//        }  
//        html.append("\r\n</ul>");  
//        return html.toString();  
//    }  
//      
//    private void build(Node node){  
//        List<Node> children = getChildren(node);  
//        if (!children.isEmpty()) {  
//            html.append("\r\n<ul>");  
//            for (Node child : children) {  
//                Integer id = child.getId();  
//                html.append("\r\n<li id='" + id + "'>" + child.getName()+ "</li>");  
//                build(child);  
//            }  
//            html.append("\r\n</ul>");  
//        }   
//    }  
//      
//    private List<Node> getChildren(Node node){  
//        List<Node> children = new ArrayList<Node>();  
//        Integer id = node.getId();  
//        for (Node child : nodes) {  
//            if (id.equals(child.getParentId())) {  
//                children.add(child);  
//            }  
//        }  
//        return children;  
//    }
    
    public Tree(List<TreeModel> TreeModel){
        this.TreeModel = TreeModel;
    }
      
    public String buildTree(String title){
        html.append("<div class='strt-block'><div class='strt-part'><span class='strt-name'>"+title+"</span>");
        int total=TreeModel.size();
        int a=1;
        String c = null;		//class样式
        String line = null;
        if(total>0){
        	html.append("<div class='line-v'><span></span></div>");
        	html.append("<div class='strt-block'>");
        }
        for (TreeModel node : TreeModel) {
            if (node.getPid() == null) {
            	if(a==1){
            		c=s[0];
            	}else if(a==total){
            		c=s[2];
            	}else{
            		c=s[1];
            	}
                html.append("\r\n<div class='strt-part'><span class='line-h "+c+"'></span><div class='line-v'><span></span></div><span class='strt-name'>"+node.getName()+"</span>");  
                if(!getChildren(node).isEmpty()){
                	html.append("<div class='line-v'><span></span></div>");
                	build(node);
                }
                html.append("\r\n</div>");
                a++;
            }
        }
        if(TreeModel.size()>0){
        	html.append("\r\n</div>");  
        }
        html.append("\r\n</div>");  
        html.append("\r\n</div>");  
        return html.toString();  
    }
      
    private void build(TreeModel node){
        List<TreeModel> children = getChildren(node);
        if (!children.isEmpty()) {
        	int total=children.size();
            int a=1;
            String c = null;		//class样式
            String line = null;
            html.append("\r\n<div class='strt-block'>");
            for (TreeModel child : children) {
            	if(children.size()==1){
            		c=s[3];
            	}else{
            		if(a==1){
            			c=s[0];
            		}else if(a==total){
            			c=s[2];
            		}else{
            			c=s[1];
            		}
            	}
                html.append("\r\n<div class='strt-part'><span class='line-h "+c+"'></span><div class='line-v'><span></span></div><span class='strt-name'>"+child.getName()+"</span>");
                if(!getChildren(child).isEmpty()){
                	html.append("<div class='line-v'><span></span></div>");
                	build(child);
                }
                html.append("\r\n</div>");
                a++;
            }
            html.append("\r\n</div>");
        }
    }
      
    private List<TreeModel> getChildren(TreeModel node){
        List<TreeModel> children = new ArrayList<TreeModel>();
        String id = node.getId();
        for (TreeModel child : TreeModel) {
            if (id.equals(child.getPid())) {
                children.add(child);
            }
        }
        return children;
    }
    
}
