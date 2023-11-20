package Player;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
	private String host;
	private Socket player;
	
	public Player(WaitingPlayers waitingPlayersFrame) {
		this.waitingPlayersFrame = waitingPlayersFrame;
		waitingPlayersFrame.setPlayer(this);
	}

	@Override
	public void run() {
		try {
			player = new Socket("192.168.1.30", port);
			DataInputStream input = new DataInputStream(player.getInputStream());
			/*while (true) {
				String s = input.readUTF();
				if(!s.equals("3")) {
					waitingPlayersFrame.setPlayersCount(s);
				}else {
					waitingPlayersFrame.setPlayersCount(s);
					break;
				}
			}*/
			String signal;
			do {
				signal = input.readUTF();
				waitingPlayersFrame.setPlayersCount(signal);
			}while(!signal.equals("3"));

			ObjectInputStream objectInput = new ObjectInputStream(player.getInputStream());

			Card card = (Card) objectInput.readObject();
		    waitingPlayersFrame.addCard(card);
		    Card card2 = (Card) objectInput.readObject();
		    waitingPlayersFrame.addCard(card2);
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void requestCards() {
		try {
			DataOutputStream output = new DataOutputStream(player.getOutputStream());
			output.writeUTF("true");
			ObjectInputStream objectInput = new ObjectInputStream(player.getInputStream());
			Card card = (Card) objectInput.readObject();
		    waitingPlayersFrame.addCard(card);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}