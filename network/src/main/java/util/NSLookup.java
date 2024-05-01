package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	public static void main(String[] args) {
		try {
			while(true) {
				System.out.print(">>");
				Scanner sc = new Scanner(System.in);
				String domain = sc.nextLine();
				
				if("exit".equals(domain)) {
					break;
				} 
			
			
				InetAddress[] inetAddresses = InetAddress.getAllByName(domain);
			
				for(InetAddress inetAddress : inetAddresses) {
					System.out.println(inetAddress);
				}	
			}
		}catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
