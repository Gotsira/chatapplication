package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import users.DisplayFriends;
import javafx.stage.FileChooser.ExtensionFilter;

public class HomeController extends StageChanged implements Initializable{
	
	private ListView<String> friendList;
	private DisplayFriends display;
	
	@FXML
	private Label usernameLabel;
	
	@FXML
	private ImageView userPicture;
	
	@FXML
	private Label userStatus;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		usernameLabel.setText( username );
		friendList = new ListView<>();
		try {
			display = new DisplayFriends( username );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void chatRoomAccess( ActionEvent event ) {
		setStage("/application/ChatRoom.fxml", "Messenger Chat Room", "chatroom.css");
		hideWindow(event);
	}
	
	@FXML
	public void logout( ActionEvent event ) {
		setStage("/application/Login.fxml", "Messenger Login" , "login.css");
		hideWindow(event);
	}
	
	@FXML
	public void addFreind( ActionEvent event ) {
		setStage("/application/AddFriend.fxml", "Messenger Add Friend" , "adddeletefriend.css");
	}
	
	@FXML
	public void deleteFriend( ActionEvent event ) {
		setStage("/application/DeleteFriend.fxml", "Messenger Delete Friend" , "adddeletefriend.css");
	}
	
	public void editPicture(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().addAll(new ExtensionFilter( "Image Files", "*.png", "*.jpg" ));
		File file = chooser.showOpenDialog(null);
		if ( file != null ) {
			Image image = new Image(file.toURI().toString());
			userPicture.setImage(image);
		}
	}
	
}
