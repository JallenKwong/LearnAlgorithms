package com.lun.algorithms4th.c5.string;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.lun.algorithms4th.util.BinaryStdIn;
import com.lun.algorithms4th.util.BinaryStdOut;
import com.lun.algorithms4th.util.PictureDump;

public class HuffmanTest {
	
	@Test
	public void test() {
		PictureDump.draw(512, 90, "src/test/resources/medTale.txt");
		
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test2() {
		try {
			BinaryStdIn.changeInputStream(new File("src/test/resources/medTale.txt"));
			BinaryStdOut.changeOutputStream(new File("result"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Huffman.compress();
	}
	
	@Test
	public void test3() {
		PictureDump.draw(512, 47, "result");
		
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
