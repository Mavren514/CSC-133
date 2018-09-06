package com.mycompany.a4;

import java.util.ArrayList;
import java.util.Random;
import com.codename1.ui.events.ActionListener;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import java.util.Iterator;
import java.util.Observable;

import com.mycompany.a4.commandregistry.BackgroundSound;

public class GameWorld extends Observable implements IDrawable
{
	
	//private ArrayList<GameObject> gameObjectList = new ArrayList<GameObject>();
	
	private GameObjectCollection gameObjectCollection;
	

	private int mapHeight;
	private int mapWidth;
	
	private int clock = 0;
	private int lives = 3;
	private int score = 0;
	private boolean sound = true;
	private boolean spiderCollision = false;
	private boolean flagCollision = false;
	private boolean foodStationCollision = false;
	private boolean isPaused = false;
	
	private ArrayList<GameObject> colliderList = new ArrayList<GameObject>();
	
	Ladybug ladybug = Ladybug.getLadybug();
	
	BackgroundSound backgroundSound = new BackgroundSound("background.mp3");
	Sound foodStationSound = new Sound("crunch.wav", "audio/wav");
	Sound spiderSound = new Sound("splat.wav", "audio/wav");
	Sound flagSound = new Sound("grab.wav", "audio/wav");
	
	
	
	public GameWorld() 
	{
		setGameObjectCollection(new GameObjectCollection());
	}
	
	
	public void init()
	{
		
		
		
		//initialize the flags
				
		getGameObjectCollection().add(new Flag(300, 250, 1));
		getGameObjectCollection().add(new Flag(100, 100, 2));
		getGameObjectCollection().add(new Flag(800, 120, 3));
		getGameObjectCollection().add(new Flag(400, 400, 4));
		
		//initialize the ladybug
		ladybug.resetLadybug();
		getGameObjectCollection().add(ladybug);

		
		//initialize the spiders
		getGameObjectCollection().add(new Spider(randX(), randY(), randSpeed(), randSize(), randHeading()));

		//initialize the food station
		getGameObjectCollection().add(new FoodStation(randX(), randY(), randSize()));
		
		if(sound)
		{
			backgroundSound.run();
		}
		
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
		return rand.nextInt(Game.getMapX());
	}

	public float randY() 
	{
		//out of 1024 
		return rand.nextInt(Game.getMapY());
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
		return rand.nextInt(50) + 1;
	}
	
	

	public void outputMap() 
	{
		System.out.println();
		IIterator elements = gameObjectCollection.getIterator();		
		while(elements.hasNext())
		{
			Object f = elements.getNext();
			
			System.out.println(f.toString());
		}
	}
	
	
	
	
	
	public void exit()
	{
		System.exit(0);
	}	
	

	public void collisionWithSpider()
	{
		Ladybug.getLadybug().spiderCollision();
		checkLadybugStatus();
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public void collisionWithFlag(int flagNum)
	{
		if((flagNum - Ladybug.getLadybug().getLastFlagReached()) == 1)
		{  
			Ladybug.getLadybug().setLastFlagReached(flagNum);
			System.out.println("SNATCH | You have collided with the next flag!");
			if(sound)
			{
				flagSound.run();
			}
		}

		this.generateDisplay();
		this.setChanged();
		this.notifyObservers();
		
	}
	
	public void collisionWithFoodStation(FoodStation foodStation)
	{
		System.out.println("You have collided with a food station!");
		ladybug.setFoodLevel(ladybug.getFoodLevel() + ((FoodStation) foodStation).getCapacity());
		if (((FoodStation)foodStation).getCapacity() !=0) {
			gameObjectCollection.add(new FoodStation(randX(), randY(), randSize()));		
		}
		
		
		((FoodStation) foodStation).setCapacity(0);
		((FoodStation) foodStation).setColor(ColorUtil.rgb(0, 120, 0));
		this.generateDisplay();
		this.setChanged();
		this.notifyObservers();
	}
	
	


	public void gameClockTick()
	{
	
		if(sound == false)
		{
			backgroundSound.pause();
		}
		
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
		Ladybug.getLadybug().eatFood();
		
		//increase the game clock
		clock++;
		
		//check if ladybug is dead, if dead, check to see if any lives are left. if there are, reinit the game. otherwise, exit
		checkLadybugStatus();
		
		elements = gameObjectCollection.getIterator();
        while(elements.hasNext()) {
            ICollider i = (ICollider)elements.getNext();
            IIterator elements2 = gameObjectCollection.getIterator();
            while(elements2.hasNext()) {
                ICollider j = (ICollider)elements2.getNext();
                if (i != j) {
                    if(i.collidesWith(j)) {
                        i.handleCollision(j);
                    }
                }
            }
            
        }
        
        IIterator theColliders = gameObjectCollection.getIterator();
		while(theColliders.hasNext()){
			ICollider curObj = (ICollider)theColliders.getNext(); // get a collidable object // check if this object collides with any OTHER object
			if (ladybug.collidesWith(curObj)) {
				
				if (!colliderList.contains((GameObject)curObj)) {
					colliderList.add((GameObject) curObj);
					if (curObj instanceof Spider) {
						collisionWithSpider();
						if(sound)
						{
							spiderSound.run();
						}
						spiderCollision = true;
					}
					if (curObj instanceof FoodStation) {
						collisionWithFoodStation((FoodStation)curObj);
						foodStationCollision = true;
						if(sound) 
						{
							foodStationSound.run();
						}
						gameObjectCollection.remove((GameObject)curObj);
					}
					if (curObj instanceof Flag) {
						collisionWithFlag(((Flag) curObj).getSequenceNum());
						flagCollision = true;
					}
					
				}
			} else {
				// take it out of the array
				colliderList.remove((GameObject) curObj);
				
			}
		}
        
		
		this.setChanged();
		this.notifyObservers();
		
	}
	
	public void checkLadybugStatus()
	{
		Ladybug.getLadybug().checkHealth();
		if(Ladybug.getLadybug().getIsDead())
		{
			setLives(getLives() - 1);
			
			
			//Attributes of the ladybug being resurrected 
			Ladybug.getLadybug().resetLadybug();
			
			if(getLives() == 0)
			{
				this.gameOver();
			}
			
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	public void setMapHeight(int height)
	{
		this.mapHeight = height;
	}
	
	public void setMapWidth(int width)
	{
		this.mapWidth = width;
	}
	
	public boolean isPaused()
	{
		return isPaused;
	}


	public void setPaused(boolean isPaused)
	{
		this.isPaused = isPaused;
	}


	private void gameOver() {
		// TODO Auto-generated method stub
		Dialog.show("Game Over!", "", "Ok", null);
	}


	public void leftHeadingLadybug()
	{
		int degrees = -5;
		Ladybug.getLadybug().steerHeading(degrees);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public void rightHeadingLadybug()
	{
		int degrees = 5;
		Ladybug.getLadybug().steerHeading(degrees);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public void accelerateLadybug()
	{
		int increase = 3;
		Ladybug.getLadybug().setLadybugSpeed(Ladybug.getLadybug().getSpeed() + increase);
	
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
		Ladybug.getLadybug().setLadybugSpeed(Ladybug.getLadybug().getSpeed() - decrease);
	
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


	public GameObjectCollection getGameObjectCollection()
	{
		return gameObjectCollection;
	}


	public void setGameObjectCollection(GameObjectCollection gameObjectCollection)
	{
		this.gameObjectCollection = gameObjectCollection;
	}
	
	public void draw(Graphics g, Point point) {
		IIterator elements = gameObjectCollection.getIterator();
		while(elements.hasNext()) {
			IDrawable drawObj = (IDrawable)elements.getNext();
			drawObj.draw(g, point);
		}
	}



}
