package Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceServer extends JFrame {
	private JButton startButton;
	private JLabel playersLabel;
	private JTextArea messageArea;
	private int numberPlayers;

	public InterfaceServer() {
		// Configuración básica del JFrame
		setTitle("Blackjack Game");
		setSize(700, 300); // Ajustado el tamaño de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null); // Establecido el diseño a nulo para controlar las posiciones manualmente

		// Inicialización de componentes
		initComponents();

		// Agregar componentes al JFrame
		add(startButton);
		add(playersLabel);
		add(messageArea);

		// Asociar el evento del botón con el manejador de eventos
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Lógica para iniciar el juego
//				startGame();
			}
		});
	}

	private void initComponents() {
		// Crear el botón y configurarlo
		startButton = new JButton("Iniciar Juego");
		startButton.setBounds(50, 30, 200, 50);

		// Crear el JLabel y configurarlo
		playersLabel = new JLabel("Número de Jugadores: " + numberPlayers);
		playersLabel.setBounds(50, 90, 200, 30);

		// Crear el JTextArea y configurarlo
		messageArea = new JTextArea();
		messageArea.setBounds(270, 30, 600, 150);
		messageArea.setEditable(false); // Hacer el área de texto no editable
	}

	/*
	 * private void startGame() { // Lógica para iniciar el juego // Puedes realizar
	 * acciones adicionales aquí
	 * 
	 * // Actualizar el número de jugadores (esto es solo un ejemplo)
	 * numberOfPlayers++; playersLabel.setText("Número de Jugadores: " +
	 * numberOfPlayers);
	 * 
	 * // Agregar mensaje al área de texto addMessage("Juego iniciado. Jugadores: "
	 * + numberOfPlayers); }
	 */

	public void addMessage(String message) {
		messageArea.append(message + "\n");
	}

	public static void main(String[] args) {
		InterfaceServer interfaceServer = new InterfaceServer();
		Server s = new Server(interfaceServer);
		interfaceServer.setVisible(true);
		s.start();
	}
}
