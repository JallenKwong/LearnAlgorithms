package com.lun.algorithms4th.c2.sorting;

/**
 * @author JK
 * From TheCProgrammingLanguage2nd Page 87
 */
public class Quick extends AbstractSorting{

	@Override
	public void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
	}
	
	private void sort(Comparable[] a, int left, int right) {
		if(left >= right) {
			return;
		}
		exchange(a, left, (left + right) / 2);//理解为打乱一点顺序
		int last = left;
		for(int i = left + 1; i <= right; i++) {
			if(less(a[i], a[left])) {
				exchange(a, i, ++last);
			}
		}
		exchange(a, last, left);
		sort(a, left, last - 1);
		sort(a, last + 1, right);
	}

}
