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
	public void roll(View view)
	{
		dice.roll();
		shotguns += dice.getNumShotguns();
		brains += dice.getNumBrains();
		
		if (shotguns >= 3)
		{

		}
	}
	
	/**
	 * 
	 * This method calls the AI class and provides it with necessary variables
	 * to have a computer play independently of a user. 
	 */ 

	private void ai()
	{
		AI ai = new AI(this.shotguns, this.brains);
		while(ai.playing(this.shotguns, this.brains))
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
		nextTurn(findViewById(android.R.id.content));
	}
}
