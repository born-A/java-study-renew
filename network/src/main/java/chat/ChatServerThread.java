package chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatServerThread extends Thread{
	static List<PrintWriter> list = 
			Collections.synchronizedList(new ArrayList<PrintWriter>());
	
	Socket socket = null;
	BufferedReader in = null;
	PrintWriter out = null;
	String nickname;
	
	List<Writer> listWriters;
	
	public ChatServerThread (Socket socket, List<Writer> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
		
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			list.add(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	@Override
	public void run() {
		try {
			while(true) {
			String request = in.readLine();
			if(request == null) throw new IOException();
			
			String[] tokens = request.split(":");
			if("join".equals(tokens[0])) {
				doJoin(tokens[1], out);	
			} else if("message".equals(tokens[0])) {
				sendAll(nickname + ">>" + tokens[1]);
			} else if("quit".equals(tokens[0])) {
				sendAll("[" + nickname + "]님이 퇴장 하였습니다.");
				break;
			} else {
				System.out.println("error input : (" + tokens[0] + ")");
			}
			
			}
		} catch (IOException e) {
			String outMessage = "[" + nickname + "]님이 퇴장 하였습니다.";
			System.out.println(outMessage);
			sendAll(outMessage);
			list.remove(this);
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
//		list.remove(out);
//		try {
//			socket.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	
	private void doJoin(String nickname, Writer writer) {
		this.nickname = nickname;
		sendAll("[" + nickname + "]님이 입장 하였습니다.");
		/* writer pool 에 저장 */
		addWriter(writer);
		
		out.println("join:ok");
		out.flush();
	}

	private void addWriter(Writer writer) {
		synchronized(listWriters) {
			listWriters.add(writer);
		}	
	}

	private void sendAll (String s) {
		for (PrintWriter out: list) {
			out.println(s);
			out.flush();
		}
	}
	
	
}
