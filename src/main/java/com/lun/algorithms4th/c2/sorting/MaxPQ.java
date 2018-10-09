package com.lun.algorithms4th.c2.sorting;

public class MaxPQ<Key extends Comparable<Key>> {
	private Key[] pq;
	private int N = 0;
	
	public MaxPQ(int maxN) {
		pq = (Key[])new Comparable[maxN + 1];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	public void insert(Key v) {
		pq[++N] = v;//
		_float(N);
	}
	
	public Key delMax() {
		Key max = pq[1];
		exchange(1, N--);
		pq[N + 1] = null;//以便回收
		sink(1);
		return max;
	}

	private boolean less(int i, int j) {		
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	private void exchange(int i, int j) {
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}

	//上浮
	private void _float(int k) {
		while(k > 1 && less(k/2, k)) {
			exchange(k, k/2);
			k /= 2;
		}
	}

	//下沉
	private void sink(int k) {
		while(2 * k <= N) {
			int j = 2 * k;
			if(j < N && less(j, j + 1)) {
				j++;
			}//选它两个子结点中的较大者交换来恢复堆
			if(!less(k, j)) {
				break;
			}
			exchange(k, j);
			
			k = j;
		}
	}
	
	public Key[] getPq() {
		return pq;
	}
	
}
