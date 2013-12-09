import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Line {
	private List<Station> stations;
	private String[] names;
	private Integer[] xaxis;
	private Integer[] yaxis;
	private Integer[] lengths;
	private SubwayMap.Lines line;
	private Integer[] frequency;
	private long totalHoursWaiting = 0;
	public static HashMap<SubwayMap.Lines, Line> lines = new HashMap<SubwayMap.Lines, Line>();
	public static HashMap<String, Station> allStations = new HashMap<String, Station>();
	public HashMap<SubwayMap.Lines, Integer> lineChangePopularity;
	public static HashMap<Station, List<Station>> combinations = new HashMap<Station, List<Station>>();
	public Integer[][] stationPopularity;
	
	public Line(String[] names, Integer[] xaxis, Integer[] yaxis, Integer[] lengths, Integer[] frecuency, SubwayMap.Lines line, Integer[][] stationPopularity) throws Exception {
		lines.put(line, this);
		this.names = names;
		this.xaxis = xaxis;
		this.yaxis = yaxis;
		this.lengths = lengths;
		stations = new ArrayList<Station>();
		this.line = line;
		this.frequency = frecuency;
		lineChangePopularity = new HashMap<SubwayMap.Lines, Integer>();
		this.stationPopularity = stationPopularity;

		loadStations();
		loadTrains();

//		Map<Station, Integer> distribution;
//		for (Station s : stations) {
//			distribution = new HashMap<Station, Integer>();
//			for (Station s2 : stations) {
//				if (!s2.equals(s))
//					distribution.put(s2, (int) (Math.random() * 10));
//			}
//			PersonArrivalSimulator a = new PersonArrivalSimulator(personArrivalChance[i++], s, distribution);
//			a.start(0L);
//		}
	}

	public Train.Direction getDirection(Station from, Station to) throws Exception {
		boolean passed_from = false;
		boolean exists = false;
//		if(Station start: combinations.keySet())
		Station partialDestiny = getPartialDestiny(from, to);
//		if(to.getLine().getLineLetter() != from.getLine().getLineLetter()){
//			for(Station key : combinations.keySet())
//				if(key.getLine().equals(from.getLine()))
//					for(Station s : combinations.get(key))
//						if(s.getLine().equals(to.getLine()))
//							partialDestiny = key;
//		}
			
		for (Station s : stations) {
			if (s.equals(from))
				passed_from = true;
			if (s.equals(partialDestiny)) {
				exists = true;
				if (passed_from)
					return Train.Direction.TO_END;
			}
		}
		if (exists)
			return Train.Direction.TO_START;
		throw new Exception("NO DIRECTIONS FROM " + from + " TO:" + to);
	}

	public Station getPartialDestiny(Station from, Station to){
		Station partialDestiny = to;
		if(to.getLine().getLineLetter() != from.getLine().getLineLetter()){
			for(Station key : combinations.keySet())
				if(key.getLine().equals(from.getLine()))
					for(Station s : combinations.get(key))
						if(s.getLine().equals(to.getLine()))
							partialDestiny = key;
		}
		return partialDestiny;
	}
	
	private void loadStations() throws Exception {

		Station currentStation = null;
		BouncePoint bp = new BouncePoint(null, this);
		SubwaySpace previousSpace = bp;
		
		for (int i = 0; i < names.length; i++) {
			currentStation = new Station(previousSpace, null, this, 40, names[i], xaxis[i], yaxis[i], stationPopularity[i]);
			allStations.put(this.line + currentStation.getName(), currentStation);
			if (previousSpace != null)
				previousSpace.setNextToEnd(currentStation);
			stations.add(currentStation);
			previousSpace = new BetweenStationSpace(currentStation, null, (long)lengths[i], null);
			currentStation.setNextToEnd(previousSpace);
			SubwayMap.getInstance().addStation(currentStation);
		}

		bp = new BouncePoint(currentStation, this);
		currentStation.setNextToEnd(bp);

		for (Station s : stations)
			s.getNextToEnd().setDefaultName();

		stations.get(0).getNextToStart().setDefaultName();

	}
	
	private void loadTrains() throws Exception{
		int lastTrain = 0;
		Train lastToStart = null, lastToEnd = null;
		for(int i = 0; i < frequency.length; i++)
			for(int j = getAppropiateStartJ(lastTrain, i, frequency); j < 3600; j+= frequency[i]){
				// although may seem obvious, it is important to keep in mind. When loading trains on START-STATION
				// the direction should be TO_END!!
				lastToStart = new Train(getLineLetter() + ": end - " + i + ":" + j/60 + ":" + j%60, this, getEnd(), Train.Direction.TO_START);
				lastToEnd = new Train(getLineLetter() + ": start - " + i + ":" + j/60 + ":" + j%60, this, getStart(), Train.Direction.TO_END);
						
				SimulatorScheduler.getInstance().registerEvent((long)j + i * 3600, new SchedulerRegistrator( lastToEnd, "Train is going to start its trip"));
				SimulatorScheduler.getInstance().registerEvent((long)j + i * 3600, new SchedulerRegistrator( lastToStart, "Train is going to start its trip"));
				lastTrain = j + i * 3600;
			}
		lastToStart.lastTrain();
		lastToEnd.lastTrain();
	}
	
	private int getAppropiateStartJ(int lastTrain, int i, Integer[] frequency){
		if(lastTrain == 0)
			return 1;
		return ((lastTrain + frequency[i-1]) % 3600);
	}
	
	public SubwayMap.Lines getLineLetter() {
		return this.line;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Line ){
			Line other = (Line) obj;
			return getLineLetter().equals(other.getLineLetter());
		}
		return false;
	}
	
	public Station getStart(){
		return stations.get(0);
	}
	
	public List<Station> getStations(){
		return this.stations;
	}
	
	public Station getEnd(){
		return stations.get(stations.size() - 1);
	}
	
	public int getPos(Station target){
		int i = 0;
		for(Station s: stations){
			i++;
			if(s.equals(target))
				return i;
		}
		return -1;
	}

	public long getTotalHoursWaiting() {
		return totalHoursWaiting;
	}

	public void setTotalHoursWaiting(long totalHoursWaiting) {
		this.totalHoursWaiting = totalHoursWaiting;
	}
}
