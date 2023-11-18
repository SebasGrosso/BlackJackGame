package Player;

import javax.swing.*;
import java.awt.*;

public class WaitingPlayers extends JFrame {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel playersLabel;
    private JLabel waitingMessage;

    public WaitingPlayers() {
       
        setTitle("Esperando Jugadores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout());

        playersLabel = new JLabel("Jugadores Conectados: 0", SwingConstants.CENTER);
        playersLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(playersLabel, BorderLayout.NORTH);

        waitingMessage = new JLabel("Esperando jugadores", SwingConstants.CENTER);
        waitingMessage.setFont(new Font("Arial", Font.BOLD, 18));
        add(waitingMessage, BorderLayout.CENTER);

        setVisible(true);
    }

    public void setPlayersCount(String count) {
        playersLabel.setText("Jugadores Conectados: " + count);
    }

    public static void main(String[] args) {
        WaitingPlayers waitingPlayersFrame = new WaitingPlayers();
        Player player = new Player(waitingPlayersFrame);
        player.start();
    }
}

