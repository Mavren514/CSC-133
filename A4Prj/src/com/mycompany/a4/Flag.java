package com.mycompany.a4;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.ui.Graphics;

public class Flag extends FixedObject implements IDrawable, ISelectable, ICollider{

	private boolean isSelected = false;
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

	public void draw(Graphics g, Point point)
	{
		// TODO Auto-generated method stub
        int locX = (int)getLocationX() + (int)point.getX();
        int locY = (int)getLocationY() + (int)point.getY();

        Point top = new Point(0 + locX, size + locY);
        Point bottomL = new Point(locX - size, locY - size);
        Point bottomR = new Point(locX + size, locY - size);

        g.setColor(ColorUtil.BLUE);

        
        //Set as triangle
        g.fillTriangle( (int)top.getX(),     (int)top.getY(), 
                        (int)bottomL.getX(), (int)bottomL.getY(), 
                        (int)bottomR.getX(), (int)bottomR.getY());
        
        String seqNum = Integer.toString(this.sequenceNumber);
        
        g.drawString(seqNum, locX, locY);
	}
	
	
	
	public boolean collidesWith(ICollider obj) {
       return false;
    }
	//collidesWith
	//handle collision
	public void handleCollision(ICollider obj)
	{
		if(obj instanceof Ladybug)
		{
			Dialog.show("Confirm exit", "Are you sure you want to exit?", "Cancel", "Ok");
		}
	}

	public void setSelected(boolean yesNo)
	{
		// TODO Auto-generated method stub
		
	}

	public boolean isSelected()
	{
		// TODO Auto-generated method stub
		return isSelected;
	}

	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt)
	{
		int px = (int) pPtrRelPrnt.getX(); 
		int py = (int) pPtrRelPrnt.getY(); 
		int xLoc = (int) (pCmpRelPrnt.getX()+ this.getLocationX());
		int yLoc = (int) (pCmpRelPrnt.getY()+ this.getLocationY());
		
		if ( (px >= xLoc) && (px <= xLoc+this.getSize())&& (py >= yLoc) && (py <= yLoc+this.getSize()) )
		        return true; 
		else
		        return false;
	}

	
	
}
