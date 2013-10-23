import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;

public class Train {

	public enum Direction{
		TO_END, TO_START;
	}
	
	private List<Person> passengers;
	private int size;
	private String name;
	private Direction direction;
	private float x;
	private float y;
	
	public Train(String name){
		this.name = name;
		passengers = new ArrayList<Person>();
		size = 100;
		direction = Direction.TO_END;
		SubwayMap.getInstance().addTrain(this);
	}
	
	public boolean passengerIn(Person p){
		if(passengers.size() < size){
			passengers.add(p);
			return true;
		}
		return false;
	}

	public Direction getDirection(){
		return direction;
	}
	
	public String getName(){
		return name;
	}
	
	public void passengerOut(Person p){
		p.descend();
		passengers.remove(p);
	}
	
	public List<Person> getPassangers(){
		return passengers;
	}
	
	public void descendPassengers(Station actual){
		List<Person> downloaders = new ArrayList<Person>();
		for(Person p: passengers)
			if (p.hasToDecend(actual))
				downloaders.add(p);
		
		for (Person p: downloaders){
			passengerOut(p);
		}

//		System.out.println("Downloading passangers in " + actual.name + " #:" + downloaders.size());
		return;
	}
	
	public void reverse(){
		if(direction == Direction.TO_END)
			direction = Direction.TO_START;
		else
			direction = Direction.TO_END;
	}

	public String toString(){
		return getName();
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public Color getColor(){
		if(getDirection() == Direction.TO_START)
			return Color.magenta;
		return Color.black;
	}
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
}
