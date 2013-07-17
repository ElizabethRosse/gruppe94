package game;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
//import java.io.OutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private final ServerSocket server;
	private boolean search = true, connection = false;
	private Char cha = new Char();
	private Socket client = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private Seria Client;
	
	/**
	 * Erstelle Server
	 * @throws IOException
	 */
	public Server () throws IOException {
		server = new ServerSocket(1234);
		//System.out.println(server.getInetAddress());
		
	}
	
	/**
	 * Starte Server
	 */
	public void startServing() {
		while(search) {
			try{
				client = server.accept();
			}
			catch ( IOException e ) {
		        e.printStackTrace();
		    }
			finally {
		        if ( client != null ){
		        	search = false;
		        	connection = true;
		        	newclient();
		      	}
			}
		}
	}
	
	/**
	 * Erstelle Client
	 */
	public void newclient() {
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
			Client = (Seria) in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		search = true;*/
		newclient();
		return Client;
	}
	
	/**
	 * Gebe Connection aus
	 * @return connection
	 */
	public boolean connection() {
		return connection;
	}
	
	/**
	 * Schlieﬂe Server
	 */
	public void close() {
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
