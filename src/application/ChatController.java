package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

	private String friend = friendUser;
	private String friendImage;
	static File imageFile;

	@FXML
	private Button sendButton;

	@FXML
	private TextField field;

	@FXML
	private MenuItem sendPhoto;

	@FXML
	private MenuItem sendContact;

	@FXML
	private Label name;

	@FXML
	private TextArea message;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		name.setText(friend);
		client.addChat(this);
		EventHandler<ActionEvent> sendHandle = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				send(event);
			}
		};

		field.setOnAction(sendHandle);
	}

	@FXML
	public void send(ActionEvent event) {
		try {
			client.sendToServer("message " + name.getText() + " " + getText() + " " + username);
			message.appendText(username + ": " + getText() + "\n");
			field.setText("");
		} catch (IOException e) {
			// do nothing
		}
	}

	public void display(Object friendMessage) {
		message.appendText(friendMessage.toString() + "\n");
	}

	public void displayImage(Object friendImage) {
		setImage((String) friendImage);
		setStage("/application/SaveImage.fxml", "Save Image", "home.css");
	}
	
	public void setImage(String friendImage) {
		this.friendImage = friendImage;
	}
	
	public String getImage() {
		return this.friendImage;
	}

	public void photoChooser(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		imageFile = chooser.showOpenDialog(null);
		if (imageFile != null) {
			try {
				client.sendToServer("image " + name.getText() + " " + convertImgtoString(imageFile.getAbsolutePath()) + " " + username);
				message.appendText("Image Sent\n");
			} catch (IOException e) {
				message.appendText("Failed to send image");
			}
		}
	}

	public String getUser() {
		return username;
	}

	public String getFriend() {
		return this.friend;
	}

	public String getText() {
		return field.getText();
	}

	public void setFriend(String nameFreind) {
		this.friend = nameFreind;
	}
}
