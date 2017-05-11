package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
//	private Button chatRoomButton;
//	
//	@FXML
//	private Button homeButton;
//	
//	@FXML
//	private MenuItem logoutMenu;
	
	@FXML
	public void login( ActionEvent event ) throws Exception {
		login = new Login( getUsername() , getPassword() );
//		if ( login.matches() ) {
			setStage("/application/Home.fxml", "Messenger Home", "home.css", loginButton);
		 if ( getUsername().isEmpty() || getPassword().isEmpty() ) {
			status.setText( "Username or password cannot be empty." );
		} else status.setText( "Username or password is incorrect." );
	}
	
	@FXML
	public void signup( ActionEvent event ) {
		setStage("/application/Signup.fxml", "Messenger Sign up", "login.css", loginButton);
	}
	
//	@FXML
//	public void loginAccess( ActionEvent event ) throws Exception {
//		Register r = new Register(regisUsername.getText(), regisPassword.getText());
//		r.add();
//		setStage("/application/Login.fxml", "Messenger Login" , regisButton , "login.css" );
//	}
//	
//	@FXML
//	public void chatRoomAccess( ActionEvent event ) {
//		setStage("/application/ChatRoom.fxml", "Messenger Chat Room" , homeButton , "home.css" );
//	}
//	
//	@FXML
//	public void homeAccess( ActionEvent event ) {
//		setStage("/application/Home.fxml", "Messenger Home" , homeButton , "home.css" );
//	}
//	
	public String getUsername() {
		return usernameField.getText();
	}
	
	public String getPassword() {
		return passwordField.getText();
	}

}
