/**
 * 
 * This class manages the players in a game giving each player a Name, a type
 * and a total count of brains which represents their score.
 * 
 * @author Bradley Dymit
 *
 */
 
import java.io.Serializable;
 
 
public class Player implements Serializable
{

	private String playerName;
	private char playerType;
	private int brains;
	
	/**
	 * 
	 * Default constructor that initializes brains to zero and a name and type
	 * to a blank String and blank character.
	 * 
	 */
	public Player()
	{
		this.brains = 0;
		this.playerName = " ";
		this.playerType = ' ';
	}
	
	/**
	 * 
	 * This constructor takes in a a player Name as a String and a player Type
	 * as a character and updates the field variables accordingly. 
	 * 
	 * @param playerName Name of a new player
	 * @param playerType Type of a new player
	 */
	public Player(String playerName, char playerType)
	{
		this.playerName = playerName;
		this.playerType = playerType;
	}
	
	/**
	 * 
	 * This method returns the String playerName.
	 * 
	 */
	public String getName()
	{
		return this.playerName;
	}
	
	/**
	 * 
	 * This method returns the character playerType.
	 * 
	 */
	public char getType()
	{
		return this.playerType;
	}
	
	/**
	 * 
	 * This method returns the Integer player brain score.
	 * 
	 */
	public int getBrainScore()
	{
		return this.brains;
	}
	
	/**
	 * 
	 * This method takes in an integer that represents brains to add to the
	 * total score and adds brains to the player's total brains score.
	 * 
	 */
	public void addBrains(int score)
	{
		this.brains += score;
	}
	
	public void resetScore()
	{
		this.brains = 0;
	}
}
