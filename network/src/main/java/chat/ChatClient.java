package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	public static void main(String[] args) {
		ChatClient multiClient = new ChatClient();
		multiClient.start();
	}
	
	public void start() {
		Socket socket = null;
		BufferedReader in = null;
		Scanner scanner =  new Scanner(System.in);
		
		try {

			socket = new Socket("localhost", 8000); 
			System.out.println("[서버와 연결되었습니다]");

			System.out.print("닉네임>>");
			String name = scanner.nextLine();
			
			Thread sendThread = new ChatClientThread(socket, name);
			sendThread.start();

			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while (true) {
				String inputMsg = in.readLine();
				if(("[" + name + "]님이 퇴장 하였습니다.").equals(inputMsg)) break;
				System.out.println(inputMsg);
			}
		} catch (IOException e) {
			System.out.println("server connection cut off");
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		scanner.close();
		System.out.println("server connection closed");
	}
}
