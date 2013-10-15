import java.util.ArrayList;
import java.util.List;


public class Station extends SubwaySpace {

	// TODO: change list for queue
	private List<Person> persons;
	private boolean terminal = false;
	
	public Station(BetweenStationSpace previous, BetweenStationSpace next, double duration, String name) {
		this.persons = new ArrayList<Person>();
		this.previous = previous;
		this.next = next;
		train = null;
		activity_duration = duration;
		this.name = name;
	}

	public Station(BetweenStationSpace previous, BetweenStationSpace next, double duration, String name, Boolean terminal){
		this(previous, next, duration, name);
		this.terminal = terminal;
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

		train.descendPassengers(this);
		
		double qty = 0;
		// Loading passengers in the train
		while (persons.size() > 0 && train.passengerIn(persons.get(0))) {
			persons.remove(0);
			qty++;
		}

		System.out.println("Passangers got in the train in:" + name + " #:" + qty);
		System.out.println("train leaving station:" + name + " with #:" + train.getPassangers().size() );

		
		if(train.getDirection() == Train.Direction.RIGHT)
			next.trainArrival(train, timestamp);
		else 
			previous.trainArrival(train, timestamp);
		if (terminal){
			train.reverse();
		}
		System.out.println("Station:" + name + " next:" + next.name + " previous: " + previous.name + " direction:" + train.getDirection());
		this.train = null;
	}

}
