package com.mycompany.a4.commandregistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a4.GameWorld;

public class SpiderCollisionCommand extends Command
{
	private GameWorld gw;
	
	public SpiderCollisionCommand(GameWorld gw)
	{
		super("Collision with spider");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed( ActionEvent e )
	{
		gw.collisionWithSpider();
	}
	
	
}