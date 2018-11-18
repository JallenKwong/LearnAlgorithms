package com.lun.algorithms4th.c2.sorting;

import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractSortingTest{

	protected static AbstractSorting sortingAlgorithms;
	
	protected static final String str = "9 71 16 53 27 83 7 95 85 38 19 50 35 41 34 80 41 1 26 69 28 47 22 31 70 12 80 36 14 12 90 82 7 43 4 50 13 52 11 47 8 18 36 53 70 65 67 19 66 39 9 91 38 9 19 2 37 57 9 12 95 29 80 26 74 11 30 6 33 82 67 82 3 50 84 75 27 76 68 50 84 10 62 98 94 29 65 27 49 12 28 23 72 82 77 68 0 95 20 99";

	protected Integer[] createUnsortedArray() {
		String[] temp = str.split(" ");
		Integer[] unsortedArray = new Integer[temp.length];
		for (int i = 0; i < temp.length; i++) {
			unsortedArray[i] = Integer.valueOf(temp[i]);
		}
		return unsortedArray;
	}
	
	protected boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++)
			if (sortingAlgorithms.less(a[i], a[i - 1]))
				return false;
		return true;
	}
	
	@Test
	public void testSort() {
		Integer[] unsortedArray = createUnsortedArray();
		sortingAlgorithms.sort(unsortedArray);
//		printArray(unsortedArray);
		Assert.assertTrue(isSorted(unsortedArray));

	}
	
}
