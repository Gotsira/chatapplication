package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DeleteFriendController extends StageChanged {
	
	@FXML
	private TextField friendField;
	
	@FXML
	public void cancel( ActionEvent event ) {
		hideWindow(event);
	}
}
