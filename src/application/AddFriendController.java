package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import users.AddFriend;

public class AddFriendController extends StageChanged {
	private AddFriend add; 
	private String username;
	
	@FXML
	private TextField friendField;
	
	@FXML
	private Label status;
	
	@FXML
	public void add( ActionEvent event ) throws Exception {
		add = new AddFriend( getUserName() , getFriendName() );
		if ( add.check() ) {
			add.add();
			status.setText( "Completed!");
		}
		if ( getFriendName().isEmpty() ) {
			status.setText( "Username cannot be empty." );
		} else status.setText( "Cannot add this username." );
	}
	
	@FXML
	public void cancel( ActionEvent event ) {
		hideWindow(event);
	}
	
	public String getFriendName() {
		return friendField.getText();
	}
	
	public void setUserName( String username ) {
		this.username = username;
	}
	
	public String getUserName() {
		return this.username;
	}
	
}
