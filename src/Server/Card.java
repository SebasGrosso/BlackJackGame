package Server;

import java.io.Serializable;

public class Card implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rank;
	private String suit;
	private int valueCard;
	
	

	public Card(String rank, String suit, int valueCard) {
		this.rank = rank;
		this.suit = suit;
		this.valueCard = valueCard;
	}



	public String getRank() {
		return rank;
	}



	public void setRank(String rank) {
		this.rank = rank;
	}



	public String getSuit() {
		return suit;
	}



	public void setSuit(String suit) {
		this.suit = suit;
	}



	public int getValueCard() {
		return valueCard;
	}



	public void setValueCard(int valueCard) {
		this.valueCard = valueCard;
	}

	

}
