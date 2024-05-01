package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			String domain = sc.nextLine();
			
			InetAddress[] inetAddresses = InetAddress.getAllByName(domain);
			
			for(InetAddress inetAddress : inetAddresses) {
				byte[] ipAddresses = inetAddress.getAddress();
				System.out.println(inetAddress);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
