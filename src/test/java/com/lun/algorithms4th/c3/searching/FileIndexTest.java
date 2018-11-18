package com.lun.algorithms4th.c3.searching;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.Test;

import com.lun.algorithms4th.util.StdIn;

public class FileIndexTest {
	
	@Test
	public void test() {
		
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("result")));
			out.println("best");			
			out.println("was");			
			out.println("age");
			out.close();
			
			StdIn.setScanner(new Scanner(new File("result")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		String[] array = "ex1.txt ex2.txt ex3.txt ex4.txt".split(" ");
		String dir = "src/test/resources/";
		for(int i = 0; i < array.length; i++) {
			array[i] = dir + array[i];
		}
		
		FileIndex.main(array);
	}
}
