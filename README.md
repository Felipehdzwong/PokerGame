# PokerGame
A simple poker game written in Java

## Software Craftmanship - Clean code best practices


### 1) Varibles
Use meaningful and pronounceable variable names.
#### Good practice
***players*** variable name is understandable and readable. The developer can understand that this is a linked list of Player objects
```java
LinkedList<Player> players = new LinkedList<>();
```
#### Bad practice
***yn*** variable name can be misinterpreted and it's difficult to pronounce.
```java
char yn = reader.readLine().toLowerCase().charAt(0);
```

### 2) Functions
Functions should do only one task. 
#### Good practice
***sortHand()*** function is responsible of sorthing the Cards in Player's hand
```java
public void sortHand() {
  Collections.sort(hand, new Comparator<Card>() {
      @Override
      public int compare(Card card1, Card card2) {
          return (card1.rank() - card2.rank());
      }
  });
}
```
#### Bad practice
***scoreHand()*** function is long and has embeded function calls. This method can be optimized maybe with switch-case statement 
```java
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
```

### 3) Comments 
Comments are an apology, not a requirement. Good code mostly documents itself.
#### Good practice
Add clear comments to your code only when necessary. 
```java
/**
 * Checks if the hand has four of a kind
 * @return boolean
 */
public boolean fourOfAKind() {
  //implementation...
}
```
#### Bad practice
It is no recommended to have commented code. It's better to use a VCS and remove it. 
```java
//	public void display() {
//		for (Card card : deck) {
//			card.display();
//		}
//		System.out.println();
//	}
```

### 4) Objects and Data Structures
#### Good practice
Use getters and setters to access data on Objects
```java
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
}
```
#### Bad practice
Not using getters and setters makes data access complicated. 

### 5) Tests
#### Good practice
#### Bad practice

### 6) SOLID
Single responsibility principle.
#### Good practice
Regarding the single responsibility principle, it's important to keep classes with minimal functionality. An example of this is the ***Card.java*** class
#### Bad practice
In the other hand, ***Player.java*** class has more than one responsibility. It also handles the "hand" which can be abstracted into a separate object. 

### 7) Error handling
#### Good practice
#### Bad practice

### 8) Formatting
Formatting is subjective. Like many rules herein, there is no hard and fast rule that you must follow.
#### Good practice
Formatted code:
```java
public LinkedList<Card> reDeal(int numCards) {
  LinkedList<Card> newCards = new LinkedList<>();

  for (int i = 0; i < numCards; i++) {
    newCards.add(deck.getLast());
    deck.removeLast();
  }

  return newCards;
}
```
#### Bad practice
Unformatted code:
```java
public LinkedList<Card> reDeal(int numCards) {
LinkedList<Card> newCards = new LinkedList<>();
for (int i = 0; i < numCards; i++) {
newCards.add(deck.getLast());
deck.removeLast();}
return newCards;}
```

