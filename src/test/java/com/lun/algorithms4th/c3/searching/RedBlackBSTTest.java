package com.lun.algorithms4th.c3.searching;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import com.lun.algorithms4th.util.StdIn;

public class RedBlackBSTTest {
	
	@Test
	public void test() {
		try {
			StdIn.setScanner(new Scanner(new File("src/test/resources/tinyST.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		RedBlackBST.main(null);
	}
	
}
