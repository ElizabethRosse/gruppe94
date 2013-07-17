package game;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	private boolean connection = false;
	private Char cha = null;
	private Socket client = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private Seria Server;
	
	public Client() {
		try {
			client = new Socket("localhost", 1234);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(client != null) {
				connection = true;
				newserver();
			}
		}
	}
	
	public void newserver() {
		try {
			out = new ObjectOutputStream(client.getOutputStream());
			in  = new ObjectInputStream(client.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(Seria me) {
		try {
			out.writeObject(me);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Seria get() {
		try {
			Server = (Seria) in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*try {
			client.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			client = new Socket("localhost", 1234);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(client != null) {
				connection = true;
				newserver();
			}
		}*/
		newserver();
		return Server;
	}
	
	public boolean connection() {
		return connection;
	}
	
	public void close() {
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
