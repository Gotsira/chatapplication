package application;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import users.Login;

/**
 * Controller class for Login.fxml
 * @author Issaree Srisomboon
 *
 */
public class LoginController extends StageChanged implements Initializable {
	private Login login;
	
	@FXML
	private AnchorPane root;
	
	@FXML
	private Label status;
	
	@FXML
	private TextField usernameField;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private Button loginButton;
	
	/**
	 * Initializes the stage. It runs automatically as the first method when
	 * this class is initialized.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if ( !Main.isWelcome ) loadWelcomeScreen();
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
	
	/**
	 * Handle when the user press login button.
	 * @param event
	 */
	@FXML
	public void login( ActionEvent event ) {
		try {
			login = new Login( getUsername() , getPassword() );
			if ( login.matches() ) {
				username = getUsername();
				client.openConnection();
				client.sendToServer("connect " + getUsername());
//				setStage("/application/Home.fxml", "Messenger Home", "home.css");
				try {
					Stage stage = new Stage();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Home.fxml"));
					Parent root = (Parent) loader.load();
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
					stage.getIcons().add(new Image("/images/logo.png"));
					stage.setResizable(false);
					stage.setTitle("Messenger Home");
					stage.setOnCloseRequest(e -> {
						try {
							client.sendToServer("disconnect " + username);
							client.closeConnection();
						} catch (IOException e1) {
							//do nothing
						}
						Platform.exit();
						System.exit(0);
					});
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					// do nothing
				}
				hideWindow(event);
			}
			if ( getUsername().isEmpty() || getPassword().isEmpty() ) {
				status.setText( "Username or password cannot be empty." );
			} else status.setText( "Username or password is incorrect." );
		} catch (Exception e) {
			// do nothing
		}
	}
	
	/**
	 * Handle when the user press signup button.
	 * @param event
	 */
	@FXML
	public void signup( ActionEvent event ) {
		AnchorPane signupPane;
		try {
			signupPane = FXMLLoader.load( getClass().getResource("/application/Signup.fxml") );
			root.getChildren().setAll(signupPane);
		} catch (IOException e) {
			// do nothing
		}
	}
	
	/**
	 * Get username of the user from the textfield.
	 * @return username of the user
	 */
	public String getUsername() {
		return usernameField.getText();
	}
	
	/**
	 * Get password of the user from the textfield.
	 * @return password of the user
	 */
	public String getPassword() {
		return passwordField.getText();
	}
	
	/**
	 * Responsibility for pre-loading page at first opening the application. 
	 */
	public void loadWelcomeScreen() {
		Main.isWelcome = true;
		try {
			AnchorPane welcomePane = FXMLLoader.load( getClass().getResource("/application/Welcome.fxml") );
			root.getChildren().setAll(welcomePane);
			FadeTransition fadeOut = new FadeTransition( Duration.seconds(5) , welcomePane );
			fadeOut.setFromValue(1);
			fadeOut.setToValue(0);
			fadeOut.setCycleCount(1);
			fadeOut.play();
			fadeOut.setOnFinished( e -> {
				try {
					AnchorPane loginPane = FXMLLoader.load( getClass().getResource("/application/Login.fxml") );
					root.getChildren().setAll(loginPane);
				} catch (IOException io) {
					io.printStackTrace();
				}
			});
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
