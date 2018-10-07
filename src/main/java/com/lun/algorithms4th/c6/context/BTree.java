package com.lun.algorithms4th.c6.context;

public class BTree<Key extends Comparable<Key>, Value> {
	// max children per B-tree node = M-1
	// (must be even and greater than 2)
	private static final int M = 4;

	private Node root; // root of the B-tree
	private int height; // height of the B-tree
	private int numOfPair; // number of key-value pairs in the B-tree

	// helper B-tree node data type
	private static final class Node {
		private int numOfChild; // number of children
		private Entry[] children = new Entry[M]; // the array of children

		// create a node with k children
		private Node(int numOfChild) {
			this.numOfChild = numOfChild;
		}
	}

	// internal nodes: only use key and next
	// external nodes: only use key and value
	private static class Entry {
		private Comparable key;
		private final Object val;
		private Node next; // helper field to iterate over array entries

		public Entry(Comparable key, Object val, Node next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}

	/**
	 * Initializes an empty B-tree.
	 */
	public BTree() {
		root = new Node(0);
	}

	/**
	 * Returns true if this symbol table is empty.
	 * 
	 * @return {@code true} if this symbol table is empty; {@code false} otherwise
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 * 
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size() {
		return numOfPair;
	}

	/**
	 * Returns the height of this B-tree (for debugging).
	 *
	 * @return the height of this B-tree
	 */
	public int height() {
		return height;
	}

	/**
	 * Returns the value associated with the given key.
	 *
	 * @param key
	 *            the key
	 * @return the value associated with the given key if the key is in the symbol
	 *         table and {@code null} if the key is not in the symbol table
	 * @throws IllegalArgumentException
	 *             if {@code key} is {@code null}
	 */
	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to get() is null");
		return search(root, key, height);
	}

	private Value search(Node x, Key key, int height) {
		Entry[] children = x.children;

		// external node
		if (height == 0) {
			for (int j = 0; j < x.numOfChild; j++) {
				if (eq(key, children[j].key))
					return (Value) children[j].val;
			}
		}

		// internal node
		else {
			for (int j = 0; j < x.numOfChild; j++) {
				if (j + 1 == x.numOfChild || less(key, children[j + 1].key))
					return search(children[j].next, key, height - 1);
			}
		}
		return null;
	}

	/**
	 * Inserts the key-value pair into the symbol table, overwriting the old value
	 * with the new value if the key is already in the symbol table. If the value is
	 * {@code null}, this effectively deletes the key from the symbol table.
	 *
	 * @param key
	 *            the key
	 * @param val
	 *            the value
	 * @throws IllegalArgumentException
	 *             if {@code key} is {@code null}
	 */
	public void put(Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException("argument key to put() is null");
		Node u = insert(root, key, val, height);
		numOfPair++;
		if (u == null)
			return;

		// need to split root
		Node t = new Node(2);
		t.children[0] = new Entry(root.children[0].key, null, root);
		t.children[1] = new Entry(u.children[0].key, null, u);
		root = t;
		height++;
	}

	private Node insert(Node newOne, Key key, Value val, int height) {
		int j;
		Entry t = new Entry(key, val, null);

		// external node
		if (height == 0) {
			for (j = 0; j < newOne.numOfChild; j++) {
				if (less(key, newOne.children[j].key))
					break;
			}
		}

		// internal node
		else {
			for (j = 0; j < newOne.numOfChild; j++) {
				if ((j + 1 == newOne.numOfChild) || less(key, newOne.children[j + 1].key)) {
					Node u = insert(newOne.children[j++].next, key, val, height - 1);
					if (u == null)
						return null;
					t.key = u.children[0].key;
					t.next = u;
					break;
				}
			}
		}

		for (int i = newOne.numOfChild; i > j; i--)
			newOne.children[i] = newOne.children[i - 1];
		newOne.children[j] = t;
		newOne.numOfChild++;
		if (newOne.numOfChild < M)
			return null;
		else
			return split(newOne);
	}

	// split node in half
	private Node split(Node oldOne) {
		Node newOne = new Node(M / 2);
		oldOne.numOfChild = M / 2;
		for (int j = 0; j < M / 2; j++)
			newOne.children[j] = oldOne.children[M / 2 + j];
		return newOne;
	}

	/**
	 * Returns a string representation of this B-tree (for debugging).
	 *
	 * @return a string representation of this B-tree.
	 */
	public String toString() {
		return toString(root, height, "") + "\n";
	}

	private String toString(Node h, int height, String indent) {
		StringBuilder s = new StringBuilder();
		Entry[] children = h.children;

		if (height == 0) {
			for (int j = 0; j < h.numOfChild; j++) {
				s.append(indent + children[j].key + " " + children[j].val + "\n");
			}
		} else {
			for (int j = 0; j < h.numOfChild; j++) {
				if (j > 0)
					s.append(indent + "(" + children[j].key + ")\n");
				s.append(toString(children[j].next, height - 1, indent + "     "));
			}
		}
		return s.toString();
	}

	// comparison functions - make Comparable instead of Key to avoid casts
	private boolean less(Comparable k1, Comparable k2) {
		return k1.compareTo(k2) < 0;
	}

	private boolean eq(Comparable k1, Comparable k2) {
		return k1.compareTo(k2) == 0;
	}

	/**
	 * Unit tests the {@code BTree} data type.
	 *
	 * @param args
	 *            the command-line arguments
	 */
	public static void main(String[] args) {
		BTree<String, String> st = new BTree<String, String>();

		st.put("www.cs.princeton.edu", "128.112.136.12");
		st.put("www.cs.princeton.edu", "128.112.136.11");
		st.put("www.princeton.edu", "128.112.128.15");
		st.put("www.yale.edu", "130.132.143.21");
		st.put("www.simpsons.com", "209.052.165.60");
		st.put("www.apple.com", "17.112.152.32");
		st.put("www.amazon.com", "207.171.182.16");
		st.put("www.ebay.com", "66.135.192.87");
		st.put("www.cnn.com", "64.236.16.20");
		st.put("www.google.com", "216.239.41.99");
		st.put("www.nytimes.com", "199.239.136.200");
		st.put("www.microsoft.com", "207.126.99.140");
		st.put("www.dell.com", "143.166.224.230");
		st.put("www.slashdot.org", "66.35.250.151");
		st.put("www.espn.com", "199.181.135.201");
		st.put("www.weather.com", "63.111.66.11");
		st.put("www.yahoo.com", "216.109.118.65");

		System.out.println("cs.princeton.edu:  " + st.get("www.cs.princeton.edu"));
		System.out.println("hardvardsucks.com: " + st.get("www.harvardsucks.com"));
		System.out.println("simpsons.com:      " + st.get("www.simpsons.com"));
		System.out.println("apple.com:         " + st.get("www.apple.com"));
		System.out.println("ebay.com:          " + st.get("www.ebay.com"));
		System.out.println("dell.com:          " + st.get("www.dell.com"));
		System.out.println();

		System.out.println("size:    " + st.size());
		System.out.println("height:  " + st.height());
		System.out.println(st);
		System.out.println();
	}

}