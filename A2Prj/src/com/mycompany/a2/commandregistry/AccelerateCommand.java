package com.mycompany.a2.commandregistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class AccelerateCommand extends Command
{
	private GameWorld gw;
	
	public AccelerateCommand(GameWorld gw)
	{
		super("Accelerate the ladybug");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed( ActionEvent e )
	{
		gw.accelerateLadybug();
	}
	
	
}