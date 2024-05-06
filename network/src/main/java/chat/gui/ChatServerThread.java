package chat.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatServerThread extends Thread {
	Socket socket;
	ChatServer server;
	BufferedReader in;		
	PrintWriter out;		
	String name;
	
	public ChatServerThread(ChatServer server, Socket socket) {
		this.server = server;
		this.socket = socket;
	}

	public void sendMessage(String str) {
		out.println(str);
	}

	@Override
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
				
			sendMessage("대화명을 입력하세요.");
			name = in.readLine();
			server.broadCasting(name + " 님이 입장하셨습니다.");
				
			while(true) {
				String str_in = in.readLine();
				if(str_in == null) throw new IOException(); 
				if("quit".equals(str_in)) {
					server.broadCasting("[" + name + "]님이 퇴장 하였습니다.");
					break;
				} else {
					server.broadCasting(name + " : " + str_in);
				}
			}	
		} catch (IOException e) {
			String outMessage = name + " 님이 퇴장했습니다.";
			System.out.println(outMessage);
			server.broadCasting(outMessage);
			server.removeClient(this);
		} finally {
			try {
				server.removeClient(this);
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}