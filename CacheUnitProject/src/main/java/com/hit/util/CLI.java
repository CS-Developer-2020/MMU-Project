
package com.hit.util;

import java.beans.PropertyChangeSupport;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class CLI extends java.lang.Object implements java.lang.Runnable {
	
	private Scanner reader;
	private PrintWriter writer;
	private String command;
	private PropertyChangeSupport changes = new PropertyChangeSupport(this);

	public CLI(java.io.InputStream in, java.io.OutputStream out) 
	{
		reader = new Scanner(new InputStreamReader(in));
		writer = new PrintWriter(new OutputStreamWriter(out));
	}
	
	public void addPropertyChangeListener(java.beans.PropertyChangeListener pcl)
	{
		changes.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(java.beans.PropertyChangeListener pcl) 
	{
		this.changes.removePropertyChangeListener(pcl);
		
	}

	@Override
	public void run()
	{	
		
		write("Please enter your command");
		
		while (true)
		{
			
			command = reader.nextLine();	
			
			
			new Thread(() -> {
				
				if (command.equals("start") || command.equals("stop")) 
				{
					changes.firePropertyChange("command", null, command);
					synchronized(changes) {
						changes.notifyAll();
					}
				}
	
				else {
					write("Not a valid command");
					
				}
				
			}).start();
			
		
		}
		}
	
	
		public void write(String string) {
			writer.println(string);
			writer.flush();
		}
		
		


}

