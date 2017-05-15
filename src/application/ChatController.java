package application;

import java.io.IOException;

import chat.Client;

public class ChatController extends StageChanged {
	private static Client client;
	private static Thread thread;
	public static void main(String[] args) throws IOException {
		client = new Client("", 5555);
		thread = new Thread();
		client.openConnection();
	}
}
