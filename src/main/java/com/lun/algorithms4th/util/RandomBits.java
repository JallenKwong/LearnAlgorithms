package com.lun.algorithms4th.util;

import java.io.File;
import java.io.FileNotFoundException;

public class RandomBits {
	public static void main(String[] args) {
		
		try {
			BinaryStdOut.changeOutputStream(new File("result"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int x = 11111;
		for (int i = 0; i < 1000000; i++) {
			x = x * 314159 + 218281;
			BinaryStdOut.write(x > 0);
		}
		BinaryStdOut.close();
	}
}
