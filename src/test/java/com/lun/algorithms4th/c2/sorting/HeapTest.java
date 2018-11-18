package com.lun.algorithms4th.c2.sorting;

import org.junit.Assert;
import org.junit.Test;

public class HeapTest {
	
	@Test
	public void test() {
		Character[] init = new Character[] { 'S', 'O', 'R', 'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E' };
		new Heap().sort(init);
		Assert.assertArrayEquals(new Character[] { 'A', 'E', 'E', 'L', 'M', 'O', 'P', 'R', 'S', 'T', 'X' }, init);
	}
	
}
