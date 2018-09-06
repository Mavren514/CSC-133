package com.mycompany.a2;

public abstract class FixedObject extends GameObject {
	
	public FixedObject(float locationX, float locationY)
	{
		super.setLocationX(locationX);
		super.setLocationY(locationY);
	}
	
	
	//override so that setting the location does nothing
	@Override
	public void setLocationX(float locationX)
	{
		
	}
	
	
	
	//override so that setting the location does nothing
	@Override
	public void setLocationY(float locationY)
	{
		
	}
	
	
	
	
	

}
