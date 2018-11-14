package com.lun.algorithms4th.util;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class PictureTest {
	
	@Test
	public void test() {
		
		Picture.main(new String[] {"src/main/java/com/lun/algorithms4th/c6/context/image/B-tree-example.png"});
		
		//避免单元测试时图片闪退
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
