package com.mycompany.a1;
import java.util.Iterator;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class Game extends Form {
	

	private GameWorld gw;
	
	public Game() {
		
		//gw = new GameWorld();
		
		gw = new GameWorld();
		gw.init();
		play();
		
	}
	

	private void play()
	 {
	
	 
		Label myLabel = new Label("Enter a Command:");
		this.addComponent(myLabel);
		
		final TextField myTextField = new TextField();
		this.addComponent(myTextField);
		
		this.show();
		myTextField.addActionListener(new ActionListener()
		{
			boolean isExiting = false;
			
			public void actionPerformed(ActionEvent evt) 
			{

				String sCommand = myTextField.getText().toString();
				myTextField.clear();
				if(!isExiting)
				{	
					switch (sCommand.charAt(0)) 
					{
						case 'a':
							//accelerate (increase the speed of) the ladybug yada yada
							System.out.println("Increasing the speed of the ladybug!");
							gw.accelerateLadybug();
							break;
						case 'b':
							//brake (reduce the speed of) yada
							System.out.println("Reducing the speed of the ladybug!");
							gw.brakeLadybug();
							break;
						case 'l':
							//change the heading of the ladybug by 5 degrees to the left
							System.out.println("Changing the heading of the ladybug to the left!");
							gw.leftHeadingLadybug();
							break;
						case 'r':
							//change the heading of the ladybug to the right by 5
							System.out.println("Changing the heading of the ladybug to the right!");
							gw.rightHeadingLadybug();
							break;
						case '1':
							System.out.println("Colliding with flag!");
							gw.collisionWithFlag(1);
							break;
						case '2':
							System.out.println("Colliding with flag!");
							gw.collisionWithFlag(2);
							break;
						case '3':
							System.out.println("Colliding with flag!");
							gw.collisionWithFlag(3);
							break;
						case '4':
							System.out.println("Colliding with flag!");
							gw.collisionWithFlag(4);
							break;
						case '5':
							System.out.println("Colliding with flag!");
							gw.collisionWithFlag(5);
							break;
						case '6':
							System.out.println("Colliding with flag!");
							gw.collisionWithFlag(6);
							break;
						case '7':
							System.out.println("Colliding with flag!");
							gw.collisionWithFlag(7);
							break;
						case '8':
							System.out.println("Colliding with flag!");
							gw.collisionWithFlag(8);
							break;
						case '9':
							System.out.println("Colliding with flag!");
							gw.collisionWithFlag(9);
							break;
						case 'f':
							//pretend that the ladybug has collided with a food station
							System.out.println("Colliding with food station!");
							gw.collisionWithFoodStation();
							break;
						case 'g':
							System.out.println("Colliding with spider!");
							gw.collisionWithSpider();
							break;
						case 't':
							//tell the game world that the game clock has "ticked"
							System.out.println("Ticking the game clock");
							gw.gameClockTick();
							break;
						case 'd':
							//generate display
							System.out.println("Generating the display");
							gw.generateDisplay();
							break;
						case 'm':
							//output a map
							System.out.println("Printing the map: ");
							gw.outputMap();
							break;
						case 'x':
							//exit the game
							isExiting = true;
							System.out.println("Please confirm you want to exit");
							myLabel.setText("Please enter y or n");
							break;
					}
				}
				else
				{
					switch(sCommand.charAt(0))
					{
						case 'y':
							//user says yes, maybe put in exit switch statement?
							System.out.println("Exit has been confirmed");
							gw.exit();
							break;
						case 'n':
							//user says no
							System.out.println("Exit cancelled");
							isExiting = false;
							break;
					}

				
				} 
			} 
		} //new ActionListener()
									); //addActionListener
	 } //play

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub

	}

}
