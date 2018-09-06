package com.mycompany.a3.commandregistry;

import java.io.IOException;
import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class BackgroundSound implements Runnable {
		
	
	private Media media;

	public BackgroundSound (String fileName) 
	{ 
		try 
		{
		InputStream iStream = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
		media = MediaManager.createMedia(iStream, "audio/mp3", this); 
		}
		
		catch (IOException e) 
		{
		   System.out.println("Cannot Play Sound");
		} 
	}
	
	public void pause()
	{
		media.pause();
	}
	
	public void play()
	{
		media.play();
	}
	
	public void run() 
	{
		media.setTime(0);
		media.play();
	}
	
}