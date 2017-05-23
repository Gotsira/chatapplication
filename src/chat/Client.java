package chat;

import java.awt.Image;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import com.lloseng.ocsf.client.AbstractClient;

import application.ChatController;

public class Client extends AbstractClient {
	private String host;
	private static final int PORT = 3014;
	private String message;
	private BufferedImage image = null;
	private ArrayList<ChatController> allChats = new ArrayList<ChatController>();

	public Client(String host, int port) {
		super(host, port);
	}

	@Override
	public void handleMessageFromServer(Object msg) {

		String[] chat = ((String) msg).split(" ");
		String sender = chat[0];
		String type = chat[1];
		String data = "";
		if (type.equals("message")) {
			for (int i = 2; i < chat.length; i++) {
				data += chat[i];
			}
		}

		for (ChatController chatUI : allChats) {
			if (sender.equals(chatUI.getFriend())) {
				chatUI.display(data);
			}
		}

	}

	public void addChat(ChatController chat) {
		allChats.add(chat);
	}

	public void deleteChat(String friend) {
		for(ChatController chat : allChats) {
			if(chat.getFriend() == friend) {
				allChats.remove(chat);
			}
		}
	}

}
