public class BetweenStationSpace extends SubwaySpace {
	
	public BetweenStationSpace(Station previous, Station next, double length, String name) {
		this.previous = previous;
		this.next = next;
		this.activity_duration = length;
		this.name = name;
	}

	@Override
	public void event(double timestamp) throws Exception {
		System.out.println("train leaving space:" + name);
		next.trainArrival(train, timestamp);
	}

}