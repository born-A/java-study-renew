package chat.gui;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ChatServer {
	ServerSocket serverSocket;
	Socket socket;
	List<Thread> list;
	
	public static void main(String[] args) {
		ChatServer multiServer = new ChatServer();
		multiServer.start();
	}
	
	public ChatServer() {
		list = new ArrayList<Thread>();
		System.out.println("server started...");
	}
	
	public void start() {
		try {
			serverSocket = new ServerSocket(6000);		
			serverSocket.setReuseAddress(true); 	
			
			while(true) {
				socket = serverSocket.accept();			
				ChatServerThread thread = new ChatServerThread(this, socket);	
				addClient(thread);		
				thread.start();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally {
			if (serverSocket!=null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private synchronized void addClient(ChatServerThread thread) {
		list.add(thread);
	}		

	public synchronized void removeClient(Thread thread) {
		list.remove(thread);
	}
	
	public synchronized void broadCasting(String str) {
		for(int i = 0; i < list.size(); i++) {
			ChatServerThread thread = (ChatServerThread)list.get(i);
			thread.sendMessage(str);
		}
	}
}
