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
			Socket player = new Socket("localhost", port);
			DataInputStream input = new DataInputStream(player.getInputStream());
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/*public static void main(String[] args) {
		Player c = new Player();
		c.start();
	}*/

}
