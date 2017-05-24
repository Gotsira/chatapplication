package application;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import users.GetPicture;

public class SaveImageController extends ChatController implements Initializable {
	int count = 1;
	BufferedImage img;
	String name;

	@FXML
	private ImageView imageView;

	@FXML
	private Button sendButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		image(getImage());
		Image image = SwingFXUtils.toFXImage(img, null);
		imageView.setImage(image);
	}

	@FXML
	public void saveImage(ActionEvent event) {
		image(getImage());
		while (true) {
			try {
				File file = new File("C:/Users/USER/Pictures/" + name + count);
				ImageIO.write(img, "jpg", file);
				break;
			} catch (IOException e) {
				// do nothing
			}
			count++;
			continue;
		} 
	}

	public void image(Object message) {
		String msg = message.toString();
		String[] keep = msg.split(":");
		name = keep[0];
		img = convertStringtoImg(message.toString().substring(keep[0].length() - 1, msg.length() - 1));
	}

}
