package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;

public class MapView extends Container implements Observer 
{
	
	private int mapViewHeight = this.getHeight();
	private int mapViewWidth = this.getWidth();
	
	public MapView()
	{		
		this.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		
	
	}

	//TODO args maybe not arg
	public void update(Observable observable, Object arg)
	{
		((GameWorld) observable).generateDisplay();
	}

	public int getMapViewWidth()
	{
		return mapViewWidth;
	}

	public void setMapViewWidth(int mapViewWidth)
	{
		this.mapViewWidth = mapViewWidth;
	}

	public int getMapViewHeight()
	{
		return mapViewHeight;
	}

	public void setMapViewHeight(int mapViewHeight)
	{
		this.mapViewHeight = mapViewHeight;
	}
	
	

}
