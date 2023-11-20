package Player;

import java.io.Serializable;

public class PlayerResult implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String namePlayer;
	private String idPlayer;
	private int scorePlayer;
	
	public PlayerResult(String namePlayer, String idPlayer, int scorePlayer) {
		this.namePlayer = namePlayer;
		this.idPlayer = idPlayer;
		this.scorePlayer = scorePlayer;
	}

	public String getNamePlayer() {
		return namePlayer;
	}

	public void setNamePlayer(String namePlayer) {
		this.namePlayer = namePlayer;
	}

	public String getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(String idPlayer) {
		this.idPlayer = idPlayer;
	}

	public int getScorePlayer() {
		return scorePlayer;
	}

	public void setScorePlayer(int scorePlayer) {
		this.scorePlayer = scorePlayer;
	}
}
