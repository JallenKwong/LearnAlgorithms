package com.lun.algorithms4th.c2.sorting;

public class Shell extends AbstractSorting {

	@Override
	public void sort(Comparable[] a) {
		int h = 1;
		while(h < a.length / 3) {
			h = 3 * h + 1;
		}
		
		while(h >= 1) {
			for(int i = h;i < a.length; i++) {
				for(int j = i; j >= h && less(a[j], a[j - h]);j-=h) {
					exchange(a, j, j - h);
				}
			}
			h /= 3;
		}
		
		
	}

}
