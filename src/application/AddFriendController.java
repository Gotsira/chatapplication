package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import users.AddFriend;

public class AddFriendController extends StageChanged {
	private AddFriend addFriend;

	@FXML
	private TextField friendField;

	@FXML
	private Label status;

	@FXML
	public void add(ActionEvent event) {
		try {
			addFriend = new AddFriend(username, getFriendName());
			if (addFriend.exist()) {
				if (!(addFriend.check()) && !username.equals(getFriendName())) {
					addFriend.add();
					homeController.refreshFreind();
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
		} catch (Exception e) {
			//do nothing
		}
	}

	@FXML
	public void cancel(ActionEvent event) {
		hideWindow(event);
	}

	public String getFriendName() {
		return friendField.getText();
	}

}
