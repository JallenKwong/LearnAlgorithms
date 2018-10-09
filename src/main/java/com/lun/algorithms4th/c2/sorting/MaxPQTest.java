package com.lun.algorithms4th.c2.sorting;

import org.junit.Assert;
import org.junit.Test;

public class MaxPQTest {
	
	@Test
	public void testMaxPQ() {
		MaxPQ<Character> pq = new MaxPQ<>(11);
//		String str = "TSRPNOAEIHG";
		String str = "AEIPNOHGTSR";
		for(Character c : str.toCharArray()) {
			pq.insert(c);
		}
		
		boolean result = true;
		
		Comparable[] array = pq.getPq();
		
		for(int i = 1; i < array.length; i++) {
			
			if(2 * i <= pq.size() || 2 * i + 1 <= pq.size()) {
				if(array[i].compareTo(array[2 * i]) < 0
						|| array[i].compareTo(array[2 * i + 1]) < 0) {
					result = false;
					break;
				}
			}
		}
		
		Assert.assertTrue(result);
	}
}
