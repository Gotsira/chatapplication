package chat;

import java.awt.image.*;
import java.io.*;
import java.util.Arrays;

import javax.imageio.ImageIO;

import com.lloseng.ocsf.client.AbstractClient;

public class Client extends AbstractClient {
	private String host;
	private static final int PORT = 5555;
	private String message;
	private BufferedImage image = null;

	public Client(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		String[] chat = ((String) msg).split(":");
		String sender = chat[0];
		String type = chat[1];
		String data = chat[2];
		if (type.equals("message")) {

		} else if (type.equals("image")) {
			try {
				image = convertStringtoImg(data);
			} catch (IOException e) {
				// do nothing
			}
		} else if (type.equals("video")) {

		}
	}

	public String convertImgtoString(String filePath) throws IOException {
		File imgPath = new File(filePath);
		BufferedImage bufferedImage = ImageIO.read(imgPath);
		WritableRaster raster = bufferedImage.getRaster();
		DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
		message = Arrays.toString(data.getData());
		return message;
	}

	public BufferedImage convertStringtoImg(String image) throws IOException {
		String[] byteValues = image.substring(1, image.length() - 1).split(",");
		byte[] imgInByte = new byte[byteValues.length];
		for (int i = 0; i < byteValues.length; i++) {
			imgInByte[i] = Byte.parseByte(byteValues[i].trim());
		}
		InputStream in = new ByteArrayInputStream(imgInByte);
		return ImageIO.read(in);
	}

}
