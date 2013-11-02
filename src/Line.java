import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Line {
	List<Station> stations;
	private String[] names;
	private Integer[] xaxis;
	private Integer[] yaxis;
	private Integer[] lengths;
	private SubwayMap.Lines line;
	private Integer[] frequency;

	public Line(String[] names, Integer[] xaxis, Integer[] yaxis, Integer[] lengths, Integer[] frecuency, SubwayMap.Lines line) throws Exception {
		this.names = names;
		this.xaxis = xaxis;
		this.yaxis = yaxis;
		this.lengths = lengths;
		stations = new ArrayList<Station>();
		this.line = line;
		this.frequency = frecuency;

		loadStations();
		loadTrains();

		Map<Station, Integer> distribution;
		for (Station s : stations) {
			distribution = new HashMap<Station, Integer>();
			for (Station s2 : stations) {
				if (!s2.equals(s))
					distribution.put(s2, (int) (Math.random() * 10));
			}
			PersonArrivalSimulator a = new PersonArrivalSimulator(10L, s, distribution);
			a.start(0L);
		}
	}

	public Train.Direction getDirection(Station from, Station to) throws Exception {
		boolean passed_from = false;
		boolean exists = false;
		for (Station s : stations) {
			if (s.equals(from))
				passed_from = true;
			if (s.equals(to)) {
				exists = true;
				if (passed_from)
					return Train.Direction.TO_END;
			}
		}
		if (exists)
			return Train.Direction.TO_START;
		throw new Exception("NO DIRECTIONS FROM " + from + " TO:" + to);
	}

	private void loadStations() throws Exception {

		Station currentStation = null;
		BouncePoint bp = new BouncePoint(null, this);
		SubwaySpace previousSpace = bp;
		
		for (int i = 0; i < names.length; i++) {
			currentStation = new Station(previousSpace, null, this, 20, names[i], xaxis[i], yaxis[i]);
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
		for(int i = 0; i < frequency.length; i++)
			if(frequency[i] > 0)
				for(int j = 1; j < 3600; j+= frequency[i]){
					// although may seem obvious, it is important to keep in mind. When loading trains on START-STATION
					// the direction should be TO_END!!
					SimulatorScheduler.getInstance().registerEvent((long)j + i * 3600, new SchedulerRegistrator( new Train(getLineLetter() + ": start - " + i + ":" + j/60 + ":" + j%60, this, getStart(), Train.Direction.TO_END), "Train is going to start its trip"));
					SimulatorScheduler.getInstance().registerEvent((long)j + i * 3600, new SchedulerRegistrator( new Train(getLineLetter() + ": end - " + i + ":" + j/60 + ":" + j%60, this, getEnd(), Train.Direction.TO_START), "Train is going to start its trip"));
				}
	}
	
	public SubwayMap.Lines getLineLetter() {
		return this.line;
	}
	
	public Station getStart(){
		return stations.get(0);
	}
	
	public Station getEnd(){
		return stations.get(stations.size() - 1);
	}
}
