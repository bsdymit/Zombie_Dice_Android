import java.util.Scanner;

/**
 * 
 * Zombie Dice CLI (Command Line Interface)
 * 
 * This class is the main application class for Zombie Dice 
 * and handles all user interface as well as initializing the
 * text menu and most other classes.
 * 
 * @author Bradley Dymit
 *
 */

public class ZombieDiceApp 
{
	private Menu textMenu;
	private PlayerQueue players;
	private Game game;
	
	/**
	 * 
	 * This method acts as a constructor to be used within the class to create
	 * menu options and instantiate/create Menu and PlayerQueue objects.
	 * 
	 */
	public void setup()
	{
		String[] menuOptions = {"Play", "Quit"};
		this.textMenu = new Menu(menuOptions);
		this.players = new PlayerQueue();
	}
	
	/**
	 * 
	 * This method is the main loop for the menu.
	 * 
	 */
	public void run()
	{
		int choice = 0;
		while(choice != 2)
		{
			choice = this.textMenu.getChoice();
			if(choice == 1)
				choice1();
		}
		choice2();
	}
	
	/**
	 * 
	 * This is the first choice in the menu which includes the user interface
	 * and player entry. Players can be entered as humans or computers, as well
	 * as removed. The game can be started by typing the word "start".
	 * 
	 */
	public void choice1()
	{
		Scanner scan = new Scanner(System.in);
		String name = "foo";
		while(!name.toLowerCase().equals("start"))
		{
			System.out.println("\n\n\n---PLAYER ENTRY---\n");
			System.out.println("Type in the name of a player and press enter.\n" +
							"Type an \"com \" (with a space) before the name for computer player. Example: com Computer1\n" +
							"Type \"Remove\" before the name to remove any player from the game. Example: remove Computer1\n");
			System.out.println("Type \"Start\" to start the game.");
			
			for(int i = 0; i < this.players.getTotalPlayers(); i ++)
			{
				if(this.players.getType(i) == 'h')
					System.out.println("Player " + (i+1) + ": " + this.players.getName(i));
				else if(this.players.getType(i) == 'c')
					System.out.println("Computer " + (i+1) + ": " + this.players.getName(i));
			}
			System.out.print("Player " + (this.players.getTotalPlayers()+1) + ": ");
			name = scan.nextLine();
			name = name.trim();
			
			if(name.toLowerCase().startsWith("remove"))
			{
				if(name.length() >= 8 && name.toLowerCase().substring(7) != " ")
					this.players.removePlayer(name.split("remove ")[1]);
				else
					System.out.println("Sorry, improper remove statement.");
			}
			
			else if(name.toLowerCase().equals("start"))
			{
				if(players.getTotalPlayers() > 0)
				{
					this.game = new Game(this.players);
					this.game.run();
				}
				else
				{
					System.out.println("Error: No name entered.");
					name = "foo";
				}
			}
			
			else 
			{
				if(name.startsWith("com "))
					this.players.addPlayer(name.split("com ")[1], 'c');
				else
					this.players.addPlayer(name, 'h');
			}
			
		}
	}
	
	/**
	 * 
	 * This is the second menu option that quits the game and prints s message.
	 * 
	 */
	public void choice2()
	{
		System.out.println("C YA!");
		System.exit(1);
	}
	
	/**
	 * 
	 * Main method to run the program.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		ZombieDiceApp app = new ZombieDiceApp();
		app.setup();
		app.run();
	}
}
