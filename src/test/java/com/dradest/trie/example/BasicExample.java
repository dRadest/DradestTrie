package com.dradest.trie.example;

import com.dradest.trie.BitTrie;
import com.dradest.trie.CharTrie;

/**
 * Main class for testing functionality of Trie data structures
 * @author DRad
 * @version 0.1.0
 * @since 01-Feb-2019
 */
public class BasicExample {
	// examples of using Trie data structures
	public static void main(String[] args) {
		/* using BitTrie */
		BitTrie bt = new BitTrie();
		// check if it's empty
		System.out.println("is empty? " + bt.isEmpty());
		// insert a few numbers
		bt.insert(5);
		bt.insert(7);
		bt.insert(9);
		// check if it's empty again
		System.out.println("is empty? " + bt.isEmpty());
		// try containsKey() method
		System.out.println("contains 5? " + bt.containsKey(5));
		System.out.println("contains 6? " + bt.containsKey(6));
		// remove existing key
		bt.remove(5);
		System.out.println("is empty? " + bt.isEmpty());
		System.out.println("contains 5? " + bt.containsKey(5));
		
		/* using CharTrie */
		CharTrie ct = new CharTrie();
		// check if it's empty
		System.out.println("is empty? " + ct.isEmpty());
		// insert a few words
		ct.insert("cat");
		ct.insert("bat");
		ct.insert("kitchen");
		// check if it's empty again
		System.out.println("is empty? " + ct.isEmpty());
		// check if it contains keys
		System.out.println("contains cat? " + ct.containsKey("cat"));
		System.out.println("contains scat? " + ct.containsKey("scat"));
		// remove existing key
		ct.remove("cat");
		System.out.println("is empty? " + ct.isEmpty());
		System.out.println("contains cat? " + ct.containsKey("cat"));

	}

}
