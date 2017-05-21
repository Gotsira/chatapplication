package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import users.Login;


public class LoginController extends StageChanged {
	private Login login;
	
	@FXML
	private Label status;
	
	@FXML
	private TextField usernameField;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private Button loginButton;
	
	@FXML
	public void login( ActionEvent event ) throws Exception {
		login = new Login( getUsername() , getPassword() );
		if ( login.matches() ) {
			username = getUsername();
			client.openConnection();
			client.sendToServer("connect " + getUsername());
			setStage("/application/Home.fxml", "title", "home.css");
			hideWindow(event);
		}
		if ( getUsername().isEmpty() || getPassword().isEmpty() ) {
			status.setText( "Username or password cannot be empty." );
		} else status.setText( "Username or password is incorrect." );
	}
	
	@FXML
	public void signup( ActionEvent event ) {
		setStage("/application/Signup.fxml", "Messenger Sign up", "login.css");
		hideWindow(event);
	}
	
	public String getUsername() {
		return usernameField.getText();
	}
	
	public String getPassword() {
		return passwordField.getText();
	}

}
