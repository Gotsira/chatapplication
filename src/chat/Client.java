package chat;

import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import com.lloseng.ocsf.client.AbstractClient;

import application.ChatController;

public class Client extends AbstractClient {
	private String host;
	private static final int PORT = 3014;
	private String message;
	private BufferedImage image = null;
	private ArrayList<ChatController> allChats = new ArrayList<ChatController>();

	public Client(String host, int port) {
		super(host, port);
	}

	@Override
	public void handleMessageFromServer(Object msg) {

		String[] chat = ((String) msg).split(" ");
		String sender = chat[0];

		String data = "";
		for (int i = 2; i < chat.length - 2; i++) {
			data += chat[i];
		}
		
		for(ChatController chatUI : allChats) {
			if(sender.equals(chatUI.getFriend())){
				//senderChat = chatUI;
				chatUI.display(data);
			}
		}
		
	}
	
	public void addChat(ChatController chat) {
		allChats.add(chat);
	}

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
		String[] byteValues = image.substring(1, image.length() - 1).split(",");
		byte[] imgInByte = new byte[byteValues.length];
		for (int i = 0; i < byteValues.length; i++) {
			imgInByte[i] = Byte.parseByte(byteValues[i].trim());
		}
		ByteArrayInputStream in = new ByteArrayInputStream(imgInByte);
		return ImageIO.read(in);
	}

}
