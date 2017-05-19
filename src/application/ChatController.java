package application;

import java.io.IOException;

import chat.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class ChatController extends StageChanged {
	private static Client client = new Client("35.185.184.40", 5135);
	private static Thread thread;
	
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
	

}
