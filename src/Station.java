import java.util.ArrayList;
import java.util.List;

public class Station extends SubwaySpace {

	// TODO: change list for queue
	private List<Person> persons;

	public Station(BetweenStationSpace next, double duration) {
		persons = new ArrayList<Person>();
		train = null;
		this.next = next;
		activity_duration = duration;
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

		for (Person p : train.getPassangers()) {
			if (p.getDestiny().equals(this)) {
				downloaders.add(p);
			}
		}

		for (Person p : downloaders)
			train.passengerOut(p);

		System.out.println("Downloading passangers in " + name + " #:" + downloaders.size());
		

		while (persons.size() > 0 && train.passengerIn(persons.get(0))) {
			persons.remove(0);
		}
		next.trainArrival(train, timestamp);

		System.out.println("train leaving station:" + name + " with #:" + train.getPassangers().size() );
	}

}
