package com.mycompany.a4;

import java.util.ArrayList;

public class GameObjectCollection implements ICollection {
	
	private ArrayList<GameObject> gameObjectList = new ArrayList<GameObject>();
	
	//Needed?
	private Iterator gameObjectIterator = new Iterator();
	
	
	public class Iterator implements IIterator
	{
		private int currentElement;
		
		public Iterator()
		{
			currentElement = -1;
		}
		
		public Object getNext()
		{
			currentElement++;
			return gameObjectList.get(currentElement);
		}
		
		public boolean hasNext()
		{
			if(gameObjectList.size() <= 0)
			{
				return false;
			}
			else if(currentElement == gameObjectList.size() - 1)
			{
				return false;
			}
			return true;
		}
		
		/*
		public void remove(Object o)
		{
			gameObjectList.remove((GameObject)o);
		}
		*/
	}
	
	
	
	public void add(Object object)
	{
		gameObjectList.add((GameObject)object);
	}
	
	public boolean contains(GameObject obj)
	{
		return false;
	}
	
	public void remove(GameObject obj)
	{
		gameObjectList.remove(obj);
	}
	
	public Iterator getIterator()
	{
		return new Iterator();
	}
	
	
}
