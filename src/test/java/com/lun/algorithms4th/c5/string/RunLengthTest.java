package com.lun.algorithms4th.c5.string;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.lun.algorithms4th.util.BinaryStdIn;
import com.lun.algorithms4th.util.BinaryStdOut;
import com.lun.algorithms4th.util.HexDump;
import com.lun.algorithms4th.util.PictureDump;

public class RunLengthTest {

	@Test
	public void test() {
		PictureDump.draw(32, 48, "src/test/resources/q32x48.bin");
		
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test2() {
		PictureDump.draw(64, 96, "src/test/resources/q64x96.bin");
		
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test3() {
		try {
			BinaryStdIn.changeInputStream(new File("src/test/resources/q32x48.bin"));
			BinaryStdOut.changeOutputStream(new File("result"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		RunLength.compress();
	}
	
	@Test
	public void test4() {
		HexDump.print(24, "result");
	}
	
	
	
}
