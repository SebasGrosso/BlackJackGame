package Server;

import java.io.Serializable;

public class Card implements Serializable{
	
	private String nameCard;
	private int valueCard;
	
	public Card(String nameCard, int valueCard) {
		this.nameCard = nameCard;
		this.valueCard = valueCard;
	}

	public String getNameCard() {
		return nameCard;
	}

	public void setNameCard(String nameCard) {
		this.nameCard = nameCard;
	}

	public int getValueCard() {
		return valueCard;
	}

	public void setValueCard(int valueCard) {
		this.valueCard = valueCard;
	}
}
