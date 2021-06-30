package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Connection implements Runnable {
	private static final String MSN_ClIENT_NEW = "nuevo Cliente conectado desde: ";
	private static final String ELEMENT_NO_FIND = "La posicion no existe dentro de la lista";
	private Thread threadRequest;
	private Socket serverSocket;
	private DataInputStream inputConnetion;
	private DataOutputStream outputConnetion;
	private ArrayList<String> list;
	private boolean connetionOn;

	public Connection(Socket socket, ArrayList<String> list) throws IOException {
		threadRequest = new Thread(this);
		serverSocket = socket;
		inputConnetion = new DataInputStream(serverSocket.getInputStream());
		outputConnetion = new DataOutputStream(serverSocket.getOutputStream());
		this.list = list;
		connetionOn = true;
		threadRequest.start();
		Logger.getGlobal().info(MSN_ClIENT_NEW + socket.getInetAddress());

	}

	@Override
	public void run() {
		try {
			while(connetionOn) {
				if(inputConnetion.available() > 0) {
					protocol();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void protocol() throws IOException {
		int indexList = inputConnetion.readInt();
		try {
			String elemet = list.get(indexList);
			outputConnetion.writeUTF(elemet);
		}catch (IndexOutOfBoundsException e) {
			outputConnetion.writeUTF(ELEMENT_NO_FIND);
		}
	}
}