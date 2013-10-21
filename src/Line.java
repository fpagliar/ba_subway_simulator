import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Line {
	List<Station> stations;

	public Line() throws Exception {
		stations = new ArrayList<Station>();
		
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
	
	public Train.Direction getDirection(Station from, Station to) throws Exception{
		boolean passed_from = false;
		boolean exists = false;
		for(Station s: stations){
			if(s.equals(from))
				passed_from = true;
			if(s.equals(to)){
				exists = true;
				if(passed_from)
					return Train.Direction.TO_END;
			}
		}
		if(exists)
			return Train.Direction.TO_START;
		throw new Exception("NO DIRECTIONS FROM " + from + " TO:" + to);
	}
	
	public void loadStations(){

		String[] names = {"Congreso de Tucuman", "Juramento", "Jose Hernandez", "Olleros", "Ministro Carranza", "Palermo",
		                  "Plaza Italia", "Scalabrini Ortiz", "Bulnes", "Aguero", "Pueyrredon", "Facultad de Medicina", "Callao",
		                  "Tribunales", "9 de Julio", "Catedral"};
		
		Integer[] xaxis = {152, 224, 297, 424, 492, 527, 551, 575, 590, 612, 644, 679, 724, 760, 800, 841};
		Integer[] yaxis = {74, 74, 74, 74, 74, 74, 96, 118, 135, 155, 155, 185, 185, 215, 257, 298};
		Station currentStation = null;
		SubwaySpace previousSpace = new BouncePoint(null);

		for(int i = 0; i < names.length; i++){
			currentStation = new Station(previousSpace, null, this, 1 + (long)(Math.random()*10), names[i]);
			currentStation.x = xaxis[i];
			currentStation.y = yaxis[i];
			if(previousSpace != null)
				previousSpace.setNextToEnd(currentStation);
			stations.add(currentStation);
			previousSpace = new BetweenStationSpace(currentStation, null, 1 + (long)(Math.random()*10), null);
			currentStation.setNextToEnd(previousSpace);
		}

		currentStation.setNextToEnd(new BouncePoint(currentStation));
		
		for(Station s: stations)
			s.getNextToEnd().setDefaultName();

		stations.get(0).getNextToStart().setDefaultName();
		
	}
	
}
