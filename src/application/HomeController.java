package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import users.DisplayFriends;
import users.EditPicture;
import javafx.stage.FileChooser.ExtensionFilter;

public class HomeController extends StageChanged implements Initializable{
	
	private DisplayFriends display;
	
	@FXML
	private ListView<String> friendList;
	
	@FXML
	private Label usernameLabel;
	
	@FXML
	private ImageView userPicture;
	
	@FXML
	private TitledPane freindTitle;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		usernameLabel.setText( "USERNAME: " + username );
		try {
			display = new DisplayFriends( username );
			ArrayList<String> list = display.display();
			freindTitle.setText( "Friends (" + list.size() + ")");
			ObservableList<String> observerList = FXCollections.<String>observableArrayList( list );
			friendList.setItems(observerList);
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
	
	public void editPicture(ActionEvent event) throws Exception {
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().addAll(new ExtensionFilter( "Image Files", "*.png", "*.jpg" ));
		File file = chooser.showOpenDialog(null);
		EditPicture edit = new EditPicture(username, convertImgtoString(file.getAbsolutePath()));
		if ( file != null ) {
			Image image = new Image(file.toURI().toString());
			userPicture.setImage(image);
			edit.setImage();
		}
	}
	
}
