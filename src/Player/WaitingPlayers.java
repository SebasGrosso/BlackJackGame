package Player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Server.Card;

public class WaitingPlayers extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel playersLabel;
    private JLabel waitingMessage;
    private JPanel waitingPlayerPanel;
    private JButton hit;
    private JButton stant;
    private JTextArea panelData;

    public WaitingPlayers() {
        setTitle("BlackJack");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        waitingPlayerPanel = new JPanel();
        add(waitingPlayerPanel);
        initComponents();
        setVisible(true);
        panelData = new JTextArea();
    }
    
    public void initComponents() {
    	playersLabel = new JLabel("Jugadores Conectados: 0", SwingConstants.CENTER);
        playersLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        waitingPlayerPanel.add(playersLabel, BorderLayout.NORTH);

        waitingMessage = new JLabel("Esperando jugadores", SwingConstants.CENTER);
        waitingMessage.setFont(new Font("Arial", Font.BOLD, 18));
        waitingPlayerPanel.add(waitingMessage, BorderLayout.CENTER);
    }

    public void setPlayersCount(String count) {
        SwingUtilities.invokeLater(() -> {
            playersLabel.setText("Jugadores Conectados: " + count);
            if (count.equals("3")) {
                initComponentsGame();
            }
        });
    }

    public void initComponentsGame() {
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        hit = new JButton("Pedir");
        stant = new JButton("Plantarse"); 
        
        panelButtons.add(hit);
        panelButtons.add(stant);
        
        
        panelData.setBackground(Color.WHITE);
        panelData.setEditable(false);
        
        waitingPlayerPanel.removeAll();
        waitingPlayerPanel.setLayout(new BorderLayout());
        waitingPlayerPanel.add(panelData, BorderLayout.CENTER);
        waitingPlayerPanel.add(panelButtons, BorderLayout.SOUTH);
        waitingPlayerPanel.revalidate();
        waitingPlayerPanel.repaint();
    }
    
    public void addCard(Card cardd) {
    	Card card = cardd;
		panelData.append("Carta :"+card.getNameCard() + "\n");
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WaitingPlayers waitingPlayersFrame = new WaitingPlayers();
            Player player = new Player(waitingPlayersFrame);
            player.start();
        });
    }
}
