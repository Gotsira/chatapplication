package application;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

import chat.Client;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Class for
 * 
 * @author Issaree Srisomboon
 *
 */
public class StageChanged {
	static Client client = new Client("35.185.184.40", 3014);
	static String username = null;
	static String friendUser = null;
	static ObservableList<String> friend = null;
	static HomeController homeController;

	/**
	 * Converts the image in the form of files to byte array and then to String.
	 * 
	 * @param filePath
	 *            is the location of the image.
	 * @return the String form of the image.
	 */
	public String convertImgtoString(String filePath) {
		File imgPath = new File(filePath);
		BufferedImage bufferedImage;
		String message = null;
		try {
			bufferedImage = ImageIO.read(imgPath);
			bufferedImage = Scalr.resize(bufferedImage, 150);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "png", baos);
			byte[] imageByteArray = baos.toByteArray();
			message = Arrays.toString(imageByteArray);
		} catch (IOException e) {
			// do nothing
		}
		return message;
	}

	/**
	 * Converts the image from String to byte array and then to image file.
	 * 
	 * @param image
	 *            is the image of the file in the form of String.
	 * @return the image of the String.
	 */
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
			return Scalr.resize(ImageIO.read(ms), 150);
		} catch (IOException e) {
			// do nothing
		}
		return null;
	}

	/**
	 * Set the new stage to open the given fxml file and add the title and css
	 * file to its.
	 * 
	 * @param resource
	 *            is the resource of the given fxml file
	 * @param title
	 *            is the title of the new stage
	 * @param cssFile
	 *            is for decorating of the new stage
	 */
	public void setStage(String resource, String title, String cssFile) {
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(cssFile).toExternalForm());
			stage.getIcons().add(new Image("/images/logo.png"));
			stage.setResizable(false);
			stage.setTitle(title);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// do nothing
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
