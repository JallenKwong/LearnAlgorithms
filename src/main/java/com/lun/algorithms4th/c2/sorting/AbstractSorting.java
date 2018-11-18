package com.lun.algorithms4th.c2.sorting;

public abstract class AbstractSorting {

	public abstract void sort(Comparable[] a);

	protected boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	protected void exchange(Comparable[] a, int i, int j) {
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	protected void printArray(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}

	public static void main(String[] args) {

		// 制造100个[0, 100)范围内的整数
		for (int i = 0; i < 100; i++) {
			System.out.print((int) (100 * Math.random()) + " ");
		}
	}

}
