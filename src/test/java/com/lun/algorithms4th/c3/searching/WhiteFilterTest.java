package com.lun.algorithms4th.c3.searching;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.Test;

import com.lun.algorithms4th.util.StdIn;

public class WhiteFilterTest {

	@Test
	public void test() {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("result")));
			out.println("was it the of");
			out.close();
			
			StdIn.setScanner(new Scanner(new File("src/test/resources/tinyTale.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		WhiteFilter.main("result".split(" "));
		
	}
	
}
