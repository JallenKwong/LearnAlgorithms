package com.lun.algorithms4th.c2.sorting;

public class Insertion extends AbstractSorting {

	@Override
	public void sort(Comparable[] a) {
		for(int i = 1; i < a.length; i++) {
			for(int j = i;j >= 1 && less(a[j], a[j - 1]); j--) {
				exchange(a, j - 1, j);
			}
		}
	}

}
