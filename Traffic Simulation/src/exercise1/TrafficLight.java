package exercise1;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class TrafficLight extends Actor {
	
	private Direction rotationDirection; 
	private int greenCounter=780; //13 sec  NORTH/SOUTH time it will be red  EAST/WEST time until red
	private int yellowCounter=600;//10 sec how long light will be green for 
	private int redCounter=180; //3 sec how long yellow light is for 
	private int lightCounter=0;


	public State state = State.GREEN; 

	public TrafficLight(Direction rotation){
		this.rotationDirection = rotation; 
		switch (rotationDirection) {
		case NORTH: 
		case SOUTH: 
			state=State.RED;  
			break;
		case EAST: 
		case WEST: 
			state = State.GREEN; 	
		}
		GreenfootImage image = new GreenfootImage(this.state.getImgPath()); 
		setImage(image);
		this.rotationDirection= rotation;

	}


public boolean isEast(){
		return rotationDirection == Direction.EAST; 
}

public boolean isWest(){
	return rotationDirection == Direction.WEST; 
}

public boolean isNorth(){
	return rotationDirection == Direction.NORTH; 
}

public boolean isSouth(){
	return rotationDirection == Direction.SOUTH; 
}


	public enum State{
		GREEN("images/trafficLightGreen.png"), YELLOW("images/trafficLightYellow.png"), RED("images/trafficLightRed.png"); 
		String imgPath;

		public String getImgPath() {
			return imgPath;
		}

		private State(String imgPath){
			this.imgPath = imgPath; 
		}
	}

	public boolean isRed(){
		return state==State.RED; 
	}
	
	public boolean isGreen(){
		return state==State.GREEN; 
	}
	
	public boolean isYellow(){
		return state==State.YELLOW; 
	}


	@Override
	public void act(){ //the act method runs 3600 X per minute (60 times per second) 
		lightCounter++; 
		switch(state){
		case GREEN: 
			if (lightCounter == yellowCounter){
				state = State.YELLOW;
				setImageFromStateAndResetCounter();
			}
			break; 
		case YELLOW:
			if (lightCounter == redCounter){
				state = State.RED;
				setImageFromStateAndResetCounter();
			}
			break; 
		case RED: 
			if (lightCounter == greenCounter){
				state = State.GREEN;
				setImageFromStateAndResetCounter();
			}
			break; 
		}

	}


	private void setImageFromStateAndResetCounter() {
		GreenfootImage image = new GreenfootImage(state.imgPath); 
		setImage(image);
		lightCounter = 0;
	}
	
	public Direction getDirection(){
		return rotationDirection; 
	}

}
