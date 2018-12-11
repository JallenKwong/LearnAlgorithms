package com.lun.algorithms4th.c6.context;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.lun.algorithms4th.util.StdIn;

public class CollisionSystemTest {
	
	@Test
	public void test() {
		
		try {
			StdIn.setScanner(new Scanner(new File("src/test/resources/diffusion.txt")));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		CollisionSystem.main(new String[] {});
		
		try {
			TimeUnit.SECONDS.sleep(60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test2() {
		
		CollisionSystem.main(new String[] {"6"});
		
		try {
			TimeUnit.SECONDS.sleep(60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
