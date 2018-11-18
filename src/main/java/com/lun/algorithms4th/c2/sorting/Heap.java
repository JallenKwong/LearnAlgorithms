package com.lun.algorithms4th.c2.sorting;

import org.junit.Assert;
import org.junit.Test;

public class Heap extends AbstractSorting {

	@Override
	public void sort(Comparable[] pq) {
		int n = pq.length;

		// 堆的构造
		for (int k = n / 2; k >= 1; k--)
			sink(pq, k, n);

		// 下沉排序
		while (n > 1) {
			exchange(pq, 1, n--);
			sink(pq, 1, n);
		}
	}

	private boolean less(Comparable[] pq, int i, int j) {
		return pq[i - 1].compareTo(pq[j - 1]) < 0;
	}

	private void sink(Comparable[] pq, int k, int n) {
		while (2 * k <= n) {
			int j = 2 * k;

			if (j < n && less(pq, j, j + 1))
				j++;

			if (!less(pq, k, j))
				break;

			exchange(pq, k, j);

			k = j;
		}
	}

	protected void exchange(Comparable[] pq, int i, int j) {
		Comparable swap = pq[i - 1];
		pq[i - 1] = pq[j - 1];
		pq[j - 1] = swap;
	}

}


