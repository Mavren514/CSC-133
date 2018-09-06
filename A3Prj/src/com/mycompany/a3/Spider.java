package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.ui.Graphics;

public class Spider extends MoveableObject implements IDrawable, ICollider
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
	
	public void draw(Graphics g, Point point)
	{
		// TODO Auto-generated method stub
        int locX = (int)getLocationX() + (int)point.getX();
        int locY = (int)getLocationX() + (int)point.getY();


        Point top = new Point(0 + locX, size + locY);
        Point bottomL = new Point(locX - size, locY - size);
        Point bottomR = new Point(locX + size, locY - size);	

        g.setColor(ColorUtil.LTGRAY);

        //Unfilled triangles using lines
        g.drawLine((int)top.getX(), (int)top.getY(), (int)bottomL.getX(), (int)bottomL.getY());
        g.drawLine((int)bottomL.getX(), (int)bottomL.getY(), (int)bottomR.getX(), (int)bottomR.getY());
        g.drawLine((int)top.getX(), (int)top.getY(), (int)bottomR.getX(), (int)bottomR.getY());


		
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

        if(distBetweenCentersSqr <= radiiSqr) 
        {
            result = true;
        }
        return result;
    }
	
	
	//collidesWith
	//handle collision
	public void handleCollision(ICollider obj)
	{
		
	}
}
