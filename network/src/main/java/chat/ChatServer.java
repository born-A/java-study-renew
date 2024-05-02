package chat;

import java.io.IOException;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	static List<Writer> listWriters;
	
	public static void main(String[] args) {
		listWriters = new ArrayList<Writer>();
		ChatServer multiServer = new ChatServer();
		multiServer.start();
	}
	
	public void start() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(8000);
			while (true) {
				System.out.println("server started....");
				socket = serverSocket.accept();
				
				ChatServerThread receiveThread = new ChatServerThread(socket, listWriters);	
				receiveThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (serverSocket!=null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
