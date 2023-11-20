package Server;

import java.io.Serializable;
import java.util.ArrayList;

public class Deck implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Card> deck;
	
	public Deck () {
		deck = new ArrayList<Card>();
		addCards();
	}
	
	public void addCards() {
		deck.add(new Card("As-Corazón",11));
		deck.add(new Card("2-Corazón",2));
		deck.add(new Card("3-Corazón",3));
		deck.add(new Card("4-Corazón",4));
		deck.add(new Card("5-Corazón",5));
		deck.add(new Card("6-Corazón",6));
		deck.add(new Card("7-Corazón",7));
		deck.add(new Card("8-Corazón",8));
		deck.add(new Card("9-Corazón",9));
		deck.add(new Card("10-Corazón",10));
		deck.add(new Card("J-Corazón",10));
		deck.add(new Card("Q-Corazón",10));
		deck.add(new Card("K-Corazón",10));
		
		deck.add(new Card("As-Pica",11));
		deck.add(new Card("2-Pica",2));
		deck.add(new Card("3-Pica",3));
		deck.add(new Card("4-Pica",4));
		deck.add(new Card("5-Pica",5));
		deck.add(new Card("6-Pica",6));
		deck.add(new Card("7-Pica",7));
		deck.add(new Card("8-Pica",8));
		deck.add(new Card("9-Pica",9));
		deck.add(new Card("10-Pica",10));
		deck.add(new Card("J-Pica",10));
		deck.add(new Card("Q-Pica",10));
		deck.add(new Card("K-Pica",10));
		
		deck.add(new Card("As-Diamante",11));
		deck.add(new Card("2-Diamante",2));
		deck.add(new Card("3-Diamante",3));
		deck.add(new Card("4-Diamante",4));
		deck.add(new Card("5-Diamante",5));
		deck.add(new Card("6-Diamante",6));
		deck.add(new Card("7-Diamante",7));
		deck.add(new Card("8-Diamante",8));
		deck.add(new Card("9-Diamante",9));
		deck.add(new Card("10-Diamante",10));
		deck.add(new Card("J-Diamante",10));
		deck.add(new Card("Q-Diamante",10));
		deck.add(new Card("K-Diamante",10));
		
		deck.add(new Card("As-Trebol",11));
		deck.add(new Card("2-Trebol",2));
		deck.add(new Card("3-Trebol",3));
		deck.add(new Card("4-Trebol",4));
		deck.add(new Card("5-Trebol",5));
		deck.add(new Card("6-Trebol",6));
		deck.add(new Card("7-Trebol",7));
		deck.add(new Card("8-Trebol",8));
		deck.add(new Card("9-Trebol",9));
		deck.add(new Card("10-Trebol",10));
		deck.add(new Card("J-Trebol",10));
		deck.add(new Card("Q-Trebol",10));
		deck.add(new Card("K-Trebol",10));
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}
}
