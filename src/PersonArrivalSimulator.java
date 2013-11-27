

public class PersonArrivalSimulator implements SimulatorObject {
	
	private Integer[] lambda;
	private Station station;
	// Map of the style: destiny -> tickets
//	private Map<Station, Integer> destinies;
	
	public PersonArrivalSimulator(Integer[] seed, Station station){
//		if(seed <= 1)
//			throw new RuntimeException("Seed has to be greater than 1");
//		System.out.println(station.getName());
//		if(station.getName().equals("Congreso de Tucuman")){
//			for(Integer aseed: seed)
//				System.out.println(aseed);
//		}
		this.lambda = seed;
		this.station = station;
//		this.destinies = distribution;
	}
	
//	private Long getTotalTickets(){
//		Long ans = 0L;
////		for(Integer d: destinies.values())
////			ans += d;
//		return ans;
//	}
	
	private Station getRandomDestiny(long timestamp) throws Exception{
		double lineDecider = Math.random() * 100;
		int ans = 0;
		for(SubwayMap.Lines line: station.getLine().lineChangePopularity.keySet()){
			ans += station.getLine().lineChangePopularity.get(line);
			if(ans > lineDecider)
				return getRandomStation(Line.lines.get(line), timestamp);
		}
		return getRandomStation(station.getLine(), timestamp);
		
//		double ticketNumber = Math.random() * getTotalTickets();
//		for(Station key : destinies.keySet()){
//			if(0 < ticketNumber && ticketNumber < destinies.get(key)){
//				return key;
//			}else {
//				ticketNumber -= destinies.get(key);
//			}
//		}
//		System.exit(0);
//		List<Station> destinies = station.getLine().getStations();
//		Station ans;
//		do
//			ans = destinies.get((int) (Math.random() * destinies.size()));
//		while(ans.equals(station));
//		return ans;
//		throw new Exception("Random destiny failed :(");
	}
	
	public void start(Long timestamp) throws Exception {
		double time = 1 + Math.log(1 - Math.random()) / - getLambda(timestamp);
		SimulatorScheduler.getInstance().registerEvent(timestamp + (long)time, new SchedulerRegistrator(this, "person arriving to -> " + station.getName()));
	}
	
	@Override
	public void event(Long timestamp) throws Exception {
		double time = 1 + Math.log(1 - Math.random())*100 / - getLambda(timestamp);
		SimulatorScheduler.getInstance().registerEvent(timestamp + (long) time, new SchedulerRegistrator(this, "person arriving to -> " + station.getName()));
		Station destiny = getRandomDestiny((long)time);
		destiny.chosen++;
		station.personArrival(new Person(station, destiny));
	}
	
	@Override
	public String toString(){
		return station + " person arrival";
	}
	
	private int getLambda(long timestamp){
		return lambda[(int) (timestamp/3600)];
	}
	
	private Station getRandomStation(Line line, long timestamp) throws Exception{
		int totalTickets = 0;
		for(Station s: line.getStations())
			totalTickets += s.getPopularity(timestamp);
		int ticket = (int)(Math.random() * totalTickets);
		int acum = 0;
		for(Station s: line.getStations()){
			acum += s.getPopularity(timestamp);
			if(acum > ticket){
				if(s.equals(this))
					return getRandomStation(line, timestamp);
				return s;
			}
		}
		System.out.println("total:" + totalTickets);
		System.out.println("ticket:" + ticket);
		System.out.println("acum:" + acum);
		throw new Exception("Can this happen?");
//		return line.getStations().get(line.getStations().size());
	}

}
