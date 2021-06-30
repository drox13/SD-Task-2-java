package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	private static final int PORT = 9575;
	private ServerSocket serverSocket;

	public Server() throws IOException {
		serverSocket = new ServerSocket(PORT);
	}
}


