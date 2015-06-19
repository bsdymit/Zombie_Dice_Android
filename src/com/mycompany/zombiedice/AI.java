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
	public boolean playing()
	{
		boolean go = true;
		
		try
		{
			Thread.sleep(2500);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		while(go)
		{
			if(shotguns >= 3)
				go = false;
				
			else if(brains == 13)
				go = false;
			
			if(shotguns == 2 && brains > 2)
			{
				go = false;
			}
			else if(shotguns == 1 && brains > 4)
			{
				go = false;
			}
			else if(brains > 10)
			{
				go = false;
			}
			
			try
			{
				Thread.sleep(4000);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		return go;
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
}
