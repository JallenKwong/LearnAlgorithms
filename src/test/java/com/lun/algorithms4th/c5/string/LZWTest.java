package com.lun.algorithms4th.c5.string;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

import com.lun.algorithms4th.util.BinaryStdIn;
import com.lun.algorithms4th.util.BinaryStdOut;
import com.lun.algorithms4th.util.HexDump;

public class LZWTest {
	
	@Test
	public void test() {
		try {
			BinaryStdIn.changeInputStream(new File("src/main/resources/ababLZW.txt"));
			BinaryStdOut.changeOutputStream(new File("result"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		LZW.compress();
	}
	
	@Test
	public void test2() {
		HexDump.print(20, "result");
	}
	
	
}
