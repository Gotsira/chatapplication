package application;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class SaveImageController extends StageChanged {
	BufferedImage img;
	String name;
	
	@FXML
	private ImageView imageView;
	
	@FXML
	private Button sendButton;
	
	@FXML
	public void saveImage(ActionEvent event) {
		File file = new File("C:/Users/USER/Pictures/" + name);
		try {
			ImageIO.write(img, "jpg", file);
		} catch (IOException e) {
			// do nothing
		}
	}
	
	public void image(Object message) {
		String msg = message.toString();
		String[] keep = msg.split(":");
		name = keep[keep.length - 1];
		img = convertStringtoImg(message.toString().substring(0, msg.length() - keep[keep.length - 1].length()));
	}

}
