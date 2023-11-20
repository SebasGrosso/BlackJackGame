package Player;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class PlayerBridge extends Thread{
	
	private Socket player;
	private WaitingPlayers waitingPlayersFrame;
	
	
	
	public PlayerBridge(Socket player, WaitingPlayers waitingPlayersFrame) {
		this.player = player;
		this.waitingPlayersFrame = waitingPlayersFrame;
	}

	@Override
	public void run() {
		DataInputStream input;
		try {
			input = new DataInputStream(player.getInputStream());
			
			if(input.readUTF().equals("result")) {
				waitingPlayersFrame.writeResults();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
