package chat;

import java.io.IOException;

import com.lloseng.ocsf.client.AbstractClient;

public class Client extends AbstractClient {
	private String host;
	private static final int PORT = 5555;
	private String[] message;
	private String username;

	public Client(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		
	}

}
