package chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClientThread extends Thread{
	Socket socket = null;
	String name;

	Scanner scanner = new Scanner(System.in);
	
	public ChatClientThread(Socket socket, String name) {
		this.socket = socket;
		this.name = name;
	}

	@Override
	public void run() {
 		try {
			PrintStream out = new PrintStream(socket.getOutputStream());
			
			while (true) {
				String outputMsg = scanner.nextLine();
				out.println(outputMsg);
				out.flush();
				if("quit".equals(outputMsg)) return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
