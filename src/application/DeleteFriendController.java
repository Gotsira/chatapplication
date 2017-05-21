package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.DeleteFriend;

public class DeleteFriendController extends StageChanged {
	private DeleteFriend deleteFriend;
	private String username;

	@FXML
	private TextField friendField;

	@FXML
	private Label status;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	}

	@FXML
	public void delete(ActionEvent event) throws Exception {
		deleteFriend = new DeleteFriend( getUserName(), getFriendName() );
		if ( deleteFriend.exist() ) {
			if ( deleteFriend.check() && !getUserName().equals(getFriendName()) ) {
				deleteFriend.add();
				status.setText("Completed!");
			} else if ( getFriendName().isEmpty() ) {
				status.setText("Username cannot be empty.");
			} else if ( !deleteFriend.check() ) {
				status.setText("Username is not your friend.");
			} else status.setText("Cannot add your own username.");
		} else {
			status.setText("Username does not exist");
		}
	}

	@FXML
	public void cancel(ActionEvent event) {
		hideWindow(event);
	}

	public String getFriendName() {
		return friendField.getText();
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getUserName() {
		return this.username;
	}

}
