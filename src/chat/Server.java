package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread{
	private ServerSocket server;
	private final int port = 5001;
	
	public Server() {
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			//do nothing
		}
	}
	
	@Override
	public void run() {
		Socket clientSocket;
		try {
			while((clientSocket = server.accept()) != null) {
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
