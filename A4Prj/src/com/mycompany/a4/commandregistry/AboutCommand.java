package com.mycompany.a4.commandregistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command
{
	
	public AboutCommand()
	{
		super("About");
	}
	
	@Override
	public void actionPerformed( ActionEvent e )
	{
		System.out.println("Hello");
	}
	
	
}
