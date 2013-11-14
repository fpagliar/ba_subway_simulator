import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulatorScheduler {

	private Map<Long, List<SchedulerRegistrator>> jobs;
	// The smallest time unit represented is 1s
	private long actual_timestamp;
	private static SimulatorScheduler instance = new SimulatorScheduler();
	private long operations;

	private SimulatorScheduler() {
		jobs = new HashMap<Long, List<SchedulerRegistrator>>(1000);
		actual_timestamp = 0;
		operations = 0;
	}

	public static SimulatorScheduler getInstance() {
		return instance;
	}

	public long getTime(){
		return actual_timestamp;
	}
	
	public void registerEvent(Long timestamp, SchedulerRegistrator listener) throws Exception {
		operations++;
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
		if(actual_timestamp % 2 == 0){
			SubwayMap.getInstance().setTime(actual_timestamp + 5 * 3600);
			SubwayMap.getInstance().render();			
		}
		
		for(Line l : Start.lines) {
			for(Station s : l.getStations()) {
				l.setTotalHoursWaiting(l.getTotalHoursWaiting() + s.getPersonsWaiting(Train.Direction.TO_END));
				l.setTotalHoursWaiting(l.getTotalHoursWaiting() + s.getPersonsWaiting(Train.Direction.TO_START));
			}
		}
		
		if(jobs.isEmpty())
			return;
		
		while ( !jobs.containsKey(actual_timestamp)){
			actual_timestamp++;
		}
		
		if(actual_timestamp > 61200){
			System.out.println("TIME - " + (5 + actual_timestamp/3600) + ":" + (actual_timestamp%3600)/60 + ":" + ((actual_timestamp%3600)%60));
			System.out.println("SIMULATION ENDED");
			System.out.println("Operations:" + operations);
			System.exit(0);
		}
			
		
		for (SchedulerRegistrator obj : jobs.get(actual_timestamp)){
			obj.getObject().event(actual_timestamp);
		}

//		System.out.println(actual_timestamp);
//		System.out.println("TIME - " + actual_timestamp/3600 + ":" + (actual_timestamp%3600)/60 + ":" + ((actual_timestamp%3600)%60));
		
		jobs.remove(actual_timestamp);
		actual_timestamp++;
	}
}
