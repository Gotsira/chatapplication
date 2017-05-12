package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import users.Register;

public class SignUpController extends StageChanged {
	
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
		setStage("/application/Login.fxml", "Messenger Login" , "login.css" , regisButton);
	}
	
	public String getRegisUsername() {
		return regisUsername.getText();
	}
	
	public String getRegisPassword() {
		return regisPassword.getText();
	}

}
