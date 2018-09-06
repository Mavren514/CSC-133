package com.mycompany.a2.commandregistry;

import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.a2.GameWorld;



public class SoundCommand extends Command
{	
	private GameWorld gw;

	public SoundCommand(GameWorld gw)
	{
		super("SOUND");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
				
		if(((CheckBox)e.getComponent()).isSelected())
		{
			gw.setSound(true);
			gw.notifyObservers();
		}
		else
		{
			gw.setSound(false);
			gw.notifyObservers();
		}
		
		SideMenuBar.closeCurrentMenu();
		
		
	}

	
	
	
	
	
	
	
}
