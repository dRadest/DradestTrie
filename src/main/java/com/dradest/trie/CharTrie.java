package com.dradest.trie;

/**
 * Trie data structure that stores words
 * made up of English lowercase letters
 * @version 1.0
 */
public class CharTrie {
    /**
     * Size of the alphabet
     * {@value #ALPHABET_SIZE}
     */
    public static final int ALPHABET_SIZE = 26; 
      
    /**
     * Class for creating Trie node
     */
    static class TrieNode { 
        TrieNode[] children = new TrieNode[ALPHABET_SIZE]; 
       
        // indicates whether this node represents the end of a word 
        boolean wordEnd; 
        /**
         * Constructs a node <b>not</b> marked as a word end
         */
        TrieNode(){ 
        	// initialize wordEnd to false
            this.wordEnd = false;
            // initialize all children to null
            for (int i = 0; i < ALPHABET_SIZE; i++) { 
                this.children[i] = null; 
            }
        } 
    }; 
    
    /**
     * Root node of the CharTrie
     */
    static TrieNode root;  
    /**
     * Creates an instance of CharTrie 
     */
    public CharTrie() {
    	root = new TrieNode();
    }
    
    /**
     * Check if the CharTrie is empty
     * @return true if it is, false otherwise
     */
    public boolean isEmpty() {
    	// if only one child of the root node not null
    	// CharTrie not empty
    	for(int i=0; i<ALPHABET_SIZE; i++) {
    		if(root.children[i] != null) {
    			return false;
    		}
    	}
    	return true;
    }
    
    /**
     * Check if the given node is a prefix to any word
     * @param node to check
     * @return true if it is, false otherwise
     */
    private boolean isPrefix(TrieNode node) {
    	for(int i=0; i<ALPHABET_SIZE; i++) {
    		if(node.children[i] != null) {
    			return true;
    		}
    	}
    	return false;
    }
      
    /**
     * Insert the key into the CharTrie
     * @param key word to insert
     */
    public void insert(String key) { 
        int level; 
        int length = key.length(); 
        int index; // to get the char position in children array
       
        // used for traversing the trie
        TrieNode temp = root; 
       
        for (level = 0; level < length; level++) { 
            index = key.charAt(level) - 'a'; 
            if (temp.children[index] == null) 
                temp.children[index] = new TrieNode(); 
            // advance
            temp = temp.children[index]; 
        } 
       
        // mark last node as one that ends the word 
        temp.wordEnd = true; 
    } 
       
    /**
     * Check if the key is present in the CharTrie
     * @param key string to check
     * @return true if it is present, false otherwise
     */
    public boolean containsKey(String key) { 
        int level; 
        int length = key.length(); 
        int index; 
        // used for traversing the trie
        TrieNode temp = root; 
       
        for (level = 0; level < length; level++) {
        	// get index of a character at the current level
            index = key.charAt(level) - 'a'; 
       
            // char not present among children
            if (temp.children[index] == null) {
            	return false;
            } 
       
            // advance
            temp = temp.children[index]; 
        } 
       
        // last node shouldn't be null and should be marked as an end of a word
        return (temp != null && temp.wordEnd); 
    } 
    
    /**
     * Utility method to recursively remove the key word from the CharTrie
     * @param node idicates the node that is being processed
     * @param key string value that is being removed
     * @param depth indicates the depth of the CharTrie the process is currently at
     * @return modified node
     */
    private TrieNode removeUtil(TrieNode node, String key, int depth) {
    	// base cases
    	if(node == null) {
    		return null;
    	}
      
        // at the last character of the key 
        if (depth == key.length()) { 
        	// remove the end of word marker
        	if(node.wordEnd) {
        		node.wordEnd = false;
        	}
        	// current node not a prefix of any other word
        	if(!isPrefix(node)) {
        		node = null;
        	}
      
            return root; 
        } 

        // recur for the child at the index of a current character
        int index = key.charAt(depth) - 'a'; 
        node.children[index] = removeUtil(node.children[index], key, depth + 1); 
      
        // node doesn't have any children and is not the end of a word
        if (!isPrefix(node) && node.wordEnd == false) { 
            node = null;
        } 
      
        return node; 
    }

    /**
     * Method to remove the word from the CharTrie
     * @param key string value to remove
     */
    public void remove(String key) {
    	// proceed only if the trie is not empty and it contains the key
    	if(!isEmpty() && containsKey(key)) {
    		removeUtil(root, key, 0);
    	}
    }

}
