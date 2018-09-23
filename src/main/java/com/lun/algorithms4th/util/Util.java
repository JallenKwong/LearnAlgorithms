package com.lun.algorithms4th.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Util {
	
	public static List<String> readLines(String filePath){
		List<String> resultList = new ArrayList<>();
		String str  = null;
		try (BufferedReader br = new BufferedReader(new FileReader(filePath));){
			
			while((str = br.readLine()) != null) {
				resultList.add(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	public static String getProjectResourcesFilePath(String str){
		try{
			return new File(".\\src\\main\\resources\\" + str)
					.getCanonicalPath();
		}catch(IOException ex){
			ex.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 
	 * 读取 src\\main\\resources文件夹的文件
	 * 
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static List<String> readLines2(String path) {
		return readLines(getProjectResourcesFilePath(path));
	}
	
	
	public static void main(String[] args) throws IOException {
		
		//C:\eclipse-workspace\LearnAlgorithms\src\main\resources\tiny.txt
		String path = getProjectResourcesFilePath("tiny.txt");
		System.out.println(path);
		System.out.println(readLines(path));
		
		
		System.out.println(readLines2("tiny.txt"));
	}
}
