package application;

import java.awt.event.ActionEvent;
import java.io.IOException;

import chat.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatController extends StageChanged {
	
	@FXML
	private Button sendButton;
	
	@FXML
	private TextField field;
	
	@FXML
	private MenuButton menu;
	
	@FXML
	private MenuItem sendPhoto;
	
	@FXML
	private MenuItem sendVideo;
	
	@FXML
	private MenuItem sendContact;
	
	@FXML
	private Label name;
	
	@FXML
	private TextArea message;
	
	@FXML
	public void send(ActionEvent event) throws IOException {
		client.sendToServer("message " + field.getText());
	}
	
	public void displayMessage(String message) {
		
	}
}
