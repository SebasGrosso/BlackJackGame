package Player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private Player player;

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
				player.calculateSocore();
				endGame(player.getScore());
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
		panelData.append("Carta :" + card.getRank() + " de " + card.getSuit() + "\n");
	}

	public void endGame(int score) {
		stant.setEnabled(false);
		hit.setEnabled(false);
		panelData.append("Tu puntaje fue: " + score + " \n");
		a();
	}
	
	public void a (){
		player.sendScore();
	}

	public void writeResults() {
		try {
			FileReader fileReader = new FileReader("results.txt");

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String linea;
			while ((linea = bufferedReader.readLine()) != null) {
				panelData.append(linea+"\n");
			}

			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getHost() {
		return JOptionPane.showInputDialog("Ingrese el host:");
	}

	public static int getPort() {
		return Integer.parseInt(JOptionPane.showInputDialog("Ingrese el port:"));
	}

	public static String getNamePlayer() {
		return JOptionPane.showInputDialog("Ingrese el su nombre:");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			String host = getHost();
			int port = getPort();
			String namePlayer = getNamePlayer();
			WaitingPlayers waitingPlayersFrame = new WaitingPlayers();
			Player player = new Player(waitingPlayersFrame, host, port, namePlayer);
			player.start();
		});
	}

}
