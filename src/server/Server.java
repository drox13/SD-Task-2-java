package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class Server implements Runnable{
	private static final int PORT = 9575;
	private static final String MSN_SERVER_ON = "servidor conectado en : ";
	private ServerSocket serverSocket;
	private ArrayList<String> list;
	private Thread threadNewConnection;
	private boolean onServer;
	private ArrayList<Connection> listConnections;

	public Server() throws IOException {
		serverSocket = new ServerSocket(PORT);
		list = new ArrayList<>(Arrays.asList("Maria", "Juana", "Pedro", "Guilermo"));;
		listConnections = new ArrayList<>();
		Logger.getGlobal().info(MSN_SERVER_ON + PORT);
		onServer = true;
		threadNewConnection = new Thread(this);
		threadNewConnection.start();
	}	
	
	@Override
	public void run() {
		while(onServer) {
			try {
				Socket socket = serverSocket.accept();
				listConnections.add(new Connection(socket, list));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}