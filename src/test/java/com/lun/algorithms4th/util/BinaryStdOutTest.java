package com.lun.algorithms4th.util;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

public class BinaryStdOutTest {

	@Test
	public void test() {
		int year = 1999, month = 12, day = 31;
		
		try {
			BinaryStdOut.changeOutputStream(new File("result"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		BinaryStdOut.write(year);
//		BinaryStdOut.write(month);
//		BinaryStdOut.write(day);
		BinaryStdOut.flush();
	}
	
}
