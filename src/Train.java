import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.Color;

public class Train implements SimulatorObject {

	public enum Direction {
		TO_END, TO_START;
	}

	private HashMap<Station, List<Person>> passengers;
	private int size;
	private int actual_passengers;
	private String name;
	private Direction direction;
	private float x;
	private float y;
	private Line line;
	private Station start;
	
	public Train(String name, Line line, Station start, Direction direction){
		this.name = name;
		this.line = line;
		passengers = new HashMap<Station, List<Person>>();
		size = 900;
		this.direction = direction;
		this.line = line;
		this.start = start;
		this.actual_passengers = 0;
	}

	public boolean passengerIn(Person p, Station target) {
		if (actual_passengers <= size) {
//			if(!passengers.containsKey(p.getDestiny()))
			if(!passengers.containsKey(target))
				passengers.put(target, new ArrayList<Person>());
			passengers.get(target).add(p);
			actual_passengers++;
			return true;
		}
		return false;
	}

	public Direction getDirection() {
		return direction;
	}

	public String getName() {
		return name;
	}

	public boolean hasPassengers(){
		return actual_passengers != 0;
	}
	
	public void descendPassengers(Station actual) throws Exception {
		if(!passengers.containsKey(actual))
			return;
		for(Person p : passengers.get(actual)) {
			p.descend();
			if(p.getDestiny().equals(actual))
				Person.descendPeople();
			else
				actual.makeCombination(p);
		}
		actual_passengers -= passengers.get(actual).size();
		passengers.remove(actual);
		return;
	}

	public void reverse() {
		if (direction == Direction.TO_END)
			direction = Direction.TO_START;
		else
			direction = Direction.TO_END;
	}

	public String toString() {
		return getName();
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Color getColor() {
		if (getDirection() == Direction.TO_START)
			return Color.magenta;
		return Color.black;
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Line getLine() {
		return this.line;
	}

	@Override
	public void event(Long timestamp) throws Exception {
		SubwayMap.getInstance().addTrain(this);
		start.trainArrival(this, timestamp);
	}
	
	public void print(){
		System.out.println("train:" + getName() + " from line:" + getLine());
		System.out.println("----------- passangers -----------");
		for(Station s: passengers.keySet())
			for(Person p: passengers.get(s))
				System.out.println("origin:" + p.getOrigin() + " destiny:" + p.getDestiny() + " se baja en:" + s);
		System.out.println("----------------------------------");
	}
}
