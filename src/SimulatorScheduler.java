import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SimulatorScheduler {

	
	Map<Double, List<SimulatorObject>> jobs;
	double actual_timestamp;

	
	public SimulatorScheduler(){
		jobs = new HashMap<Double, List<SimulatorObject>>();
		actual_timestamp = 0;
	}

	public void registerEvent(Double timestamp, SimulatorObject listener) throws Exception{
		if(timestamp < actual_timestamp){
			throw new Exception("Invalid time" + timestamp + " for object " + listener + " actual_time" + actual_timestamp);
		}
		if(! jobs.containsKey(timestamp)){
			jobs.put(timestamp, new ArrayList<SimulatorObject>());
		}
		jobs.get(timestamp).add(listener);		
	}
	
	public void advanceTime(){
		while( ! jobs.containsKey(actual_timestamp)){
			actual_timestamp++;
		}
		for(SimulatorObject obj: jobs.get(actual_timestamp))
			obj.event(actual_timestamp);
		actual_timestamp++;
	}
}
