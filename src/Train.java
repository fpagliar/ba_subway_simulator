import java.util.ArrayList;
import java.util.List;


public class Train implements SimulatorObject {

	public enum Direction{
		LEFT, RIGHT;
	}
	private List<Person> passengers;
	private int size;
	private String name;
	private Direction direction;
	
	public Train(){
		passengers = new ArrayList<Person>();
		size = 100;
		direction = Direction.RIGHT;
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

		System.out.println("Downloading passangers in " + actual.name + " #:" + downloaders.size());
		return;
	}
	
	public void reverse(){
		if(direction == Direction.LEFT)
			direction = Direction.RIGHT;
		else
			direction = Direction.LEFT;
	}
	
	
	@Override
	public void event(double timestamp) {
		// TODO Auto-generated method stub
		
	}

}
