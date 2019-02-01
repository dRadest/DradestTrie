package com.dradest.trie;

/**
 * Trie data structure that stores binary values of unsigned integer in 31 bits
 * @version 1.0
*/
public class BitTrie {
	private static final int BIT_SIZE = 31;
	
    /**
     * Class for creating Trie node
     */
    static class TrieNode{
        int value;
        TrieNode[] child = new TrieNode[2];
        /**
         * Constructs a node with value -1
         */
        public TrieNode(){
            this.value = -1;
            this.child[0] = null;
            this.child[1] = null;
        }
        /**
         * Constructs a node with value x
         * @param x node value initialized to x
         */
        public TrieNode(int x) {
        	this.value = x;
            this.child[0] = null;
            this.child[1] = null;
        }
    };
    
    /**
     * Root node of the BitTrie
     */
    static TrieNode root;
    /**
     * Creates instance of BitTrie with root node's value initialized to 0
     */
    public BitTrie() {
    	root = new TrieNode(0);
    }
    
    /**
     * Inserts a key into BitTrie
     * @param key unsigned integer value
     */
    public void insert(int key) {
        TrieNode temp = root;

        // Storing 31 bits as integer representation
        for (int i = BIT_SIZE; i >= 0; i--) {
            // integer value of current bit
            int current_bit = (key & (1 << i)) >= 1 ? 1 : 0;
            
            // New node required
            if (temp.child[current_bit] == null){
                temp.child[current_bit] = new TrieNode();
            }

            // Traversing the trie
            temp = temp.child[current_bit];
        }
        // Assigning value to the leaf Node
        temp.value = key;
    }
    
    /**
     * Check if the BitTrie contains a key
     * @param key unsigned integer
     * @return true if the key is present, false otherwise
     */
    public boolean containsKey(int key) {
        TrieNode temp = root;

        // Checking for all bits in integer range
        for (int i = BIT_SIZE; i >= 0; i--) {
            // Current bit in the number
            int current_bit = (key & (1 << i)) >= 1 ? 1 : 0;

            // no child node for current bit
            if (temp.child[current_bit] == null){
                return false;
            }
            
            // traverse trie for the current bit
            temp = temp.child[current_bit];
        }

        // end of the trie reached
        return true;
    }
    
    /**
     * Check if the BitTrie is empty
     * @return true if the BitTrie is empty, false otherwise
     */
    public boolean isEmpty() {
    	if(root.child[0] == null && root.child[1] == null) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Utility method to recursively remove key and all other empty nodes from the BitTrie
     * @param node Node to start recursion from
     * @param key to remove
     * @param bit_size Bit that is being examined
     * @return modified node
     */
    private TrieNode removeUtil(TrieNode node, int key, int bit_size) {
    	// base cases
    	if(node == null) {
    		return null;
    	}
    	// process leaf node
    	if(bit_size == -1) {
    		// contains the key we want to remove
    		if(node.value == key) {
    			node = null;
    		// has no children and value is -1, indicating it's not any other key stored
    		}else if(node.child[0]==null && node.child[1]==null && node.value == -1) {
    			node = null;
    		}
    		return node;
    	}
    	// get the current bit we're looking at
    	int current_bit = (key & (1<<bit_size)) >= 1 ? 1 : 0;
    	// recursively call removeUtil for the child of current node
    	node.child[current_bit] = removeUtil(node.child[current_bit], key, bit_size-1);
    	// node has no children and it doesn't store any key
		if(node.child[0]==null && node.child[1]==null && node.value == -1) {
			node = null;
		}
    	return node;
    }
    /**
     * Method to remove the key from the BitTrie
     * @param key to remove
     */
    public void remove(int key) {
    	// proceed only if the trie is not empty and it contains the key
    	if(!isEmpty() && containsKey(key)) {
    		removeUtil(root, key, BIT_SIZE);
    	}
    }
}
