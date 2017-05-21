package application;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class HomeController extends StageChanged {
	
	@FXML
	private Label username;
	
	@FXML
	private ImageView profilePicture = null;
	
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
//		setStage("/application/AddFriend.fxml", "Messenger Add Friend" , "adddeletefriend.css");
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AddFriend.fxml"));     
			Parent root = (Parent)loader.load();          
			AddFriendController add = loader.<AddFriendController>getController();
			add.setUserName( username.getText() );
			Scene scene = new Scene(root); 
			scene.getStylesheets().add( getClass().getResource("adddeletefriend.css").toExternalForm() );
			Font.loadFont(getClass().getResourceAsStream("/application/fonts/Moon Flower Bold.ttf"), 14);
			Font.loadFont(getClass().getResourceAsStream("/application/fonts/RaiNgan.ttf"), 14);
			Font.loadFont(getClass().getResourceAsStream("/application/fonts/Sunrise International Demo.otf"), 14);
			stage.setResizable( false );
			stage.setTitle( "Messenger Add Friend" );
			stage.setScene(scene);
			stage.show();   
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void deleteFriend( ActionEvent event ) {
//		setStage("/application/DeleteFriend.fxml", "Messenger Delete Friend" , "adddeletefriend.css");
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/DeleteFriend.fxml"));     
			Parent root = (Parent)loader.load();
			DeleteFriendController delete = loader.<DeleteFriendController>getController();
			delete.setUserName( username.getText() );
			Scene scene = new Scene(root); 
			scene.getStylesheets().add( getClass().getResource("adddeletefriend.css").toExternalForm() );
			Font.loadFont(getClass().getResourceAsStream("/application/fonts/Moon Flower Bold.ttf"), 14);
			Font.loadFont(getClass().getResourceAsStream("/application/fonts/RaiNgan.ttf"), 14);
			Font.loadFont(getClass().getResourceAsStream("/application/fonts/Sunrise International Demo.otf"), 14);
			stage.setResizable( false );
			stage.setTitle( "Messenger Delete Friend" );
			stage.setScene(scene);
			stage.show();   
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getUsername( String name ) {
		username.setText( name );
	}
	
	public void editPicture(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().addAll(new ExtensionFilter( "Image Files", "*.png", "*.jpg", "*.gif" ));
		File file = chooser.showOpenDialog(null);
		if ( file != null ) {
			Image image = new Image(file.toURI().toString());
			profilePicture.setImage(image);
		}
	}
	
}
