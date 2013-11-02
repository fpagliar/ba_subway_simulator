import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulatorScheduler {

	private Map<Long, List<SchedulerRegistrator>> jobs;
	// The smallest time unit represented is 1s
	private long actual_timestamp;
	private static SimulatorScheduler instance = new SimulatorScheduler();

	private SimulatorScheduler() {
		jobs = new HashMap<Long, List<SchedulerRegistrator>>(1000);
		actual_timestamp = 0;
	}

	public static SimulatorScheduler getInstance() {
		return instance;
	}

	public long getTime(){
		return actual_timestamp;
	}
	
	public void registerEvent(Long timestamp, SchedulerRegistrator listener) throws Exception {
		if(timestamp == actual_timestamp)
			throw new Exception("Impossible to register event in the same moment! time:" + timestamp + " listener:" + listener);

		if (timestamp < actual_timestamp)
			throw new Exception("Invalid time" + timestamp + " for object " + listener + " actual_time" + actual_timestamp);

		if (!jobs.containsKey(timestamp))
			jobs.put(timestamp, new ArrayList<SchedulerRegistrator>());

		// To avoid registering the same event twice (for ex a train leaving on each direction at the same time)
		if(! jobs.get(timestamp).contains(listener))
			jobs.get(timestamp).add(listener);
	}
	
	public void deleteEvent(Long timestamp, SimulatorObject listener){
		List<SchedulerRegistrator> list = jobs.get(timestamp);
		if(list != null)
			list.remove(listener);
	}

	public void advanceTime() throws Exception {
		SubwayMap.getInstance().render();
		if(jobs.isEmpty())
			return;
		
		while ( !jobs.containsKey(actual_timestamp)){
			actual_timestamp++;
		}
		
		for (SchedulerRegistrator obj : jobs.get(actual_timestamp)){
			obj.getObject().event(actual_timestamp);
		}

//		System.out.println(actual_timestamp);
		System.out.println("TIME - " + actual_timestamp/3600 + ":" + (actual_timestamp%3600)/60 + ":" + ((actual_timestamp%3600)%60));
		
		jobs.remove(actual_timestamp);
		actual_timestamp++;
	}
}
