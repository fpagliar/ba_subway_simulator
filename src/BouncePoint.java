import java.util.LinkedList;
import java.util.Queue;


public class BouncePoint extends SubwaySpace {
	
	private Line line;
	private TrainFactory factory;
	
	public BouncePoint(Station previousAndNext, Line line) {
		super(previousAndNext, previousAndNext, 0, "");
		this.factory = new TrainFactory(this);
		this.line = line;
	}
	
	public TrainFactory getFactory() {
		return factory;
	}

	public String getName(){
		return getNextToEnd().getName() + " - bouncepoint";
	}

	@Override
	public void event(Long timestamp) throws Exception {
//		throw new Exception("Not implemented");
	}

	public void trainArrival(Train train, Long timestamp, boolean factoryCall) throws Exception {
		if(!factoryCall)
			factory.trainArrival();
		train.reverse();
		getNextToEnd().trainArrival(train, timestamp);
	}

	@Override
	public void trainArrival(Train train, Long timestamp) throws Exception {
		trainArrival(train, timestamp, false);
//		SimulatorScheduler.getInstance().registerEvent(startTimes.poll(), new SchedulerRegistrator(this, "Train starting from " + getName() + " at " + timestamp));
	}

	@Override
	public void setNextToEnd(SubwaySpace nextToEnd) {
		super.setNextToStart(nextToEnd);
		super.setNextToEnd(nextToEnd);
	}

	@Override
	public void setNextToStart(SubwaySpace nextToStart) {
		super.setNextToStart(nextToStart);
		super.setNextToEnd(nextToStart);
	}
	
	public void setDefaultName(){
		return;
	}
	
	public Line getLine(){
		return line;
	}
}
