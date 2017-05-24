package chat;

import java.io.IOException;
import java.util.ArrayList;

import com.lloseng.ocsf.client.AbstractClient;

import application.ChatController;
import application.StageChanged;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends AbstractClient {
	private ArrayList<ChatController> allChats = new ArrayList<ChatController>();
	private String message = "";
	private String sender;

	public Client(String host, int port) {
		super(host, port);
	}

	@Override
	public void handleMessageFromServer(Object msg) {
		boolean check = true;
		String[] chat = ((String) msg).split(" ");
		sender = chat[0];
		String type = chat[1];
		for (int i = 2; i < chat.length; i++) {
			message += chat[i] + " ";
		}
		for (ChatController chatUI : allChats) {
			if (sender.equals(chatUI.getFriend())) {
				if (type.equals("message")) {
					check = false;
					chatUI.display(sender + ": " + message);
					message = "";
				} else if (type.equals("offline")) {
					chatUI.display(sender + " " + message);
					message = "";
				}
			}
		} if(check) {
			System.out.println("yo");
			create(sender);
		}
		check = true;
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
	
	public void create(String friend) {
//		StageChanged chat = new StageChanged();
//		chat.setFriendUser(friend);
//		chat.setStage("/src/application/Chat.fxml", "Messenger Chat", "chat.css");
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("application/Chat.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("chat.css").toExternalForm());
			stage.setResizable(false);
			stage.setTitle("Messenger Chat");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// do nothing
		}
	}
}

