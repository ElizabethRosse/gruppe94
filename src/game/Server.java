package game;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private final ServerSocket server;
	public Server ( int port ) throws IOException {
		server = new ServerSocket(port);
	}

}
