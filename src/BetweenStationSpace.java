import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BetweenStationSpace extends SubwaySpace {
		
	private Queue<Train> trainsToEnd;
	private Queue<Train> trainsToStart;
	private List<Long> departuresToEnd;
	private List<Long> departuresToStart;
	
	public BetweenStationSpace(Station nextToStart, Station nextToEnd, Long length, String name) {
		super(nextToStart, nextToEnd, length, name);
		trainsToStart = new LinkedList<Train>();
		trainsToEnd = new LinkedList<Train>();
		departuresToStart = new ArrayList<Long>();
		departuresToEnd = new ArrayList<Long>();
	}

	@Override
	public void event(Long timestamp) throws Exception {
		Train train;
		while(departuresToEnd.remove(timestamp)){
			train = trainsToEnd.poll();
			getNextToEnd().trainArrival(train, timestamp);
		}
			
		while(departuresToStart.remove(timestamp)){
			train = trainsToStart.poll();
			getNextToStart().trainArrival(train, timestamp);
		}
	}
	
	public void trainArrival(Train train, Long timestamp) throws Exception {
		System.out.println("****************");
		System.out.println("Train " + train + " arriving to space: " + getName() + " direction:" + train.getDirection() + " passengers:" + train.getPassangers().size() + " time: " + timestamp + " leaving:" + (timestamp + getActivityDuration()));
		System.out.println("****************");
		Long departure = timestamp + getActivityDuration();
		if(train.getDirection().equals(Train.Direction.TO_END)){
			trainsToEnd.add(train);
			departuresToEnd.add(departure);
		}else{
			trainsToStart.add(train);
			departuresToStart.add(departure);
		}
		SimulatorScheduler.getInstance().registerEvent(departure, new SchedulerRegistrator(this, "train: " + train + " will leave space:" + this + " at: " +  departure + " with direction: " + train.getDirection()));
	}
	
	public void setDefaultName(){
		setName(getNextToStart() + " - " + getNextToEnd());
	}
	
	public String toString(){
		return getName();
	}
}