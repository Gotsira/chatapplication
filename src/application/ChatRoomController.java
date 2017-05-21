package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChatRoomController extends StageChanged {
	
	@FXML
	private Button homeButton;
	
	@FXML
	private Button logoutButton;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	}
	
	@FXML
	public void homeAccess( ActionEvent event ) {
		setStage("/application/Home.fxml", "Messenger Home", "home.css");
		hideWindow(event);
	}
	
	@FXML
	public void logout( ActionEvent event ) {
		setStage("/application/Login.fxml", "Messenger Login" , "login.css");
		hideWindow(event);
	}
	
	@FXML
	public void chat( ActionEvent event ) {
		setStage("/application/Chat.fxml", "Messenger Chat" , "chat.css");
	}
}
