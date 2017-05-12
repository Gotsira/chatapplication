package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import users.Login;
import java.sql.*;

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

//	
//	@FXML
//	private MenuItem logoutMenu;
	
	@FXML
	public void login( ActionEvent event ) throws Exception {
		login = new Login( getUsername() , getPassword() );
		if ( login.matches() ) {
		setStage("/application/Home.fxml", "Messenger Home", "home.css", loginButton);
		}
		 if ( getUsername().isEmpty() || getPassword().isEmpty() ) {
			status.setText( "Username or password cannot be empty." );
		} else status.setText( "Username or password is incorrect." );
	}
	
	@FXML
	public void signup( ActionEvent event ) {
		setStage("/application/Signup.fxml", "Messenger Sign up", "login.css", loginButton);
	}
	
	public String getUsername() {
		return usernameField.getText();
	}
	
	public String getPassword() {
		return passwordField.getText();
	}

}
