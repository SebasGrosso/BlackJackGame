package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread{
	
	private final int port = 8081;
	private ArrayList<Socket> players;
	private InterfaceServer interfaceServer;
	
	
	public Server(InterfaceServer interfaceServer) {
		this.players = new ArrayList<Socket>();
		this.interfaceServer = interfaceServer;
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
				
				output.writeUTF("Esperando jugadores");
				players.add(player);
				output.writeUTF(players.size()+"");
				interfaceServer.addMessage("Jugador "+ players.size() +" conectado con ip: "+player.getInetAddress());
				if(players.size()==3) {
					break; 
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void TalkWithClients() {
		for (Socket socket : players) {
			try {
				DataOutputStream output = new DataOutputStream(socket.getOutputStream());
				output.writeUTF("Se te ha asignado la carta");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		 
	}
	
	/*public static void main(String[] args) {
		Server s = new Server();
		s.start();
	}*/
}
