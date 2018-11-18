package com.lun.algorithms4th.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

public class StdInTest {
	
	@Test
	public void test() {
		try {
			StdIn.setScanner(new Scanner(new File("src/test/resources/StdInTestData.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		StdIn.main(null);
	}
}
