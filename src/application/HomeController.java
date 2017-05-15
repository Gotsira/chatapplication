package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomeController extends StageChanged {
	
	@FXML
	private Button chatRoomButton;
	
	@FXML
	private Button logoutButton;
	
	@FXML
	private Label username;
	
	@FXML
	public void chatRoomAccess( ActionEvent event ) {
		setStage("/application/ChatRoom.fxml", "Messenger Chat Room", "chatroom.css");
		hideWindow(event);
	}
	
	@FXML
	public void logout( ActionEvent event ) {
		setStage("/application/Login.fxml", "Messenger Login" , "login.css");
		hideWindow(event);
	}
	
	public void getUsername( String name ) {
		username.setText( name );
	}
	
}
