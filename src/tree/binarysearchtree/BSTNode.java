package tree.binarysearchtree;

import tree.binarysearchtree.TreePrinter.PrintableNode;

public class BSTNode implements PrintableNode {
	BSTNode left = null;
	BSTNode right = null;
	int key;
	
	public BSTNode() {
		this(0);
	}
	
	public BSTNode(int key) {
		this.key = key;
	}
	
	public PrintableNode getLeft() {
		return left;
	}
	
	public PrintableNode getRight() {
		return right;
	}
	
	public String getText() {
		return Integer.toString(this.key);
	}
}
