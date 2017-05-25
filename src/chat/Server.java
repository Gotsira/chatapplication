package chat;

import java.io.IOException;
import java.util.*;
import com.lloseng.ocsf.server.*;

/**
 * Server class is the server used for running the chat application. The server
 * acts as a center point of all communication. It passes on the message to the
 * specific client.
 * 
 * @author Sirasath Piyapootinun
 *
 */
public class Server extends AbstractServer {

	/**
	 * Constructor used for creating the server.
	 * 
	 * @param port
	 *            is the port to be opened on the server.
	 */
	public Server(int port) {
		super(port);
	}

	/**
	 * Handles all type of message from all of the client.
	 * 
	 * @param msg
	 *            is the message sent by the client to the server.
	 * @param client
	 *            is the client who sent the message.
	 */
	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		boolean check = false;
		String[] message = ((String) msg).split(" ");
		String type = message[0];
		String sender = message[message.length - 1];
		String name = message[1];
		if (type.equals("connect")) {
			client.setInfo("name", name);
		} else if (type.equals("message")) {
			String data = "";
			for (int i = 2; i < message.length - 1; i++) {
				data += message[i] + " ";
			}
			System.out.println(Arrays.toString(message));
			for (Thread t : getClientConnections()) {
				ConnectionToClient c = (ConnectionToClient) t;
				System.out.println(c.getInfo("name"));
				if (c.getInfo("name").equals(name)) {
					try {
						check = true;
						c.sendToClient(client.getInfo("name") + " " + type + " " + data);
					} catch (IOException e) {
						try {
							client.sendToClient("Failed to send message");
						} catch (IOException e1) {
							// do nothing
						}
					}
				}
			}
			if (!check) {
				for (Thread thread : getClientConnections()) {
					ConnectionToClient cl = (ConnectionToClient) thread;
					if (cl.getInfo("name").equals(sender)) {
						try {
							cl.sendToClient(name + " offline is offline.");
						} catch (IOException e) {
							// do nothing
						}
					} else if (cl.getInfo("name").equals(name)) {
						thread.destroy();
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

	/**
	 * Main method used for running the server.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Server server = new Server(3014);
		try {
			server.listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
