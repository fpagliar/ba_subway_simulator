import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulatorScheduler {

	private Map<Double, List<SimulatorObject>> jobs;
	private double actual_timestamp;
	private static SimulatorScheduler instance = new SimulatorScheduler();

	private SimulatorScheduler() {
		jobs = new HashMap<Double, List<SimulatorObject>>();
		actual_timestamp = 0;
	}

	public static SimulatorScheduler getInstance() {
		return instance;
	}

	public void registerEvent(Double timestamp, SimulatorObject listener)
			throws Exception {
		if (timestamp < actual_timestamp) {
			throw new Exception("Invalid time" + timestamp + " for object "
					+ listener + " actual_time" + actual_timestamp);
		}
		if (!jobs.containsKey(timestamp)) {
			jobs.put(timestamp, new ArrayList<SimulatorObject>());
		}
		jobs.get(timestamp).add(listener);
	}
	
	public void deleteEvent(Double timestamp, SimulatorObject listener){
		List<SimulatorObject> list = jobs.get(timestamp);
		if(list != null)
			list.remove(listener);
	}

	public void advanceTime() throws Exception {
		if(jobs.isEmpty())
			return;
		while ( !jobs.containsKey(actual_timestamp)) {
			actual_timestamp++;
		}
		for (SimulatorObject obj : jobs.get(actual_timestamp))
			obj.event(actual_timestamp);
		jobs.remove(actual_timestamp);
		actual_timestamp++;
	}
}
