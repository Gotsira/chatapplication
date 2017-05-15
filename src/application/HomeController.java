package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomeController extends StageChanged {
	
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
	
	@FXML
	public void addFreind( ActionEvent event ) {
		setStage("/application/AddFriend.fxml", "Messenger Add Friend" , "adddeletefriend.css");
	}
	
	@FXML
	public void deleteFriend( ActionEvent event ) {
		setStage("/application/DeleteFriend.fxml", "Messenger Delete Friend" , "adddeletefriend.css");
	}
	
	public void getUsername( String name ) {
		username.setText( name );
	}
	
}
