public abstract class SubwaySpace implements SimulatorObject {

	private float x;
	private float y;

	private SubwaySpace nextToEnd;
	private SubwaySpace nextToStart;
	private long activityDuration;
	private String name;

	protected SubwaySpace(SubwaySpace nextToStart, SubwaySpace nextToEnd, long activityDuration, String name) {
		this.nextToStart = nextToStart;
		this.nextToEnd = nextToEnd;
		this.name = name;
		this.activityDuration = activityDuration;
	}

	protected SubwaySpace(SubwaySpace nextToStart, SubwaySpace nextToEnd, long activityDuration, String name, float x, float y) {
		this(nextToStart, nextToEnd, activityDuration, name);
		this.x = x;
		this.y = y;
	}

	// Setters

	public void setNextToEnd(SubwaySpace nextToEnd) {
		this.nextToEnd = nextToEnd;
	}

	public void setNextToStart(SubwaySpace nextToStart) {
		this.nextToStart = nextToStart;
	}

	public void setActivityDuration(long activityDuration) {
		this.activityDuration = activityDuration;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Getters

	public SubwaySpace getNextToEnd() {
		return nextToEnd;
	}

	public SubwaySpace getNextToStart() {
		return nextToStart;
	}

	public long getActivityDuration() {
		return activityDuration;
	}

	public String getName() {
		return name;
	}

//	public void removeFromMap() {
//		if (!SubwayMap.getInstance().removeSpace(this))
//			System.err.println("No se pudo borrar la estacion");
//	}

	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}

	public abstract void trainArrival(Train train, Long timestamp) throws Exception;

	public abstract void setDefaultName();
}
