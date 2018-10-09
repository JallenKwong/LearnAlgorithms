package com.lun.algorithms4th.c2.sorting;

/**
 * 来自I2A
 * @author JK
 *
 */
public class Insertion2 extends AbstractSorting{

	@Override
	public void sort(Comparable[] a) {
		for(int i = 1; i < a.length; i++) {
			Comparable key = a[i];
			
			int j = i - 1;
			while( j >= 0 && less(key, a[j])) {
				a[j + 1] = a[j];
				j--;
			}
			a[j + 1] = key;
		}
	}
	
}
