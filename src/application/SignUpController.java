package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import users.Register;

/**
 * Controller class for Signup.fxml
 * @author Issaree Srisomboon
 *
 */
public class SignUpController extends StageChanged implements Initializable {
	
	@FXML
	private Label regisStatus;
	
	@FXML
	private TextField regisUsername;
	
	@FXML 
	private PasswordField regisPassword;
	
	@FXML
	private AnchorPane root;
	
	/**
	 * Initializes the stage. It runs automatically as the first method when
	 * this class is initialized.
	 */
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
	
	/**
	 * Handle when the user press signup button.
	 * @param event
	 */
	@FXML
	public void signup( ActionEvent event ) {
		try {
			Register regis = new Register( getRegisUsername() , getRegisPassword() );
			for ( int i=0 ; i<getRegisUsername().length() ; i++ ) {
				if ( !Character.isLetter(getRegisUsername().charAt(i)) && !Character.isDigit(getRegisUsername().charAt(i)) ) {
					regisStatus.setText( "No special characters allowed" );
					return;
				}
			} 
			if ( getRegisUsername().isEmpty() || getRegisPassword().isEmpty() ) {
				regisStatus.setText( "Cannot register with an empty username or password.");
			} else if(regis.check()) {
				regisStatus.setText( "Username already exists.");
			} else {
				regis.add();
				login(event);
			}
		} catch (Exception e) {
			// do nothing
		}
	}
	
	/**
	 * Handle when the user press login button.
	 * @param event
	 */
	@FXML
	public void login( ActionEvent event ) {
		try {
			AnchorPane loginPane = FXMLLoader.load( getClass().getResource("/application/Login.fxml") );
			root.getChildren().setAll(loginPane);
		} catch (IOException e) {
			// do nothing
		}
	}
	
	/**
	 * Get the register username of the user from the textfield.
	 * @return the register username of the user.
	 */
	public String getRegisUsername() {
		return regisUsername.getText();
	}
	
	/**
	 * Get the register password of the user from the textfield.
	 * @return the register password of the user.
	 */
	public String getRegisPassword() {
		return regisPassword.getText();
	}

}
