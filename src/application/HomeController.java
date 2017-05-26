package application;

import java.io.*;
import java.net.URL;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import java.util.*;

import javafx.collections.*;
import javafx.concurrent.Task;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.paint.Paint;
import javafx.stage.*;
import users.*;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.*;

/**
 * Controller class for Home.fxml. 
 * @author Issaree Srisomboon
 *
 */
public class HomeController extends StageChanged implements Initializable {

	private DisplayFriends display;
	private NotificationType noti = NotificationType.ERROR;
	private TrayNotification tray = new TrayNotification();
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

	/**
	 * Initializes the stage. It runs automatically as the first method when
	 * this class is initialized.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		usernameLabel.setText("USERNAME: " + username);
		client.addHome(this);
		Task<Void> getPicTask = new Task<Void>() {

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
		new Thread(getPicTask).start();
		refreshFreind();
		homeController = this;
	}

	/**
	 * Handle when the user press logout button.
	 * @param event
	 */
	@FXML
	public void logout(ActionEvent event) {
		if (!client.exists()) {
			try {
				client.sendToServer("disconnect " + username);
				client.closeConnection();
			} catch (IOException e) {
				// do nothing
			} finally {
				setStage("/application/Login.fxml", "Messenger Login", "login.css");
				hideWindow(event);
			}
		} else {
			tray.setNotificationType(noti);
			tray.setTitle("Chats");
			tray.setMessage("Please close all of your chats!");
			tray.setRectangleFill(Paint.valueOf("#e51902"));
			tray.setAnimationType(AnimationType.POPUP);
			tray.showAndDismiss(Duration.seconds(2));
		}
	}

	/**
	 * Handle when the user press add friend menu.
	 * @param event
	 */
	@FXML
	public void addFreind(ActionEvent event) {
		setStage("/application/AddFriend.fxml", "Messenger Add Friend", "adddeletefriend.css");
	}

	/**
	 * Handle when the user press delete friend menu.
	 * @param event
	 */
	@FXML
	public void deleteFriend(ActionEvent event) {
		setStage("/application/DeleteFriend.fxml", "Messenger Delete Friend", "adddeletefriend.css");
	}

	/**
	 * Handle when the user press refresh button.
	 * @param event
	 */
	@FXML
	public void refreshHandle(ActionEvent event) {
		refreshFreind();
	}

	/**
	 * For changing the user's profile picture.
	 * The user can get the image from his or her repository.
	 * @param event
	 */
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

	/**
	 * Handle when the user press chat now button.
	 * It get the friend information when the user select the friend.
	 * @param event
	 */
	public void newChat(ActionEvent event) {
		ObservableList<String> selected = friendList.getSelectionModel().getSelectedItems();
		if (!selected.isEmpty()) {
			friend = selected;
			friendUser = friend.toString().substring(1, friend.toString().length() - 1);
			// setStage("/application/Chat.fxml", "Messenger Chat", "chat.css");
			try {
				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Chat.fxml"));
				Parent root = (Parent) loader.load();
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("chat.css").toExternalForm());
				stage.getIcons().add(new Image("/images/logo.png"));
				stage.setResizable(false);
				stage.setTitle("Messenger Chat");
				stage.setOnCloseRequest(e -> {
					try {
						client.deleteChat(friendUser);
					} catch (Exception e1) {
						// do nothing
					}
				});
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				// do nothing
			}
		}
	}

	/**
	 * Update the friend list.
	 */
	public void refreshFreind() {
		Task<ObservableList<String>> refreshTask = new Task<ObservableList<String>>() {

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
				friendList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			}
		};

		new Thread(refreshTask).start();

	}
}
