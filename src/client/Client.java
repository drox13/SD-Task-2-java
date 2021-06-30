package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	private static final String HOST = "localhost";
	private static final int PORT = 9575;
	private Socket socketClient;
	private DataInputStream inputClient;
	private DataOutputStream outputClient;
	private Scanner sc;
	
	public Client() throws UnknownHostException, IOException {
		socketClient = new Socket(HOST, PORT);
		inputClient = new DataInputStream(socketClient.getInputStream());
		outputClient = new DataOutputStream(socketClient.getOutputStream());
		sc = new Scanner(System.in);
		requestElement();
	}
	
	private void requestElement() throws IOException {
		System.out.println("Ingrese la posicion de la lista que desea optener: \n");
		int postion = sc.nextInt();
		outputClient.writeInt(postion);
		response(postion);
	}
	
	private void response(int postion) throws IOException {
		System.out.println("El elemento de la posision " + postion +
				" es: " +inputClient.readUTF());
		requestElement();
	}
	
	public static void main(String[] args) {
		try {
			new Client();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}