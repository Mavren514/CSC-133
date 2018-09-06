package com.mycompany.a1;

import java.util.ArrayList;
import java.util.Random;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import java.util.Iterator;

public class GameWorld{
	
	private ArrayList<GameObject> gameObjectList = new ArrayList<GameObject>();
	Ladybug ladybug = new Ladybug(0,0);
	private int clock = 0;
	private int lives = 3;
	
	
	public void init()
	{
		//clear it at initialization
		gameObjectList.clear();
		
		//initialize the flags
		gameObjectList.add(new Flag(0, 0, 1));
		gameObjectList.add(new Flag(100, 100, 2));
		gameObjectList.add(new Flag(400, 120, 2));
		gameObjectList.add(new Flag(400, 400, 2));
		
		//initialize the ladybug
		ladybug.resetLadybug();
		gameObjectList.add(ladybug);

		//initialize the spiders
		gameObjectList.add(new Spider(randX(), randY(), randSpeed(), randSize(), randHeading()));

		//initialize the food station
		gameObjectList.add(new FoodStation(randX(), randY(), randSize()));
		
	}


	//iterator?
	
	
	/* 
	 * public void addObject(Object newObject)
	{
		//need this if statement?
		if(GameObjects == null)
		{
			GameObjects = new ArrayList<Object>();
		}
		GameObjects.add(newObject);
	}
	*/
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
		// TODO Auto-generated method stub
		System.out.println();
		for(GameObject g: gameObjectList)
		{
			System.out.println(g.toString());
		}
	}
	
	public void exit()
	{
		System.exit(0);
	}	
	

	public void collisionWithSpider()
	{
		ladybug.spiderCollision();
	}
	
	public void collisionWithFlag(int flagNum)
	{
		if((flagNum - ladybug.getLastFlagReached()) == 1);
		{
			ladybug.setLastFlagReached(flagNum);
		}
	}
	
	public void gameClockTick()
	{
		for (GameObject f : gameObjectList)
		{
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
		
		//check if ladybug is dead, if dead, check to see if any lives are left. if there are,  reinit the game. otherwise, exit
		if(ladybug.isDead())
		{
			lives--;
			if(lives == 0)
			{
				this.gameOver();
			}
		}
	}
	
	private void gameOver() {
		// TODO Auto-generated method stub
		System.out.println("Game over!");
	}

	public void leftHeadingLadybug()
	{
		int degrees = -5;
		ladybug.steerHeading(degrees);
	}
	
	public void rightHeadingLadybug()
	{
		int degrees = 5;
		ladybug.steerHeading(degrees);
	}
	
	public void accelerateLadybug()
	{
		int increase = 3;
		ladybug.setLadybugSpeed(ladybug.getSpeed() + increase);
	}
	
	public void generateDisplay()
	{
		System.out.println();
		System.out.println("Number of lives = " + lives);
		
		System.out.println("Clock = " + clock);
		
		System.out.println("Food level = " + ladybug.getFoodLevel());
		
		System.out.println("Last flag reached = " + ladybug.getLastFlagReached());
		
		System.out.println("Health = " + ladybug.getHealthLevel());
	}
	
	
	public void brakeLadybug()
	{
		int decrease = 3;
		ladybug.setLadybugSpeed(ladybug.getSpeed() - decrease);
	}
	
	public void collisionWithFoodStation()
	{
		System.out.println("You have collided with a food station!");
		GameObject foodStation = null;
		
		for(GameObject f : gameObjectList)
		{
			if(f instanceof FoodStation)
			{
				if(((FoodStation) f).getCapacity() != 0)
				{
					foodStation = f;
				}
			}
		}
		//bump up the ladybug food
		ladybug.setFoodLevel(ladybug.getFoodLevel() + ((FoodStation) foodStation).getCapacity());
		//empty out the foodStation
		((FoodStation) foodStation).setCapacity(0);
		//create new foodStation
		gameObjectList.add(new FoodStation(randX(), randY(), randSize()));
		
		
		
		
		/*
		int foodCap = 0;
		
		Iterator<GameObject> gameObjectsIterator = gameObjectList.iterator();
		
		for(Object l: gameObjectList)
		{
			
		}
		
		while(gameObjectsIterator.hasNext())
		{
			Object go = gameObjectsIterator.next();
			if( go instanceof FoodStation)
			{
				System.out.println("It makes it to here");
				foodCap = ((FoodStation) go).capacity;
				
				Iterator<GameObject> gameObjectsIterator1 = gameObjectList.iterator();
				while(gameObjectsIterator1.hasNext())
				{
					System.out.println("and here");
					if( go instanceof Ladybug)
					{
						System.out.println("but not here");
						System.out.println("Holy crap this actually works");
						((Ladybug) go).foodLevel = foodCap;
						break;
					}

					gameObjectsIterator1.next();
				}
				
			}
			gameObjectsIterator.next();
		}
		*/
		
		
	}


}
