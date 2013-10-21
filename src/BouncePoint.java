
public class BouncePoint extends SubwaySpace {

	public BouncePoint(Station previousAndNext) {
		super(previousAndNext, previousAndNext, 0, "");
	}
	
	public String getName(){
		return getNextToEnd().getName() + " - bouncepoint";
	}

	@Override
	public void event(Long timestamp) throws Exception {
		throw new Exception("Not implemented");
	}

	@Override
	public void trainArrival(Train train, Long timestamp) throws Exception {
		train.reverse();
		getNextToEnd().trainArrival(train, timestamp);
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
}
