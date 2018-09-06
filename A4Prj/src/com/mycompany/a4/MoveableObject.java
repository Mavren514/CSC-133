package com.mycompany.a4;

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
		this.setLocationX((this.getLocationX() + (float) deltaX));
		this.setLocationY((this.getLocationY() + (float) deltaY));
		
		
		if(this.getLocationY() > 0 && this.getLocationY() < Game.getMapY() - (this.size))
		{
			//Object is inside the map
			//this.setLocationY(0);
		}
			else if (this.getLocationY() > Game.getMapY() - (this.size))
			{
				this.setLocationY(Game.getMapY() - (this.size));
			}
				else if(this.getLocationY() < 0)
				{
					this.setLocationY(0);
				}

		
		
		if(this.getLocationX() > 0 && this.getLocationX() < Game.getMapX() - (this.size))
		{
			//Object is inside map
			//this.setLocationX(0);
		}
			else if (this.getLocationX() > Game.getMapX() - (this.size))
			{
				this.setLocationX(Game.getMapX() - (this.size));
			}
				else if(this.getLocationX() < 0)
				{
					this.setLocationX(0);
				}
		
		
		
		
		
		
		

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
