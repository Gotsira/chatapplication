package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController extends StageChanged {
	
	@FXML
	private Button chatRoomButton;
	
	@FXML
	public void chatRoomAccess( ActionEvent event ) {
		setStage("/application/ChatRoom.fxml", "Messenger Chat Room", "home.css", chatRoomButton);
	}
}
