package com.lun.algorithms4th.c2.sorting;

public class Quick2 extends AbstractSorting {

	@Override
	public void sort(Comparable[] a) {
		shuffle(a);//这样做希望能够预测（并依赖）该算法的性能特性
		sort(a, 0, a.length - 1);
	}

	protected void sort(Comparable[] a, int left, int right) {
		if (left >= right)
			return;
		int j = partition(a, left, right);
		sort(a, left, j - 1);
		sort(a, j + 1, right);
	}

	protected int partition(Comparable[] a, int left, int right) {
		int i = left;
		int j = right + 1;
		Comparable v = a[left];
		while (true) {

			// find item on left to swap
			while (less(a[++i], v)) {
				if (i == right)
					break;
			}

			// find item on right to swap
			while (less(v, a[--j])) {
				if (j == left)
					break; // redundant since a[left] acts as sentinel
			}

			// check if pointers cross
			if (i >= j)
				break;

			exchange(a, i, j);
		}

		// put partitioning item v at a[j]
		exchange(a, left, j);

		// now, a[left .. j-1] <= a[j] <= a[j+1 .. right]
		return j;
	}

	// 打乱顺序, 
	private void shuffle(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			exchange(a, i, (int) (Math.random() * a.length));
		}
	}
}
