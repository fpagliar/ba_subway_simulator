import java.util.ArrayList;
import java.util.List;


public class Start {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Station last = new Station(null, null, 5, "last");
		BetweenStationSpace space2 = new BetweenStationSpace(null, last, 5, "entre2");
		Station middle = new Station(space2,null, 2, "middle");
		BetweenStationSpace space = new BetweenStationSpace(middle, null, 10, "entre1");
		Station first = new Station(space, null, 3, "first");

		
		Train train = new Train();
		Person p = null;

		for(int i = 0; i < 70; i++){
			p = new Person(last);
			train.passengerIn(p);
		}

		for(int i = 0; i < 10; i++){
			p = new Person(middle);
			train.passengerIn(p);
		}

		for(int i = 0; i < 50; i++){
			p = new Person(last);
			middle.personArrival(p);
		}

		first.trainArrival(train, 0);
		
		for(int i = 0; i < 10; i++){
			System.out.println("tick");
			SimulatorScheduler.getInstance().advanceTime();
		}
		
		System.out.println("Final State");

		System.out.println("last -> train:" + last.train + " #:" + last.getPersonsWaiting());
		System.out.println("middle -> train:" + middle.train + " #:" + middle.getPersonsWaiting());
		System.out.println("first -> train:" + first.train + " #:" + first.getPersonsWaiting());

		System.out.println("train -> #:" + train.getPassangers().size());
		
//		List<Person> array = new ArrayList<Person>();
//		for (int i = 0; i < 2000000; i++){
//			array.add(new Person());
//		}
//
//		System.out.println("END");
//		double d = 0;
//		while(d >= 0){
//			for(Person p: array)
//				p.event(1);
//			System.out.println(d);
//			d++;
//		}
	}
	
	private class Line {
		List<Station> stations;
		
		public Line() {
			stations = new ArrayList<Station>();
			Station congreso = new Station(null, null, 3, "Congreso de Tucuman");
			Station juramento = new Station(null, null, 3, "Juramento");
			Station josehernandez = new Station(null, null, 3, "Jose Hernandez");
			Station olleros = new Station(null, null, 3, "Olleros");
			Station carranza = new Station(null, null, 3, "Ministro Carranza");
			Station palermo = new Station(null, null, 3, "Palermo");
			Station plazaitalia = new Station(null, null, 3, "Plaza Italia");
			Station scalabrini = new Station(null, null, 3, "Scalabrini Ortiz");
			Station bulnes = new Station(null, null, 3, "Bulnes");
			Station aguero = new Station(null, null, 3, "Aguero");
			Station pueyrredon = new Station(null, null, 3, "Pueyrredon");
			Station facultad = new Station(null, null, 3, "Fctad de Medicina");
			Station callao = new Station(null, null, 3, "Callao");
			Station tribunales = new Station(null, null, 3, "Tribunales");
			Station nuevejulio = new Station(null, null, 3, "9 de Julio");
			Station catedral = new Station(null, null, 3, "Catedral");
			
			congreso.setPrevious(new BetweenStationSpace(congreso, congreso, 3, "Congreso-Cabecera"));
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
			catedral.setNext(new BetweenStationSpace(catedral, catedral, 3, "Catedral-Cabecera"));
			
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
		}
	}

}
