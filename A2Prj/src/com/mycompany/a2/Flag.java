package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;

public class Flag extends FixedObject {

	private int sequenceNumber;
	
	
	public Flag(float locationX, float locationY, int sequenceNumber)
	{
		super(locationX, locationY);
		this.setSize(15);
		this.sequenceNumber = sequenceNumber;
		super.setColor(ColorUtil.rgb(0, 255, 255));
	}
	
	//override so that it doesn't do anything
	@Override
	public void setColor(int color)
	{
		
	}

	
	public int getSequenceNum()
	{
		return sequenceNumber;
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
