package com.hhu.common.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import com.hhu.entity.Function;

public class Tree {
	
	private List<Node> nodes = new LinkedList<>();
	
	private Node root = null;
	
	public Tree(List<Function> functions) {
		for(Function f:functions) {
			Node node = new Node(f.getId(), f.getParentId(), f.getName(), "open", 
					new NodeAttribute(null==f.getUrl()? "":f.getUrl(), f.getId()), f.getSerialNum());
			nodes.add(node);
			
			if(node.getId()==0) {
				root = node;
			}
			
		}
	}
	
	public List<Node> build() {
		buildTree(root);
		List<Node> result = new ArrayList<Node>();
		result.add(root);
		return result;
	}
	
	private void buildTree(Node parent) {
		Node node = null;
		for(Iterator<Node> ite = nodes.iterator();ite.hasNext();) {
			node = ite.next();
			if(Objects.equals(node.getParentId(), parent.getId())) {
				parent.getChildern().add(node);
				buildTree(node);
			}
		}
	}

}
