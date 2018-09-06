package com.mycompany.a2.commandregistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class GameClockTickCommand extends Command
{
	private GameWorld gw;
	
	public GameClockTickCommand(GameWorld gw)
	{
		super("Clock Tick");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed( ActionEvent e )
	{
		gw.gameClockTick();
	}
	
	
}