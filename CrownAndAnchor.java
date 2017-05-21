import java.util.Random;

public class CrownAndAnchor {

	static int WAGER;
	static int DICE_FACES;
	static int NUMBER_OF_GAMES;
	static int NUMBER_OF_CARDS;

	public static void main(String [] args){
		WAGER = 1;
		DICE_FACES = 6;
		NUMBER_OF_GAMES = 10000000;
		NUMBER_OF_CARDS = 6;
		int totalWinnings = 0;
		int totalPercentChange = 0;

		for(int i = 0; i < NUMBER_OF_GAMES; i++){
			totalWinnings += gameWinnings(WAGER);
		}

		int avgPercentChange = (100 * totalWinnings) / NUMBER_OF_GAMES;

		System.out.println("Money Kept: " + avgPercentChange + "%");
		System.out.println("The game favors the house by " + (100 - avgPercentChange) + "%");		

	}

	private static double gameWinnings(double wager){
		int card = (int)(Math.random()* 6 + 1);	

		int die1 = (int)(Math.random()* 6 + 1);
		int die2 = (int)(Math.random()* 6 + 1);
		int die3 = (int)(Math.random()* 6 + 1);

		int winnings = 0;
		int matches = 0;

		if(die1 == card){matches++;}
		if(die2 == card){matches++;}
		if(die3 == card){matches++;}

		if(matches == 0){
			return 0;
		}else{
			return wager + wager * matches;
		}		
	}
}