import java.util.*;

public class lotteryGame	{

	public static void main (String args[])	{
		
		//user number choices, randomly generated array using assignNumbers method
		int		userNum[] = new int[6];
		int		compNum[] = new int[6];
		
		int 	numRounds=0,currentRound; //loop controls
		int		numduplicates; //duplicate count, compare numbers output
		boolean playing=true; //loop control, allows player to replay lottery
		int		userMoney = 100; //gamifies the lottery by adding a budget
		Scanner keyin = new Scanner (System.in);
		
		while (playing) {
			
			//Determine round with highest profits, and what the profits were
			int		greatestProfit = 0;
			int 	highestRound = 0;
			
			int		winnings=-1; //winnings of current round. Starts at -1 because your first ticket costs $1
			int		totalWinnings=0; //running total of winnings, used as final winnings
			int		jackpot=0;
			Random rand = new Random();
			
			boolean affordable = true; //ensures player has enough money
			while (affordable) {
				System.out.printf ("You have $%d to spend. How many times would you like to play the lottery?\n", userMoney);
					numRounds = keyin.nextInt();
				System.out.printf ("\n");
				if (numRounds > userMoney) {
					System.out.printf ("You don't have enough money! Try again.\n");
					affordable = true;
				} else if (numRounds <= userMoney) {
					affordable = false;
					break;
				}
			}
			for (currentRound=0; currentRound<numRounds; currentRound++) {
			
				assignNumbers(userNum, rand);
				
				assignNumbers(compNum, rand);
				
				numduplicates = compareNumbers(userNum, compNum);
				
				switch(numduplicates) {
					
					default:
							winnings = 0;
							break;
					case 0:
							winnings = 0;
							break;
					case 1:
							winnings = 0;
							break;
					case 2:
							winnings = 0;
							break;
					case 3:
							winnings = 3;
							break;
					case 4:
							winnings = 50;
							break;
					case 5:
							winnings = 2000;
							break;
					case 6:
							winnings = 20000000;
							jackpot = currentRound + 1;
							break;					
				}
				
				int roundProfit = winnings - (currentRound+1);
				
				if (roundProfit > greatestProfit) {
					greatestProfit = roundProfit;
					highestRound = currentRound+1;					
				}
				
				totalWinnings = (totalWinnings + winnings);
			}
			
			//output variables
			int		amountSpent = numRounds;
			int		profit = totalWinnings-amountSpent;
			boolean didWinJackpot = jackpot != 0;
			boolean profitted = greatestProfit > 0;
			
			System.out.printf ("______________________________________________________\n");
			System.out.printf ("                       REPORT\n");
			
			System.out.printf ("You spent: \t\t\t\t$%d\n", amountSpent);
			System.out.printf ("You earned: \t\t\t\t$%d\n", totalWinnings);
			System.out.printf ("Your profit/loss: \t\t\t$%d\n", profit);
			if (profitted) { //tells you when you profitted the most, and how much profit you had
				System.out.printf ("Your highest total profit was: \t\t$%d\n", greatestProfit);
				System.out.printf ("Your highest profit occured on round: \t %d\n", highestRound);
			} else {
				System.out.printf ("At no point did you have more than $%d!\n", amountSpent);
			}
			if (didWinJackpot) { //tells you if you won the jackpot and at which round you won it. assumes only one jackpot.
				System.out.printf("You won the jackpot on round %d\n", jackpot);
			} else {
				System.out.printf ("You didn't win the jackpot!\n");
			}

			System.out.printf ("______________________________________________________\n\n");
			
			if (profit > 0) {
			  System.out.printf ("**Congrats! You beat the odds and made a profit.**\n\n");
			} else if (profit < 0) {
			  System.out.printf ("Yikes! Like most of the other players, you lost money. But you've gotta play to win!...\n\n");
			} else { // has to be profit == 0
			  System.out.printf ("Well... you didn't lose! That's better than most people!\n\n");
			}
			
			//option to replay lottery
			boolean replay = true;
			String yesno;
			userMoney = userMoney + profit; //adds or subtracts profits

			while (replay) {
				if (userMoney <= 0) { //if statement in case user has no money to play again.
					System.out.printf ("You spent all of your money on lotto tickets... You lost! Are you happy with yourself?\n");
					replay = false; //exits game
					playing = false;
				} else if (userMoney > 0) {
					System.out.printf("You now have $%d\n", userMoney);
					System.out.printf ("Would you like to continue spending? (y/n)\n");
					yesno = keyin.next();
					if (yesno.equals("y")) {
						replay = false; //when replay is false, you exit this loop, but since playing is still true, start at top
					}
					else if(yesno.equals("n")) {
						playing = false;
						replay = false;
					} else {
						replay = true;
						}
				}
			}
		}
	}
	
	

	

	public static void assignNumbers(int num[], Random rand)	{

		boolean	duplicate;
		int 	i=0, j=0;
		int 	candidate;
		
		for (i=0; i<6; i++)	{
			//Variable we want to check (see if it's already in the array)
			candidate = rand.nextInt(53)+1;	
			
			//set this to false, no duplicates found
			duplicate = false;
			
			for (j=0; j<i; j++)	{
				if (candidate == num[j])	{
					duplicate = true;
					//breaks out of loop if we find one dup, so we don't continue looking
					break;
				}
			}
			
			if (duplicate == true)	{
				//rewind i so it doesn't duplicate
				i--;
			}
			else	{
				//no duplicate, assign value
				num[i] = candidate;
			}
		
		}
	}		
	
	
	public static int compareNumbers(int usernum[], int compnum[])	{
		
		//this method assumes that there are no duplicates in either array (there shouldn't be)
		
		boolean	duplicate;
		int 	i=0, j=0;
		int 	candidate;
		int		dupcount=0;
		
		for (i=0; i<6; i++)	{
			//set usernum[i] to a variable so we can compare it in an array in the following for loop
			candidate = usernum[i];	
			
			//set this to false, no duplicates yet
			duplicate = false;
			
			//comparing usernum[i] to all of compnum
			for (j=0; j<6; j++)	{
				if (candidate == compnum[j])	{
					dupcount++;
				}
			}
		}
		
		return dupcount;
	}	
	
}
	