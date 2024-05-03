package chat.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientWindow implements Runnable{

	private Socket socket;
	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;

	private PrintWriter out;
	private BufferedReader in;
	private String str; 
	private String name;
	
	public ClientWindow(String name, String ip, int port) {
		this.name = name;
		init();
		initNet(ip, port);
	}	

	private void initNet(String ip, int port) {
		try {
			socket = new Socket(ip, port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		} catch (IOException e) {
			System.out.println("접속 실패");
		}
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	private void init() {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
		
		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.BLACK);
		buttonSend.addActionListener( new ActionListener() {
					@Override
					public void actionPerformed( ActionEvent actionEvent ) {
						str = textField.getText();
						out.println(str);

						textField.setText("");
					}
		});

		// Textfield
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					char keyCode = e.getKeyChar();
					if(keyCode == KeyEvent.VK_ENTER) {
						str = textField.getText();
						out.println(str);
						// textField 초기화
						textField.setText("");
					}
				}
			});

		
		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
				
		frame.setVisible(true);
		frame.pack();
	}

	@Override
	public void run() {
		while(true) {
			try {
				str = in.readLine();
				textArea.append(str+ "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
