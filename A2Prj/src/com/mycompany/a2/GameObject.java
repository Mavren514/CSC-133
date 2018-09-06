package com.mycompany.a2;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public abstract class GameObject
{
	//for random numbers
	Random rand = new Random();
	int number = rand.nextInt();
	
	public int size = 0;
	private float locationX = 0;
	private float locationY = 0;
	private int color = 0;
	
	
	//random number
	public float getLocationX()
	{
		return locationX;
	}
	
	public void setLocationX(float locationX)
	{
		this.locationX = locationX;
	}
	
	public float getLocationY()
	{
		return locationY;
	}
	
	public void setLocationY(float locationY)
	{
		this.locationY = locationY;
	}
	
	
	public void setSize(int size)
	{
		this.size = size;
	}

	
	public int getColor()
	{
		return color;
	}
	
	public void setColor(int color)
	{
		this.color = color;
	}
	
	public int getSize()
	{
		return size;
	}
	
	
	@Override
	public String toString() 
	{
		String location = " loc: " + Math.round(this.getLocationX()) + "," + Math.round(this.getLocationY());
		String color = " color: " +  "[" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "] ";
		String size = " size: " + this.getSize();
		
		return location + color + size;
	}
	
	
	
	
	
}
