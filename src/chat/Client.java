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
	 * @param port
	 *            is the port used
	 */
	public Client(String host, int port) {
		super(host, port);
	}

	/**
	 * Handles all the different type of messages received from the server and
	 * displays on its ui.
	 * 
	 * @param msg
	 *            is the message received from the server.
	 */
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
				} else if (type.equals("offline")) {
					chatUI.display(sender + " " + message);
				}
			}
		}
	}

	/**
	 * Adds the id of the chat ui.
	 * 
	 * @param chat
	 *            is the chat ui.
	 */
	public void addChat(ChatController chat) {
		allChats.add(chat);
	}

	/**
	 * Deletes the chat when the user closes a chat.
	 * 
	 * @param friend
	 *            is the name of the friend whose ui needs to be deleted.
	 */
	public void deleteChat(String friend) {
		for (ChatController chat : allChats) {
			if (chat.getFriend() == friend) {
				allChats.remove(chat);
			}
		}
	}

	/**
	 * Checks whether a chat ui of a friend exist or not.
	 * 
	 * @param friend
	 *            is the name of the friend whose ui needs to be checked.
	 * @return true if the ui of the friend exist, and false otherwise.
	 */
	public ChatController existFriend(String friend) {
		for (ChatController chat : allChats) {
			if (chat.getFriend() == friend) {
				return chat;
			}
		}
		return null;
	}

	/**
	 * Checks if any chat still exists.
	 * 
	 * @return true if a chat still exist, and false otherwise.
	 */
	public boolean exists() {
		if(allChats.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the current message of the client.
	 * 
	 * @return the current message of the client.
	 */
	public String getMessage() {
		return sender + ": " + message;
	}

	/**
	 * Sets the current message for the client.
	 * 
	 * @param message
	 *            is the message to be set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the name of the sender of the message.
	 * 
	 * @return the name of the sender of the message.
	 */
	public String getSender() {
		return sender;
	}
}
