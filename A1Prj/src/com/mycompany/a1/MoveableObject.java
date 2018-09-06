package com.mycompany.a1;

public abstract class MoveableObject extends GameObject
{
	//0 is north, 90 means heading east. In degrees
	protected int heading;
	protected int speed = 1;
	
	public MoveableObject(int heading){
		this.heading = heading;
	}
	
	public void move() {
		double deltaX = (Math.cos(Math.toRadians(90 - this.heading))) * this.speed;
		double deltaY = (Math.sin(Math.toRadians(90 - this.heading))) * this.speed;
		
		//will this cast make the world blow up? tune in next time, on "Jonathon attempts to code"
		this.setLocationX(this.getLocationX() + (float) deltaX);
		this.setLocationY(this.getLocationY() + (float) deltaY);
		

	}
	
	
	
	
	
	public int getHeading()
	{
		return heading;
	}
	
	public void setHeading(int heading)
	{
		this.heading = heading;
	}
	
	public int getSpeed()
	{
		return speed;
	}

	
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}
	
	@Override 
	public String toString() {
		String parentDesc = super.toString();
		String heading = " heading: " + this.getHeading();
		String speed = " speed: " + this.getSpeed();
		return parentDesc + heading + speed;
	}
	
	//public abstract void move();

}
