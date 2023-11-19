package Player;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Player extends Thread {

    private static final int PORT = 8081;
    private static final String SERVER_HOST = "localhost";
    private WaitingPlayers waitingPlayersFrame;

    public Player(WaitingPlayers waitingPlayersFrame) {
        this.waitingPlayersFrame = waitingPlayersFrame;
    }

    @Override
    public void run() {
        try (Socket player = new Socket(SERVER_HOST, PORT);
             DataInputStream input = new DataInputStream(player.getInputStream())) {
            while (true) {
                waitingPlayersFrame.setPlayersCount(input.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}