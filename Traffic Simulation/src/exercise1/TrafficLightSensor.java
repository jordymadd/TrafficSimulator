package exercise1;

public interface TrafficLightSensor {

	public void nearIntersection(Intersection intersection);
	public void inInterSection(Intersection intersection); 
	public void leavingIntersection(Intersection intersection);
	
	public Direction getDirection(); 
	
	
}
