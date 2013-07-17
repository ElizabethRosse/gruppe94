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
			in  = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(Char cha) {
		try {
			out.writeObject(cha);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Char get() {
		try {
			cha = (Char) in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cha;
	}
	
	public boolean connection() {
		return connection;
	}
}
