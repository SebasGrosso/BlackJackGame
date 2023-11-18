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
    private JPanel waitingPlayerPanel;

    public WaitingPlayers() {
       
        setTitle("BlackJack");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout());
        
        waitingPlayerPanel = new JPanel();

        playersLabel = new JLabel("Jugadores Conectados: 0", SwingConstants.CENTER);
        playersLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        waitingPlayerPanel.add(playersLabel, BorderLayout.NORTH);

        waitingMessage = new JLabel("Esperando jugadores", SwingConstants.CENTER);
        waitingMessage.setFont(new Font("Arial", Font.BOLD, 18));
        waitingPlayerPanel.add(waitingMessage, BorderLayout.CENTER);

        this.add(waitingPlayerPanel, BorderLayout.CENTER);
        
        setVisible(true);
    }

    public void setPlayersCount(String count) {
        playersLabel.setText("Jugadores Conectados: " + count);
        if (count.equals("3")) {
			waitingPlayerPanel.removeAll();
			JButton yap = new JButton("Panel de juego");
			waitingPlayerPanel.add(yap, BorderLayout.CENTER);
			waitingPlayerPanel.revalidate();
			waitingPlayerPanel.repaint();
		}
    }

    public static void main(String[] args) {
        WaitingPlayers waitingPlayersFrame = new WaitingPlayers();
        Player player = new Player(waitingPlayersFrame);
        player.start();
    }
    
}

