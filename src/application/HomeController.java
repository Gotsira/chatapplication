package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.collections.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.FileChooser;
import users.DisplayFriends;
import users.EditPicture;
import users.GetPicture;
import javafx.stage.FileChooser.ExtensionFilter;

public class HomeController extends StageChanged implements Initializable{
	
	private DisplayFriends display;
	private GetPicture pic;
	private Image image = null;
	ArrayList<String> list = null;
	
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
			pic = new GetPicture(username);
			display = new DisplayFriends( username );
			if(list == null) {
				list = display.display();				
			}
			freindTitle.setText( "Friends (" + list.size() + ")");
			ObservableList<String> observerList = FXCollections.<String>observableArrayList( list );
			friendList.setItems(observerList);
			friendList.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );
			if(image != null) {
				userPicture.setImage(image);
			}
			Image image = SwingFXUtils.toFXImage(convertStringtoImg(pic.get()), null);
			userPicture.setImage(image);
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
	public void logout( ActionEvent event ) throws IOException {
		setStage("/application/Login.fxml", "Messenger Login" , "login.css");
		client.sendToServer("disconnect " + username);
		client.closeConnection();
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
		if ( file != null ) {
			Image image = new Image(file.toURI().toString());
			userPicture.setImage(image);
			EditPicture edit = new EditPicture(username, convertImgtoString(file.getAbsolutePath()));
			edit.setImage();
		}
	}
	
	public void newChat( ActionEvent event ) {
		ObservableList<String> freind = friendList.getSelectionModel().getSelectedItems();
	}
	
}
