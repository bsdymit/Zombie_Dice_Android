import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * This class holds the data for the different collections of dice used throughout
 * a turn. The thirteen dice can be split up between the cup, the player's hand, 
 * or in a used pile. The number of red, yellow, and green dice are listed below
 * and methods to roll and record dice rolls are implemented below.
 * 
 * @author Bradley Dymit
 *
 */

public class Dice 
{
	private final int numRed = 3;
	private final int numYellow = 4;
	private final int numGreen = 6;
	private int numInCup;
	private int numInHand;
	private int numUsedDice;
	
	private Die redDie;
	private Die yellowDie;
	private Die greenDie;
	
	private ArrayList<Die> diceInCup;
	private ArrayList<Die> diceInHand;
	private ArrayList<Die> usedDice;
	private char[] rollResults;
	
	
	/**
	 * 
	 * Constructor used to set up the amount of dice, what sides are given
	 * to what type of die, and adds the dice to the cup.
	 * 
	 */
	public Dice()
	{
		numInCup = 13;
		numInHand = 0;
		numUsedDice = 0;
		
		// These three lines describe the sides of each type of die.
		redDie = new Die(1, 2); //1, 2
		yellowDie = new Die(2, 2); // 2,2
		greenDie = new Die(3, 2); //3,2
		
		diceInCup = new ArrayList<Die>();
		diceInHand = new ArrayList<Die>();
		usedDice = new ArrayList<Die>();
		rollResults = new char[3];
		
		for(int i = 0; i < numRed; i++)
		{
			diceInCup.add(redDie);
		}
		
		for(int j = 0; j < numYellow; j++)
		{
			diceInCup.add(yellowDie);
		}
		
		for(int k = 0; k < numGreen; k++)
		{
			diceInCup.add(greenDie);
		}
	}
	
	/**
	 * 
	 * This method randomly chooses a hand from the cup and adds roll results
	 * to the rollResults character array using the Die roll method.
	 * 
	 */
	public void roll()
	{
		chooseHand();
		for(int i = 0; i < numInHand; i++)
		{
			rollResults[i] = diceInHand.get(i).roll();
		}
	}
	
	/**
	 * 
	 * This private method first checks the current hand for brains and shotguns from
	 * the previous roll and removes those dice to the usedDice array if they
	 * exist. Then dice are taken from the cup to fill the hand if dice are
	 * needed.
	 * 
	 */
	private void chooseHand()
	{
		Random rand = new Random();
		removeBrainsandShotguns();
		
		int neededDice = 3 - (numInHand + 1);
		for(int i = neededDice; i >= 0; i--)
		{
			if(numInCup > 0)
			{
				if(numInCup == 1)
					diceInHand.add(diceInCup.remove(0));
				else
					diceInHand.add(diceInCup.remove(rand.nextInt(numInCup - 1)));
				
				numInCup--;
				numInHand++;
			}
		}
	}
	
	/**
	 * 
	 * This private method checks the hand for brains or shotguns that were
	 * rolled from previous rolls and removes them to the usedDice array if
	 * they exist.
	 * 
	 */
	private void removeBrainsandShotguns()
	{
		int check = numInHand - 1;
		for(int i = check; i >= 0; i--)
		{
			if(rollResults[i] == 's' || rollResults[i] == 'b')
			{
				usedDice.add(diceInHand.remove(i));
				numUsedDice++;
				numInHand--;
			}
		}
		
		// Used to reset the roll results
		for(int i = 0; i < rollResults.length; i++)
		{
			rollResults[i] = ' ';
		}
	}
	
	/**
	 * 
	 * This method returns the number of shotguns rolled in a specific turn.
	 * 
	 * @return shotguns rolled in a turn.
	 */
	public int getNumShotguns()
	{
		int shotguns = 0;
		for(int i = 0; i < rollResults.length; i++)
		{
			if(rollResults[i] == 's')
				shotguns++;
		}
		return shotguns;
	}
	
	/**
	 * 
	 * This method returns the number of brains rolled in a specific turn.
	 * 
	 * @return brains rolled in a turn.
	 */
	public int getNumBrains()
	{
		int brains = 0;
		for(int i = 0; i < rollResults.length; i++)
		{
			if(rollResults[i] == 'b')
				brains++;
		}
		return brains;
	}
	
	/**
	 * 
	 * This method returns the ArrayList of dice that are in a player's hand.
	 * 
	 * @return diceInHand ArrayList of Die objects
	 */
	public ArrayList<Die> getDiceInHand()
	{
		return diceInHand;
	}
	
	/**
	 * 
	 * This method returns the character Array of roll results from a single roll.
	 * 
	 * @return rollResults character array
	 */
	public char[] getRollResult()
	{
		return rollResults;
	}
	
	/**
	 * 
	 * This method returns the amount of dice in the cup.
	 * 
	 * @return numInCup integer
	 */
	public int getNumInCup() 
	{
		return numInCup;
	}

	/**
	 * 
	 * This method returns the amount of dice in the player's hand.
	 * 
	 * @return numInHand integer
	 */
	public int getNumInHand() 
	{
		return numInHand;
	}
}
