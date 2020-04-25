import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Poker {

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		LinkedList<Player> players = new LinkedList<>();
		int numPlayers = 0;
		
		System.out.println("Number of players: ");				// Request number of players
		numPlayers = input.nextInt();
		
		for (int i = 1; i <= numPlayers; i++) {					// Create players
			System.out.println("Player name " + i + ": ");
			players.add(new Player(reader.readLine()));
		}
		
		while(true) {
			int bet = 0;
			int centerBet = 0;
			Deck deck = new Deck();
			
			if (numPlayers > 5)
				deck.addDeck(new Deck());						// If there are more than 5 players add a one more deck 
	
			deck.shuffle();										// Shuffle deck
			
			System.out.println("********** Poker Game **********");
			for (Player player : players) {
				player.draw(deck.deal());						// deal cards from deck to each player
				player.sortHand();								// sort hand
				player.display();								// display hand
				player.scoreHand();								// display initial score
			}
			
			// Ask for bets
			while(true) {
				System.out.println("\n"+players.getFirst().getName() + ", Enter your bet: ");
				bet = input.nextInt();
				if (bet <= players.getFirst().getMoney()) {
					for (Player player : players) {				// get the bet for each player and put it in the center
						player.bet(bet);
						centerBet += player.getBet();
					}
					break;
				}
				else
					System.out.println("You don't have enough money to bet $" +bet+". Please enter a smaller amount.");
				
			}
			
			// Re Draw Cards
			for (Player player : players) {
				boolean isValid = false;
				while(!isValid) {								// while the option is invalid, keep asking for new cards
					System.out.println(player.getName() + ", Would you like to change cards? (Y/N) ");
					char yn = reader.readLine().toLowerCase().charAt(0);
					
					switch (yn) {
					case 'y':
						System.out.println("Enter card's position separated by coma: ");
						String pos = reader.readLine();
						List<String> values = Arrays.asList(pos.split("\\s*,\\s*"));	// card's position saved in a list
						List<Integer> positions = new ArrayList<>();
						
						for(String s : values) {
							if (s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4") || s.equals("5")) // verify that user enter only valid positions
								positions.add(Integer.valueOf(s));
						}
						
						int numCards = positions.size();
						LinkedList<Card> newCards = deck.reDeal(numCards);				// re-deal the same number of cards thrown away
						player.reDraw(positions, newCards);
						isValid = true;
						break;
					case 'n':
						isValid = true;
						break;
					default:
						System.out.println("Invalid option. Try again.");
					}
				}
			}
			
			// Sort and display each players hand and score
			for (Player player : players) {
				player.sortHand();
				player.display();
				player.scoreHand();
			}
			
			System.out.println("\nTotal Bet: $"+centerBet);
			
			// Loop to find the winner
			Player winner = players.getFirst();
			for (int i = 0; i < players.size()-1; i++) {
				if (winner.getScore() < players.get(i+1).getScore()) {
					winner = players.get(i+1);
				}
				else if (winner.getScore() == players.get(i+1).getScore()) {
					if (winner.getMainCard().rank() < players.get(i+1).getMainCard().rank()) {
						winner = players.get(i+1);
					}
					else if ((winner.getMainCard().rank() == players.get(i+1).getMainCard().rank()) && (winner.getMainCard2() != null)) {
						 if (winner.getMainCard2().rank() < players.get(i+1).getMainCard2().rank()) {
							 winner = players.get(i+1);
						 }
					}
				}
			}
			
			if (winner.getBet() < bet) { 						// if the winner's bet is lower than the original bet
				int adjustedBet = winner.getBet() * players.size();
				winner.winCenterBet(adjustedBet);				// then will only have his/her bet times number of players
				for (Player player : players) {
					if(player.getBet() > winner.getBet()) {
						player.winCenterBet(winner.getBet()); 	// and return the their bet difference to the other players
					}
				}
			}
			else {
				winner.winCenterBet(centerBet);					// otherwise, will get the total bet in the center
			}
			System.out.println("\n\tThe WINNER is: "+ winner.getName() + "\n");
			
			// checks whether is there a player without money, if so, then same the players in a list
			LinkedList<Player> playersOut = new LinkedList<>();
			for(Player player : players) {
				if (player.getMoney() == 0) {
					playersOut.add(player);
					System.out.println(player.getName() +" is out of the game.");
				}
			}
			
			// then remove the players without money from the game
			for (Player player : playersOut) {
				players.remove(player);
			}
			
			// if there is the last player in the game, then he/she is the absolute winner and the game is over. 
			if (players.size() <= 1) {
				System.out.println("\n\tTHE ABSOLUTE WINNER IS " +winner.getName() + " with $" + winner.getMoney());
				break;
			}
			
			// Rotate players 
			Player p = players.getFirst();
			players.removeFirst();
			players.addLast(p);
			
		}

		input.close();
		
	}

}