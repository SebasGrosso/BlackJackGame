package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Server extends Thread{
	
	private final int port = 8081;
	private ArrayList<Socket> players;
	private InterfaceServer interfaceServer;
	private Deck deck;
	private ArrayList<Card> deckGame;
	private int count;
	
	
	public Server(InterfaceServer interfaceServer) {
		this.players = new ArrayList<Socket>();
		this.interfaceServer = interfaceServer;
		deck = new Deck();
		deckGame = deck.getDeck();
		count = 52;
	}
	
	@Override
	public void run() {
		Socket player;
		try(ServerSocket server = new ServerSocket(port);){
			
			interfaceServer.addMessage("Servidor iniciado");
			
			while(true) {
				interfaceServer.addMessage("Esperando jugadores");
				player = server.accept();
				DataOutputStream output = new DataOutputStream(player.getOutputStream());
				
				players.add(player);
				for (Socket socket : players) {
					DataOutputStream output1 = new DataOutputStream(socket.getOutputStream());
					output1.writeUTF(players.size()+"");
				}
				
				interfaceServer.addMessage("Jugador "+ players.size() +" conectado con ip: "+player.getInetAddress());
				if(players.size()==3) {
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
	}
	
	public int ramdomNumber() {
		Random random = new Random();
		int number = random.nextInt(count);
		deckGame.remove(number);
		count--;
        return number;
	}
	
	/*public static void main(String[] args) {
		Server s = new Server();
		s.start();
	}*/
}
