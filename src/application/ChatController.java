package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import users.GetPicture;

public class ChatController extends StageChanged implements Initializable {

	private String friend = friendUser;
	private Image friendIm;
	static File imageFile;

	@FXML
	private Button sendButton;

	@FXML
	private TextField field;

	@FXML
	private Label name;
	
	@FXML
	private ImageView image;

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
		
		Task<Void> loadImageTask = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				GetPicture freindDisplay = new GetPicture(friend);
				friendIm = SwingFXUtils.toFXImage(convertStringtoImg(freindDisplay.get()), null);
				return null;
			}
			
			@Override
			protected void succeeded() {
				image.setImage(friendIm);
			}
		};
		
		new Thread(loadImageTask).start();

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
