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
import users.Register;

import java.sql.*;

public class MainController {
	
	private Login login;
	
	@FXML
	private TextArea status;
	
	@FXML
	private TextField usernameField;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private Button loginButton;
	
	@FXML
	private TextField regisUsername;
	
	@FXML 
	private PasswordField regisPassword;
	
	@FXML
	private Button regisButton;
	
	@FXML
	private Button chatRoomButton;
	
	@FXML
	private Button homeButton;
	
	@FXML
	private MenuItem logoutMenu;
	
	@FXML
	public void login( ActionEvent event ) throws Exception {
		login = new Login( usernameField.getText(), passwordField.getText() );
		if ( login.matches() ) {
			setStage("/application/Home.fxml", "Messenger Home" , loginButton , "home.css" );
		} else status.setText( "Username or password is either incorrect or not registered." );
	}
	
	@FXML
	public void signup( ActionEvent event ) {
		setStage("/application/Signup.fxml", "Messenger Sign up" , loginButton , "login.css" );
	}
	
	@FXML
	public void loginAccess( ActionEvent event ) throws Exception {
		Register r = new Register(regisUsername.getText(), regisPassword.getText());
		r.add();
		setStage("/application/Login.fxml", "Messenger Login" , regisButton , "login.css" );
	}
	
	@FXML
	public void chatRoomAccess( ActionEvent event ) {
		setStage("/application/ChatRoom.fxml", "Messenger Chat Room" , homeButton , "home.css" );
	}
	
	@FXML
	public void homeAccess( ActionEvent event ) {
		setStage("/application/Home.fxml", "Messenger Home" , homeButton , "home.css" );
	}
	
	public String getUsername() {
		return usernameField.getText();
	}
	
	public String getPassword() {
		return passwordField.getText();
	}
	
	/**
	 * Set a new stage from the given resource and set title of its.
	 * And also call handleCloseButtonAction method to close the stage.
	 * @param resource is a path to get FXML file
	 * @param title of the stage
	 */
	public void setStage( String resource , String title , Button button , String cssFile ) {
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load( getClass().getResource( resource ) );
			Scene scene = new Scene(root , 300 , 400);
			scene.getStylesheets().add( getClass().getResource( cssFile ).toExternalForm() );
			primaryStage.setTitle( title );
			primaryStage.setScene(scene);
			primaryStage.setResizable( false );
			primaryStage.show();
			handleCloseButtonAction( button );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Closing the current stage.
	 * @param button for handling closing the stage.
	 */
	public void handleCloseButtonAction( Button button ) {
		Stage closeStage = (Stage) button.getScene().getWindow();
	    closeStage.close();
	}

}
