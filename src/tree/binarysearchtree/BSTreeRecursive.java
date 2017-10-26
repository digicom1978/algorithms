package tree.binarysearchtree;

import java.util.LinkedList;

/**
 * Using Recursive call for tree traversal
 * @author soh
 *
 */
public class BSTreeRecursive {

	BSTNode root;

	public BSTreeRecursive() {
		this.root = null;
	}

	public void insertRecursively(int value) {
		this.root = insertRecursivelyInternal(this.root, value);
	}

	private BSTNode insertRecursivelyInternal(BSTNode node, int value) {
		if( node == null || node == null ) {
			node = new BSTNode(value);
			return node;
		}

		if( node.key < value ) {
			node.right = insertRecursivelyInternal(node.right, value);
		} else {
			node.left = insertRecursivelyInternal(node.left, value);
		}

		return node;
	}

	public void search(int value) {
		searchInternal(this.root, value);
	}

	private void searchInternal(BSTNode node, int value) {
		if( node == null ) {
			System.out.println("No value in the tree");
			return;
		}

		if( node.key == value ) {
			System.out.println(value + " is found!");
			return;
		}

		if( node.key < value ) {
			searchInternal(node.right, value);
		} else {
			searchInternal(node.left, value);
		}
	}

	public void remove(int value) {
		removeInternal(this.root, this.root, value);
	}

	public int findMax() {
		return findMaxInternal(this.root);
	}

	public int findMaxInternal(BSTNode node) {
		if( node == null ) {
			System.out.println("No node");
			return -1;
		}

		if( node.right == null ) {
			return node.key;
		} else {
			return findMaxInternal(node.right);
		}
	}

	private boolean removeInternal(BSTNode node, BSTNode parent, int value) {
		boolean deleted = false;
		if( node == null ) {
			System.out.println("No node to be deleted in the tree");
			return deleted;
		}

		if( node.key == value ) {
			System.out.println("Node of "+value+" is deleted in the tree");

			// 1. node to be deleted is leaf node
			if( node.left == null && node.right == null ) {
				if( this.root == node ) {
					this.root = null;
				} else if( parent.key < value ) {
					parent.right = null;
				} else {
					parent.left = null;
				} 
				return true;
			}
			// 2. node to be deleted has both left, right children
			else if( node.left != null && node.right != null ) {
				int max = findMaxInternal(node.left);
				node.key = max;
				removeInternal(node.left, node, max);
			}
			// 3. node to be deleted is half-leaf node
			else if( node.left != null || node.right != null ) {
				if( node == this.root ) {
					this.root = (node.left != null ? node.left : node.right);
				}
				else {
					if( parent.key < value )
						parent.right = (node.left != null ? node.left : node.right);
					else
						parent.left = (node.left != null ? node.left : node.right);
				}
				return true;
			}
		} else if( node.key < value ) {
			deleted = removeInternal(node.right, node, value);
		} else {
			deleted = removeInternal(node.left, node, value);
		}

		return deleted;
	}

	public void print() {
		if( root == null ) {
			System.out.println("Empty tree");
			return;
		}

		LinkedList<BSTNode> queue = new LinkedList<BSTNode>();
		queue.add(this.root);

		int depth = 0;
		double numInDepth = Math.pow(2,  depth);

		BSTNode temp = null;
		while( !queue.isEmpty() ) {
			temp = queue.poll();

			numInDepth--;
			if( temp != null ) {
				System.out.print(temp.key + "(" 
						+((temp.left != null) ? temp.left.key : "")+","
						+((temp.right != null) ? temp.right.key : "")+")");
				queue.add(temp.left);
				queue.add(temp.right);
			}

			if( numInDepth == 0 ) {
				numInDepth = Math.pow(2,  ++depth);
				System.out.println();
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {

		BSTreeRecursive bst = new BSTreeRecursive();
		bst.insertRecursively(5);
		bst.insertRecursively(3);
		bst.insertRecursively(4);
		bst.insertRecursively(1);
		bst.insertRecursively(10);
		bst.insertRecursively(16);
		bst.insertRecursively(13);
		bst.insertRecursively(8);
		bst.insertRecursively(9);

		System.out.println("Print...");
		bst.print();

		System.out.println( "Max of tree is : " + bst.findMax() );

		bst.insertRecursively(23);
		System.out.println( "Max of tree after adding 23 is : " + bst.findMax() );

		bst.insertRecursively(19);
		System.out.println( "Max of tree after adding 19 is : " + bst.findMax() );
		bst.print();

		System.out.println("Search 5, which is root of the tree");
		bst.search(5);
		System.out.println("Search 13");
		bst.search(13);
		System.out.println("Search 17, which is not in the tree");
		bst.search(17);

//		bst.remove(9);
//
//		System.out.println("Print...");
//		bst.print();
//
//		bst.remove(5);
//		System.out.println("Print...");
//		bst.print();
//
//		bst.remove(3);
//		System.out.println("Print...");
//		bst.print();
//
//		bst.remove(10);
//		System.out.println("Print...");
//		bst.print();
//
//		bst.remove(19);
//		System.out.println("Print...");
//		bst.print();
//
//		int[] aa = {4,13,16,23,1,8};
//		for( int i=0; i<aa.length; i++) {
//			bst.remove(aa[i]);
//			bst.print();
//		}

		System.out.println("Print...");
		bst.print();
		
		System.out.println("Print in a pretty way");
		TreePrinter.print(bst.root);
	}
}