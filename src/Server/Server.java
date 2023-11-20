package Server;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import Player.PlayerResult;

public class Server extends Thread {

	private int port;
	private ArrayList<Socket> players;
	private InterfaceServer interfaceServer;
	private Deck deck;
	private ArrayList<Card> deckGame;
	private int count;
	private PlayerHandler playerHanler1;
	private PlayerHandler playerHanler2;
	private PlayerHandler playerHanler3;
	private int idPlayer = 0;
	private ArrayList<PlayerResult> playerResultArray;
	private int playersCount = 0;

	public Server(InterfaceServer interfaceServer, int port) {
		this.port = port;
		playerResultArray = new ArrayList<PlayerResult>();
		this.players = new ArrayList<Socket>();
		this.interfaceServer = interfaceServer;
		deck = new Deck();
		deckGame = deck.getDeck();
		count = 51;
	}

	@Override
	public void run() {
		Socket player;
		try (ServerSocket server = new ServerSocket(port);) {
			interfaceServer.addMessage("Servidor iniciado");

			while (true) {
				interfaceServer.addMessage("Esperando jugadores");
				player = server.accept();
				if (playersCount == 3) {
					JOptionPane.showInternalMessageDialog(null, "No te has podido conectar, juego lleno.");
					player.close();
				}
				playersCount++;
				DataOutputStream output = new DataOutputStream(player.getOutputStream());
				output.writeUTF(idPlayer + "");
				idPlayer++;

				players.add(player);
				for (Socket socket : players) {
					DataOutputStream output1 = new DataOutputStream(socket.getOutputStream());
					output1.writeUTF(players.size() + "");
				}

				interfaceServer
						.addMessage("Jugador " + players.size() + " conectado con ip: " + player.getInetAddress());
				interfaceServer.setPlayersCount(players.size() + "");
				if (players.size() == 3) {
					break;
				}

			}
			TalkWithPlayers();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void TalkWithPlayers() {
		for (int i = 0; i < players.size(); i++) {
			ObjectOutputStream output;
			try {
				output = new ObjectOutputStream(players.get(i).getOutputStream());
				output.writeObject(deckGame.get(ramdomNumber()));
				output.writeObject(deckGame.get(ramdomNumber()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		playerHanler1 = new PlayerHandler(0, players.get(0), this);
		playerHanler2 = new PlayerHandler(1, players.get(1), this);
		playerHanler3 = new PlayerHandler(2, players.get(2), this);
		playerHanler1.start();
		playerHanler2.start();
		playerHanler3.start();

	}

	public void sendNewCard(Socket player) {
		ObjectOutputStream output;
		try {
			output = new ObjectOutputStream(player.getOutputStream());
			output.writeObject(deckGame.get(ramdomNumber()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reciveResult(Socket player) {

		ObjectInputStream input;
		PlayerResult playerResult;
		try {
			input = new ObjectInputStream(player.getInputStream());
			playerResult = (PlayerResult) input.readObject();
			playerResultArray.add(playerResult);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (playerResultArray.size() == 3) {
			showResult();
		}
	}

	public void showResult() {

		try {
			File archivo = new File("results.txt");
			FileWriter fileWriter = new FileWriter(archivo);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write("El jugador: " + playerResultArray.get(0).getNamePlayer() + " sacó un puntaje de : "
					+ playerResultArray.get(0).getScorePlayer());
			bufferedWriter.newLine();
			bufferedWriter.write("El jugador: " + playerResultArray.get(1).getNamePlayer() + " sacó un puntaje de : "
					+ playerResultArray.get(1).getScorePlayer());
			bufferedWriter.newLine();
			bufferedWriter.write("El jugador: " + playerResultArray.get(2).getNamePlayer() + " sacó un puntaje de : "
					+ playerResultArray.get(2).getScorePlayer());
			bufferedWriter.newLine();
			if (playerResultArray.get(0).getScorePlayer() > 21 && playerResultArray.get(1).getScorePlayer() > 21
					&& playerResultArray.get(2).getScorePlayer() > 21) {
				bufferedWriter.write("Todos los jugadores perdieron, nadie gana.");
			} else {

				if (playerResultArray.get(0).getScorePlayer() > 21) {
					if (playerResultArray.get(1).getScorePlayer() > playerResultArray.get(2).getScorePlayer()) {
						int maxResultado = playerResultArray.get(1).getScorePlayer();
					} else {
						int maxResultado = playerResultArray.get(2).getScorePlayer();
					}
				}

				int maxResultado = Math.max(
						Math.max(playerResultArray.get(0).getScorePlayer(), playerResultArray.get(1).getScorePlayer()),
						playerResultArray.get(2).getScorePlayer());

				if (playerResultArray.get(0).getScorePlayer() == maxResultado
						&& playerResultArray.get(1).getScorePlayer() == maxResultado) {
					bufferedWriter.write(playerResultArray.get(0).getNamePlayer() + " y "
							+ playerResultArray.get(1).getNamePlayer() + " empataron.");
				} else if (playerResultArray.get(0).getScorePlayer() == maxResultado
						&& playerResultArray.get(2).getScorePlayer() == maxResultado) {
					bufferedWriter.write(playerResultArray.get(0).getNamePlayer() + " y "
							+ playerResultArray.get(2).getNamePlayer() + " empataron.");
				} else if (playerResultArray.get(1).getScorePlayer() == maxResultado
						&& playerResultArray.get(2).getScorePlayer() == maxResultado) {
					bufferedWriter.write(playerResultArray.get(1).getNamePlayer() + " y "
							+ playerResultArray.get(2).getNamePlayer() + " empataron.");
				}
			}
			bufferedWriter.close();
			fileWriter.close();

			for (int i = 0; i < players.size(); i++) {
				DataOutputStream output;
				try {
					output = new DataOutputStream(players.get(i).getOutputStream());
					output.writeUTF("result");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int ramdomNumber() {
		Random random = new Random();
		int number = random.nextInt(count);
		deckGame.remove(number);
		count--;
		return number;
	}
}
