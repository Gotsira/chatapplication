package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start( Stage primaryStage ) {
		try {
			Parent root = FXMLLoader.load( getClass().getResource("/application/Login.fxml") );
			Scene scene = new Scene(root);
			scene.getStylesheets().add( getClass().getResource("login.css").toExternalForm() );
			Font.loadFont(getClass().getResourceAsStream("/application/fonts/BebasNeue.otf"), 14);
			primaryStage.setTitle( "Messenger Login" );
			primaryStage.setScene(scene);
//			primaryStage.setResizable( false );
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

