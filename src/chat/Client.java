package chat;

import java.util.ArrayList;
import com.lloseng.ocsf.client.AbstractClient;
import application.ChatController;

/**
 * Client class represents as the user who sends the message to the server and
 * gets the message from the server.
 * 
 * @author Sirasath Piyapootinun
 *
 */
public class Client extends AbstractClient {
	private ArrayList<ChatController> allChats = new ArrayList<ChatController>();
	private String message = "";
	private String sender;

	/**
	 * Constructor for creating the client.
	 * 
	 * @param host
	 *            is the host of the server used to connecting to the server.
	 * @param port is the port used 
	 */
	public Client(String host, int port) {
		super(host, port);
	}

	@Override
	public void handleMessageFromServer(Object msg) {
		String[] chat = ((String) msg).split(" ");
		sender = chat[0];
		String type = chat[1];
		for (int i = 2; i < chat.length; i++) {
			message += chat[i] + " ";
		}
		for (ChatController chatUI : allChats) {
			if (sender.equals(chatUI.getFriend())) {
				if (type.equals("message")) {
					chatUI.display(sender + ": " + message);
					message = "";
				} else if (type.equals("offline")) {
					chatUI.display(sender + " " + message);
					message = "";
				}
			} 
		}
	}

	public void addChat(ChatController chat) {
		allChats.add(chat);
	}

	public void deleteChat(String friend) {
		for (ChatController chat : allChats) {
			if (chat.getFriend() == friend) {
				allChats.remove(chat);
			}
		}
	}

	public ChatController exist(String friend) {
		for (ChatController chat : allChats) {
			if (chat.getFriend() == friend) {
				return chat;
			}
		}
		return null;
	}

	public String getMessage() {
		return sender + ": " + message;
	}

	public String getSender() {
		return sender;
	}
}
