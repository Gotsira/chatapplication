package application;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

import javax.imageio.ImageIO;

import chat.Client;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.Stage;

public class StageChanged {
	static Client client = new Client("35.185.184.40", 3014);
	static String username = null;
	static String friendUser = null;
	static String keep = "";
	static ObservableList<String> friend = null;

	public String convertImgtoString(String filePath) {
		File imgPath = new File(filePath);
		BufferedImage bufferedImage;
		String message = null;
		try {
			bufferedImage = ImageIO.read(imgPath);
			bufferedImage.getScaledInstance(5, 5, Image.SCALE_FAST);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "png", baos);
			byte[] imageByteArray = baos.toByteArray();
			message = Arrays.toString(imageByteArray);
		} catch (IOException e) {
			// do nothing
		}
		return message;
	}

	public BufferedImage convertStringtoImg(String image) {
		if (image == null) {
			return null;
		}
		String[] byteValues = image.substring(1, image.length() - 1).split(",");
		byte[] imgInByte = new byte[byteValues.length];
		for (int i = 0; i < byteValues.length; i++) {
			imgInByte[i] = Byte.parseByte(byteValues[i].trim());
		}
		ByteArrayInputStream ms = new ByteArrayInputStream(imgInByte);
		try {
			return ImageIO.read(ms);
		} catch (IOException e) {
			// do nothing
		}
		return null;
	}

	public void setStage(String resource, String title, String cssFile) {
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(cssFile).toExternalForm());
			stage.setResizable(false);
			stage.setTitle(title);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Hide current page.
	 * 
	 * @param event
	 */
	@FXML
	public void hideWindow(ActionEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide();
	}
}
