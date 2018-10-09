package com.lun.algorithms4th.c2.sorting;

import org.junit.Assert;
import org.junit.Test;

public class MergeX extends Merge{
	
	private static final int CUTOFF = 7;  // cutoff to insertion sort
	
	public void insertionSort(Comparable[] a, int left, int right) {
		for(int i = left + 1 ;i <= right; i++) {
			for(int j = i ; j >= left + 1 && less(a[j], a[j - 1]); j--) {
				exchange(a, j - 1, j);
			}
		}
	}
	
	protected void sort(Comparable[] a, int left, int right) {
		
		if(left + CUTOFF >= right) {
			insertionSort(a, left, right);
			return;
		} 
		int mid = (left + right) / 2;
		
		sort(a, left, mid);
		sort(a, mid + 1, right);
		
		merge(a, left, mid, right);
		
	}
	
	@Test
	public void testInsertSort() {
		Integer[] unsortedArray = createUnsortedArray();
		insertionSort(unsortedArray, 0, unsortedArray.length - 1);
//		printArray(unsortedArray);
		Assert.assertTrue(isSorted(unsortedArray));
	}
}
