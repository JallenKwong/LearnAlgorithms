package com.lun.spring;


import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.core.io.*;


public class FileSourceExample {
	
	public static void main(String[] args) {
		try {
			String filePath = "C:\\eclipse-workspace\\LearnAlgorithms\\src\\main\\resources\\mediumEWG.txt";
			
			Resource res1 = new PathResource(filePath);
			Resource res2 = new ClassPathResource("mediumEWG.txt");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(res1.getInputStream())); 
			
			StringBuilder sb = new StringBuilder();
			String s;
			while ((s = reader.readLine()) != null) {
				sb.append(s);
				sb.append("\n");
			}
			
			System.out.println(sb.toString());
        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
