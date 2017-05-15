package application;

import java.awt.Desktop.Action;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public abstract class StageChanged {
	
	public void setStage(String resource, String title, String cssFile) {
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load( getClass().getResource( resource ) );
			Scene scene = new Scene(root);
			scene.getStylesheets().add( getClass().getResource( cssFile ).toExternalForm() );
			Font.loadFont(getClass().getResourceAsStream("/application/fonts/Moon Flower Bold.ttf"), 14);
			Font.loadFont(getClass().getResourceAsStream("/application/fonts/RaiNgan.ttf"), 14);
			Font.loadFont(getClass().getResourceAsStream("/application/fonts/Sunrise International Demo.otf"), 14);
			primaryStage.setTitle( title );
			primaryStage.setScene(scene);
			primaryStage.setResizable( false );
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Hide current page.
	 * @param event
	 */
	@FXML
	public void hideWindow( ActionEvent event ) {
		((Node) event.getSource()).getScene().getWindow().hide();
	}
}
