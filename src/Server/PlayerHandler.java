package Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class PlayerHandler extends Thread {

	private int playerId;
	private Socket player;
	private Server server;

	public PlayerHandler(int clientId, Socket clientSocket, Server server) {
		this.playerId = clientId;
		this.player = clientSocket;
		this.server = server;
	}

	@Override
	public void run() {
		try {
			DataInputStream inputStream = new DataInputStream(player.getInputStream());

			while (true) {
				String request = inputStream.readUTF();

				if (request.equals("true")) {
					server.sendNewCard(player);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
