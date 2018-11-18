package com.lun.algorithms4th.c3.searching;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.Test;

import com.lun.algorithms4th.util.StdIn;

public class LookupIndexTest {
	
	@Test
	public void test() {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("result")));
		
			out.println("Serine");			
			out.println("TCG");
			
			out.close();
			
			StdIn.setScanner(new Scanner(new File("result")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		LookupIndex.main("aminoI.txt ,".split(" "));
	}

	@Test
	public void test2() {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("result")));
		
			out.println("Bacon, Kevin");			
			out.println("Tin Men (1987)");
			
			out.close();
			
			StdIn.setScanner(new Scanner(new File("result")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		LookupIndex.main("movies.txt /".split(" "));
	}
}
