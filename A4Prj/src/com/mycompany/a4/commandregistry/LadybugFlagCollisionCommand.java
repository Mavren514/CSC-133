package com.mycompany.a4.commandregistry;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a4.GameWorld;

public class LadybugFlagCollisionCommand extends Command
{
	private GameWorld gw;
	Command cEnter = new Command("Enter");
	Command cCancel = new Command("Cancel");
	TextField myTF = new TextField();
	
	
	public LadybugFlagCollisionCommand(GameWorld gw)
	{
		super("Ladybug Flag Collision");
		this.gw = gw;
		
		
		
	}
	
	
	@Override
	public void actionPerformed( ActionEvent e )
	{
		int flagNum;
		
		Dialog.show("Please enter a flag number between 1 and 9.", myTF, cEnter);
		flagNum = Integer.parseInt(myTF.getText());
		myTF.remove();
		
		if(flagNum > 0 && flagNum < 10)
		{
			System.out.println(flagNum);
			gw.collisionWithFlag(flagNum);
		}
		else
		{
			Dialog.show("Error", "Please enter a flag number between 1 and 9.", "Ok", null);
		}		
		
	}
	
	
}