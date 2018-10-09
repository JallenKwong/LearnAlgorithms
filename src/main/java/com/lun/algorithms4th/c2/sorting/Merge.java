package com.lun.algorithms4th.c2.sorting;

public class Merge extends AbstractSorting{

	protected Comparable[] aux;
	
	@Override
	public void sort(Comparable[] a) {
		aux = new Comparable[a.length];
		sort(a, 0 , a.length - 1);
	}
	
	protected void sort(Comparable[] a, int left, int right) {
		if(left >= right) return;
		int mid = (left + right) / 2;
		
		sort(a, left, mid);
		sort(a, mid + 1, right);
		
		merge(a, left, mid, right);
		
	}

	protected void merge(Comparable[] a, int left, int mid, int right) {
		
		int i = left, j = mid + 1;
		
		System.arraycopy(a, left, aux, left, right - left + 1);
//		for(int k = left; k <= right; k++)
//			aux[k] = a[k];
		
		for(int k = left; k <= right;k++)
			if(i > mid) 
				a[k] = aux[j++];
			else if(j > right) 
				a[k] = aux[i++];
			else if(less(aux[j], aux[i])) 
				a[k] = aux[j++];
			else 
				a[k] = aux[i++];
		
	}

}
