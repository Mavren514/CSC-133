package com.mycompany.a4.commandregistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a4.GameWorld;

public class LadybugHeadingRightChangeCommand extends Command
{
	private GameWorld gw;
	
	public LadybugHeadingRightChangeCommand(GameWorld gw)
	{
		super("Change Ladybug Heading Right");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed( ActionEvent e )
	{
		gw.rightHeadingLadybug();
	}
	
	
}
