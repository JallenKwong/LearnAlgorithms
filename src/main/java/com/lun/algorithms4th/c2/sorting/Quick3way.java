package com.lun.algorithms4th.c2.sorting;

public class Quick3way extends Quick2{

	protected void sort(Comparable[] a, int left, int right) {
		if (left >= right)
			return;
		
		int lt = left, i = left + 1, gt = right;
		
		Comparable v = a[left];
		
		while(i <= gt) {
			int cmp = a[i].compareTo(v);
			
			if(cmp < 0) {
				exchange(a, lt++, i++);
			}else if(cmp > 0) {
				exchange(a, i, gt--);
			}else {
				i++;
			}
		}
		
		sort(a, left, lt - 1);
		sort(a, gt + 1, right);
	}

}
