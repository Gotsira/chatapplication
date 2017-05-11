package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ChatRoomController extends StageChanged {
	
	@FXML
	private Button homeButton;
	
	@FXML
	public void homeAccess( ActionEvent event ) {
		setStage("/application/Home.fxml", "Messenger Home", "home.css", homeButton);
	}
}
