package Player;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;

import Server.Card;

public class Player extends Thread implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
				String s = input.readUTF();
				if(!s.equals("3")) {
					waitingPlayersFrame.setPlayersCount(s);
				}else {
					waitingPlayersFrame.setPlayersCount(s);
					break;
				}
			}
		    ObjectInputStream objectInput = new ObjectInputStream(player.getInputStream());
			while(true) {
			    Card card = (Card) objectInput.readObject();
			    waitingPlayersFrame.addCard(card);
			}
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}