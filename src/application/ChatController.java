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
import users.GetPicture;

/**
 * Controller class for Chat.fxml.
 * @author Issaree Srisomboon
 *
 */
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

	/**
	 * Initializes the stage. It runs automatically as the first method when
	 * this class is initialized.
	 */
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

	/**
	 * Handle when the user press the send button to send the message conditionally.
	 * @param event
	 */
	@FXML
	public void send(ActionEvent event) {
		try {
			if (!getText().trim().isEmpty()) {
				client.sendToServer("message " + name.getText() + " " + getText() + " " + username);
				message.appendText(username + ": " + getText() + "\n");
				field.setText("");
			}
		} catch (IOException e) {
			// do nothing
		}
	}

	/**
	 * Show text message in the textarea when someone send a message to the user.
	 * @param friendMessage is the message from the client
	 */
	public void display(Object friendMessage) {
		message.appendText(friendMessage.toString());
		client.setMessage("");
	}

	/**
	 * Text from the user that send in the chat.
	 * @return text is the user's text that type in the textfield
	 */
	public String getText() {
		return field.getText();
	}
	
	/**
	 * Get the name of the friend at current chat page.
	 * @return the name of the friend at current chat page
	 */
	public String getFriend() {
		return this.friend;
	}
}
