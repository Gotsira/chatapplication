package chat;

import java.io.IOException;
import java.util.*;

import com.lloseng.ocsf.server.AbstractServer;
import com.lloseng.ocsf.server.ConnectionToClient;


public class Server extends AbstractServer {
	final private static int DEFAULT_PORT = 3014;

	public Server(int port) {
		super(port);
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		String[] message = ((String) msg).split(" ");
		String type = message[0];
		if (type.equals("connect")) {
			String name = message[1];
			client.setInfo("name", name);
		} else if (type.equals("message") || type.equals("image")) {
			String name = message[1];
			String data = "";
			for (int i = 2; i < message.length; i++) {
				data += message[i] + " ";
			}
			System.out.println(Arrays.toString(message));
			for (Thread t : getClientConnections()) {
				ConnectionToClient c = (ConnectionToClient) t;
				System.out.println(c.getInfo("name"));
				if (c.getInfo("name").equals(name)) {
					try {
						c.sendToClient(client.getInfo("name") + " " + type + " " + data);
					} catch (IOException e) {
						// do nothing
					}
				}
			}
		} else if (type.equals("disconnect")) {
			try {
				client.close();
			} catch (IOException e) {
				// do nothing
			}
		}
	}

	public static void main(String[] args) {
		Server server = new Server(3014);
		try {
			server.listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
