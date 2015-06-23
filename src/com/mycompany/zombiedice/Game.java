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

public class Game
{
	private int turnCounter;
	private PlayerQueue players;
	private Dice dice;
	private int shotguns;
	private int brains;
	private boolean finalRound;
	private int lastPlayer;
	private char[] rollResults;
	
	public Game(PlayerQueue queue)
	{
		initializeGame(queue);
	}
	
	private void initializeGame(PlayerQueue queue)
	{
		this.dice = new Dice();
		this.players = queue;
		this.rollResults = new char[3];
		this.brains = 0;
		this.shotguns = 0;
		this.turnCounter = 0;
		this.finalRound = false;
		turnCounter = 0;
	}
	
	public void roll(View view)
	{
		dice.roll();
		shotguns += dice.getNumShotguns();
		brains += dice.getNumBrains();
		
		if (shotguns >= 3)
		{

		}
	}
	
	public void nextTurn(View view)
	{
		if (shotguns < 3)
		{
			//next turn?
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
			getWinner();
			
		if (players.getPlayers()[turnCounter].getType() == 'c')
			ai();
	}
	
	public int getWinner()
	{	
		//Display Winner and prompt play again
		int winner = 0;
		for(int i = 0; i < this.players.getTotalPlayers(); i++)
		{
			if(this.players.getPlayers()[i].getBrainScore() > 
				this.players.getPlayers()[winner].getBrainScore())
				winner = i;
		}
		return winner;
	}
	
	/**
	 * 
	 * This method calls the AI class and provides it with necessary variables
	 * to have a computer play independently of a user. 
	 */ 

	private void ai()
	{
		AI ai = new AI(this.shotguns, this.brains);
		/*while(ai.playing(this.shotguns, this.brains))
		{
			roll(findViewById(android.R.id.content));
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		nextTurn(findViewById(android.R.id.content));*/
	}
}
