import java.util.ArrayList;
import java.util.List;


public class Train implements SimulatorObject {

	private List<Person> passengers;
	private int size;
	private String name;
	
	public Train(){
		passengers = new ArrayList<Person>();
		size = 100;
	}
	
	public boolean passengerIn(Person p){
		if(passengers.size() < size){
			passengers.add(p);
			return true;
		}
		return false;
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
			p.descend();
			passengers.remove(p);
		}
		return;
	}
	
	
	@Override
	public void event(double timestamp) {
		// TODO Auto-generated method stub
		
	}

}
