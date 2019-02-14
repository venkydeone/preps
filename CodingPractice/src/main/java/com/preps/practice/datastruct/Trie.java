package com.preps.practice.datastruct;

public class Trie {
	
	TrieNode root;
	public Trie() {
		root = new TrieNode(' ');
    }
    
    class TrieNode{
    	public TrieNode(char c){
    		this.c = c;
    	}
    	public TrieNode(char c, boolean isWord){
    		this.c = c;
    		this.isWord = isWord;
    	}
    	char c;
    	boolean isWord;
    	TrieNode[] children = new TrieNode[26]; // Assuming all characters are lowercase only
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if(word==null||word.isEmpty())
        	return;
        
        TrieNode temp = root;
        for(char ch : word.toCharArray()){
        	if(temp.children[ch-'a']==null){
        		TrieNode newNode = new TrieNode(ch);
        		temp.children[ch - 'a'] = newNode;
        		temp = newNode;
        	}else{
        		temp = temp.children[ch - 'a'];
        	}
        }
        temp.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
       if(word==null || word.isEmpty())
    	   return false;
       
       TrieNode temp = root;
       for(char ch : word.toCharArray()){
    	   if(temp.children[ch-'a']==null)
    		   return false;
    	   else{
    		   TrieNode currentNode = temp.children[ch-'a'];
    		   temp = currentNode;
    	   }
       }
       
       return temp.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
    	if(prefix==null || prefix.isEmpty())
     	   return false;
        
        TrieNode temp = root;
        for(char ch : prefix.toCharArray()){
     	   if(temp.children[ch-'a']==null)
     		   return false;
     	   else{
     		   TrieNode currentNode = temp.children[ch-'a'];
     		   temp = currentNode;
     	   }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("apple");
		trie.insert("app");
		System.out.println(trie.search("app"));
		System.out.println(trie.search("apple"));
		System.out.println(trie.startsWith("appled"));
		System.out.println(trie.startsWith("appled"));
	}
}
