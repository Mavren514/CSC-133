package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class Spider extends MoveableObject
{
	
	//private double deltaX = 0;
	//private double deltaY = 0;
	
	//private float oldX = 0;
	//private float oldY = 0;
	
	
	
	//spider constructor
	public Spider(float locationX, float locationY, int speed, int size, int heading) 
	{
		super(heading);
		
		//should be random-ish
		this.setLocationX(locationX);
		this.setLocationY(locationY);
		
		this.speed = speed;

		super.setSize(size);
		super.setColor(ColorUtil.rgb(0, 255, 0));
	}
	
	//Maybe make it turn around if it hits a wall??
	public void randomHeading()
	{
		this.setHeading(rand.nextInt(360));
	}
	
	//Override the size setter so that it doesn't do anything
	@Override
	public void setSize(int size)
	{
		
	}
	
	//Override the color setter so that it doesn't do anything
	@Override
	public void setColor(int color)
	{
		
	}

	
	@Override 
	public String toString() 
	{
		@SuppressWarnings("deprecation")
		String name = this.getClass().getSimpleName();
		String parentDesc = super.toString();
		return name + parentDesc;
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

		
	*/
}
