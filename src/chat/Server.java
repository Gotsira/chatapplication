package chat;

import java.io.IOException;
import java.net.*;
import java.util.*;

import javax.imageio.ImageIO;

import com.lloseng.ocsf.server.AbstractServer;
import com.lloseng.ocsf.server.ConnectionToClient;

public class Server extends AbstractServer {
	final private static int DEFAULT_PORT = 5555;
	private ArrayList<String> users;
	private String callingUser = "";

	public Server(int port) {
		super(port);
		users = new ArrayList<String>();
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		String[] message = ((String) msg).split(":");
		String type = message[0];
		if (type.equals("connect")) {
			String name = message[1];
			client.setInfo("name", name);
		} else if (type.equals("message") || type.equals("image")) {
			String name = message[1];
			String data = message[2];
			for (Thread t : getClientConnections()) {
				ConnectionToClient c = (ConnectionToClient) t;
				if (c.getInfo("name").equals(name)) {
					try {
						c.sendToClient(client.getInfo("name") + ":" + type + ":" + data);
					} catch (IOException e) {
						// do nothing
					}
				}
			}
		} else if (type.equals("disconnect")) {
			
		}
	}

	/**
	 * 
	 */
	public void clientConnected(ConnectionToClient client) {
		System.out.println("The client " + client + " has connected.");

	}

	/**
	 * 
	 */
	public void clientDisconnected(ConnectionToClient client) {
		System.out.println("The client " + client + " has disconnected.");
		// users.remove(client.getInfo("loginID")) ;
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
	}

	/**
	 * Checks if a user is on the list of current valid users. Persists even if
	 * a user logs off
	 * 
	 * @param user
	 * @return
	 */
	public boolean doesUserExist(String user) {
		return (users.contains(user));
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * stops listening for connections.
	 */
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}

	// Class methods ***************************************************

	/**
	 * This method is responsible for the creation of the server instance (there
	 * is no UI in this phase).
	 * 
	 * @param args
	 *            [0] The port number to listen on. Defaults to 5555 if no
	 *            argument is entered.
	 */

	public static void main(String[] args) {
		int port = 5555; // Port to listen on
		try {
			port = Integer.parseInt(args[0]); // Get port from command line
		} catch (Throwable t) {
			port = DEFAULT_PORT; // Set port to 5555
		}

		Server sv = new Server(port);
		ServerConsole server = new ServerConsole(sv);
		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
		server.accept();
	}
}
