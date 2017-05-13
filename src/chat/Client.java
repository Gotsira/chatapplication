package chat;


import com.lloseng.ocsf.client.AbstractClient;

public class Client extends AbstractClient {
	private String host;
	private final int PORT = 5555;
	private String[] message;
	private String username;
	
	public Client(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		String chat = (String) msg;
		if(msg.getClass() == String.class) {
			message = chat.split(" ");
			if(message[message.length - 1] == username) {
				
			}
		}
	}

}
