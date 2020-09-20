package com.hit.server;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.Dictionary;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.DataModel;
import com.hit.services.CacheUnitController;


public class HandleRequest<T> extends java.lang.Object implements java.lang.Runnable {
	
	private Socket clientSocket;
	private CacheUnitController<T> controller;
	
	public HandleRequest(java.net.Socket s, CacheUnitController<T> controller)
	{
		this.controller = controller;
		this.clientSocket = s;
	}
	

	@Override
	public void run() {
		InputStreamReader inputReader = null;
		PrintWriter streamWriter = null;
		try {
			inputReader = new InputStreamReader(clientSocket.getInputStream());
			streamWriter = new PrintWriter(clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		Gson gson = new Gson();
		Type type = new TypeToken<Request<DataModel<T>[]>>(){}.getType();
		Request<DataModel<T>[]> request = gson.fromJson(inputReader, type);
		try {
			clientSocket.shutdownInput();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Dictionary<String, String> headers = request.getHeader();
		String action = headers.get("action");
		
		switch (action.toLowerCase()) {
			case "get":{
				DataModel<T>[] result = controller.get((DataModel<T>[]) request.getBody());
				if(result != null) {
					streamWriter.print(gson.toJson(true));
					streamWriter.flush();
					break;
					}
				else {
					streamWriter.print(gson.toJson(false));
					streamWriter.flush();
				}
			}
			case "update":{
				boolean result = controller.update((DataModel<T>[]) request.getBody());
				streamWriter.print(result);
				streamWriter.flush();
				break;
			}
			case "delete":{
				boolean result = controller.delete((DataModel<T>[]) request.getBody());
				streamWriter.print(result);
				streamWriter.flush();
				break;
			}
			case "statistics":{
				String result = controller.statistics();
				streamWriter.print(result);
				streamWriter.flush();
			}
			default:{
				break;
			}
		}
		try {
			clientSocket.shutdownOutput();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
