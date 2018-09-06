package com.mycompany.a2;
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
import com.mycompany.a2.commandregistry.*;

public class Game extends Form {
	

	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	
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
		
		
		
		
		//Define all the commands to be used in the gameworld
		Command accelerateCommand 					= new AccelerateCommand(gw);
		Command brakeCommand 						= new BrakeCommand(gw);
		Command closeApplicationCommand 			= new CloseApplicationCommand();
		Command gameClockTickCommand 				= new GameClockTickCommand(gw);
		Command ladybugFlagCollisionCommand 	    = new LadybugFlagCollisionCommand(gw);
		Command ladybugFoodStationCollisionCommand  = new LadybugFoodStationCollisionCommand(gw);
		Command ladybugHeadingLeftChangeCommand 	= new LadybugHeadingLeftChangeCommand(gw);
		Command ladybugHeadingRightChangeCommand 	= new LadybugHeadingRightChangeCommand(gw);
		Command spiderCollisionCommand 				= new SpiderCollisionCommand(gw);
		
		
		CheckBox soundCheckBox = new CheckBox();
		soundCheckBox.setSelected(true);
		Command soundChangeCommand					= new SoundCommand(gw);
		//soundChangeCommand.putClientProperty("Sound", soundCheckBox);
		soundCheckBox.setCommand(soundChangeCommand);
		//soundChangeCommand.putClientProperty("Sound", soundCheckBox);
		soundChangeCommand.putClientProperty("SideComponent", soundCheckBox);
		
		
		myToolbar.addComponentToSideMenu(soundCheckBox);
		
		
		//Create all the buttons to be used in the gameworld
		Button accelerateButton 					= new Button();
		Button brakeButton 							= new Button();
		Button closeApplicationButton				= new Button();
		Button gameClockTickButton 					= new Button();
		Button ladybugFlagCollisionButton 			= new Button();
		Button ladybugFoodStationCollisionButton 	= new Button();
		Button ladybugHeadingLeftChangeButton 		= new Button();
		Button ladybugHeadingRightChangeButton 		= new Button();
		Button spiderCollisionButton 				= new Button();

		
	//	accelerateButton.getAllStyles().setPadding(Component.TOP, 10);
		
		//Assign a command to each of the buttons
		accelerateButton.setCommand(accelerateCommand);
		brakeButton.setCommand(brakeCommand);		
		closeApplicationButton.setCommand(closeApplicationCommand);
		gameClockTickButton.setCommand(gameClockTickCommand);		
		ladybugFlagCollisionButton.setCommand(ladybugFlagCollisionCommand);		
		ladybugFoodStationCollisionButton.setCommand(ladybugFoodStationCollisionCommand);
		ladybugHeadingLeftChangeButton.setCommand(ladybugHeadingLeftChangeCommand);
		ladybugHeadingRightChangeButton.setCommand(ladybugHeadingRightChangeCommand);
		spiderCollisionButton.setCommand(spiderCollisionCommand);
		
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
		//myToolbar.addCommandToSideMenu(soundChangeCommand);
		
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		leftContainer.add(accelerateButton);
		leftContainer.add(brakeButton);
		leftContainer.add(ladybugFlagCollisionButton);
		leftContainer.add(ladybugFoodStationCollisionButton);
		leftContainer.add(spiderCollisionButton);
		
		leftContainer.getAllStyles().setPadding(Component.RIGHT, 30);
		this.add(BorderLayout.WEST, leftContainer);

		Container rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		rightContainer.add(closeApplicationButton);
		rightContainer.add(gameClockTickButton);
		rightContainer.getAllStyles().setPadding(Component.LEFT, 30);
		this.add(BorderLayout.EAST, rightContainer);
		
		
		
		Container centerContainer = new Container();
		centerContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.rgb(255, 0, 0)));
		centerContainer.getAllStyles().setPadding(Component.LEFT, 20);
		this.add(BorderLayout.CENTER, centerContainer);
		
		
		
		Container bottomContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		centerContainer.getAllStyles().setPadding(Component.RIGHT, 20);
		bottomContainer.add(ladybugHeadingLeftChangeButton);
		bottomContainer.add(ladybugHeadingRightChangeButton);
		this.add(BorderLayout.SOUTH, bottomContainer);
		

		this.show();
		
		gw.init();
		
			
		
	}
	

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub

	}

}
