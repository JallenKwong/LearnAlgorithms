package com.lun.algorithms4th.c3.searching;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.Test;

import com.lun.algorithms4th.util.StdIn;

public class DeDupTest {

	@Test
	public void test() {
		
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("result")));
			String str = "aaaaaaaaaaaaaaaaaaaaaaabcasdyiouooo";
			
			for(String s : str.split("")) {
				out.println(s);
			}
			
			out.close();
			
			StdIn.setScanner(new Scanner(new File("result")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DeDup.main(null);
	}
	
	@Test
	public void test2() {
		
		try {
			StdIn.setScanner(new Scanner(new File("src/test/resources/tinyTale.txt")));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DeDup.main(null);
	}
	
}
