package com.mycompany.a4.commandregistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a4.GameWorld;

public class LadybugFoodStationCollisionCommand extends Command
{
	private GameWorld gw;
	
	public LadybugFoodStationCollisionCommand(GameWorld gw)
	{
		super("Ladybug -> Food Station collision");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed( ActionEvent e )
	{
		//gw.collisionWithFoodStation();
	}
	
	
}