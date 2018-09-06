package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;
import java.lang.Math;

public class Ladybug extends MoveableObject implements ISteerable
{
	//Moveable object
	
	//Initialize sizes and locations
	//private double deltaX = 0;
	//private double deltaY = 0;
	
	//private float oldX = 0;
	//private float oldY = 0;
	


	private int maximumSpeed = 50;
	private int foodLevel = 0;
	private int foodConsumptionRate = 0;
	private int healthLevel = 0;
	private int lastFlagReached = 1;
	
	private boolean isDead = false;
	
	public Ladybug(float locationX, float locationY)
	{
		super(0);
		this.setLocationX(locationX);
		this.setLocationY(locationY);
		this.setColor(ColorUtil.rgb(255, 0, 0));
		this.setSpeed(15);
		this.setSize(25);
		
		
		
		this.setColor(ColorUtil.rgb(120, 255, 0));
	}
	
	public void resetLadybug()
	{
		this.setColor(ColorUtil.rgb(255, 0, 0));
		this.setSpeed(15);
		this.setSize(25);
		this.setHeading(0);
		
		//location of the first flag
		this.setLastFlagReached(1);
		this.setLocationX(0);
		this.setLocationY(0);
		this.setFoodLevel(20);
		this.setHealthLevel(4);
		this.isDead = false;
	}
	
	
	public void setLadybugSpeed(int speed)
	{
		if(speed > this.maximumSpeed)
		{
			System.out.println("This is above the maximum speed!");
		}
		else
		{
			this.speed = speed;
		}
	}
	
	
	public void checkHealth()
	{
		if(this.getHealthLevel() == 0)
		{
			this.setSpeed(0);
		}
		if(this.foodLevel == 0)
		{
			this.setSpeed(0);
		}
		if(this.getSpeed() == 0)
		{
			this.isDead = true;
		}
	}
	
	
	public int getFoodLevel()
	{
		return foodLevel;
	}
	
	public void setFoodLevel(int foodLevel)
	{
		this.foodLevel = foodLevel;
	}
	
	public void setMaximumSpeed(int maximumSpeed)
	{
		this.maximumSpeed = maximumSpeed;
	}
	
	public int getMaximumSpeed()
	{
		return maximumSpeed;
	}
	
	public int getFoodConsumptionRate()
	{
		return foodConsumptionRate;
	}
	
	public void setFoodConsumptionRate(int foodConsumptionRate)
	{
		this.foodConsumptionRate = foodConsumptionRate;
	}
	
	public int getHealthLevel()
	{
		return healthLevel;
	}
	
	public void setHealthLevel(int healthLevel)
	{
		this.healthLevel = healthLevel;
	}
	
	public int getLastFlagReached()
	{
		return lastFlagReached;
	}
	
	public void setLastFlagReached(int lastFlagReached)
	{
		this.lastFlagReached = lastFlagReached;
	}
	
	public boolean isDead()
	{
		return isDead;
	}
	
	
	@Override
	public String toString() {
		
		String parentDesc = super.toString();
		//yeah yeah
		@SuppressWarnings("deprecation")
		String name = this.getClass().getSimpleName();
		String foodRate = " foodConsumptionRate: " + this.getFoodConsumptionRate();
		String maxSpeed = " maxSpeed: " + this.getMaximumSpeed();
		return name + parentDesc + maxSpeed + foodRate; 
	}
	
	public void eatFood()
	{
		foodLevel = foodLevel - foodConsumptionRate;
		if(foodLevel == 0)
		{
			this.setLadybugSpeed(0);
		}
		//else, continue
	}
	
	public void updateLadybugSpeed()
	{
		if(this.getSpeed() < this.getMaximumSpeed() * this.getHealthLevel())
		{
			
		}
		else
		{
			this.setSpeed(this.getMaximumSpeed() * this.getHealthLevel());
		}
		this.checkHealth();
		
	}
	
	
	
	
	public void steerHeading(int changeHeading)
	{
		this.setHeading(this.getHeading() + changeHeading);
	}
	
	public void spiderCollision()
	{
		this.healthLevel--;
		this.updateLadybugSpeed();
		//decrease color by like 100 ish to show that it is dying
		this.setColor(ColorUtil.rgb(255 - (20 *(10 - this.getHealthLevel())) , 0, 0));
	}
	


	/*
	@Override
	public void move() {
		deltaX = (Math.cos(Math.toRadians(90 - this.heading))) * this.speed;
		deltaY = (Math.sin(Math.toRadians(90 - this.heading))) * this.speed;
		
		oldX = this.locationX;
		oldY = this.locationY;
		
		//will this cast make the world blow up? tune in next time, on "Jonathon attempts to code"
		this.locationX = (float) (oldX + deltaX);
		this.locationY = (float) (oldY + deltaY);

	}
	*/
	
}

