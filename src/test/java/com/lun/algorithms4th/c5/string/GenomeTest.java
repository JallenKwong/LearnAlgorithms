package com.lun.algorithms4th.c5.string;



import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.lun.algorithms4th.util.BinaryDump;
import com.lun.algorithms4th.util.HexDump;
import com.lun.algorithms4th.util.PictureDump;

public class GenomeTest {
	
	//按顺序逐个执行
	//TODO:将连串操作混入一个方法中，而不是逐个逐个执行
	
	@Test
	public void test1() {
		BinaryDump.print(64, "src/test/resources/genomeTiny.txt");
	}
	
	@Test
	public void test2() {
		Genome.compress("src/test/resources/genomeTiny.txt", "result");
	}
	
	@Test
	public void test3() {
		BinaryDump.print(64, "result");
	}
	
	@Test
	public void test4() {
		HexDump.print(8, "result");
	}
	
	@Test
	public void test5() {
		PictureDump.draw(512, 100, "src/test/resources/genomeVirus.txt");
		
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test6() {
		Genome.compress("src/test/resources/genomeVirus.txt", "result");
	}
	
	@Test
	public void test7() {
		PictureDump.draw(512, 25, "result");
		
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
