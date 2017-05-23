package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static boolean isWelcome = false;

	@Override
	public void start( Stage stage ) {
		try {
			Parent root = FXMLLoader.load( getClass().getResource("/application/Login.fxml") );
			Scene scene = new Scene(root);
			scene.getStylesheets().add( getClass().getResource("login.css").toExternalForm() );
			scene.getStylesheets().add( getClass().getResource("home.css").toExternalForm() );
			scene.getStylesheets().add( getClass().getResource("chatroom.css").toExternalForm() );
			stage.setHeight(625);
			stage.setWidth(500);
			stage.setScene(scene);
			stage.setResizable( false );
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

