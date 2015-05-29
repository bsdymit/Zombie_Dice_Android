package com.mycompany.zombiedice;



import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.Intent;

/**
 * 
 * This class handles all gameplay and game logic.
 * 
 * @author Bradley Dymit
 *
 */
public class Game extends Activity
{
	private int turnCounter;
	private PlayerQueue players;
	private Dice dice;
	private int shotguns;
	private int brains;
	private char[] rollResults;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_entry);
		ActionBar actionbar = getActionBar();
		actionbar.hide();
		
		initializeGame(//get queue);
	}
	
	public initializeGame(PlayerQueue queue)
	{
		this.dice = new Dice();
		this.players = queue;
		this.rollResults = new char[3];
		this.turnCounter = 0;
	}
	
	/**
	 * 
	 * This is the main game-loop method which controls player turns as well as
	 * keeps track of dice and score. 
	 * 
	 */
	public void run()
	{
		boolean go = true;
		
		// Main Loop
		while(go)
		{
			if(brains == 13)
				nextTurn();
			if(this.players.getType(turnCounter) == 'c')
				ai();
			
			else
			{
				Scanner scan = new Scanner(System.in);
				String action = scan.nextLine();
				
				// If player types "roll"
				if(action.toLowerCase().equals("roll"))
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
					
					// Checks to see if player was shotgunned
					if(shotguns < 3)
					{
						System.out.print(printGameHud());
					}
					else
					{
						System.out.println("\nSHOTGUNNED!");
						nextTurn();
					}
				}
				// If player types "stop"
				else if(action.toLowerCase().equals("stop"))
				{
					nextTurn();
				}
				
				// If player types "quit"
				else if(action.toLowerCase().equals("quit"))
				{
					Scanner input = new Scanner(System.in);
					String quit;
					
					System.out.println("Are you sure you want to end the game? (yes or no)");
					System.out.print(">>> ");
					
					quit = input.nextLine();
					
					if(quit.toLowerCase().equals("yes"))
					{
						System.out.println("\n\n\n");
						
						ZombieDiceApp app = new ZombieDiceApp();
						app.setup();
						app.run();
					}
					else
					{
						System.out.println("Press enter to continue.");
						continue;
					}
					
				}
				
				else
				{
					System.out.println(printHeading());
					System.out.println("Sorry, not a valid entry.");
					System.out.print(printGameHud());
					go = true;
				}
			}
		}
	}
	
	/**
	 * 
	 * This method calls the AI class and provides it with necessary variables
	 * to have a computer play independently of a user. 
	 * 
	 */
	private void ai()
	{
		AI ai = new AI(this.dice, this.shotguns, this.brains, this.rollResults, this.players, this.turnCounter);
		ai.play();
		brains = ai.getBrains();
		shotguns = ai.getShotguns();
		rollResults = ai.getRollResults();
		nextTurn();
	}
	
	/**
	 * 
	 * This private method ends a player's turn and moves to the next player. If the 
	 * current player did not get shotgunned then they transfer their round
	 * brains to their total brains. 
	 * 
	 */
	private void nextTurn()
	{
		if(shotguns < 3)
			players.getPlayers()[turnCounter].addBrains(brains);
		brains = 0;
		shotguns = 0;
	
		if(players.getPlayers()[turnCounter].getBrainScore() >= 13)
			finalRound();
		
		else
		{
			dice = new Dice();
			
			if(turnCounter < players.getTotalPlayers() - 1)
				turnCounter++;
			else
				turnCounter = 0;
			
			run();
		}
	}
	
	/**
	 * 
	 * This private method is called from the nextTurn method when a player or 
	 * computer has reached thirteen brains. This gives every other player one
	 * more turn to gain as many brains as possible to win. The same basic while
	 * loop from the main gameloop is implemented here. 
	 * 
	 */
	private void finalRound()
	{	
		for(int i = 0; i < players.getTotalPlayers() - 1; i++)
		{
			boolean go = true;
			dice = new Dice();
			
			if(turnCounter < players.getTotalPlayers() - 1)
				turnCounter++;
			else
				turnCounter = 0;
			
			System.out.println(printHeading());
			System.out.print(printGameHud());
			
			while(go)
			{
				if(brains == 13)
					go = false;
				Scanner scan = new Scanner(System.in);
				String action = scan.nextLine();
				
				if(action.toLowerCase().equals("roll"))
				{
					dice.roll();
					shotguns += dice.getNumShotguns();
					brains += dice.getNumBrains();
					
					System.out.println(printHeading());
					System.out.print("Dice Rolled: ");
					for(int j = 0; j < dice.getNumInHand(); j++) 
					{
						System.out.print(dice.getDiceInHand().get(j).getType() + " | ");
					}
					
					System.out.print("\nDice Results: ");
					for(int k = 0; k < dice.getNumInHand(); k++)
					{
						if(dice.getRollResult()[k] == 's')
							System.out.print("Shotgun " + " | ");
						else if(dice.getRollResult()[k] == 'r')
							System.out.print("  Run   " + " | ");
						else
							System.out.print("Braaains" + " | ");
					}
					
					if(shotguns < 3)
					{
						System.out.print(printGameHud());
					}
					else
					{
						System.out.println("\nSHOTGUNNED!");
						brains = 0;
						shotguns = 0;
						go = false;
					}
				}
				else if(action.toLowerCase().equals("stop"))
				{
					if(shotguns < 3)
						players.getPlayers()[turnCounter].addBrains(brains);
					brains = 0;
					shotguns = 0;
					go = false;
				}
				else
				{
					System.out.println(printHeading());
					System.out.println("Sorry, not a valid entry.");
					System.out.print(printGameHud());
					go = true;
				}
			}
		}
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\n\n\n\nAnd the winner is.....");
		System.out.println("****** " + players.getWinner().toUpperCase() + " ******\n\n");
		
		boolean go = true;
		while(go)
		{
			System.out.println("Play Again? (\"Yes\" or \"No\")");
			System.out.print(">>> ");
			String input = scan.nextLine();
			
			if(input.toLowerCase().equals("yes"))
			{
				go = false;
				System.out.println("\n\n\n");
				
				ZombieDiceApp app = new ZombieDiceApp();
				app.setup();
				app.run();
			}
			else if(input.toLowerCase().equals("no"))
			{
				System.out.println("CYA!");
				go = false;
				System.exit(1);
			}
			else
			{
				System.out.println("Sorry, not a valid entry. Enter \"yes\" or \"no\".");
			}
		}
	}
}
