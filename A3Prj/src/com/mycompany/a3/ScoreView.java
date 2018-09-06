package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

public class ScoreView extends Container implements Observer 
{
	private GameWorld gw;
	private Label soundValueLabel = new Label("OFF");
	private Label scoreValueLabel;
	private Label livesLeftValueLabel;
	private Label foodLevelValueLabel;
	private Label healthLevelValueLabel;
	private Label lastFlagReachedValueLabel;
	
	public ScoreView(GameWorld go)
	{
		gw = go;
		//Instantiate text labels
		Label scoreTextLabel = new Label("Score: ");
		scoreValueLabel = new Label(""+gw.getScore());
		scoreTextLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		scoreValueLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		
		Label livesLeftTextLabel  = new Label("Lives:  ");
		livesLeftValueLabel = new Label(""+gw.getLives());
		livesLeftTextLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		livesLeftValueLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		Label foodLevelTextLabel = new Label("Food Level: ");
		foodLevelValueLabel = new Label(""+Ladybug.getLadybug().getFoodLevel());
		foodLevelTextLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		foodLevelValueLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		Label healthLevelTextLabel = new Label("Health Level: ");
		healthLevelValueLabel = new Label(""+Ladybug.getLadybug().getHealthLevel());
		healthLevelTextLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		healthLevelValueLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		Label lastFlagReachedTextLabel  = new Label("Last flag:  ");
		lastFlagReachedValueLabel = new Label(""+Ladybug.getLadybug().getLastFlagReached());
		lastFlagReachedTextLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		lastFlagReachedValueLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		
		
		Label soundTextLabel = new Label("Sound :");
		soundValueLabel = new Label(""+gw.getSound());
		soundTextLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		soundValueLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		//Instantiating value labels
		//scoreValueLabel = new Label("000");
		//Set the color	
		
		//Adding a container with a BoxLayout
		this.setLayout(new FlowLayout(Component.CENTER));
		//foodLevelTextLabel.myContainer.getAllStyles().setPadding(Component.RIGHT, 50);
		
		//Adding all labels in order
		this.add(scoreTextLabel);
		this.add(scoreValueLabel);
		this.add(livesLeftTextLabel);
		this.add(livesLeftValueLabel);
		this.add(lastFlagReachedTextLabel);
		this.add(lastFlagReachedValueLabel);
		this.add(healthLevelTextLabel);
		this.add(healthLevelValueLabel);
		this.add(foodLevelTextLabel);
		this.add(foodLevelValueLabel);
		this.add(soundTextLabel);
		this.add(soundValueLabel);
		
		
	}
	
	public void update(Observable o, Object arg)
	{
		//GameWorld gw = (GameWorld)o;
		int livesLeft = gw.getLives();
		int foodLevel = Ladybug.getLadybug().getFoodLevel();
		int healthLevel = Ladybug.getLadybug().getHealthLevel();
		int lastFlagReached = Ladybug.getLadybug().getLastFlagReached();
		int playerScore = gw.getScore();
		boolean sound = gw.getSound();
		
		
		scoreValueLabel.setText(""+playerScore);
		
		livesLeftValueLabel.setText("" + livesLeft);
		
		lastFlagReachedValueLabel.setText("" + lastFlagReached);
		
		foodLevelValueLabel.setText("" + foodLevel);
		
		healthLevelValueLabel.setText(""+ healthLevel);		
				
		soundValueLabel.setText("" + sound);
		
		if(sound) {
			soundValueLabel.setText("ON");
			this.repaint();
		}else {
			soundValueLabel.setText("OFF");
			this.repaint();
		}
		
		this.repaint();
		
	}
	
	
	
	

}
