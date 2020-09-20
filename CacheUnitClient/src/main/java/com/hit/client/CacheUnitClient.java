package com.hit.client;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.InetSocketAddress;

public class CacheUnitClient {

	private Socket clientSocket;
	
	public String send(String request){
		try {
			clientSocket = new Socket();
			clientSocket.connect(new InetSocketAddress("localhost", 5555));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		char[] buffer = new char[2048];
		try {
			PrintWriter streamWriter = new PrintWriter(clientSocket.getOutputStream());
			InputStreamReader inputReader = new InputStreamReader(clientSocket.getInputStream());

			streamWriter.write(request);
			streamWriter.flush();
			clientSocket.shutdownOutput();
			
			inputReader.read(buffer);
			clientSocket.shutdownInput();
			
			clientSocket.close();
			
			return String.valueOf(buffer);
			
		} catch (IOException e) {
			e.printStackTrace();
			
			return null;
		}
	}


}
