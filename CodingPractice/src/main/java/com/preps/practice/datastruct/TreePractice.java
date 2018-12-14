package com.preps.practice.datastruct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.preps.practice.datastruct.BTreePrinterTest.BTreePrinter;

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
	 * http://www.programcreek.com/2012/12/leetcode-solution-of-binary-tree-inorder-traversal-in-java/
	 * @param root
	 * @return
	 */
	static List<Integer> printInorderTraversalStack(TreeNode root) {
	    Stack<TreeNode> stack = new Stack<TreeNode>();
	    List<Integer> result = new ArrayList<Integer>();
	 
	    TreeNode p = root;
	    while(p!=null){
	        stack.push(p);
	        p = p.left;
	    }
	 
	    while(!stack.isEmpty()){
	        TreeNode t = stack.pop();
	        result.add(t.val);
	 
	        if(t.right!=null){
	 
	            t= t.right;
	            while(t!=null){
	                stack.push(t);
	                t=t.left;
	            }
	        }
	    }
	    return result;
	}
	
	static List<Integer> printInorderTraversalNoStack(TreeNode tree) {
	    List<Integer> result = new ArrayList<Integer>();
	    while (tree != null) {
	      if (tree.left != null) {
	        // Finds the predecessor of tree.
	    	TreeNode pre = tree.left;
	        while (pre.right != null && pre.right != tree) {
	          pre = pre.right;
	        }

	        // Processes the successor link.
	        if (pre.right != null) { // pre.right == tree
	          // Reverts the successor link if predecessor's successor is tree.
	          pre.right = null;
	          System.out.println(tree.val);
	          // @exclude
	          result.add(tree.val);
	          // @include
	          tree = tree.right;
	        } else { // If predecessor's successor is not tree.
	          pre.right = tree;
	          tree = tree.left;
	        }
	      } else {
	        System.out.println(tree.val);
	        // @exclude
	        result.add(tree.val);
	        // @include
	        tree = tree.right;
	      }
	    }
	    return result;
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
	
	/**
	 * http://www.programcreek.com/2012/12/leetcode-solution-for-binary-tree-preorder-traversal-in-java/
	 * @param root
	 * @return
	 */
	static ArrayList<Integer> printPreOrderStack(TreeNode root) {
        ArrayList<Integer> returnList = new ArrayList<Integer>();
 
        if(root == null)
            return returnList;
 
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
 
        while(!stack.empty()){
            TreeNode n = stack.pop();
            returnList.add(n.val);
 
            if(n.right != null){
                stack.push(n.right);
            }
            if(n.left != null){
                stack.push(n.left);
            }
 
        }
        return returnList;
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
	
	/**
	 * Below algo runs a reverse of Post order traversal - Root , right and left but saves values in linked list in reverse fashion; thereby ensures Post order 
	 * @param root
	 * @return
	 */
	static List<Integer> printPostOrderStack(TreeNode root) {
		LinkedList<Integer> ans = new LinkedList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		
		if (root == null) 
			return ans;
		stack.push(root);

		while (!stack.isEmpty()) {
			TreeNode cur = stack.pop();
			ans.addFirst(cur.val);
			if (cur.left != null) {
				stack.push(cur.left);
			}
			if (cur.right != null) {
				stack.push(cur.right);
			} 
		}
		return ans;
	}
	
	/**
	 * This approach isn't O(n); Refer O(n) approach here : {@link TreePractice#levelOrderTraversalOptimal()}
	 */
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
	
	/**
	 * http://www.geeksforgeeks.org/print-level-order-traversal-line-line/
	 */
	public void levelOrderTraversalOptimal(){
		Tree newTree = new Tree("D", 
				new Tree("B",new Tree("A"),new Tree("C")), 
				new Tree("F"));
		Deque<Tree> data = new LinkedList<Tree>();
		//Add the root
		data.add(newTree);
		
		while(true){
			int size = data.size();
			if(size==0)
				break;
			while(size>0){
				Tree tree = data.pop();
				System.out.print(tree.node +"\t");
				if(tree.getLeftTree()!=null){
					data.add(tree.getLeftTree());
				}
				if(tree.getRightTree()!=null){
					data.add(tree.getRightTree());
				}
				size--;
			}
			System.out.println("");
		}
	}
	
	private Tree getTree() {
		Tree newTree = new Tree("D", 
								new Tree("B",new Tree("A"),new Tree("C")), 
								new Tree("F",new Tree("E"),new Tree("G")));
		return newTree;
	}
	
	static int getTreeLevel(Tree newTree) {
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
		System.out.println(printPreOrderStack(getSampleTree()));
		System.out.println(printPostOrderStack(getSampleTree()));
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
	
	
	
	/**
	 * Find the diameter of the tree
	 * @param root
	 * @return
	 */
	static int diameter(TreeNode root)
    {
        /* base case if tree is empty */
        if (root == null)
            return 0;
 
        /* get the height of left and right sub trees */
        int lheight = height(root.left);
        int rheight = height(root.right);
 
        /* get the diameter of left and right subtrees */
        int ldiameter = diameter(root.left);
        int rdiameter = diameter(root.right);
 
        /* Return max of following three
          1) Diameter of left subtree
         2) Diameter of right subtree
         3) Height of left subtree + height of right subtree + 1 */
        return Math.max(lheight + rheight + 1,
                        Math.max(ldiameter, rdiameter));
 
    }
	
	static int height(TreeNode node)
    {
        /* base case tree is empty */
        if (node == null)
            return 0;
 
        /* If tree is not empty then height = 1 + max of left
           height and right heights */
        return (1 + Math.max(height(node.left), height(node.right)));
    }

	/**
	 * Recover BST with swapped nodes
	 * https://discuss.leetcode.com/topic/3988/no-fancy-algorithm-just-simple-and-powerful-in-order-traversal
	 */
	static class RecoverBST {
		TreeNode firstElement = null;
	    TreeNode secondElement = null;
	    TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);
	    
	    public void recoverTree(TreeNode root) {
	        
	        // In order traversal to find the two elements
	        traverse(root);
	        
	        // Swap the values of the two nodes
	        int temp = firstElement.val;
	        firstElement.val = secondElement.val;
	        secondElement.val = temp;
	    }
	    
	    private void traverse(TreeNode root) {
	        
	        if (root == null)
	            return;
	            
	        traverse(root.left);

	        if (firstElement == null && prevElement.val >= root.val) {
	            firstElement = prevElement;
	        }
	    
	        if (firstElement != null && prevElement.val >= root.val) {
	            secondElement = root;
	        }        
	        prevElement = root;

	        traverse(root.right);
	    }

	}
	
	/**
	 * http://www.programcreek.com/2014/04/leetcode-binary-search-tree-iterator-java/
	 *
	 */
	static class BSTIterator {
		Stack<TreeNode> stack;
		 
		public BSTIterator(TreeNode root) {
			stack = new Stack<TreeNode>();
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
		}
	 
		public boolean hasNext() {
			return !stack.isEmpty();
		}
	 
		public int next() {
			TreeNode node = stack.pop();
			int result = node.val;
			if (node.right != null) {
				node = node.right;
				while (node != null) {
					stack.push(node);
					node = node.left;
				}
			}
			return result;
		}
	}
	
	static boolean isValidBST(TreeNode root) {
	if(root == null || (root.left==null && root.right == null))
            return true;
       return bstHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE); 
    }
    
    static boolean bstHelper(TreeNode root, int min, int max){
    	if(root == null)
            return true;
        if(root.val <= min || root.val >= max){
            return false;
        }else{
            return bstHelper(root.left, min, root.val) && bstHelper(root.right, root.val, max);
        }
    }
	
	public static class TreeNode {
		public int val;
		public void setVal(int val) {
			this.val = val;
		}

		public void setLeft(TreeNode left) {
			this.left = left;
		}

		public void setRight(TreeNode right) {
			this.right = right;
		}

		public int getVal() {
			return val;
		}

		public TreeNode getLeft() {
			return left;
		}

		public TreeNode getRight() {
			return right;
		}

		public TreeNode left;
		public TreeNode right;

		public TreeNode(int x) {
			val = x;
		}

		public TreeNode(int x, TreeNode left, TreeNode right) {
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
	
	static class Trie{
		static TrieNode[] root = new TrieNode[26];
		
		static void addWord(String s){
			if(s==null||s.isEmpty())
				return;
			
			TrieNode[] pre = root;
			for(int i=0; i<s.length(); i++){
				char c = s.charAt(i);
				TrieNode n = pre[c-'a'];
				if(n==null){
					n = new TrieNode(c);
					pre[c-'a'] = n;
				}
				if(i==s.length()-1){
					n.isWord=true;
				}
				pre=n.trieNodes;
			}
		}
		
		
		static String findWord(String word){
			if(word==null||word.isEmpty()){
				return null;
			}
			StringBuilder sb = new StringBuilder();
			TrieNode[] next = root;
			for(char c : word.toCharArray()){
				TrieNode t = next[c-'a'];
				if(t!=null){
					sb.append(c);
					if(t.isWord){
						return sb.toString();
					}
					next = t.trieNodes;
				}else{
					return null;
				}
			}
			return null;
		}
		
	}
	
	static class TrieNode{
		TrieNode[] trieNodes;
		char c;
		boolean isWord;
		
		TrieNode(char c){
			trieNodes= new TrieNode[26];
			this.c = c;
		}
	}
	
	
	static TreeNode getSampleTree(){
		return new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3, new TreeNode(6), new TreeNode(7)));
	}
}

