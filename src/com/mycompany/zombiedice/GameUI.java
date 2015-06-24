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
	private Game game;
	private PlayerQueue queue;
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
		
		this.queue = (PlayerQueue)bundle.getSerializable("queue");

		initializeGame(queue);
		
	}

	public void initializeGame(PlayerQueue queue)
	{
		this.game = new Game(queue);
		this.queue = this.game.getPlayers();
		int buttonId = getResources().getIdentifier("totalPlayerBrains", "id", getPackageName());
		this.totalBrains = (TextView)findViewById(buttonId);
		this.totalBrains.setText(Integer.toString(this.game.getBrains()));
		
		buttonId = getResources().getIdentifier("currentPlayerName", "id", getPackageName());
		this.currentPlayer = (TextView)findViewById(buttonId);
		this.currentPlayer.setText(this.game.getPlayers().getName(this.game.getTurnCounter()));
		
		updateBrainsAndShots(null);
	}

	public void roll(View view)
	{
		if(this.game.roll())
			shotgunDialog(view);
		else
		{
			updateBrainsAndShots(view);
		}
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
		if(this.game.nextTurn())
			endGame(view);
		else
		{
			int buttonId = getResources().getIdentifier("totalPlayerBrains", "id", getPackageName());
			this.totalBrains = (TextView)findViewById(buttonId);
			this.totalBrains.setText(Integer.toString(this.game.getPlayers().getPlayers()[this.game.getTurnCounter()].getBrainScore()));
		
			buttonId = getResources().getIdentifier("currentPlayerName", "id", getPackageName());
			this.currentPlayer = (TextView)findViewById(buttonId);
			this.currentPlayer.setText(this.game.getPlayers().getName(this.game.getTurnCounter()));
		
			buttonId = getResources().getIdentifier("totalPlayerBrains", "id", getPackageName());
			this.totalBrains = (TextView)findViewById(buttonId);
			this.totalBrains.setText(Integer.toString(this.game.getPlayers().getPlayers()[this.game.getTurnCounter()].getBrainScore()));
		
			buttonId = getResources().getIdentifier("brainsImage", "id", getPackageName());
			this.brainsImage = (ImageView)findViewById(buttonId);
			this.brainsImage.setBackgroundResource(numbers[this.game.getBrains()]);


			buttonId = getResources().getIdentifier("shotgunsImage", "id", getPackageName());
			this.shotsImage = (ImageView)findViewById(buttonId);
			this.shotsImage.setBackgroundResource(numbers[this.game.getShotguns()]);
		
			if(this.game.chechIfAi())
			{
				while(this.game.ai())
				{
					roll(view);
				}
			}
		}
	}

	private void updateBrainsAndShots(View view)
	{
		int buttonId = getResources().getIdentifier("brainsImage", "id", getPackageName());
		this.brainsImage = (ImageView)findViewById(buttonId);
		this.brainsImage.setBackgroundResource(numbers[this.game.getBrains()]);


		buttonId = getResources().getIdentifier("shotgunsImage", "id", getPackageName());
		this.shotsImage = (ImageView)findViewById(buttonId);
		this.shotsImage.setBackgroundResource(numbers[this.game.getShotguns()]);
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
		new AlertDialog.Builder(this)
			.setTitle("GAME OVER")
			.setMessage("WINNER:\n" + this.game.getPlayers().getPlayers()[this.game.getWinner()].getName())
			.setNegativeButton("Main Menu", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int id)
				{
					toMainMenu();
				}
			})
			.setPositiveButton("Play Again", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int id)
				{
					initializeGame(queue);
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
