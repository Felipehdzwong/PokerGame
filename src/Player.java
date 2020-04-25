import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Player {
	String name;
	int score;
	int money;
	int bet;
	Card mainCard;
	Card mainCard2;
	Card highCard;
	LinkedList<Card> hand = new LinkedList<>();
	
	public Player (String name) {
		setName(name);
		setMoney(1000);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	public int getBet() {
		return bet;
	}
	
	public void setBet(int bet) {
		this.bet = bet;
	}
	
	public Card getMainCard() {
		return mainCard;
	}
	
	public void setMainCard(Card card) {
		this.mainCard = card;
	}
	
	public Card getMainCard2() {
		return mainCard2;
	}
	
	public void setMainCard2(Card card) {
		this.mainCard2 = card;
	}
	
	public Card getHighCard() {
		return highCard;
	}
	
	public void setHighCard(Card card) {
		this.highCard = card;
	}
	
	public LinkedList<Card> getHand() {
		return hand;
	}
	
	public void setHand(LinkedList<Card> hand) {
		this.hand = hand;
	}
	
	public void draw(LinkedList<Card> cards) {
		this.hand = cards;
	}
	
	/**
	 * Removes the cards in the given positions and adds new cards in the hand
	 * @param positions
	 * @param newCards
	 */
	public void reDraw(List<Integer> positions, LinkedList<Card> newCards) {
		LinkedList<Card> oldCards = new LinkedList<>();
		
		for (int position : positions)
			oldCards.add(getHand().get(position-1));
			
		for (Card oldCard : oldCards)
			getHand().remove(oldCard);
		
		for (Card newCard : newCards)
			getHand().add(newCard);
	}
	
	/**
	 * Displays player's name, money and hand
	 */
	public void display() {
		System.out.println(getName() + "\t--> Total Money: $" + getMoney());

		for (Card card : getHand()) {
			card.display();
		} 
		System.out.println();
	}
	
	/**
	 * Sorts the hand's cards by rank in ascending order 
	 */
	public void sortHand() {
		Collections.sort(hand, new Comparator<Card>() {
		    @Override
		    public int compare(Card card1, Card card2) {
		        return (card1.rank() - card2.rank());
		    }
		});
	}
	
	/**
	 * Checks if the hand is a royal flush
	 * @return
	 */
	public boolean royalFlush() {
		LinkedList<String> ranks = new LinkedList<String>();
		
		for (Card card : getHand()) {
			ranks.add(card.getRank());
		}
		
		if ((ranks.contains("A")) &&
			(ranks.contains("K")) &&
			(ranks.contains("Q")) &&
			(ranks.contains("J")) &&
			(ranks.contains("10")) && isSameSuit()) {
			return true;
		}

		return false;
	}
	
	/**
	 * Checks if the hand is a straight flush
	 * @return
	 */
	public boolean straightFlush() {
		if (isSameSuit() && straight()) {
			setMainCard(getHand().getLast());
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Checks if the hand has four of a kind and set the Main and High cards
	 * @return
	 */
	public boolean fourOfAKind() {
		Card first 	= getHand().getFirst();
		Card second = getHand().get(1);
		Card third 	= getHand().get(2);
		Card fourth = getHand().get(3);
		Card fifth 	= getHand().getLast();
		
		if 		((first.rank() == second.rank()) && (first.rank() == third.rank()) && (first.rank() == fourth.rank()) ) {
			setMainCard(first);
			setHighCard(fifth);
			return true;
		}
		else if ((fifth.rank() == second.rank()) && (fifth.rank() == third.rank()) && (fifth.rank() == fourth.rank()) ) {
			setMainCard(fifth);
			setHighCard(first);
			return true;
		}
		else return false;
	}
	
	/**
	 * Checks if the hand is a Full House and sets the Main two cards cards
	 * @return
	 */
	public boolean fullHouse() {
		Card first 	= getHand().getFirst();
		Card second = getHand().get(1);
		Card third 	= getHand().get(2);
		Card fourth = getHand().get(3);
		Card fifth 	= getHand().getLast();

		if		((first.rank() == second.rank()) && (first.rank() == third.rank()) && (fourth.rank() == fifth.rank()) ) {
			setMainCard(first);
			setMainCard2(fifth);
			return true;
		}
		else if ((first.rank() == second.rank()) && (third.rank() == fourth.rank()) && (third.rank() == fifth.rank()) ) { 
			setMainCard(fifth);
			setMainCard2(first);
			return true;
		}
		else return false;
	}
	
	/**
	 * Checks if the hand is a flush
	 * @return
	 */
	public boolean flush() {
		if (isSameSuit() && !straight()) {
			setMainCard(getHand().getLast());
			return true;
		}
		else return false;
	}
	
	/**
	 * Checks if the hand is straight
	 * @return
	 */
	public boolean straight() {
		for (int i = 0; i < getHand().size()-1; i++) {
			if((getHand().get(i).rank()+1) != getHand().get(i+1).rank())
				return false;
		}
		return true;
	}
	
	/**
	 * Checks if the hand has three of a kind and set the Main and High cards
	 * @return
	 */
	public boolean threeOfAKind() {
		Card first 	= getHand().getFirst();
		Card second = getHand().get(1);
		Card third 	= getHand().get(2);
		Card fourth = getHand().get(3);
		Card fifth 	= getHand().getLast();
		
		if		((first.rank() == second.rank()) && (first.rank() == third.rank()) && (fourth.rank() != fifth.rank()) ) {
			setMainCard(first);
			setHighCard(fifth);
			return true;
		}			
		else if ((second.rank() == third.rank()) && (second.rank() == fourth.rank()) && (first.rank() != fifth.rank()) ) { 
			setMainCard(second);
			setHighCard(fifth);
			return true;
		}
		else if ((third.rank() == fourth.rank()) && (third.rank() == fifth.rank()) && (first.rank() != second.rank()) ) {
			setMainCard(third);
			setHighCard(second);
			return true;
		}
		else return false;
	}
	
	/**
	 * Checks if the hand has two pairs and set the Main cards and High card
	 * @return
	 */
	public boolean twoPairs() {
		Card first 	= getHand().getFirst();
		Card second = getHand().get(1);
		Card third 	= getHand().get(2);
		Card fourth = getHand().get(3);
		Card fifth 	= getHand().getLast();
		
		if		((first.rank() == second.rank()) && (third.rank() == fourth.rank()) && (first.rank() != fifth.rank()) && (third.rank() != fifth.rank())) { 
			setMainCard(third);
			setMainCard2(first);
			setHighCard(fifth);
			return true;
		}
		else if ((first.rank() == second.rank()) && (fourth.rank() == fifth.rank()) && (first.rank() != third.rank()) && (fourth.rank() != third.rank())) {
			setMainCard(fifth);
			setMainCard2(first);
			setHighCard(third);
			return true;
		}
		else if ((second.rank() == third.rank()) && (fourth.rank() == fifth.rank()) && (first.rank() != second.rank()) && (first.rank() != fourth.rank())) { 
			setMainCard(fifth);
			setMainCard2(third);
			setHighCard(first);
			return true;
		}
		else return false;
	}
	
	/**
	 * Checks if the hand is a pair and set the Main and High cards
	 * @return
	 */
	public boolean pair() {
		Card first 	= getHand().getFirst();
		Card second = getHand().get(1);
		Card third 	= getHand().get(2);
		Card fourth = getHand().get(3);
		Card fifth 	= getHand().getLast();
		
		if		((first.rank() == second.rank()) && (third.rank() != first.rank()) && (fourth.rank() != first.rank()) && (fifth.rank() != first.rank())) { 
			setMainCard(first);
			setHighCard(fifth);
			return true;
		}
		else if ((second.rank() == third.rank()) && (second.rank() != first.rank()) && (fourth.rank() != second.rank()) && (fifth.rank() != second.rank())) { 
			setMainCard(second);
			setHighCard(fifth);
			return true;
		}
		else if ((third.rank() == fourth.rank()) && (first.rank() != third.rank()) && (second.rank() != third.rank()) && (fifth.rank() != third.rank())) { 
			setMainCard(third);
			setHighCard(fifth);
			return true;
		}
		else if ((fourth.rank() == fifth.rank()) && (first.rank() != fourth.rank()) && (second.rank() != fourth.rank()) && (third.rank() != fourth.rank())) { 
			setMainCard(fourth);
			setHighCard(third);
			return true;
		}
		else return false;
	}
	
	/**
	 * Checks if the cards in a hand have the same suit 
	 * @return
	 */
	public boolean isSameSuit() {
		String frstSuit, nextSuit;
		frstSuit = getHand().get(0).getSuit();			// get the first suit in the hand
		
		for (int b = 1; b < getHand().size() ; b++) {
			nextSuit = getHand().get(b).getSuit();		// get the next suit in the hand
			if (!frstSuit.equals(nextSuit)) 		// if first suit is not equal to the next suit 
				return false;						// then return false
		}

		return true;
	}
	
	/**
	 * Sets the score based on the type of hand
	 */
	public void scoreHand() {
		if (royalFlush()) {
			setScore(10);;
			System.out.println("\tRoyal Flush");
		}
		else if (straightFlush()) {
			setScore(9);
			System.out.println("\tStraight Flush");
		}
		else if (fourOfAKind())	{
			setScore(8);
			System.out.println("\tFour of " + getMainCard().getRank());
		}
		else if (fullHouse()) {
			setScore(7);
			System.out.println("\tFull House of " + getMainCard().getRank() + " with " + getMainCard2().getRank());
		}
		else if (flush()) {
			setScore(6);
			System.out.println("\tFlush");
		}
		else if (straight()) {
			setScore(5);
			System.out.println("\tStraight");
		}
		else if (threeOfAKind()) {
			setScore(4);
			System.out.println("\tThree of " + getMainCard().getRank());
		}
		else if (twoPairs()){
			setScore(3);
			System.out.println("\tTwo Pairs of " + getMainCard().getRank() + " with " + getMainCard2().getRank());
		}
		else if (pair()) {
			setScore(2);
			System.out.println("\tPair of " + getMainCard().getRank());
		}
		else {
			setScore(1);
			setMainCard(getHand().getLast());
			System.out.println("\tHigh card " + getHand().getLast().getRank());
		}
	}

	/**
	 * Reduces the bet money from the total amount 
	 * @param bet
	 */
	public void bet(int bet) {
		if (bet > getMoney()) {
			System.out.println(getName() + " you don't have enough money. You're all in $"+getMoney());
			setBet(getMoney());
			setMoney(getMoney() - getBet());
		}
		else {
			setBet(bet);
			setMoney(getMoney() - getBet());
		}
	}
	
	/**
	 * Obtain the money in bets and add it to total amount
	 * @param centerBet
	 */
	public void winCenterBet(int centerBet) {
		setMoney(getMoney() + centerBet);
	}
	
}
