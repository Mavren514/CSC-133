package com.mycompany.a4.commandregistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a4.Game;
import com.mycompany.a4.GameWorld;

public class PausePlayCommand extends Command
{

	private Game g;
	
	public PausePlayCommand(Game g)
	{
		super("Pause");
		this.g = g;
	}
	
	@Override
	public void actionPerformed( ActionEvent e )
	{
		g.pausePlay();
	}
}
