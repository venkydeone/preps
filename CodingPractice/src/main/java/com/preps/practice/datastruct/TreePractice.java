package com.preps.practice.datastruct;

import java.util.Arrays;
import java.util.LinkedList;

public class TreePractice {
	
	/**
	 * IN-ORDER Traversal on Binary Search Tree provides Sorted value in ASCENDING
	 * @param tree
	 */
	public void inOrderTraversal(){
		Tree newTree = getTree();
		printInOrderTree(newTree);
		System.out.println("");
	}
	private void printInOrderTree(Tree tree){
		if(tree==null)
			return;
		printInOrderTree(tree.getLeftTree());
		System.out.print(tree.getNode() +"\t");
		printInOrderTree(tree.getRightTree());
	}

	/**
	 * REVERSE IN-ORDER Traversal on Binary Search Tree outputs Sorted value in DESCENDING
	 * @param tree
	 */
	public void revInOrderTraversal(){
		Tree newTree = getTree();
		printRevInOrderTree(newTree);
		System.out.println("");
	}
	private void printRevInOrderTree(Tree tree){
		if(tree==null)
			return;
		printRevInOrderTree(tree.getRightTree());
		System.out.print(tree.getNode() +"\t");
		printRevInOrderTree(tree.getLeftTree());
	}

	
	public void preOrderTraversal(){
		Tree newTree = getTree();
		printPreOrderTree(newTree);
		System.out.println("");
	}
	
	private void printPreOrderTree(Tree tree){
		if(tree==null)
			return;
		System.out.print(tree.getNode()+"\t");
		printPreOrderTree(tree.getLeftTree());
		printPreOrderTree(tree.getRightTree());
	}
	
	public void postOrderTraversal(){
		Tree newTree = getTree();
		printPostOrderTree(newTree);
		System.out.println("");
	}
	
	private void printPostOrderTree(Tree tree){
		if(tree==null)
			return;
		printPostOrderTree(tree.getLeftTree());
		printPostOrderTree(tree.getRightTree());
		System.out.print(tree.getNode()+"\t");
	}
	
	public void levelOrderTraversal(){
		Tree newTree = new Tree("D", 
				new Tree("B",new Tree("A"),new Tree("C")), 
				new Tree("F"));
		int level = getTreeLevel(newTree);
		for(int i=1; i<=level; i++){
			printLevelOrderTree(newTree,i);
		}
		System.out.println("");
	}
	private Tree getTree() {
		Tree newTree = new Tree("D", 
								new Tree("B",new Tree("A"),new Tree("C")), 
								new Tree("F",new Tree("E"),new Tree("G")));
		return newTree;
	}
	
	private int getTreeLevel(Tree newTree) {
		/*if(newTree==null){
			return 0;
		}else{
			int leftLevelHeight = getTreeLevel(newTree.getLeftTree());
			int rightLevelHeight = getTreeLevel(newTree.getRightTree());
			
			if(leftLevelHeight>rightLevelHeight){
				return leftLevelHeight+1;
			}else{
				return rightLevelHeight+1;
			}
		}*/
		if(newTree==null){
			return 0;
		}
		
		return Math.max(getTreeLevel(newTree.getLeftTree()), getTreeLevel(newTree.getRightTree()))+1;
	}
	private void printLevelOrderTree(Tree tree, int level){
		if(tree==null)
			return;
		if(level==1){
			System.out.print(tree.getNode()+"\t");
		}else if(level>1){
			printLevelOrderTree(tree.getLeftTree(),level-1);
			printLevelOrderTree(tree.getRightTree(),level-1);
		}
	}
	
	public static void printTraversal(){
		TreePractice practice = new TreePractice();
		practice.inOrderTraversal();
		practice.revInOrderTraversal();
		practice.preOrderTraversal();
		practice.postOrderTraversal();
		practice.levelOrderTraversal();
	}
	
	public static void main(String[] args) {
		TreeNode newTree = new TreeNode(1, 
				new TreeNode(2,new TreeNode(4),new TreeNode(5)), 
				new TreeNode(3,new TreeNode(6),new TreeNode(7)));
		System.out.println(newTree);
		printPath(newTree, new int[10], 0);
		System.out.println(invertTreeRec(newTree));
		
	}

	static class Tree{
		@Override
		public String toString() {
			return "Tree :" +this.node;
		}
		private String node;
		private Tree leftTree;
		private Tree rightTree;
		public String getNode() {
			return node;
		}
		public Tree getLeftTree() {
			return leftTree;
		}
		public Tree getRightTree() {
			return rightTree;
		}

		public Tree(String node) {
			super();
			this.node = node;
			this.leftTree = null;
			this.rightTree = null;
		}
		public Tree(String node, Tree leftTree, Tree rightTree) {
			super();
			this.node = node;
			this.leftTree = leftTree;
			this.rightTree = rightTree;
		}
	}
	
	
	/**
	 * http://www.geeksforgeeks.org/root-to-leaf-path-sum-equal-to-a-given-number/
	 * @param root
	 * @param sum
	 * @return
	 */
    static boolean hasPathSum(TreeNode root, int sum) {
    	if(root==null){
            return sum==0;
        }
        
        if(sum==root.val && root.left==null && root.right==null){
        	return true;
        }else if(hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val)){
            	return true;
        }
        return false;
    }
    
    
    /**
	 * http://www.geeksforgeeks.org/given-a-binary-tree-print-out-all-of-its-root-to-leaf-paths-one-per-line/
	 * @param root
	 * @param sum
	 * @return
	 */
    static void printPath(TreeNode root, int[] path, int pathLen) {
    	if(root==null){
            return ;
        }
    	
        path[pathLen] = root.val;
        pathLen++;
        
        if(root.left==null && root.right==null){
        	System.out.println(Arrays.toString(path));
        }else{
        	printPath(root.left, path, pathLen);
        	printPath(root.right, path, pathLen);
        }
    }
    
    /**
     * https://leetcode.com/problems/invert-binary-tree/ 
     * @param root
     * @return
     */
    static TreeNode invertTreeRec(TreeNode root){
    	if(root!=null)
    		invertTreeHelper(root);
    	
    	return root;
    }
	private static void invertTreeHelper(TreeNode root) {
		TreeNode temp = root.left;
    	root.left = root.right;
    	root.right = temp;
    	
    	if(root.left!=null)
    		invertTreeHelper(root.left);
    	if(root.right!=null)
    		invertTreeHelper(root.right);
	}
	
	static TreeNode invertTree(TreeNode root) {
	    LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
	 
	    if(root!=null){
	        queue.add(root);
	    }
	 
	    while(!queue.isEmpty()){
	        TreeNode p = queue.poll();
	        if(p.left!=null)
	            queue.add(p.left);
	        if(p.right!=null)
	            queue.add(p.right);
	 
	        TreeNode temp = p.left;
	        p.left = p.right;
	        p.right = temp;
	    }
	 
	    return root;    
	}
	
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}

		TreeNode(int x, TreeNode left, TreeNode right) {
			this.val = x;
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			BTreePrinter.printNode(this);
			return "Tree :" + this.val;
		}
	}
}

