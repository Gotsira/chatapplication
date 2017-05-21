package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.AddFriend;

public class AddFriendController extends StageChanged {
	private AddFriend addFriend;
	private String username;

	@FXML
	private TextField friendField;

	@FXML
	private Label status;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	}

	@FXML
	public void add(ActionEvent event) throws Exception {
		addFriend = new AddFriend(getUserName(), getFriendName());
		if (addFriend.exist()) {
			if (!(addFriend.check()) && !getUserName().equals(getFriendName())) {
				addFriend.add();
				status.setText("Completed!");
			} else if (getFriendName().isEmpty()) {
				status.setText("Username cannot be empty.");
			} else if (addFriend.check()) {
				status.setText("Username is already your friend.");
			} else
				status.setText("Cannot add your own username.");
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
