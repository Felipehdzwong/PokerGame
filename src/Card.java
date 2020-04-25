
public class Card {
	
	String suit;
	String rank;

	public Card(String rank, String suit) {
		setSuit(suit);
		setRank(rank);
	}

	public String getSuit() {
		return suit;
	}

	public String getRank() {
		return rank;
	}
	
	public void setSuit(String suit) {
		this.suit = suit;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	
	public void display() {
		System.out.print(rank + "" + suit + ", ");
	}
	
	/**
	 * Changes the score's String value to it's corresponding Integer value
	 * @param score
	 * @return
	 */
	public int rank() {
		switch (rank) {
		case "A":
			return 14;
		case "K":
			return 13;
		case "Q": 
			return 12;
		case "J":
			return 11;
		default: 
			return Integer.parseInt(rank);
		}		
	}
}
