package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PhoneList01 {

	public static void main(String[] args) {
		try {
			File file = new File("./phone.txt");
			if(file.exists()) {
				System.out.println("file not found");
				return;
			}
			
			FileInputStream fis = new FileInputStream(file);
			fis.read();
		} catch (FileNotFoundException e) {
			System.out.println();
		} catch (IOException e) {
			System.out.println();
		}
	}
}
