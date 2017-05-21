package application;

import java.io.File;
import java.io.IOException;

import chat.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ChatController extends StageChanged {
	
	@FXML
	private Button sendButton;
	
	@FXML
	private TextField field;

	@FXML
	private MenuItem sendPhoto;
	
	@FXML
	private MenuItem sendVideo;
	
	@FXML
	private MenuItem sendContact;
	
	@FXML
	private Label name;
	
	@FXML
	private TextArea message;
	
	@FXML
	public void send(ActionEvent event) throws IOException {
		client.sendToServer("message " + field.getText());
	}
	
	public void displayMessage(String message) {
		
	}

	public void photoChooser(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().addAll(new ExtensionFilter( "Image Files", "*.png", "*.jpg", "*.gif" ));
		File file = chooser.showOpenDialog(null);
		if ( file != null ) {
			try {
				client.sendToServer("image " + client.convertImgtoString(file.getName()));
				message.appendText("Image Sent");
			} catch (IOException e) {
				message.appendText("Failed to send image");
			}
		}
	}
	
	public void videoChooser(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().addAll(new ExtensionFilter( "Video Files", "*.mp4" ));
		File file = chooser.showOpenDialog(null);
		if ( file != null ) field.setText( file.getName() );
	}
}
