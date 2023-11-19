package Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceServer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton startButton;
	private JLabel playersLabel;
	private JTextArea messageArea;

	public InterfaceServer() {
		setTitle("Blackjack Game");
		setSize(700, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		initComponents();

		add(startButton);
		add(playersLabel);
		add(messageArea);

		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
	}

	public void setPlayersCount(String count) {
		playersLabel.setText("Jugadores Conectados: " + count);

	}

	private void initComponents() {
		startButton = new JButton("Iniciar Juego");
		startButton.setBounds(50, 30, 200, 50);

		playersLabel = new JLabel("Número de Jugadores: 0");
		playersLabel.setBounds(50, 90, 200, 30);

		messageArea = new JTextArea();
		messageArea.setBounds(270, 30, 600, 150);
		messageArea.setEditable(false);
	}

	public void addMessage(String message) {
		messageArea.append(message + "\n");
	}

	public static void main(String[] args) {
		InterfaceServer interfaceServer = new InterfaceServer();
		Server server = new Server(interfaceServer);
		interfaceServer.setVisible(true);
		server.start();
	}
}
