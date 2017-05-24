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
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import users.DisplayFriends;
import users.EditPicture;
import users.GetPicture;
import javafx.stage.FileChooser.ExtensionFilter;

public class HomeController extends StageChanged implements Initializable {

	private DisplayFriends display;
	private GetPicture pic;
	private Image image = null;
	private ObservableList<String> observerList;
	private ArrayList<String> list = null;

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
		set();
		Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				pic = new GetPicture(username);
				if (image == null) {
					image = SwingFXUtils.toFXImage(convertStringtoImg(pic.get()), null);
				}
				return null;
			}
			
			@Override
			protected void succeeded() {
				userPicture.setImage(image);
			}
		};
		new Thread(task).start();
		refreshFreind();
		homeController = this;
		
	}

	@FXML
	public void chatRoomAccess(ActionEvent event) {
		setStage("/application/ChatRoom.fxml", "Messenger Chat Room", "chatroom.css");
		hideWindow(event);
	}

	@FXML
	public void logout(ActionEvent event) {
		setStage("/application/Login.fxml", "Messenger Login", "login.css");
		try {
			client.sendToServer("disconnect " + username);
			client.closeConnection();
		} catch (IOException e) {
			// do nothing
		}
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

	public void editPicture(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg"));
		File file = chooser.showOpenDialog(null);
		if (file != null) {
			Image image = new Image(file.toURI().toString());
			userPicture.setImage(image);
			EditPicture edit;
			try {
				edit = new EditPicture(username, convertImgtoString(file.getAbsolutePath()));
				edit.setImage();
			} catch (Exception e) {
				// do nothing
			}
			
		}
	}

	public void newChat(ActionEvent event) {
		ObservableList<String> selected = friendList.getSelectionModel().getSelectedItems();
		if (!selected.isEmpty()) {
			friend = selected;
			friendUser = friend.toString().substring(1, friend.toString().length() - 1);
			String [] friendsList = friendUser.split(",");
			friendUser = "";
			for(int i = 0 ; i < friendsList.length; i++) {
				friendUser += friendsList[i].trim();
				if(i != friendsList.length - 1) {
					friendUser += ",";
				}
			}
			setStage("/application/Chat.fxml", "Messenger Chat", "chat.css");
		}
	}

	public void refreshFreind() {
		Task<ObservableList<String>> task = new Task<ObservableList<String>>() {

			@Override
			protected ObservableList<String> call() throws Exception {
				display = new DisplayFriends(username);
				list = display.display();
				Collections.sort(list);
				observerList = FXCollections.<String>observableArrayList(list);
				return observerList;
			}
			
			@Override
			protected void succeeded() {
	            super.succeeded();
	            freindTitle.setText("Friends (" + list.size() + ")");
	            friendList.setItems(observerList);
	    		friendList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	        }
		};

		new Thread(task).start();
		
	}
}
