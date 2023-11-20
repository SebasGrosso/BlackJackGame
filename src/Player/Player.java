package Player;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import Server.Card;

public class Player extends Thread{
	
	private final int port;
	private WaitingPlayers waitingPlayersFrame;
	private String host;
	private Socket player;
	private ArrayList<Card> deck;
	private int score;
	private String idPlayer;
	private String namePlayer;
	
	public Player(WaitingPlayers waitingPlayersFrame, String host, int port, String namePlayer) {
		this.host = host;
		this.namePlayer = namePlayer;
		this.port = port;
		deck = new ArrayList<Card>();
		this.waitingPlayersFrame = waitingPlayersFrame;
		waitingPlayersFrame.setPlayer(this);
	}

	@Override
	public void run() {
		try {
			player = new Socket(host, port);
			DataInputStream input = new DataInputStream(player.getInputStream());
			idPlayer = input.readUTF();
			String signal;
			do {
				signal = input.readUTF();
				waitingPlayersFrame.setPlayersCount(signal);
			}while(!signal.equals("3"));

			ObjectInputStream objectInput = new ObjectInputStream(player.getInputStream());

			Card card = (Card) objectInput.readObject();
			deck.add(card);
		    waitingPlayersFrame.addCard(card);
		    Card card2 = (Card) objectInput.readObject();
		    deck.add(card2);
		    waitingPlayersFrame.addCard(card2);
		    endGame();
			
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
			deck.add(card);
		    waitingPlayersFrame.addCard(card);
		    endGame();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public int calculateSocore() {
		score = 0;
		int asCount = 0;
		for (Card card : deck) {
			score += card.getValueCard();
			if(card.getRank().equals("As")) {
				asCount++;
			}
		}
		return asCount;
	}
	
	public void endGame() {
		int asCount = calculateSocore();
		if(score>=21 && asCount == 0) {
			waitingPlayersFrame.endGame(score);
		}else if(score == 21 && asCount == 1) {
			waitingPlayersFrame.endGame(score);
		}else if(score>21 && asCount == 1){
			for (int i = 0; i < deck.size(); i++) {
				if(deck.get(i).getRank().equals("As")) {
					deck.get(i).setValueCard(1);
					calculateSocore();
   					if(score >= 21) {
						waitingPlayersFrame.endGame(score);
					}
				}
			}
		}else if(score>=21 && asCount == 2) {
			waitingPlayersFrame.endGame(score);
		}
	}
	
	public void sendScore() {
		ObjectOutputStream objectOutput;
		DataOutputStream output;
		PlayerResult playerResult = new PlayerResult(namePlayer, idPlayer, score);
		try {
			output = new DataOutputStream(player.getOutputStream());
			output.writeUTF("endGame");
			objectOutput = new ObjectOutputStream(player.getOutputStream());
			objectOutput.writeObject(playerResult);
			PlayerBridge b = new PlayerBridge(player, waitingPlayersFrame);
			b.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public int getScore() {
		return score;
	}
}