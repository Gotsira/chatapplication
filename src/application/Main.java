package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main class for running the application.
 * @author Issaree Srisomboon
 *
 */
public class Main extends Application {
	
	public static boolean isWelcome = false;

	/**
	 * First stage. It runs automatically as the first method when
	 * run the main class.
	 */
	@Override
	public void start( Stage stage ) {
		try {
			Parent root = FXMLLoader.load( getClass().getResource("/application/Login.fxml") );
			Scene scene = new Scene(root);
			scene.getStylesheets().add( getClass().getResource("login.css").toExternalForm() );
			stage.getIcons().add(new Image("/images/logo.png"));
			stage.setTitle("Messeger");
			stage.setHeight(625);
			stage.setWidth(500);
			stage.setScene(scene);
			stage.setResizable( false );
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Running the application.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}

