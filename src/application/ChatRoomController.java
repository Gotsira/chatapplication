package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChatRoomController extends StageChanged {
	
	@FXML
	private Button homeButton;
	
	@FXML
	private Button logoutButton;
	
	@FXML
	public void homeAccess( ActionEvent event ) {
		setStage("/application/Home.fxml", "Messenger Home", "home.css");
		hideWindow(event);
	}
	
	@FXML
	public void logout( ActionEvent event ) {
		setStage("/application/Login.fxml", "Messenger Login" , "login.css");
		try {
			client.sendToServer("disconnect " + username);
			client.closeConnection();
		} catch (IOException e) {
			// do nothing
		}
		hideWindow(event);
	}
	
}
