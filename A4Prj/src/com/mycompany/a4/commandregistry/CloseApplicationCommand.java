package com.mycompany.a4.commandregistry;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;

public class CloseApplicationCommand extends Command
{

	public CloseApplicationCommand()
	{
		super("Close Application");
	}
	
	@Override
	public void actionPerformed( ActionEvent e)
	{
		Boolean bOk = Dialog.show("Confirm exit", "Are you sure you want to exit?", "Cancel", "Ok");
		if(bOk == false)
		{
			Display.getInstance().exitApplication();
		}
	}
	
	
}
