package Server;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class InterfaceServer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel playersLabel;
	private JTextArea messageArea;

	public InterfaceServer() {
		setTitle("Blackjack Game");
		setSize(700, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		initComponents();

		add(playersLabel);
		add(messageArea);

	}

	public void setPlayersCount(String count) {
		playersLabel.setText("Jugadores Conectados: " + count);

	}

	private void initComponents() {

		playersLabel = new JLabel("Número de Jugadores: 0");
		playersLabel.setBounds(50, 90, 200, 30);

		messageArea = new JTextArea();
		messageArea.setBounds(270, 30, 600, 150);
		messageArea.setEditable(false);
	}

	public void addMessage(String message) {
		messageArea.append(message + "\n");
	}
	
	public static int getPort() {
		return Integer.parseInt(JOptionPane.showInputDialog("Ingrese el puerto:"));
	}

	public static void main(String[] args) {
		int port = getPort();
		InterfaceServer interfaceServer = new InterfaceServer();
		Server server = new Server(interfaceServer, port);
		interfaceServer.setVisible(true);
		server.start();
	}
}
