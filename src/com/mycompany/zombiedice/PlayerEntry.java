package com.mycompany.zombiedice;



import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.Intent;
import PlayerQueue;

public class PlayerEntry extends Activity
{
	private PlayerQueue queue;
	private ImageButton[] deleteButtons;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_entry);
		ActionBar actionbar = getActionBar();
		actionbar.hide();
		
		queue = new PlayerQueue();
		deleteButtons = new ImageButton[queue.getMaxPlayers()];

		String button;
		int buttonId;
		for(int i = 0; i < deleteButtons.length; i++)
		{
			button = "delplayer" + (i+1);
			buttonId = getResources().getIdentifier(button, "id", getPackageName());
			ImageButton testo = (ImageButton)findViewById(buttonId);
			deleteButtons[i] = testo;
		    deleteButtons[i].setTag(i+1);
		}
    }

	public void addHumanPlayer(View view)
	{
		EditText newName = (EditText)findViewById(R.id.playerentryEditText1);
		
		if(queue.addPlayer(newName.getText().toString(), 'h'))
		{
			String player = "player" + (queue.getTotalPlayers());
			int playerId = getResources().getIdentifier(player, "id", getPackageName());
			TextView playerChange = (TextView)findViewById(playerId);
			playerChange.setText(newName.getText().toString());
			
			String button = "delplayer" + queue.getTotalPlayers();
			int buttonId = getResources().getIdentifier(button, "id", getPackageName());
			ImageButton imgButton = (ImageButton)findViewById(buttonId);
			imgButton.setVisibility(1);
		}
		else
		{
			//Maybe Display Issue Message
		}
	}
	
	public void addCompPlayer(View view)
	{
		EditText newName = (EditText)findViewById(R.id.playerentryEditText1);

		if(queue.addPlayer(newName.getText().toString(), 'c'))
		{
			String player = "player" + (queue.getTotalPlayers());
			int playerId = getResources().getIdentifier(player, "id", getPackageName());
			TextView playerChange = (TextView)findViewById(playerId);
			playerChange.setText((String)newName.getText().toString());
			
			String button = "delplayer" + queue.getTotalPlayers();
			int buttonId = getResources().getIdentifier(button, "id", getPackageName());
			ImageButton imgButton = (ImageButton)findViewById(buttonId);
			imgButton.setVisibility(1);
		}
		else
		{
			//Maybe Display Issue Message
		}
	}
	
    public void removePlayer(View view)
	{
		String player = "player" + view.getTag();
		int playerId = getResources().getIdentifier(player, "id", getPackageName());
		TextView name = (TextView)findViewById(playerId);
	
		if(queue.removePlayer(name.getText().toString()))
		{
			player = "player" + (queue.getTotalPlayers()+1);
			playerId = getResources().getIdentifier(player, "id", getPackageName());
			TextView playerChange = (TextView)findViewById(playerId);
			playerChange.setText("");
			
			String button = "delplayer" + (queue.getTotalPlayers()+1);
			int buttonId = getResources().getIdentifier(button, "id", getPackageName());
			ImageButton imgButton = (ImageButton)findViewById(buttonId);
			imgButton.setVisibility(4);
			
			for(int i = 0; i < queue.getTotalPlayers(); i++)
			{
				player = "player" + (i+1);
				playerId = getResources().getIdentifier(player, "id", getPackageName());
				playerChange = (TextView)findViewById(playerId);
				playerChange.setText(queue.getName(i));
			}
		}
	}
	
	public void startGame(View view)
	{
		Intent intent = new Intent(this, Game.class);
		intent.putExtra("queue", queue);
		startActivity(intent);
	}
}
