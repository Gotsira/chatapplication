package application;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import chat.Client;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StageChanged {
	static Client client = new Client("35.185.184.40", 3014);
	static String username = null;
	static String friendUser = null;
	static String keep = "";
	static ObservableList<String> friend = null;

	public String convertImgtoString(String filePath) throws IOException {
		File imgPath = new File(filePath);
		BufferedImage bufferedImage = ImageIO.read(imgPath);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "png", baos);
		byte[] imageByteArray = baos.toByteArray();
		String message = Arrays.toString(imageByteArray);
		return message;
	}

	public BufferedImage convertStringtoImg(String image) throws IOException {
		if (image == null) {
			return null;
		}
		String[] byteValues = image.substring(1, image.length() - 1).split(",");
		byte[] imgInByte = new byte[byteValues.length];
		for (int i = 0; i < byteValues.length; i++) {
			imgInByte[i] = Byte.parseByte(byteValues[i].trim());
		}
		ByteArrayInputStream ms = new ByteArrayInputStream(imgInByte);
		return ImageIO.read(ms);
	}

	public void setStage(String resource, String title, String cssFile) {
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(cssFile).toExternalForm());
			Font.loadFont(getClass().getResourceAsStream("/application/fonts/Moon Flower Bold.ttf"), 14);
			Font.loadFont(getClass().getResourceAsStream("/application/fonts/RaiNgan.ttf"), 14);
			Font.loadFont(getClass().getResourceAsStream("/application/fonts/Sunrise International Demo.otf"), 14);
			stage.setResizable(false);
			stage.setTitle(title);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// public static void main(String[] args) throws Exception {
	// GetPicture p = new GetPicture("got");
	// StageChanged s = new StageChanged();
	// String a = s.convertImgtoString("C:/Users/USER/Desktop/New folder
	// (2)/chatapplication/src/cat.jpg");
	// System.out.println(s.convertStringtoImg(p.get()));
	// }

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
