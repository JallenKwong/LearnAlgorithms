package com.lun.algorithms4th.c2.sorting;

public class Quick2X extends Quick2 {
	
	protected static final int CUTOFF = 7;  // cutoff to insertion sort
	
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
		int j = partition(a, left, right);
		sort(a, left, j - 1);
		sort(a, j + 1, right);
	}
	
}
