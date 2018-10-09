package com.lun.algorithms4th.c2.sorting;


public class Selection extends AbstractSorting {
	
	@Override
	public void sort(Comparable[] a) {
		for(int i = 0; i < a.length; i++) {
			int minElemIndex = i;//初始最小元素索引
			for(int j = i + 1; j < a.length; j++) {
				if(less(a[j], a[minElemIndex])) {
					minElemIndex = j;
				}
			}
			exchange(a, minElemIndex, i);
		}
	}

}
