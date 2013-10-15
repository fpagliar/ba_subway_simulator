import java.util.ArrayList;
import java.util.List;

public class Station extends SubwaySpace {

	// TODO: change list for queue
	private List<Person> persons;

	public Station(BetweenStationSpace previous, BetweenStationSpace next, double duration, String name) {
		this.persons = new ArrayList<Person>();
		this.previous = previous;
		this.next = next;
		train = null;
		activity_duration = duration;
		this.name = name;
	}

	public void personArrival(Person p) {
		persons.add(p);
	}
	
	public double getPersonsWaiting(){
		return persons.size();
	}

	@Override
	public void event(double timestamp) throws Exception {
		if(next == null){
			// TODO: bounce behavior
			return;
		}
		List<Person> downloaders = new ArrayList<Person>();

		train.descendPassengers(this);
		
		System.out.println("Downloading passangers in " + name + " #:" + downloaders.size());
		

		while (persons.size() > 0 && train.passengerIn(persons.get(0))) {
			persons.remove(0);
		}
		next.trainArrival(train, timestamp);

		System.out.println("train leaving station:" + name + " with #:" + train.getPassangers().size() );
	}

}
