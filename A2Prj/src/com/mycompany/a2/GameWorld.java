package com.mycompany.a2;

import java.util.ArrayList;
import java.util.Random;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import java.util.Iterator;
import java.util.Observable;

@SuppressWarnings("unused")
public class GameWorld extends Observable
{
	
	//private ArrayList<GameObject> gameObjectList = new ArrayList<GameObject>();
	
	private GameObjectCollection gameObjectCollection;
	
	Ladybug ladybug = new Ladybug(0,0);
	private int clock = 0;
	private int lives = 3;
	private int score = 0;
	private boolean sound = true;
	
	
	public GameWorld() 
	{
		gameObjectCollection = new GameObjectCollection();
	}
	
	
	public void init()
	{
		
		
		
		//clear it at initialization
		//gameObjectCollection.clear();
		
		
		//initialize the flags
		
		gameObjectCollection.add(new Flag(0, 0, 1));
		gameObjectCollection.add(new Flag(100, 100, 2));
		gameObjectCollection.add(new Flag(400, 120, 2));
		gameObjectCollection.add(new Flag(400, 400, 2));
		
		//initialize the ladybug
		ladybug.resetLadybug();
		gameObjectCollection.add(ladybug);

		//initialize the spiders
		gameObjectCollection.add(new Spider(randX(), randY(), randSpeed(), randSize(), randHeading()));

		//initialize the food station
		gameObjectCollection.add(new FoodStation(randX(), randY(), randSize()));
		
				
		this.setChanged();
		this.notifyObservers();
		
	}
	
	
	Random rand = new Random();



	/*
	 		a minimum of four Flag objects, positioned
			and sized as you choose and numbered sequentially defining the path (you may add more
			than four initial flags if you like - maximum number of flags you can add is nine); one
			LadyBug, initially positioned at the flag #1 with initial heading which is zero, initial positive
			(non-zero) speed as you choose, and initial size as you choose; at least two Spider objects,
			randomly positioned with a randomly-generated size, heading, and speed; and at least two
			FoodStation objects with random location and with random sizes. 
	*/
	
	
	/*
	 * 
	 * 
	 * Random variables
	 * 
	 * 
	 * 
	 */
	
	
	public float randX() 
	{
		//out of 1024
		return rand.nextInt(1024);
	}

	public float randY() 
	{
		//out of 1024 
		return rand.nextInt(1024);
	}
	
	public int randSpeed() 
	{
		return rand.nextInt(5);
	}
	
	public int randHeading()
	{
		//may need to do something about radians to degress etc
		return rand.nextInt(90);
	}
	
	public int randSize()
	{
		return rand.nextInt(50);
	}
	
	

	public void outputMap() 
	{
		System.out.println();
		IIterator elements = gameObjectCollection.getIterator();		while(elements.hasNext())
		{
			Object f = elements.getNext();
			
			System.out.println(f.toString());
		}
	}

	
	
	/*
	
	public void missileKill()
	{
		Missle missile = null;
		Asteroid asteroid = null;
		
		IIterator elements = gameObjectCollection.getIterator();
		
		while(elements.hasNext())
		{
			Object o = elements.getNext();
			if(o instanceof Missile)
			{
				missile = (Missile)o;
			}
			if(o instanceof Asteroid)
			{
				asteroid = (Asteroid)o;
			}
		}
		
		if(missile == null);
		
		
		
		this.setChanged();
		//this.notifyObservers(new GameWorldProxy(this));
		this.notifyObservers();
	}
	*/
	
	
	
	
	
	public void exit()
	{
		System.exit(0);
	}	
	

	public void collisionWithSpider()
	{
		ladybug.spiderCollision();
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public void collisionWithFlag(int flagNum)
	{
		if((flagNum - ladybug.getLastFlagReached()) == 1)
		{
			ladybug.setLastFlagReached(flagNum);
			System.out.println("SNATCH | You have collided with the next flag!");
			
		}
		else
		{
			Dialog.show("Error", "Incorrect next flag.", "Ok", null);		
		}
		this.generateDisplay();
		this.setChanged();
		this.notifyObservers();
		
	}
	
	public void collisionWithFoodStation()
	{
		System.out.println("You have collided with a food station!");
		FoodStation foodStation = null;
		
	    IIterator elements = gameObjectCollection.getIterator();
		
		while(elements.hasNext())
		{
			Object f = elements.getNext();
			if(f instanceof FoodStation)
			{
				if(((FoodStation) f).getCapacity() != 0)
				{
					foodStation = ((FoodStation)f);
				}
			}
		}
		//bump up the ladybug food
		ladybug.setFoodLevel(ladybug.getFoodLevel() + ((FoodStation)foodStation).getCapacity());
		//empty out the foodStation
		((FoodStation) foodStation).setCapacity(0);
		//create new foodStation
		gameObjectCollection.add(new FoodStation(randX(), randY(), randSize()));
		
		this.generateDisplay();
		this.setChanged();
		this.notifyObservers();
	}


	public void gameClockTick()
	{
		IIterator elements = gameObjectCollection.getIterator();		
		while(elements.hasNext())
		{
			GameObject f = (GameObject) elements.getNext();

			if(f instanceof Spider)
			{
				((Spider) f).randomHeading();
			}
			if(f instanceof MoveableObject)
			{
				((MoveableObject) f).move();
			}
		}
				
		//ladybug is to consume food on the tick
		ladybug.eatFood();
		
		//increase the game clock
		clock++;
		
		//check if ladybug is dead, if dead, check to see if any lives are left. if there are, reinit the game. otherwise, exit
		ladybug.checkHealth();
		if(ladybug.isDead())
		{
			setLives(getLives() - 1);
			if(getLives() == 0)
			{
				this.gameOver();
			}
		}

	}
	
	
	
	private void gameOver() {
		// TODO Auto-generated method stub
		Dialog.show("Game Over!", "", "Ok", null);
	}


	public void leftHeadingLadybug()
	{
		int degrees = -5;
		ladybug.steerHeading(degrees);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public void rightHeadingLadybug()
	{
		int degrees = 5;
		ladybug.steerHeading(degrees);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public void accelerateLadybug()
	{
		int increase = 3;
		ladybug.setLadybugSpeed(ladybug.getSpeed() + increase);
	
		this.setChanged();
		this.notifyObservers();
	}
	
	public void generateDisplay()
	{
		outputMap();
		
	}
	
	
	public void brakeLadybug()
	{
		int decrease = 3;
		ladybug.setLadybugSpeed(ladybug.getSpeed() - decrease);
	
		this.setChanged();
		this.notifyObservers();
	}


	public int getLives()
	{
		return lives;
	}


	public void setLives(int lives)
	{
		this.lives = lives;
	}


	public int getScore()
	{
		return score;
	}


	public void setScore(int score)
	{
		this.score = score;
	}


	public boolean getSound()
	{
		return sound;
	}


	public void setSound(boolean sound)
	{
		this.sound = sound;
		this.notifyObservers();
		this.setChanged();
	}


}
