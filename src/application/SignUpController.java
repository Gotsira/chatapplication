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
import users.Register;

public class SignUpController extends StageChanged implements Initializable {
	
	@FXML
	private Label regisStatus;
	
	@FXML
	private TextField regisUsername;
	
	@FXML 
	private PasswordField regisPassword;
	
	@FXML
	private Button regisButton;
	
	@FXML
	private Button signinButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventHandler<ActionEvent> signupHandle = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					signup(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		regisUsername.setOnAction(signupHandle);
		regisPassword.setOnAction(signupHandle);
	}
	
	@FXML
	public void signup( ActionEvent event ) throws Exception {
		Register regis = new Register( getRegisUsername() , getRegisPassword() );
		if ( getRegisUsername().isEmpty() || getRegisPassword().isEmpty() ) {
			regisStatus.setText( "Cannot register with an empty username or password.");
		} else if(regis.check()) {
			regisStatus.setText( "Username already exists.");
		}
		else {
			regis.add();
			login(event);
		}
	}
	
	@FXML
	public void login( ActionEvent event ) {
		setStage("/application/Login.fxml", "Messenger Login" , "login.css");
		hideWindow(event);
	}
	
	public String getRegisUsername() {
		return regisUsername.getText();
	}
	
	public String getRegisPassword() {
		return regisPassword.getText();
	}

}
