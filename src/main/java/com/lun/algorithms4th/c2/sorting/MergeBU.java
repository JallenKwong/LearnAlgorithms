package com.lun.algorithms4th.c2.sorting;

public class MergeBU extends Merge {
	public void sort(Comparable[] a) {
		aux = new Comparable[a.length];
		for(int sz = 1; sz < a.length; sz *= 2) {
			for(int lo = 0;lo < a.length - sz; lo += sz * 2) {
				merge(a, lo, lo + sz - 1, Math.min(lo + sz * 2 - 1, a.length - 1));
			}
		}
	}
}
