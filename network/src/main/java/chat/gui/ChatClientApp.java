package chat.gui;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClientApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String ip = null;
		String name = null;
		
		while(true) {
			System.out.println("대화명을 입력하세요.");
			System.out.print(">>> ");
			name = scanner.nextLine();
			
			
			if (name.isEmpty() == false ) {
				break;
			}
			
			System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
	
			try {
				InetAddress ia = InetAddress.getLocalHost();
				String ip_str = ia.toString();
				ip = ip_str.substring(ip_str.indexOf("/") + 1);
				 
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		scanner.close();
		
		new ClientWindow(name, ip, 6000);
	}
}