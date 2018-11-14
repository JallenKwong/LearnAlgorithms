package com.lun.algorithms4th.util;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class RandomBitsTest {
	
	
	@Test
	public void test() {
		RandomBits.main(null);		
		
        PictureDump.draw(2000, 500, "result");
		
		//避免单元测试时图片闪退
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void clear() {
		//只能手动删除文件
		new File("result").deleteOnExit();
	}
	
	
}
