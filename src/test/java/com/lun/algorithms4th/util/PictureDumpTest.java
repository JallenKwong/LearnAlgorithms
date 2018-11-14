package com.lun.algorithms4th.util;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class PictureDumpTest {
	@Test
	public void test() {
		
        int width = 16, height = 100;
		
        PictureDump.draw(width, height, "src/main/resources/mediumEWG.txt");
		
		//避免单元测试时图片闪退
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
