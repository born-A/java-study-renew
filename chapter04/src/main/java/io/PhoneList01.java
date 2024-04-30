package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class PhoneList01 {

	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			File file = new File("./phone.txt");
			if(!file.exists()) {
				System.out.println("file not found");
				return;
			}
			
			System.out.println("====파일 정보====");
			System.out.println(file.getAbsolutePath());
			System.out.println(file.length() + "Bytes");
			Date d = new Date(file.lastModified());
			System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
					.format(new Date(file.lastModified())));
			
			// 1. 기반 스트림
			FileInputStream fis = new FileInputStream(file);
			
			// 2. 보조 스트림(byte | byte | byte -> char)
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			
			// 3. 보조 스트림 03 (char | char | char | \n -> "charcharchar")
			br = new BufferedReader(isr);
			
			// 4. 처리
			String line = null;
			while((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "\t ");
				int index = 0;
				
				while(st.hasMoreElements()) {
					String token = st.nextToken();
					
					if(index == 0) {
						System.out.print(token + ":");
					} else if(index == 1) {
						System.out.print(token + "-");
					} else if(index == 2) {
						System.out.print(token + "-");
					} else {
						System.out.println(token + "\n");
					}
				}
				System.out.println(line);
			}
			
		} catch (UnsupportedEncodingException e) {
			System.out.println("error:" + e);
		} catch (IOException e) {
			System.out.println();
		} finally {
			
				try {
					if(br != null) br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
