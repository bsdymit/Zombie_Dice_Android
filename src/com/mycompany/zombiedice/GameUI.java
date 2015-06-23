package com.mycompany.zombiedice;



import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.Intent;
import PlayerQueue;
import Dice;
import AI;
import android.content.*;

/**
 * 
 * This class handles all gameplay and game logic.
 * 
 * @author Bradley Dymit
 *
 */

import android.view.View.*;

public class GameUI extends Activity
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
	ImageView brainsImage;
	ImageView shotsImage;
	int[] numbers = {R.drawable.zero, R.drawable.one, R.drawable.two, 
					R.drawable.three, R.drawable.four, R.drawable.five, 
					R.drawable.six, R.drawable.seven, R.drawable.eight,
					R.drawable.nine, R.drawable.ten, R.drawable.eleven, 
					R.drawable.twelve, R.drawable.thirteen};

	@Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
		ActionBar actionbar = getActionBar();
		actionbar.hide();

		Intent intent=this.getIntent();
		Bundle bundle=intent.getExtras();
		
		PlayerQueue queue = (PlayerQueue)bundle.getSerializable("queue");

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
		turnCounter = 0;
		
		int buttonId = getResources().getIdentifier("totalPlayerBrains", "id", getPackageName());
		this.totalBrains = (TextView)findViewById(buttonId);
		this.totalBrains.setText(Integer.toString(brains));
		
		buttonId = getResources().getIdentifier("currentPlayerName", "id", getPackageName());
		this.currentPlayer = (TextView)findViewById(buttonId);
		this.currentPlayer.setText(this.players.getName(turnCounter));
	}

	public void rollResults(View view)
	{
		// Print out dice results 
		int buttonId = getResources().getIdentifier("brainsImage", "id", getPackageName());
		this.brainsImage = (ImageView)findViewById(buttonId);
		this.brainsImage.setBackgroundResource(numbers[brains]);

		
		buttonId = getResources().getIdentifier("shotgunsImage", "id", getPackageName());
		this.shotsImage = (ImageView)findViewById(buttonId);
		this.shotsImage.setBackgroundResource(numbers[shotguns]);
		
		// Checks to see if player was shotgunned
		
	}
	
	public void shotgunDialog(View view)
	{
		new AlertDialog.Builder(this)
			.setTitle("ShotGunned!")
			.setMessage("You rolled 3 shotguns this round and lost your brains!")
			.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int id)
				{
					nextTurn(null);
				}
			})
			.show();
	}

	public void quit(View view)
	{
		new AlertDialog.Builder(this)
			.setTitle("Quit Game")
			.setMessage("Are you sure you want to quit?")
			.setPositiveButton("Quit", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int id)
				{
					toMainMenu();
				}
			})
			.setNegativeButton("Cancel",  new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int id){
					//Continue Gameplay
				}
			})
			.show();
	}
	
	@Override
	public void onBackPressed()
	{
		quit(findViewById(android.R.id.content));
	}


	/**
	 * 
	 * This private method ends a player's turn and moves to the next player. If the 
	 * current player did not get shotgunned then they transfer their round
	 * brains to their total brains. 
	 */
	 
	public void nextTurn(View view)
	{
		if (shotguns < 3)
		{
			players.getPlayers()[turnCounter].addBrains(brains);
			int buttonId = getResources().getIdentifier("totalPlayerBrains", "id", getPackageName());
			this.totalBrains = (TextView)findViewById(buttonId);
			this.totalBrains.setText(Integer.toString(players.getPlayers()[turnCounter].getBrainScore()));
		}
			
		brains = 0;
		shotguns = 0;

		if (players.getPlayers()[turnCounter].getBrainScore() >= 13)
		{
			finalRound = true;
			this.lastPlayer = turnCounter;
		}

		dice = new Dice();

		if (turnCounter < players.getTotalPlayers() - 1)
			turnCounter++;
		else
			turnCounter = 0;
			
		if(this.finalRound && turnCounter == this.lastPlayer)
			endGame(view);
			
		int buttonId = getResources().getIdentifier("currentPlayerName", "id", getPackageName());
		this.currentPlayer = (TextView)findViewById(buttonId);
		this.currentPlayer.setText(this.players.getName(turnCounter));
		
		buttonId = getResources().getIdentifier("totalPlayerBrains", "id", getPackageName());
		this.totalBrains = (TextView)findViewById(buttonId);
		this.totalBrains.setText(Integer.toString(this.players.getPlayers()[turnCounter].getBrainScore()));
		
		buttonId = getResources().getIdentifier("brainsImage", "id", getPackageName());
		this.brainsImage = (ImageView)findViewById(buttonId);
		this.brainsImage.setBackgroundResource(numbers[brains]);


		buttonId = getResources().getIdentifier("shotgunsImage", "id", getPackageName());
		this.shotsImage = (ImageView)findViewById(buttonId);
		this.shotsImage.setBackgroundResource(numbers[shotguns]);
	
		if (players.getPlayers()[turnCounter].getType() == 'c')
			ai();
	}

	/**
	 * 
	 * This private method is called from the nextTurn method when a player or 
	 * computer has reached thirteen brains. This gives every other player one
	 * more turn to gain as many brains as possible to win. The same basic while
	 * loop from the main gameloop is implemented here. 
	 */
	 
	private void endGame(View view)
	{	
		//Display Winner and prompt play again
		int winner = 0;
		for(int i = 0; i < this.players.getTotalPlayers(); i++)
		{
			if(this.players.getPlayers()[i].getBrainScore() > 
				this.players.getPlayers()[winner].getBrainScore())
				winner = i;
		}
		new AlertDialog.Builder(this)
			.setTitle("GAME OVER")
			.setMessage("WINNER:\n" + this.players.getPlayers()[winner].getName())
			.setNegativeButton("Main Menu", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int id)
				{
					toMainMenu();
				}
			})
			.setPositiveButton("Play Again", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int id)
				{
					Intent intent = new Intent(GameUI.this, GameUI.class);
					finish();
					startActivity(intent);
				}
			})
			.show();
	}
	
	private void toMainMenu()
	{
		finish();
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}