import java.util.LinkedList;
import java.util.Queue;

public class Station extends SubwaySpace {

	private Queue<Person> passangersToStart;
	private Queue<Person> passangersToEnd;
	private Train trainToStart;
	private Long timeToLeaveToStart;
	private Train trainToEnd;
	private Long timeToLeaveToEnd;
	private Line line;

	public Station(SubwaySpace nextToStart, SubwaySpace nextToEnd, Line line, long waitingDuration, String name, float x, float y) {
		super(nextToStart, nextToEnd, waitingDuration, name, x, y);
		this.passangersToStart = new LinkedList<Person>();
		this.passangersToEnd = new LinkedList<Person>();
		this.line = line;
	}

	public void personArrival(Person p) throws Exception {
		if (line.getDirection(this, p.getDestiny()).equals(Train.Direction.TO_END))
			passangersToEnd.add(p);
		else
			passangersToStart.add(p);
	}

	public double getPersonsWaiting(Train.Direction direction) {
		if (direction.equals(Train.Direction.TO_END))
			return passangersToEnd.size();
		return passangersToStart.size();
	}

	@Override
	public void trainArrival(Train train, Long timestamp) throws Exception {
		train.setPosition(getX(), getY());
		
		// if I have a pending event this moment, first execute it in order to
		// free the space for the new train
		if ((timeToLeaveToEnd != null && timeToLeaveToEnd.equals(timestamp)) || (timeToLeaveToStart != null  && timeToLeaveToStart.equals(timestamp))) {
			this.event(timestamp);
			SimulatorScheduler.getInstance().deleteEvent(timestamp, this);
		}

		if (train.getDirection().equals(Train.Direction.TO_END)) {
			if (trainToEnd != null)
				throw new Exception("Trains crashed in " + getName() + "-> entered: " + train.getName() + " in station was: " + trainToEnd.getName() + " duration:" + getActivityDuration() + " time to leave was:" + timeToLeaveToEnd + " actual time: " + timestamp);
			trainToEnd = train;
			timeToLeaveToEnd = timestamp + getActivityDuration();
			SimulatorScheduler.getInstance().registerEvent(
					timeToLeaveToEnd,
					new SchedulerRegistrator(this, "train: " + trainToEnd + " will leave station: " + this + " at: " + timeToLeaveToEnd
							+ " with direction: " + trainToEnd.getDirection()));
			return;
		}

		if (trainToStart != null)
			throw new Exception("Trains crashed in " + getName() + "-> entered: " + train.getName() + " in station was: " + trainToStart.getName() + " time:" + timestamp + " time to leave to start:" + timeToLeaveToStart + " duration:" + getActivityDuration());

		trainToStart = train;
		timeToLeaveToStart = timestamp + getActivityDuration();
		SimulatorScheduler.getInstance().registerEvent(
				timeToLeaveToStart,
				new SchedulerRegistrator(this, "train: " + trainToStart + " will leave station: " + this + " at: " + timeToLeaveToStart
						+ " with direction: " + trainToStart.getDirection()));
	}

	public void setDefaultName() {
		return;
	}

	public String toString() {
		return getName();
	}

	@Override
	public void event(Long timestamp) throws Exception {
		if (timeToLeaveToStart != null && timeToLeaveToStart.equals(timestamp)) {
			trainToStart.descendPassengers(this);
			while (passangersToStart.size() > 0 && trainToStart.passengerIn(passangersToStart.peek())) {
				passangersToStart.poll();
			}
			SubwayMap.getInstance().addPassengersNotLoaded(this.line.getLineLetter(), passangersToStart.size());
			timeToLeaveToStart = null;
			getNextToStart().trainArrival(trainToStart, timestamp);
			trainToStart = null;
		}

		if (timeToLeaveToEnd != null && timeToLeaveToEnd.equals(timestamp)) {
			trainToEnd.descendPassengers(this);
			while (passangersToEnd.size() > 0 && trainToEnd.passengerIn(passangersToEnd.peek())) {
				passangersToEnd.poll();
			}
			SubwayMap.getInstance().addPassengersNotLoaded(this.line.getLineLetter(), passangersToEnd.size());
			// Setted before calling trainArrival because if not it would
			// resolve in a bounce-back
			// stack overflow due to calling in event -> trainArrival
			// -> event (in order for trains to leave before entering new ones)
			timeToLeaveToEnd = null;
			getNextToEnd().trainArrival(trainToEnd, timestamp);
			trainToEnd = null;
		}
	}

	public Integer getTotalPassengers() {
		return passangersToEnd.size() + passangersToStart.size();
	}

	@Override
	public int hashCode() {
		return line.getPos(this);
	}
	
	public Line getLine(){
		return line;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Station ){
			Station other = (Station) obj;
			return getName().equals(other.getName()) && line.equals(other.getLine());
		}
		return false;
	}
}
