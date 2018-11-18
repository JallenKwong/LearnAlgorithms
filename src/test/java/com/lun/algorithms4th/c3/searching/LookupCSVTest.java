package com.lun.algorithms4th.c3.searching;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.Test;

import com.lun.algorithms4th.util.StdIn;

public class LookupCSVTest {
	
	@Test
	public void test() {
		
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("result")));
			out.println("TCC");			
			out.close();
			
			StdIn.setScanner(new Scanner(new File("result")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		LookupCSV.main("src/test/resources/amino.csv 0 3".split(" "));
	}

	@Test
	public void test2() {
		
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("result")));
			out.println("128.112.136.35");			
			out.close();
			
			StdIn.setScanner(new Scanner(new File("result")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		LookupCSV.main("src/test/resources/ip.csv 1 0".split(" "));
	}

}
