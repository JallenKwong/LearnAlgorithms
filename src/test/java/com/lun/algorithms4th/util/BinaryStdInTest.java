package com.lun.algorithms4th.util;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

public class BinaryStdInTest {
	
	@Test
	public void test() throws FileNotFoundException {
		BinaryStdIn.changeInputStream(new File("pom.xml"));
		//BinaryStdIn.changeInputStream(new File("src/main/resources/abra.txt"));
		
        // read one 8-bit char at a time
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            System.out.print(c);
            
            BinaryStdOut.write(c);
        }
        BinaryStdOut.flush();
		
	}
}
