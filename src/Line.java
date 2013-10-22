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

	public Line(String[] names, Integer[] xaxis, Integer[] yaxis, Integer[] lengths) throws Exception {
		this.names = names;
		this.xaxis = xaxis;
		this.yaxis = yaxis;
		this.lengths = lengths;
		stations = new ArrayList<Station>();

		if(names.length != lengths.length)
			System.out.println(names[0] + " l1:" + names.length + " l2:" + lengths.length);
		loadStations();

		Map<Station, Integer> distribution;
		for (Station s : stations) {
			distribution = new HashMap<Station, Integer>();
			for (Station s2 : stations) {
				if (!s2.equals(s))
					distribution.put(s2, (int) (Math.random() * 10));
			}
			PersonArrivalSimulator a = new PersonArrivalSimulator(3L, s, distribution);
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

	public void loadStations() {

		Station currentStation = null;
		SubwaySpace previousSpace = new BouncePoint(null);

		for (int i = 0; i < names.length; i++) {
			currentStation = new Station(previousSpace, null, this, 1 + (long) (Math.random() * 10), names[i], xaxis[i], yaxis[i]);
			if (previousSpace != null)
				previousSpace.setNextToEnd(currentStation);
			stations.add(currentStation);
			previousSpace = new BetweenStationSpace(currentStation, null, (long)lengths[i], null);
			currentStation.setNextToEnd(previousSpace);
		}

		currentStation.setNextToEnd(new BouncePoint(currentStation));

		for (Station s : stations)
			s.getNextToEnd().setDefaultName();

		stations.get(0).getNextToStart().setDefaultName();

	}
}
