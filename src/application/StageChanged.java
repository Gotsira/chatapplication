package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public abstract class StageChanged {
	
	public void setStage(String resource, String title, String cssFile, Button button) {
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
