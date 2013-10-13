public class BetweenStationSpace extends SubwaySpace {
	
	public BetweenStationSpace(Station next, double length, String name) {
		this.next = next;
		this.activity_duration = length;
		this.name = name;
	}

	@Override
	public void event(double timestamp) throws Exception {
		next.trainArrival(train, timestamp);
	}

}