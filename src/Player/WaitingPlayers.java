package Player;

import javax.swing.*;
import java.awt.*;

public class WaitingPlayers extends JFrame {
	
    private JLabel playersLabel;
    private JLabel waitingMessage;

    public WaitingPlayers() {
        // Configurar el JFrame
        setTitle("Esperando Jugadores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Configurar el diseño del JFrame
        setLayout(new BorderLayout());

        // Crear el JLabel para mostrar el número de jugadores conectados
        playersLabel = new JLabel("Jugadores Conectados: 0", SwingConstants.CENTER);
        playersLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(playersLabel, BorderLayout.NORTH);

        // Crear el JLabel para mostrar el mensaje "Esperando jugadores"
        waitingMessage = new JLabel("Esperando jugadores", SwingConstants.CENTER);
        waitingMessage.setFont(new Font("Arial", Font.BOLD, 18));
        add(waitingMessage, BorderLayout.CENTER);

        // Hacer visible la ventana
        setVisible(true);
    }

    // Método para actualizar el número de jugadores conectados
    public void setPlayersCount(int count) {
        playersLabel.setText("Jugadores Conectados: " + count);
    }

    public static void main(String[] args) {
        // Ejemplo de uso: Crear una instancia de WaitingPlayers
        WaitingPlayers waitingPlayersFrame = new WaitingPlayers();
        Player c = new Player(waitingPlayersFrame);
		c.start();

    }
}

