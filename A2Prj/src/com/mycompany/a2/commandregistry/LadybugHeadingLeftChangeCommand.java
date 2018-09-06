package com.mycompany.a2.commandregistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class LadybugHeadingLeftChangeCommand extends Command
{
	private GameWorld gw;
	
	public LadybugHeadingLeftChangeCommand(GameWorld gw)
	{
		super("Ladybug Heading Left  Change");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed( ActionEvent e )
	{
		gw.leftHeadingLadybug();
	}
	
	
}