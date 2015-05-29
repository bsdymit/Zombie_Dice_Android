/**
 * 
 * This class handles all AI programming and how the computer players respond
 * to dice rolls when playing. 
 * 
 * @author Bradley Dymit
 *
 */
public class AI 
{
	private Dice dice;
	private int shotguns;
	private int brains;
	private char[] rollResults;
	private PlayerQueue players;
	private int turnCounter;
	
	/**
	 * 
	 * Constructor that takes in all necessary variables to complete field variables
	 * as well as provide the computer with enough information to decide roll 
	 * options properly.
	 * 
	 * @param dice
	 * @param shotguns
	 * @param brains
	 * @param rollResults
	 * @param players
	 * @param turnCounter
	 */
	public AI (Dice dice, int shotguns, int brains, char[] rollResults, PlayerQueue players, int turnCounter)
	{
		this.dice = dice;
		this.shotguns = shotguns;
		this.brains = brains;
		this.rollResults = rollResults;
		this.players = players;
		this.turnCounter = turnCounter;
	}
	
	/**
	 * 
	 * This is the main loop for computer gameplay which resembles the main
	 * gameloop from the Game class utilizing Thread.sleep() method 
	 * instead of waiting for user input. 
	 *
	 */
	public void play()
	{
		boolean go = true;
		
		System.out.println("Computer " + players.getName(turnCounter) + "'s turn.");
		
		try
		{
			Thread.sleep(2500);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		while(go)
		{
			if(brains == 13)
				go = false;
			
			if(shotguns == 2 && brains > 2)
			{
				System.out.println("Computer stopped rolling.");
				go = false;
			}
			else if(shotguns == 1 && brains > 4)
			{
				System.out.println("Computer stopped rolling.");
				go = false;
			}
			else if(brains > 10)
			{
				System.out.println("Computer stopped rolling.");
				go = false;
			}
			
			else
			{
				dice.roll();
				shotguns += dice.getNumShotguns();
				brains += dice.getNumBrains();
				
				System.out.println(printHeading());
				// Prints out dice rolled
				System.out.print("Dice Rolled: ");
				for(int i = 0; i < dice.getNumInHand(); i++) 
				{
					System.out.print(dice.getDiceInHand().get(i).getType() + " | ");
				}
				
				// Prints out dice results 
				System.out.print("\nDice Results: ");
				for(int j = 0; j < dice.getNumInHand(); j++)
				{
					if(dice.getRollResult()[j] == 's')
						System.out.print("Shotgun " + " | ");
					else if(dice.getRollResult()[j] == 'r')
						System.out.print("  Run   " + " | ");
					else
						System.out.print("Braaains" + " | ");
				}
				
				// Checks to see if computer was shotgunned
				if(shotguns < 3)
				{
					System.out.print(printGameHud());
				}
				else
				{
					System.out.println("\nSHOTGUNNED!");
					go = false;
				}
			}
			try
			{
				Thread.sleep(4000);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Used to print a generic heading for gameplay.
	 * @return heading
	 */
	private String printHeading()
	{
		String heading = "\n\n\n\t\t\t-------------------" +
				   "\n\t\t\t|***Z*O*M*B*I*E***|" +
				   "\n\t\t\t|*****D*I*C*E*****|" +
				   "\n\t\t\t-------------------\n";
		
		return heading;
	}
	
	/**
	 * Used to print a generic game hud which instructs user on how to play,
	 * displays current player, current player's total brains/round brains/shotguns,
	 * and shows prompt for next action to take.
	 * @return HUD
	 */
	private String printGameHud()
	{
		String playerType;
		if(players.getType(turnCounter) == 'c')
			playerType = "Computer";
		else
			playerType = "Player";
		
		String HUD ="\nType \"roll\" to roll, or \"stop\" to save brains.\n" +
		playerType + ": " + players.getName(turnCounter) + 
		"\nTotal Brains: " + players.getPlayers()[turnCounter].getBrainScore() + 
		"\nRound Brains: " + brains +
		"\nShotguns: " + shotguns +
		"\nAction: ";
		
		return HUD;
	}
	
	/**
	 * 
	 * This method returns the amount of shotguns rolled.
	 * 
	 */
	public int getShotguns() 
	{
		return shotguns;
	}

	/**
	 * 
	 * This method returns the amount of brains rolled.
	 * 
	 */
	public int getBrains() 
	{
		return brains;	
	}
	
	/**
	 * 
	 * This method returns the roll results at the end of the round.
	 * 
	 */
	public char[] getRollResults() 
	{
		return rollResults;
	}
}
