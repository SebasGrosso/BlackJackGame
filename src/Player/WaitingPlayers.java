package Player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private ArrayList<Card> deck;
    private int score;
    private Player player;
	
    public WaitingPlayers() {
    	deck = new ArrayList<Card>();
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
    
    public void setPlayer(Player player) {
		this.player = player;
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
        
        hit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				player.requestCards();
			}
		});
        
        stant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Card card : deck) {
					score = score + card.getValueCard();
				}
				panelData.append("Tu puntaje fue: " +score+ " \n");
				stant.setEnabled(false);
				hit.setEnabled(false);
			}
		});
        
        waitingPlayerPanel.removeAll();
        waitingPlayerPanel.setLayout(new BorderLayout());
        waitingPlayerPanel.add(panelData, BorderLayout.CENTER);
        waitingPlayerPanel.add(panelButtons, BorderLayout.SOUTH);
        waitingPlayerPanel.revalidate();
        waitingPlayerPanel.repaint();
    }
    
    public void addCard(Card cardd) {
    	Card card = cardd;
    	deck.add(card);
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
