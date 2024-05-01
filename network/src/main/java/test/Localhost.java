package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Localhost {

	public static void main(String[] args) {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			String hostName = inetAddress.getHostName();
			String hostIpAddress = inetAddress.getHostAddress();
			
			System.out.println(hostName);
			System.out.println(hostIpAddress);
			
			byte[] ipAddresses = inetAddress.getAddress();
			for(byte IpAddress : ipAddresses) {
				System.out.println(IpAddress);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
