import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Start {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		SubwayMap.getInstance().start();
		Line linea = new Line();
		Station first = linea.stations.get(0);
		
		Train train = new Train();
		Person p = null;
		
		int random = 0;
		for(int i = 0; i < 70; i++){
			random = (int) (Math.random() * linea.stations.size());
			p = new Person(linea.stations.get(random));
			train.passengerIn(p);
		}
		
		first.trainArrival(train, 0);
		for(int i = 0; i < 1000; i++){
			Thread.sleep(500);
			System.out.println("tick");
			SimulatorScheduler.getInstance().advanceTime();
		}
	}
	
	private static class Line {
		List<Station> stations;

		public Line() throws Exception {
			stations = new ArrayList<Station>();
			Station congreso = new Station(null, null, 3, "Congreso de Tucuman", 152, 74, true);
			Station juramento = new Station(null, null, 3, "Juramento", 224, 74);
			Station josehernandez = new Station(null, null, 3, "Jose Hernandez", 297, 74);
			Station olleros = new Station(null, null, 3, "Olleros", 424, 74);
			Station carranza = new Station(null, null, 3, "Ministro Carranza", 492, 74);
			Station palermo = new Station(null, null, 3, "Palermo", 527, 74);
			Station plazaitalia = new Station(null, null, 3, "Plaza Italia", 551, 96);
			Station scalabrini = new Station(null, null, 3, "Scalabrini Ortiz", 575, 118);
			Station bulnes = new Station(null, null, 3, "Bulnes", 590, 135);
			Station aguero = new Station(null, null, 3, "Aguero", 612, 155);
			Station pueyrredon = new Station(null, null, 3, "Pueyrredon", 644, 155);
			Station facultad = new Station(null, null, 3, "Fctad de Medicina", 679, 185);
			Station callao = new Station(null, null, 3, "Callao", 724, 185);
			Station tribunales = new Station(null, null, 3, "Tribunales", 760, 215);
			Station nuevejulio = new Station(null, null, 3, "9 de Julio", 800, 257);
			Station catedral = new Station(null, null, 3, "Catedral", 841, 298, true);
			
			congreso.setPrevious(new BetweenStationSpace(juramento, congreso, 3, "Juramento-Congreso"));
			congreso.setNext(new BetweenStationSpace(congreso, juramento, 3, "Congreso-Juramento"));
			juramento.setPrevious(new BetweenStationSpace(congreso, juramento, 3, "Congreso-Juramento"));
			juramento.setNext(new BetweenStationSpace(juramento, josehernandez, 3, "Juramento-Jose Hernandez"));
			josehernandez.setPrevious(new BetweenStationSpace(juramento, josehernandez, 3, "Juramento-Jose Hernandez"));
			josehernandez.setNext(new BetweenStationSpace(josehernandez, olleros, 3, "Jose Hernandez-Olleros"));
			olleros.setPrevious(new BetweenStationSpace(josehernandez, olleros, 3, "Jose Hernandez-Olleros"));
			olleros.setNext(new BetweenStationSpace(olleros, carranza, 3, "Olleros-Ministro Carranza"));
			carranza.setPrevious(new BetweenStationSpace(olleros, carranza, 3, "Olleros-Ministro Carranza"));
			carranza.setNext(new BetweenStationSpace(carranza, palermo, 3, "Ministro Carranza-Palermo"));
			palermo.setPrevious(new BetweenStationSpace(carranza, palermo, 3, "Ministro Carranza-Palermo"));
			palermo.setNext(new BetweenStationSpace(palermo, plazaitalia, 3, "Palermo-Plaza Italia"));
			plazaitalia.setPrevious(new BetweenStationSpace(palermo, plazaitalia, 3, "Palermo-Plaza Italia"));
			plazaitalia.setNext(new BetweenStationSpace(plazaitalia, scalabrini, 3, "Plaza Italia-Scalabrini"));
			scalabrini.setPrevious(new BetweenStationSpace(plazaitalia, scalabrini, 3, "Plaza Italia-Scalabrini"));
			scalabrini.setNext(new BetweenStationSpace(scalabrini, bulnes, 3, "Scalabrini-Bulnes"));
			bulnes.setPrevious(new BetweenStationSpace(scalabrini, bulnes, 3, "Scalabrini-Bulnes"));
			bulnes.setNext(new BetweenStationSpace(bulnes, aguero, 3, "Bulnes-Aguero"));
			aguero.setPrevious(new BetweenStationSpace(bulnes, aguero, 3, "Bulnes-Aguero"));
			aguero.setNext(new BetweenStationSpace(aguero, pueyrredon, 3, "Aguero-Pueyrredon"));
			pueyrredon.setPrevious(new BetweenStationSpace(aguero, pueyrredon, 3, "Aguero-Pueyrredon"));
			pueyrredon.setNext(new BetweenStationSpace(pueyrredon, facultad, 3, "Pueyrredon-Facultad"));
			facultad.setPrevious(new BetweenStationSpace(pueyrredon, facultad, 3, "Pueyrredon-Facultad"));
			facultad.setNext(new BetweenStationSpace(facultad, callao, 3, "Facultad-Callao"));
			callao.setPrevious(new BetweenStationSpace(facultad, callao, 3, "Facultad-Callao"));
			callao.setNext(new BetweenStationSpace(callao, tribunales, 3, "Callao-Tribunales"));
			tribunales.setPrevious(new BetweenStationSpace(callao, tribunales, 3, "Callao-Tribunales"));
			tribunales.setNext(new BetweenStationSpace(tribunales, nuevejulio, 3, "Tribunales-9 de Julio"));
			nuevejulio.setPrevious(new BetweenStationSpace(tribunales, nuevejulio, 3, "Tribunales-9 de Julio"));
			nuevejulio.setNext(new BetweenStationSpace(nuevejulio, catedral, 3, "9 de Julio-Catedral"));
			catedral.setPrevious(new BetweenStationSpace(nuevejulio, catedral, 3, "9 de Julio-Catedral"));
			catedral.setNext(new BetweenStationSpace(catedral, nuevejulio, 3, "Catedral-9 de Julio"));
			
			stations.add(congreso);
			stations.add(juramento);
			stations.add(josehernandez);
			stations.add(olleros);
			stations.add(carranza);
			stations.add(palermo);
			stations.add(plazaitalia);
			stations.add(scalabrini);
			stations.add(bulnes);
			stations.add(aguero);
			stations.add(pueyrredon);
			stations.add(facultad);
			stations.add(callao);
			stations.add(tribunales);
			stations.add(nuevejulio);
			stations.add(catedral);
			
			
			Map<Station, Integer> distribution;
			for(Station s: stations) {
				distribution = new HashMap<Station, Integer>();
				for(Station s2: stations){
					if(!s2.equals(s))
						distribution.put(s2, (int)(Math.random() * 10) );
				}
				PersonArrivalSimulator a = new PersonArrivalSimulator(3, s, distribution);
				a.start(0.0);
			}
			
		}
	}

}
