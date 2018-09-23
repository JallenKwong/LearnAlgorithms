package com.lun.algorithms4th.c3.searching;

/**
 * Symbol table
 * @author JK
 *
 */
public interface BaseST<Key, Value> {
	void put(Key key, Value value);
	Value get(Key key);
	void delete(Key key);
}
