package com.lun.algorithms4th.c2.sorting;

public class Bubble extends AbstractSorting{

	@Override
	public void sort(Comparable[] a) {
		for(int i = 0; i < a.length ;i++ ) {
			for(int j = 0 ; j < a.length - 1 - i; j++) {
				if(less(a[j + 1], a[j])) {
					exchange(a, j + 1, j);
				}
			}
		}
	}
	
}
