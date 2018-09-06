package com.mycompany.a3;
import java.util.Iterator;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;

import com.mycompany.a3.commandregistry.*;

public class Game extends Form implements Runnable {
	

	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	
	UITimer timer = new UITimer(this);

	private boolean isSelected = true;
	
	private static int mapX, mapY;
	BackgroundSound backgroundSound = new BackgroundSound("crunch.wav");
	
	
	public Game() {

		gw = new GameWorld();
		mv = new MapView();
		sv = new ScoreView(gw);
		//Form myForm = new Form();
	
		
		this.setLayout(new BorderLayout());	
		
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.CENTER, mv);
		
		gw.addObserver(mv);
		gw.addObserver(sv);
	
		Toolbar myToolbar = new Toolbar();
		Toolbar.setOnTopSideMenu(false);
		this.setToolbar(myToolbar);
		
		/*
		 * Timer
		 */
		timer.schedule(50, true, this);
		
		
		
		//Define all the commands to be used in the gameworld
		Command accelerateCommand 					= new AccelerateCommand(gw);
		Command brakeCommand 						= new BrakeCommand(gw);
		Command closeApplicationCommand 			= new CloseApplicationCommand();
		Command gameClockTickCommand 				= new GameClockTickCommand(gw);
		Command ladybugFlagCollisionCommand 	    = new LadybugFlagCollisionCommand(gw);
		Command	aboutCommand						= new AboutCommand();
		Command helpCommand							= new HelpCommand();
		Command ladybugFoodStationCollisionCommand  = new LadybugFoodStationCollisionCommand(gw);
		Command ladybugHeadingLeftChangeCommand 	= new LadybugHeadingLeftChangeCommand(gw);
		Command ladybugHeadingRightChangeCommand 	= new LadybugHeadingRightChangeCommand(gw);
		Command spiderCollisionCommand 				= new SpiderCollisionCommand(gw);
		Command pausePlayCommand					= new PausePlayCommand(this);
		
		CheckBox soundCheckBox = new CheckBox();
		soundCheckBox.setSelected(true);
		Command soundChangeCommand					= new SoundCommand(gw);
		soundCheckBox.setCommand(soundChangeCommand);
		soundChangeCommand.putClientProperty("SideComponent", soundCheckBox);
		
		
		myToolbar.addComponentToSideMenu(soundCheckBox);
		
		
		//Create all the buttons to be used in the gameworld
		Button accelerateButton 					= new Button();
		Button brakeButton 							= new Button();
		Button closeApplicationButton				= new Button();
		Button aboutButton							= new Button();
		Button helpButton							= new Button();
		Button gameClockTickButton 					= new Button();
		Button ladybugFlagCollisionButton 			= new Button();
		Button ladybugFoodStationCollisionButton 	= new Button();
		Button ladybugHeadingLeftChangeButton 		= new Button();
		Button ladybugHeadingRightChangeButton 		= new Button();
		Button spiderCollisionButton 				= new Button();
		Button pausePlayButton						= new Button();
		
		
		//Assign a command to each of the buttons
		accelerateButton.setCommand(accelerateCommand);
		brakeButton.setCommand(brakeCommand);		
		closeApplicationButton.setCommand(closeApplicationCommand);
		gameClockTickButton.setCommand(gameClockTickCommand);
		aboutButton.setCommand(aboutCommand);
		helpButton.setCommand(helpCommand);
		ladybugFlagCollisionButton.setCommand(ladybugFlagCollisionCommand);		
		ladybugFoodStationCollisionButton.setCommand(ladybugFoodStationCollisionCommand);
		ladybugHeadingLeftChangeButton.setCommand(ladybugHeadingLeftChangeCommand);
		ladybugHeadingRightChangeButton.setCommand(ladybugHeadingRightChangeCommand);
		spiderCollisionButton.setCommand(spiderCollisionCommand);
		pausePlayButton.setCommand(pausePlayCommand);
		
		//Key Listeners for commands
		addKeyListener('a', accelerateCommand);
		addKeyListener('b', brakeCommand);
		addKeyListener('l', ladybugHeadingLeftChangeCommand);
		addKeyListener('r', ladybugHeadingRightChangeCommand);
		addKeyListener('f', ladybugFoodStationCollisionCommand);
		addKeyListener('g', spiderCollisionCommand);
		addKeyListener('t', gameClockTickCommand);
		addKeyListener('x', closeApplicationCommand);
		
	
		
		myToolbar.addCommandToSideMenu(accelerateCommand);
		myToolbar.addCommandToSideMenu(closeApplicationCommand);
		myToolbar.addCommandToSideMenu(aboutCommand);
		myToolbar.addCommandToSideMenu(helpCommand);
		
		
		//Left container for holding the button commands
		/*
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		leftContainer.add(accelerateButton);
		leftContainer.add(brakeButton);
		leftContainer.add(ladybugFlagCollisionButton);
		leftContainer.add(ladybugFoodStationCollisionButton);
		leftContainer.add(spiderCollisionButton);
		leftContainer.add(pausePlayButton);
		
		leftContainer.getAllStyles().setPadding(Component.RIGHT, 30);
		this.add(BorderLayout.WEST, leftContainer);
		*/
		
		
		//Right container for holding more button commands
		/*
		Container rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		rightContainer.add(closeApplicationButton);
		rightContainer.add(gameClockTickButton);
		rightContainer.getAllStyles().setPadding(Component.LEFT, 30);
		this.add(BorderLayout.EAST, rightContainer);
		*/
		
		
		Container bottomContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		bottomContainer.getAllStyles().setPadding(Component.RIGHT, 20);
		bottomContainer.add(ladybugHeadingLeftChangeButton);
		bottomContainer.add(ladybugHeadingRightChangeButton);
		bottomContainer.add(pausePlayButton);
		this.add(BorderLayout.SOUTH, bottomContainer);
		

		this.show();
		
		mapX = mv.getWidth();
		mapY = mv.getHeight();
		
		
		gw.init();
		
		
	}
	
	
	
	
	public static int getMapX() {
        return mapX;
    }

    public static int getMapY() {
        return mapY;
    }

	public void run()
	{
		gw.gameClockTick();
	}

	
	public void pausePlay()
	{
		if(isSelected == true)
		{
			timer.cancel();
			isSelected = false;
			gw.setSound(false);
			gw.setPaused(true);
		}
		else
		{
			timer.schedule(50, true, this);
			isSelected = true;
			gw.setPaused(false);
		}
	}




	public boolean isSelected()
	{
		return isSelected;
	}




	public void setSelected(boolean isSelected)
	{
		this.isSelected = isSelected;
	}
	


}
