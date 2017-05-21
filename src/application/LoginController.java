package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import users.Login;


public class LoginController extends StageChanged implements Initializable {
	private Login login;
	
	@FXML
	private Label status;
	
	@FXML
	private TextField usernameField;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private Button loginButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventHandler<ActionEvent> loginHandle = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					login(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		usernameField.setOnAction(loginHandle);
		passwordField.setOnAction(loginHandle);
	}
	
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
