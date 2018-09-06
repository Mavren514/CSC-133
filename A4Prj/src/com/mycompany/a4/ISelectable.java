package com.mycompany.a4;

import com.codename1.ui.Graphics;
import com.codename1.charts.models.Point;

public interface ISelectable {

	// Mark object as selected, or not.
	 public void setSelected(boolean yesNo);
	 
	// Check if object is selected
	 public boolean isSelected();
	 
	// a way to determine if a pointer is “in” an object
	// pPtrRelPrnt = pointer position relative to the parent origin
	// pCmpRelPrnt = component position relative to the parent origin
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
	
	// a way to “draw” the object that knows about drawing 
	// different ways depending on “isSelected”
	// draw differently based on if it is selected or not
	public void draw(Graphics g, Point pCmpRelPrnt);
	
}
