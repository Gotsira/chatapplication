package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import users.Login;

import java.io.IOException;
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
	
	@FXML
	public void login( ActionEvent event ) throws Exception {
		login = new Login( getUsername() , getPassword() );
		if ( login.matches() ) {
			try {
				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Home.fxml"));     
				Parent root = (Parent)loader.load();          
				HomeController home = loader.<HomeController>getController();
				home.getUsername( getUsername() );
				Scene scene = new Scene(root); 
				scene.getStylesheets().add( getClass().getResource("home.css").toExternalForm() );
				Font.loadFont(getClass().getResourceAsStream("/application/fonts/Moon Flower Bold.ttf"), 14);
				Font.loadFont(getClass().getResourceAsStream("/application/fonts/RaiNgan.ttf"), 14);
				Font.loadFont(getClass().getResourceAsStream("/application/fonts/Sunrise International Demo.otf"), 14);
				stage.setResizable( false );
				stage.setTitle( "Messenger Home" );
				stage.setScene(scene);
				stage.show();   
				client.openConnection();
				client.sendToServer("connect " + getUsername());
			} catch (IOException e) {
				e.printStackTrace();
			}
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
