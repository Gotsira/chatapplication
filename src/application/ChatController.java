package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import chat.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ChatController extends StageChanged implements Initializable {
	
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventHandler<ActionEvent> sendHandle = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					send(event);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		field.setOnAction(sendHandle);
	}
	
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
