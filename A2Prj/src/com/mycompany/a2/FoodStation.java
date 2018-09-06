package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;

public class FoodStation extends FixedObject{
	
	int capacity = 0;
	int size = 0;
	
	
	
	public FoodStation(float locationX, float locationY, int size)
	{
		super(locationX, locationY);
		this.setColor(ColorUtil.rgb(0, 255, 120));
		this.size = size;
		setCapacity(size);
		
	}
	
	public void setCapacity(int size)
	{
		this.capacity = size;
	}
	
	public int getCapacity()
	{
		return capacity;
	}
	
	
	
	

	@Override 
	public String toString() 
	{
		@SuppressWarnings("deprecation")
		String name = this.getClass().getSimpleName();
		String parentDesc = super.toString();
		return name + parentDesc;
	}
	
	

}
