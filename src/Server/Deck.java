package Server;

import java.util.ArrayList;

public class Deck {

	private ArrayList<Card> deck;
	
	public Deck () {
		deck = new ArrayList<Card>();
		addCards();
	}
	
	public void addCards() {
		deck.add(new Card("As","Tréboles",11));
		deck.add(new Card("2","Tréboles",2));
		deck.add(new Card("3","Tréboles",3));
		deck.add(new Card("4","Tréboles",4));
		deck.add(new Card("5","Tréboles",5));
		deck.add(new Card("6","Tréboles",6));
		deck.add(new Card("7","Tréboles",7));
		deck.add(new Card("8","Tréboles",8));
		deck.add(new Card("9","Tréboles",9));
		deck.add(new Card("10","Tréboles",10));
		deck.add(new Card("J","Tréboles",10));
		deck.add(new Card("Q","Tréboles",10));
		deck.add(new Card("K","Tréboles",10));
		
		deck.add(new Card("As","Corazones",11));
		deck.add(new Card("2","Corazones",2));
		deck.add(new Card("3","Corazones",3));
		deck.add(new Card("4","Corazones",4));
		deck.add(new Card("5","Corazones",5));
		deck.add(new Card("6","Corazones",6));
		deck.add(new Card("7","Corazones",7));
		deck.add(new Card("8","Corazones",8));
		deck.add(new Card("9","Corazones",9));
		deck.add(new Card("10","Corazones",10));
		deck.add(new Card("J","Corazones",10));
		deck.add(new Card("Q","Corazones",10));
		deck.add(new Card("K","Corazones",10));
		
		deck.add(new Card("As","Picas",11));
		deck.add(new Card("2","Picas",2));
		deck.add(new Card("3","Picas",3));
		deck.add(new Card("4","Picas",4));
		deck.add(new Card("5","Picas",5));
		deck.add(new Card("6","Picas",6));
		deck.add(new Card("7","Picas",7));
		deck.add(new Card("8","Picas",8));
		deck.add(new Card("9","Picas",9));
		deck.add(new Card("10","Picas",10));
		deck.add(new Card("J","Picas",10));
		deck.add(new Card("Q","Picas",10));
		deck.add(new Card("K","Picas",10));
		
		deck.add(new Card("As","Diamantes",11));
		deck.add(new Card("2","Diamantes",2));
		deck.add(new Card("3","Diamantes",3));
		deck.add(new Card("4","Diamantes",4));
		deck.add(new Card("5","Diamantes",5));
		deck.add(new Card("6","Diamantes",6));
		deck.add(new Card("7","Diamantes",7));
		deck.add(new Card("8","Diamantes",8));
		deck.add(new Card("9","Diamantes",9));
		deck.add(new Card("10","Diamantes",10));
		deck.add(new Card("J","Diamantes",10));
		deck.add(new Card("Q","Diamantes",10));
		deck.add(new Card("K","Diamantes",10));
		
	} 

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}
}
