package com.hit.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import com.hit.services.CacheUnitController;
import com.hit.util.CLI;

public class Server
extends java.lang.Object
implements java.beans.PropertyChangeListener, java.lang.Runnable 
{
	
	private boolean serverAlive;
	ServerSocket serverSocket;
	private CacheUnitController<String> controller;
	
	public Server() 
	{
		
		try {
			serverSocket = new ServerSocket(5555);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	@Override
	public void propertyChange(java.beans.PropertyChangeEvent evt)
	{	
		CLI currentCLI = (CLI) evt.getSource();
		String newStatus = (String) evt.getNewValue();
		switch (newStatus)
		{
		case "start":
			if (serverAlive) 
			{
				currentCLI.write("Server is allready running");
				
			} else 
			{
				currentCLI.write("Starting server...");
				run();
			}
			break;
		case "stop":
			if (serverAlive) {
				currentCLI.write("Shutdown server");
				try {
					serverSocket.close();
					serverAlive = false;
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				currentCLI.write("Server is allready down");
			}
			break;
		}
	}
		

	public void run() 
	{
		Socket clientSocket = null;
		try {	
			controller = new CacheUnitController<String>();
			serverAlive = true;
			while (serverAlive) {
				clientSocket = serverSocket.accept();
				HandleRequest<String> req = new HandleRequest<String>(clientSocket, controller);
				Executor executor = Executors.newCachedThreadPool();
				executor.execute(req);
			}
		} catch (SocketException ex) {
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (clientSocket != null) {
					clientSocket.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		}	
	}






