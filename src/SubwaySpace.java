
public abstract class SubwaySpace implements SimulatorObject{

	protected Train train;
	protected SubwaySpace next;
	protected SubwaySpace previous;
	protected double activity_duration;
	protected double last_arrival;
	protected String name;

	public double getTimeOff() {
		return last_arrival + activity_duration;
	}	
	
	public void checkTrainArrivalAndTakeOff(double timestamp) throws Exception{
		if (this.train != null) {
			// there is a train and it is not leaving the station
			if (getTimeOff() != timestamp)
				throw new Exception("Trains crashed in station: " + name
						+ " - Train1:" + this.train.getName() );
			// to make the other train leave first
			this.event(timestamp);
			// to avoid being called on the same event two times
			SimulatorScheduler.getInstance().deleteEvent(timestamp, this);
		}
		return;
	}
	
	public void trainArrival(Train train, double timestamp) throws Exception {
		checkTrainArrivalAndTakeOff(timestamp);
		this.train = train;
		last_arrival = timestamp;
		SimulatorScheduler.getInstance().registerEvent(last_arrival + activity_duration, this);
	}

	public SubwaySpace getNext() {
		return next;
	}

	public void setNext(SubwaySpace next) {
		this.next = next;
	}

	public SubwaySpace getPrevious() {
		return previous;
	}

	public void setPrevious(SubwaySpace previous) {
		this.previous = previous;
	}
}
