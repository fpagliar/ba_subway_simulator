public class BetweenStationSpace implements SimulatorObject {

	private Station next;
	private double length;
	private Train train;
	private double arrival;

	/**
	 * @param next
	 * @param length
	 *            - in time events
	 */
	public BetweenStationSpace(Station next, double length) {
		this.next = next;
		this.length = length;
	}

	public void trainArrival(Train train, double timestamp) throws Exception {
		this.train = train;
		arrival = timestamp;
		SimulatorScheduler.getInstance().registerEvent(arrival + length, this);
	}

	@Override
	public void event(double timestamp) throws Exception {
		next.trainArrival(train, timestamp);		
	}

}