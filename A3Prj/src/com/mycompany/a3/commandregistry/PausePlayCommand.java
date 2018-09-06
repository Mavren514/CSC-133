package com.mycompany.a3.commandregistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;

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
