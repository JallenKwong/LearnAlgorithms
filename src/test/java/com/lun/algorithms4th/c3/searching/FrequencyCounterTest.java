package com.lun.algorithms4th.c3.searching;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import com.lun.algorithms4th.util.StdIn;

public class FrequencyCounterTest {

	@Test
	public void test() {
		try {
			StdIn.setScanner(new Scanner(new File("src/test/resources/tinyTale.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		FrequencyCounter.main("1".split(" "));
	}
	
	@Test
	public void test2() {
		try {
			StdIn.setScanner(new Scanner(new File("src/test/resources/tale.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		FrequencyCounter.main("8".split(" "));
	}
	
}
