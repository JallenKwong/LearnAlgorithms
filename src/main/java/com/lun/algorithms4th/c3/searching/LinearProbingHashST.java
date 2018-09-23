package com.lun.algorithms4th.c3.searching;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.lun.algorithms4th.util.Util;


/**
 * The {@code LinearProbingHashST} class represents a symbol table of generic
 * key-value pairs. It supports the usual <em>put</em>, <em>get</em>,
 * <em>contains</em>, <em>delete</em>, <em>size</em>, and <em>is-empty</em>
 * methods. It also provides a <em>keys</em> method for iterating over all of
 * the keys. A symbol table implements the <em>associative array</em>
 * abstraction: when associating a value with a key that is already in the
 * symbol table, the convention is to replace the old value with the new value.
 * Unlike {@link java.util.Map}, this class uses the convention that values
 * cannot be {@code null}—setting the value associated with a key to
 * {@code null} is equivalent to deleting the key from the symbol table.
 * <p>
 * This implementation uses a linear probing hash table. It requires that the
 * key type overrides the {@code equals()} and {@code hashCode()} methods. The
 * expected time per <em>put</em>, <em>contains</em>, or <em>remove</em>
 * operation is constant, subject to the uniform hashing assumption. The
 * <em>size</em>, and <em>is-empty</em> operations take constant time.
 * Construction takes constant time.
 * <p>
 * For additional documentation, see
 * <a href="https://algs4.cs.princeton.edu/34hash">Section 3.4</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne. For other
 * implementations, see {@link BaseST}, {@link BinarySearchST},
 * {@link SequentialSearchST}, {@link BST}, {@link RedBlackBST}, and
 * {@link SeparateChainingHashST},
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class LinearProbingHashST<Key, Value> implements BaseST<Key, Value>{
	private static final int INIT_CAPACITY = 4;

	private int numOfKvPairs; // number of key-value pairs in the symbol table
	private int tableSize; // size of linear probing table
	private Key[] keys; // the keys
	private Value[] vals; // the values

	/**
	 * Initializes an empty symbol table.
	 */
	public LinearProbingHashST() {
		this(INIT_CAPACITY);
	}

	/**
	 * Initializes an empty symbol table with the specified initial capacity.
	 *
	 * @param capacity
	 *            the initial capacity
	 */
	@SuppressWarnings("unchecked")
	public LinearProbingHashST(int capacity) {
		tableSize = capacity;
		numOfKvPairs = 0;
		keys = (Key[]) new Object[tableSize];
		vals = (Value[]) new Object[tableSize];
	}

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 *
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size() {
		return numOfKvPairs;
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
	 * Returns true if this symbol table contains the specified key.
	 *
	 * @param key
	 *            the key
	 * @return {@code true} if this symbol table contains {@code key}; {@code false}
	 *         otherwise
	 * @throws IllegalArgumentException
	 *             if {@code key} is {@code null}
	 */
	public boolean contains(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}

	// hash function for keys - returns value between 0 and M-1
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % tableSize;
	}

	// resizes the hash table to the given capacity by re-hashing all of the keys
	private void resize(int capacity) {
		LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(capacity);
		for (int i = 0; i < tableSize; i++) {
			if (keys[i] != null) {
				temp.put(keys[i], vals[i]);
			}
		}
		keys = temp.keys;
		vals = temp.vals;
		tableSize = temp.tableSize;
	}

	/**
	 * Inserts the specified key-value pair into the symbol table, overwriting the
	 * old value with the new value if the symbol table already contains the
	 * specified key. Deletes the specified key (and its associated value) from this
	 * symbol table if the specified value is {@code null}.
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
			throw new IllegalArgumentException("first argument to put() is null");

		if (val == null) {
			delete(key);
			return;
		}

		// double table size if 50% full
		if (numOfKvPairs >= tableSize / 2)
			resize(2 * tableSize);

		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % tableSize) {
			if (keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
		}
		keys[i] = key;
		vals[i] = val;
		numOfKvPairs++;
	}

	/**
	 * Returns the value associated with the specified key.
	 * 
	 * @param key
	 *            the key
	 * @return the value associated with {@code key}; {@code null} if no such value
	 * @throws IllegalArgumentException
	 *             if {@code key} is {@code null}
	 */
	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to get() is null");
		for (int i = hash(key); keys[i] != null; i = (i + 1) % tableSize)
			if (keys[i].equals(key))
				return vals[i];
		return null;
	}

	/**
	 * Removes the specified key and its associated value from this symbol table (if
	 * the key is in this symbol table).
	 *
	 * @param key
	 *            the key
	 * @throws IllegalArgumentException
	 *             if {@code key} is {@code null}
	 */
	public void delete(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to delete() is null");
		if (!contains(key))
			return;

		// find position i of key
		int i = hash(key);
		while (!key.equals(keys[i])) {
			i = (i + 1) % tableSize;
		}

		// delete key and associated value
		keys[i] = null;
		vals[i] = null;

		// rehash all keys in same cluster
		i = (i + 1) % tableSize;
		while (keys[i] != null) {
			// delete keys[i] an vals[i] and reinsert
			Key keyToRehash = keys[i];
			Value valToRehash = vals[i];
			keys[i] = null;
			vals[i] = null;
			numOfKvPairs--;
			put(keyToRehash, valToRehash);
			i = (i + 1) % tableSize;
		}

		numOfKvPairs--;

		// halves size of array if it's 12.5% full or less
		if (numOfKvPairs > 0 && numOfKvPairs <= tableSize / 8)
			resize(tableSize / 2);

		assert check();
	}

	/**
	 * Returns all keys in this symbol table as an {@code Iterable}. To iterate over
	 * all of the keys in the symbol table named {@code st}, use the foreach
	 * notation: {@code for (Key key : st.keys())}.
	 *
	 * @return all keys in this symbol table
	 */
	public Iterable<Key> keys() {
		LinkedList<Key> queue = new LinkedList<Key>();
		for (int i = 0; i < tableSize; i++)
			if (keys[i] != null)
				queue.add(keys[i]);
		return queue;
	}

	// integrity check - don't check after each put() because
	// integrity not maintained during a delete()
	private boolean check() {

		// check that hash table is at most 50% full
		if (tableSize < 2 * numOfKvPairs) {
			System.err.println("Hash table size m = " + tableSize + "; array size n = " + numOfKvPairs);
			return false;
		}

		// check that each key in table can be found by get()
		for (int i = 0; i < tableSize; i++) {
			if (keys[i] == null)
				continue;
			else if (get(keys[i]) != vals[i]) {
				System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + vals[i]);
				return false;
			}
		}
		return true;
	}

	/**
	 * Unit tests the {@code LinearProbingHashST} data type.
	 *
	 * @param args
	 *            the command-line arguments
	 */
	public static void main(String[] args) {
		LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>();
		List<String> list = Util.readLines2("tiny.txt");
		int i = 0;
		for (String str : list) {
			st.put(str, i++);
		}
		for (String s : st.keys())
			System.out.println(s + " " + st.get(s));
	}
}
