import java.util.ArrayList;
import java.util.List;


public class Station implements SimulatorObject {

	private List<Person> persons;
	private Train train;
	private BetweenStationSpace next;
	private double descending_duration;
	private double last_arrival;
	
	public Station(BetweenStationSpace next, double duration){
		persons = new ArrayList<Person>();
		train = null;
		this.next = next;
		descending_duration = duration;
	}

	public void trainArrival(Train train, double timestamp) throws Exception {
		this.train = train;
		this.last_arrival = timestamp;
		SimulatorScheduler.getInstance().registerEvent(last_arrival + descending_duration, this);
	}
	
	
	@Override
	public void event(double timestamp) throws Exception {
		for(Person p: train.passangers){
			if(p.getDestiny().equals(this)){
				p.descend();
				train.passangers.remove(p);
			}
		}
		next.trainArrival(train, timestamp);
	}
	
}
