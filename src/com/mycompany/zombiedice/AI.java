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
	private int shotguns;
	private int brains;
	
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
	public AI (int shotguns, int brains)
	{
		this.shotguns = shotguns;
		this.brains = brains;
	}
	
	/**
	 * 
	 * This is the main loop for computer gameplay which resembles the main
	 * gameloop from the Game class utilizing Thread.sleep() method 
	 * instead of waiting for user input. 
	 *
	 */
	public boolean playing(int shotguns, int brains)
	{
		this.shotguns = shotguns;
		this.brains = brains;
		boolean go = true;
		
		while(go)
		{
			if(this.shotguns >= 3 || (this.shotguns == 2 && this.brains > 2) || 
				(this.shotguns == 1 && this.brains > 4) || this.brains > 10)
				go = false;
				
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
		return this.shotguns;
	}

	/**
	 * 
	 * This method returns the amount of brains rolled.
	 * 
	 */
	public int getBrains() 
	{
		return this.brains;	
	}
}
