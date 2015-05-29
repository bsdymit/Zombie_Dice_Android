import java.util.Random;

/**
 * 
 * This class contains the data to create any type of die for Zombie Dice. Any
 * die will have six sides but be a different type (red, yellow, or green) and
 * depending on that type, will have different sides (brains, run, or shotgun)
 * represented by a char b, r, or s respectively. 
 * 
 * @author Bradley Dymit
 *
 */

public class Die
{
	private char[] sides;
	private int numSides = 6;
	private String type;
	
	/**
	 * Constructor that takes in the amount of brains and amount of runs on a
	 * die and from those two numbers, determines the type and other sides.
	 * 
	 * @param brain Number of brains on a die.
	 * @param run Number of runs on a die.
	 */
	public Die(int brain, int run)
	{
		sides = new char[numSides];
		if(brain == 3)
			type = "Green";
		else if(brain == 2)
			type = "Yellow";
		else
			type = "Red";
		for(int i = 0; i < brain; i++)
		{
			sides[i] = 'b';
		}
		
		for(int j = brain; j < (brain + run); j++)
		{
			sides[j] = 'r';
		}
		
		for(int k = (brain + run); k < sides.length; k++)
		{
			sides[k] = 's';
		}
	}
	
	/**
	 * 
	 * Using the Random class, this method chooses a random side on the die and
	 * returns the character of that side. 
	 * 
	 * @return Character of that randomly chosen side.
	 */
	public char roll()
	{
		Random rand = new Random();
		return sides[rand.nextInt(numSides)];
	}
	
	/**
	 * 
	 * Getter for Number of sides on a die.
	 * 
	 * @return Number of sides on a die.
	 */
	public int getNumSides()
	{
		return numSides;
	}
	
	
	/**
	 * 
	 * Returns the type of a die.
	 *
	 */
	public String getType()
	{
		return "   " + type + "   ";
	}
}
