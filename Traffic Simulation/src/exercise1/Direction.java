package exercise1;



public enum Direction {
	NORTH(270), 
	SOUTH(90), 
	EAST(0), 
	WEST(180); 
	
	public boolean isHorizontal(){
		return this==EAST || this==WEST; 
	}
	
	public boolean isVertical(){
		return this==NORTH || this==SOUTH; 
	}

	private int direction; 

	private Direction(int direction){
		this.direction=direction; 	
	}
	
	public int getRotation(){
		return direction; 
	}
	

}


