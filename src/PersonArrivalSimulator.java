import java.util.Map;


public class PersonArrivalSimulator implements SimulatorObject {

	
	private double seed;
	private Station station;
	// Map of the style: destiny -> tickets
	private Map<Station, Integer> distribution;
	
	public PersonArrivalSimulator(double seed, Station station, Map<Station, Integer> distribution){
		if(seed <= 1)
			throw new RuntimeException("Seed has to be greater than 1");
		this.seed = seed;
		this.station = station;
		this.distribution = distribution;
	}
	
	private double getTotalTickets(){
		double ans = 0;
		for(Integer d: distribution.values())
			ans += d;
		return ans;
	}
	
	private Station getRandomDestiny(){
		double ticketNumber = Math.random() * getTotalTickets();
		for(Station key : distribution.keySet())
			if(distribution.get(key) < ticketNumber){
				return key;
			}else {
				ticketNumber -= distribution.get(key);
			}
		return null;
	}
	
	public void start(double timestamp) throws Exception{
		double next = timestamp + (int) (seed * Math.random());
		SimulatorScheduler.getInstance().registerEvent(next, this);		
	}
	
	@Override
	public void event(double timestamp) throws Exception {
		double next = timestamp + (int)(seed * Math.random()) + 1;
		SimulatorScheduler.getInstance().registerEvent(next, this);
		station.personArrival(new Person(getRandomDestiny()));
	}

}
