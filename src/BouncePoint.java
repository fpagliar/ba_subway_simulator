

public class BouncePoint extends SubwaySpace {
	
	private Line line;
	
	public BouncePoint(Station previousAndNext, Line line) {
		super(previousAndNext, previousAndNext, 0, "");
		this.line = line;
	}
	
	public String getName(){
		return getNextToEnd().getName() + " - bouncepoint";
	}

	@Override
	public void event(Long timestamp) throws Exception {
//		throw new Exception("Not implemented");
	}

	public void trainArrival(Train train, Long timestamp) throws Exception {
		if(train.getPassangers().size() != 0)
			throw new Exception("Passengers still in the train! + qty:" + train.getPassangers().size());
		SubwayMap.getInstance().removeTrain(train);
//		System.out.println("TRAIN ARRIVING AT BOUNCE POINT: " + train.getName());
		// trains now fall over the edge!!
//		train.reverse();
//		getNextToEnd().trainArrival(train, timestamp);
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
