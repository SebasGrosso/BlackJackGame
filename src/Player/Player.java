package Player;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Player extends Thread{
	
	private final int port = 8081;
	private WaitingPlayers waitingPlayersFrame;
	
	
	public Player(WaitingPlayers waitingPlayersFrame) {
		this.waitingPlayersFrame = waitingPlayersFrame;
	}



	@Override
	public void run() {
		try {
			Socket player = new Socket("192.168.1.30", port);
			DataInputStream input = new DataInputStream(player.getInputStream());
			while (true) {
				waitingPlayersFrame.setPlayersCount(input.readUTF());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*public static void main(String[] args) {
		Player c = new Player();
		c.start();
	}*/

}
