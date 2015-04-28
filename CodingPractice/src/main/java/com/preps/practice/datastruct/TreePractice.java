package com.preps.practice.datastruct;

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
		TreePractice.printTraversal();
		
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
	
    static boolean hasPathSum(TreeNode root, int sum) {
    	if(root==null){
            return false;
        }
        
        if(sum==root.val && root.left==null && root.right==null){
        	return true;
        }else if(hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val)){
            	return true;
        }
        return false;
    }
	
	
	public static class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
	  }
}

