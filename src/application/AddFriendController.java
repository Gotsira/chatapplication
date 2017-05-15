package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AddFriendController extends StageChanged {
	
	@FXML
	public void cancel( ActionEvent event ) {
		hideWindow(event);
	}
}
