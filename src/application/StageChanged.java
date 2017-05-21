package application;

import java.io.IOException;

import chat.Client;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public abstract class StageChanged extends Application {
	static Client client = new Client("35.185.184.40", 3014);
	
	public void setStage(String resource, String title, String cssFile) {
		try { 
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));     
			Parent root = (Parent) loader.load();          
			Scene scene = new Scene(root); 
			scene.getStylesheets().add( getClass().getResource(cssFile).toExternalForm() );
			Font.loadFont(getClass().getResourceAsStream("/application/fonts/Moon Flower Bold.ttf"), 14);
			Font.loadFont(getClass().getResourceAsStream("/application/fonts/RaiNgan.ttf"), 14);
			Font.loadFont(getClass().getResourceAsStream("/application/fonts/Sunrise International Demo.otf"), 14);
			stage.setResizable( false );
			stage.setTitle(title);
			stage.setScene(scene);
			stage.show();   
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
