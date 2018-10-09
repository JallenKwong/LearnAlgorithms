package com.lun.algorithms4th.c2.sorting.kendalltau;

public class KendallTau {
	private static long counter = 0;

	public static long distance(int[] a, int[] b) {
		if (a.length != b.length) {
			throw new IllegalArgumentException("Array dimensions disagree");
		}
		int N = a.length;
		int[] aIndex = new int[N];// 记录a数组的索引
		for (int i = 0; i < N; i++) {
			aIndex[a[i]] = i;
		}
		int[] bIndex = new int[N];// b数组引用a数组的索引
		for (int i = 0; i < N; i++) {
			bIndex[i] = aIndex[b[i]];
		}
		return mergeCount(bIndex);
	}

	// 使用插入排序方法求逆序数
	public static long insertionCount(int[] a) {
		for (int i = 1; i < a.length; i++) {
			for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
				int temp = a[j];
				a[j] = a[j - 1];
				a[j - 1] = temp;
				counter++;// 插入排序每交换一次，就存在一对逆序数
			}
		}
		return counter;
	}

	// 使用归并排序方法求逆序数
	private static int[] aux;

	public static long mergeCount(int[] a) {
		aux = new int[a.length];
		mergeSort(a, 0, a.length - 1);
		return counter;
	}

	private static void mergeSort(int[] a, int lo, int hi) {
		if (hi <= lo) {
			return;
		}
		int mid = lo + (hi - lo) / 2;
		mergeSort(a, lo, mid);
		mergeSort(a, mid + 1, hi);
		merge(a, lo, mid, hi);
	}

	public static void merge(int[] a, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}
		for (int k = lo; k <= hi; k++) {
			if (i > mid) {
				a[k] = aux[j++];
			} else if (j > hi) {
				a[k] = aux[i++];
			} else if (aux[j] < aux[i]) {
				a[k] = aux[j++];
				counter += mid - i + 1;// 每个比前子数组小的后子数组元素，逆序数为前子数组现有的长度
			} else {
				a[k] = aux[i++];
			}
		}
	}

	public static void main(String[] args) {
		int[] a = new int[] { 0, 3, 1, 6, 2, 5, 4 };
		int[] b = new int[] { 1, 0, 3, 6, 4, 2, 5 };
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i] + " " + b[i]);
		}
		System.out.println("Inversions:" + distance(a, b));
	}
	
}
