package com.mycompany.zombiedice;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.Intent;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
	
	public void playerEntry(View view)
	{
		Intent intent = new Intent(this, PlayerEntry.class);
		finish();
		startActivity(intent);
	}
	
	public void exitGame(View view)
	{
		finish();
	}
	
}
