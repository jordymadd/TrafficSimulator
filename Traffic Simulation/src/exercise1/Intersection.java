package exercise1;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import exercise1.Direction;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Intersection extends Actor {

	private TrafficLight northLight; 
	private TrafficLight southLight; 
	private TrafficLight eastLight; 
	private TrafficLight westLight; 
	private static final int NUMOFLIGHTS=4;  
	private TrafficLight[] trafficLight = new TrafficLight[NUMOFLIGHTS]; 
	private ArrayList<TrafficLightSensor> previousNear = new ArrayList<TrafficLightSensor>();
	private ArrayList<TrafficLightSensor> previousIn = new ArrayList<TrafficLightSensor>();
	private GreenfootImage image; 

	public Intersection(){
		image = new GreenfootImage(TrafficWorld.WIDTH_OF_ROAD, TrafficWorld.WIDTH_OF_ROAD); 
//		 this is an image of my intersection in case I want to see it visually
//				image.setColor(Color.blue);
//				image.fill();
		setImage(image);
		Direction[] directionArray = Direction.values(); 
		for (int i = 0; i < trafficLight.length; i++){
			trafficLight[i]= new TrafficLight(directionArray[i]); 
			if (directionArray[i] == Direction.NORTH){
				northLight= trafficLight[i]; 
			}
			else if (directionArray[i] == Direction.SOUTH){
				southLight= trafficLight[i]; 
			}
			else if (directionArray[i] == Direction.EAST){
				eastLight= trafficLight[i]; 
			}
			else if (directionArray[i] == Direction.WEST){
				westLight= trafficLight[i]; 
			}

		}

	}

	public enum lightDirection{
		NORTH, 
		SOUTH,
		EAST, 
		WEST
	}

	public TrafficLight[] getTrafficLight() {
		return trafficLight;
	}

	public void act(){
		notifyNearCars();
		notifyInCars();
		notifyLeavingCars();

	}

	public boolean isGreen(Direction d){
		return d.isHorizontal() && eastLight.isGreen() || d.isVertical() && northLight.isGreen(); 
	}

	public boolean isYellow(Direction d){
		return d.isHorizontal() && eastLight.isYellow() || d.isVertical() && northLight.isYellow(); 
	}

	public boolean isRed(Direction d){
		return d.isHorizontal() && eastLight.isRed() || d.isVertical() && northLight.isRed(); 
	}

	private void notifyNearCars(){
		List<TrafficLightSensor> currentNear = getObjectsInRange(image.getWidth()*2, TrafficLightSensor.class);
		for (TrafficLightSensor trafficLightSensor : currentNear) {
			if (!previousNear.contains(trafficLightSensor)){
				trafficLightSensor.nearIntersection(this);
			}
		}
		previousNear = (ArrayList<TrafficLightSensor>) currentNear; 
	}

	private void notifyInCars(){
		List<TrafficLightSensor> currentIn = getIntersectingObjects(TrafficLightSensor.class);
		for (TrafficLightSensor trafficLightSensor : currentIn){
			if (!previousIn.contains(trafficLightSensor)){
				trafficLightSensor.inInterSection(this);
			}
		}
		previousIn = (ArrayList<TrafficLightSensor>) currentIn; 
	}


	private void notifyLeavingCars(){
			for (TrafficLightSensor trafficLightSensor : previousIn){
				if (previousNear.contains(trafficLightSensor)){
					if(!isRed(trafficLightSensor.getDirection())){
						trafficLightSensor.leavingIntersection(this);
					}
				
			}
		}

	}


}




