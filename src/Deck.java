import java.util.Collections;
import java.util.LinkedList;

public class Deck {
	LinkedList<Card> deck = new LinkedList<>();
	String suits[] = {"\u2666", "\u2665", "\u2663", "\u2660"};
	int scores[] = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
	
	public Deck() { 	
		for (int score : scores) {
			for (String suit : suits) {
				deck.add(new Card(scoreToString(score), suit));
			}
		}
	}

	public LinkedList<Card> getDeck() {
		return deck;
	}

	public void setDeck(LinkedList<Card> deck) {
		this.deck = deck;
	}
	
	public void addDeck(Deck deck) {
		for (Card card : deck.getDeck()) {
			this.deck.add(card);
		}
	}
	
	/**
	 * Shuffles the cards in the deck
	 */
	public void shuffle() {
		Collections.shuffle(deck);
	}	
	
	/**
	 * Deals 5 cards from the deck 
	 * @return
	 */
	public LinkedList<Card> deal(){
		LinkedList<Card> hand = new LinkedList<>();
		
		for (int i = 0; i < 5; i++) {
			hand.add(deck.getLast());
			deck.removeLast();
		}
		
		return hand;
	}
	
	/**
	 * Re-deals the requested number of cards from the deck
	 * @param numCards
	 * @return
	 */
	public LinkedList<Card> reDeal(int numCards) {
		LinkedList<Card> newCards = new LinkedList<>();
		
		for (int i = 0; i < numCards; i++) {
			newCards.add(deck.getLast());
			deck.removeLast();
		}

		return newCards;
	}
	
	/**
	 * Displays the cards in the deck
	 */
	public void display() {
		for (Card card : deck) {
			card.display();
		}
		System.out.println();
	}
	
	/**
	 * Changes the score's integer value to it's corresponding String value
	 * @param score
	 * @return
	 */
	public static String scoreToString(int score) {
		switch (score) {
		case 11:
			return "J";
		case 12:
			return "Q";
		case 13:
			return "K";
		case 14: 
			return "A";
		default: 
			return String.valueOf(score);
		}
	}
}
