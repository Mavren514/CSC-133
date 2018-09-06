package com.mycompany.a4;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Border;
import com.mycompany.a4.GameWorld;
import com.codename1.charts.models.Point;

public class MapView extends Container implements Observer 
{
	private GameWorld gw;
	
	
	public MapView()
	{
		this.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		this.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.rgb(255, 0, 0)));
	}
	

	public void update(Observable observable, Object arg)
	{
		gw = (GameWorld) observable;
		gw.addObserver(this);
		gw.generateDisplay();
		this.repaint();
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		Point pCmpRelPrnt = new Point(getX(), getY());
		gw.draw(g, pCmpRelPrnt);
		
		GameObjectCollection theCollection = gw.getGameObjectCollection();
		IIterator theSelectors = theCollection.getIterator();
		while(theSelectors.hasNext()){
			GameObject curObj = (GameObject)theSelectors.getNext();
			if ((curObj instanceof Flag || curObj instanceof FoodStation) && gw.isPaused() == false) {
				((ISelectable)curObj).setSelected(false);
			}
		}
	}
	
	public void pointerPressed(int x, int y) { 
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY(); 
		Point pPtrRelPrnt = new Point(x, y);
	       
		Point pCmpRelPrnt = new Point(getX(), getY());
		GameObjectCollection theCollection = gw.getGameObjectCollection();
			
		IIterator theSelectors = theCollection.getIterator();
		while(theSelectors.hasNext()){
			GameObject curObj = (GameObject)theSelectors.getNext();
			if (curObj instanceof Flag || curObj instanceof FoodStation) {
				
				//check if it was selected so we can move it
				if (((ISelectable)curObj).isSelected()) 
				{
					((GameObject)curObj).setLocationX(x - getX() - curObj.getSize() / 2);
					((GameObject)curObj).setLocationY(y - getY() - curObj.getSize() / 2);
					((ISelectable)curObj).setSelected(false);
					//model.setPositionable(false);
					
				} else if(((ISelectable)curObj).contains(pPtrRelPrnt, pCmpRelPrnt)) 
				{
					((ISelectable)curObj).setSelected(true);
					
				}
				else 
				{
					((ISelectable)curObj).setSelected(false);
				}
			
			}

			repaint(); 
			
		}

	}
}
