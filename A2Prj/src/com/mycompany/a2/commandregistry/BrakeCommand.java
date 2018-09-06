package com.mycompany.a2.commandregistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class BrakeCommand extends Command
{
	private GameWorld gw;
	
	public BrakeCommand(GameWorld gw)
	{
		super("Brake the ladybug");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed( ActionEvent e )
	{
		gw.brakeLadybug();
	}
	
	
}