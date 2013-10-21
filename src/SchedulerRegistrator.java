
public class SchedulerRegistrator {

	private SimulatorObject object;
	private String description;
	
	public SchedulerRegistrator(SimulatorObject object, String description){
		this.object = object;
		this.description = description;
	}

	public SimulatorObject getObject() {
		return object;
	}

	public String getDescription() {
		return description;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SchedulerRegistrator){
			SchedulerRegistrator other = (SchedulerRegistrator) obj;
			return (other.getObject() != null) && (other.getObject().equals(this.getObject()));
		}
		return false;
	}
	
}
