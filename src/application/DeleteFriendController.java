package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import users.DeleteFriend;

public class DeleteFriendController extends StageChanged {
	private DeleteFriend deleteFriend;

	@FXML
	private TextField friendField;

	@FXML
	private Label status;

	@FXML
	public void delete(ActionEvent event) {
		try {
			deleteFriend = new DeleteFriend( username, getFriendName() );
			if ( deleteFriend.exist() ) {
				if ( deleteFriend.check() && !username.equals(getFriendName()) ) {
					deleteFriend.delete();
					homeController.refreshFreind();
					status.setText("Completed!");
				} else if ( getFriendName().isEmpty() ) {
					status.setText("Username cannot be empty.");
				} else if ( !deleteFriend.check() ) {
					status.setText("Username is not your friend.");
				} else status.setText("Cannot add your own username.");
			} else {
				status.setText("Username does not exist");
			}
		} catch (Exception e) {
			// do nothing
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
