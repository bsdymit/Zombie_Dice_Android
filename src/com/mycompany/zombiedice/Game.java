package com.mycompany.zombiedice;



import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.Intent;
import PlayerQueue;
import Dice;
import AI;

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
	private boolean finalRound;
	private int lastPlayer;
	private char[] rollResults;
	TextView currentPlayer;
	TextView totalBrains;

	@Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
		ActionBar actionbar = getActionBar();
		actionbar.hide();

		Intent intent = getIntent();
		PlayerQueue queue = (PlayerQueue)intent.getSerializableExtra("queue");

		initializeGame(queue);
		
	}

	public void initializeGame(PlayerQueue queue)
	{
		this.dice = new Dice();
		this.players = queue;
		this.rollResults = new char[3];
		this.brains = 0;
		this.shotguns = 0;
		this.turnCounter = 0;
		this.finalRound = false;
		int buttonId = getResources().getIdentifier("totalPlayerBrains", "id", getPackageName());
		this.totalBrains = (TextView)findViewById(buttonId);
		this.totalBrains.setText(Integer.toString(brains));
		//currentPlayer = 
	}

	public void roll()
	{
		dice.roll();
		shotguns += dice.getNumShotguns();
		brains += dice.getNumBrains();

		// Prints out dice results 

		// Checks to see if player was shotgunned
		if (shotguns >= 3)
		{
			//Print Shotguns
			//nextTurn();
		}
	}

	public void stop()
	{
		//nextTurn();
	}

	public void quit()
	{
		//Ask if theyreallywanttoquit

		//Goto main menu ifthey do

		//Continue otherwise
	}

	/**
	 * 
	 * This method calls the AI class and provides it with necessary variables
	 * to have a computer play independently of a user. 
	 * 
	 
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
	 
	private void nextTurn()
	{
		if(this.finalRound && turnCounter == this.lastPlayer)
			endGame();
			
		if (shotguns < 3)
			players.getPlayers()[turnCounter].addBrains(brains);
			
		brains = 0;
		shotguns = 0;

		if (players.getPlayers()[turnCounter].getBrainScore() >= 13)
		{
			finalRound = true;
			this.lastPlayer = turnCounter;
		}

		else
		{
			dice = new Dice();

			if (turnCounter < players.getTotalPlayers() - 1)
				turnCounter++;
			else
				turnCounter = 0;

			if (players.getPlayers()[turnCounter].getType() == 'c')
				ai();
		}
	}

	/**
	 * 
	 * This private method is called from the nextTurn method when a player or 
	 * computer has reached thirteen brains. This gives every other player one
	 * more turn to gain as many brains as possible to win. The same basic while
	 * loop from the main gameloop is implemented here. 
	 * 
	 
	private void endGame()
	{	

		//Display Winner and prompt play again
	}*/
}
