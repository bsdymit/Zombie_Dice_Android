/**
 * 
 * This class defines the number and types of players in a game.
 * 
 * @author Bradley Dymit
 *
 */
 
 import java.io.Serializable;
 
public class PlayerQueue implements Serializable
{
	private Player[] players;
	private Player newPlayer;
	private int totalPlayers;
	private int numCPU;
	private int numHumans;
	private static final int MAXPLAYERS = 8;
	
	/**
	 * 
	 * Constructor that sets up a Player array and the beginning amount of 
	 * all types of players.
	 * 
	 */
	public PlayerQueue()
	{
		this.numCPU = 0;
		this.numHumans = 0;
		this.totalPlayers = 0;
		this.players = new Player[this.MAXPLAYERS];
	}
	
	/**
	 * 
	 * This method takes in a name and type of a player where the name is a String
	 * and the type is a character. The method will not allow duplicates, more than
	 * eight players, and will specify what type of player is added updating
	 * appropriate variables.
	 * 
	 * @param name Name of player
	 * @param type Type of player (computer or human)
	 */
	public boolean addPlayer(String name, char type)
	{
		if(this.totalPlayers >= this.MAXPLAYERS)
		{
			return false;
		}
		else if(findPlayer(name) != -1)
		{
			return false;
		}
		else if(name.replaceAll("\\s", "").equals(""))
		{
			return false;
		}
		else
		{
			this.newPlayer = new Player(name, type);
			this.players[this.totalPlayers] = newPlayer;
			this.totalPlayers++;
			if(newPlayer.getType() == 'c')
				this.numCPU++;
			else
				this.numHumans++;
			return true;
		}
	}
	
	/**
	 * 
	 * This method takes in a player name as a String and attempts to remove
	 * that player from the queue. The method checks to see if the player
	 * exists, and if it does, the player is removed and respective variables
	 * are updated.
	 * 
	 * @param name Name of player to be removed
	 */
	public boolean removePlayer(String name)
	{
		int index = this.findPlayer(name);
		if(index == -1)
		{
			return false;
		}
		else
		{
			if(this.players[index].getType() == 'c')
			{
				this.numCPU--;
			}
			else
			{
				this.numHumans--;
			}
			
			for(int i = index; i < (this.MAXPLAYERS-1); i++)
			{
				this.players[i] = this.players[i+1];
			}
			this.totalPlayers--;
			return true;
		}
	}
	
	/**
	 * 
	 * This method returns the array of Player objects.
	 * 
	 * @return array of Player objects
	 */
	public Player[] getPlayers()
	{
		return players;
	}
	
	/**
	 * 
	 * This method takes in an integer and returns a String of a player name
	 * at the integer index location in the Player object array.
	 * 
	 * @param index Integer index of player in a array.
	 * @return String name of player at index
	 */
	public String getName(int index)
	{
		return this.players[index].getName();
	}
	
	/**
	 * 
	 * This method takes in an integer and returns a character of a player type
	 * at the integer index location in the Player object array.
	 * 
	 * @param index Integer index of player in a array.
	 * @return Character type of player at index
	 */
	public char getType(int index)
	{
		return this.players[index].getType();
	}
	
	/**
	 * 
	 * Returns the integer value of the total amount of players.
	 * 
	 */
	public int getTotalPlayers()
	{
		return this.totalPlayers;
	}
	
	/**
	 * 
	 * Returns the integer value of the total amount of computer players.
	 * 
	 */
	public int getNumCPUs()
	{
		return this.numCPU;
	}
	
	/**
	 * 
	 * Returns the integer value of the total amount of human players.
	 * 
	 */
	public int getNumHumans()
	{
		return this.numHumans;
	}
	
	/**
	 * 
	 * Returns the integer value of the total amount of players allowed.
	 * 
	 */
	public int getMaxPlayers()
	{
		return this.MAXPLAYERS;
	}
	
	/**
	 * 
	 * This method returns the name of the player who has the highest amount
	 * of brains at the time this method is called. 
	 * 
	 */
	public String getWinner()
	{
		int highest = 0;
		int indexOfHighest = -1;
		for(int i = 0; i < this.totalPlayers; i++)
		{
			if(players[i].getBrainScore() > highest)
			{
				highest = players[i].getBrainScore();
				indexOfHighest = i;
			}
		}
		return players[indexOfHighest].getName();
	}
	
	/**
	 * 
	 * This method takes in a name of a player as a String and if that user exists
	 * their index is returned as an integer. If the player does not exist, a -1
	 * is returned instead.
	 * 
	 * @param name Name of player as a String
	 * @return Index of player if they exist. -1 if they do not exist
	 */
	private int findPlayer(String name)
	{
		int index = -1;
		for(int i = 0; i < this.totalPlayers; i++)
		{
			if(this.players[i].getName().equals(name))
				index = i;
		}
		return index;
	}
}
