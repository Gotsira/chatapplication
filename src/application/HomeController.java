package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import users.DisplayFriends;
import users.EditPicture;
import users.GetPicture;
import javafx.stage.FileChooser.ExtensionFilter;

public class HomeController extends StageChanged implements Initializable {

	private DisplayFriends display;
	private GetPicture pic;
	private Image image = null;
	ArrayList<String> list = null;

	@FXML
	private BorderPane root;

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
		usernameLabel.setText("USERNAME: " + username);
		try {
			pic = new GetPicture(username);
			display = new DisplayFriends(username);
			if (list == null) {
				list = display.display();
			}
			Collections.sort(list);
			freindTitle.setText("Friends (" + list.size() + ")");
			ObservableList<String> observerList = FXCollections.<String>observableArrayList(list);
			friendList.setItems(observerList);
			friendList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			if (image == null) {
				image = SwingFXUtils.toFXImage(convertStringtoImg(pic.get()), null);
			}
			userPicture.setImage(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void chatRoomAccess(ActionEvent event) {
		setStage("/application/ChatRoom.fxml", "Messenger Chat Room", "chatroom.css");
		hideWindow(event);
		// BorderPane chatRoomPane = FXMLLoader.load(
		// getClass().getResource("/application/ChatRoom.fxml") );
		// root.getChildren().setAll(chatRoomPane);
	}

	@FXML
	public void logout(ActionEvent event) throws IOException {
		setStage("/application/Login.fxml", "Messenger Login", "login.css");
		client.sendToServer("disconnect " + username);
		client.closeConnection();
		hideWindow(event);
	}

	@FXML
	public void addFreind(ActionEvent event) {
		setStage("/application/AddFriend.fxml", "Messenger Add Friend", "adddeletefriend.css");
	}

	@FXML
	public void deleteFriend(ActionEvent event) {
		setStage("/application/DeleteFriend.fxml", "Messenger Delete Friend", "adddeletefriend.css");
	}

	public void editPicture(ActionEvent event) throws Exception {
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg"));
		File file = chooser.showOpenDialog(null);
		if (file != null) {
			Image image = new Image(file.toURI().toString());
			userPicture.setImage(image);
			EditPicture edit = new EditPicture(username, convertImgtoString(file.getAbsolutePath()));
			edit.setImage();
		}
	}

	public void newChat(ActionEvent event) throws IOException {
		ObservableList<String> selected = friendList.getSelectionModel().getSelectedItems();
		if (!selected.isEmpty()) {
			friend = selected;
			friendUser = friend.toString().substring(1, friend.toString().length() - 1);
			setStage("/application/Chat.fxml", "Messenger Chat", "chat.css");
		}
	}

}
