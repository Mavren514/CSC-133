package com.mycompany.a4;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.ui.Graphics;

public class FoodStation extends FixedObject implements IDrawable, ISelectable, ICollider{
	
	int capacity = 25;
	private boolean isSelected;
	
	
	public FoodStation(float locationX, float locationY, int size)
	{
		super(locationX, locationY);
		this.setColor(ColorUtil.rgb(0, 255, 120));
		this.setSize(size);
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
	
	public void draw(Graphics g, Point point) {
		
        int locX = (int)getLocationX() + (int)point.getX();
        int locY = (int)getLocationY() + (int)point.getY();
		
        if(isSelected())
		{
        	System.out.println("woooooooo");
			g.setColor(ColorUtil.BLUE);
		}
		else
		{
			g.setColor(ColorUtil.BLACK);

		}
		g.fillRect(locX, locY, this.getSize(), this.getSize());
		
		
	}
	
	

	@Override 
	public String toString() 
	{
		@SuppressWarnings("deprecation")
		String name = this.getClass().getSimpleName();
		String parentDesc = super.toString();
		return name + parentDesc;
	}

	
	public boolean collidesWith(ICollider obj) {
        boolean result = false;

        int thisCenterX = (int) this.getLocationX() + getSize() / 2;
        int thisCenterY = (int) this.getLocationY() + getSize() / 2;

        int otherCenterX = (int) ((GameObject)obj).getLocationX() + (getSize() / 2);
        int otherCenterY = (int) ((GameObject)obj).getLocationY() + (getSize() / 2);


        int dx = thisCenterX - otherCenterX;
        int dy = thisCenterY - otherCenterY;

        int distBetweenCentersSqr = (dx * dx + dy * dy);

        int thisRadius  = getSize() / 2;
        int otherRadius = getSize() / 2;

        int radiiSqr = (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);

        if(distBetweenCentersSqr <= radiiSqr) {
            System.out.println("Collision with FoodStation!");
            result = true;

        }else {
         /*   
        	if(collisions.contains(obj)) {
                collisions.remove(obj);
            }
        */
        	result = false;
        }
        return result;
    }
	//collidesWith
	//handle collision
	public void handleCollision(ICollider obj)
	{
		
	}

	public void setSelected(boolean yesNo)
	{
		// TODO Auto-generated method stub
		isSelected = yesNo;
	}

	public boolean isSelected()
	{
		// TODO Auto-generated method stub
		return isSelected;
	}

	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt)
	{
		float px = pPtrRelPrnt.getX();
		float py = pPtrRelPrnt.getY();
		float xLoc = pCmpRelPrnt.getX() + getLocationX();
		float yLoc = pCmpRelPrnt.getY() + getLocationY();
		int selectR = size;
		
		if ((px > xLoc-selectR && px < xLoc+selectR)
			&&
			(py > yLoc-selectR && py < yLoc+selectR))
			return true;
		return false;
	}

}
