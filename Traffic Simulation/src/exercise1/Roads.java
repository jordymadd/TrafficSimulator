package exercise1;

import java.awt.Color;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Roads extends Actor {
	
	
	public Roads(int length, int widthOfRoad){
		GreenfootImage image = new GreenfootImage(length, widthOfRoad); 
		image.setColor(Color.GRAY);
		image.fill();
		this.setImage(image);
		
	
	}
	

}
