package game;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private final ServerSocket server;
	private boolean search = true, connection = false;
	private Char cha = null;
	private Socket client = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	
	
	public Server () throws IOException {
		server = new ServerSocket(1234);
		System.out.println(server.getInetAddress());
		
	}
	
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
	
	public void newclient() {
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
