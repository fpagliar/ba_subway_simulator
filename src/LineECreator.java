import java.util.ArrayList;
import java.util.List;


public class LineECreator {

	private Line e;
	public void create() throws Exception {
		String[] names = { "Plaza de los Virreyes", "Varela",
			"Medalla Milagrosa", "Emilio Mitre", "Jose M. Moreno",
			"Avenida La Plata", "Boedo", "General Urquiza", "Jujuy",
			"Pichincha", "Entre Rios", "San Jose", "Independencia", "Belgrano",
			"Bolivar" };
		Integer[][] popularity = {
				//    5     6    7     8     9    10   11   12   13   14   15   16   17   18   19   20   21   22
	/* Virreyes    */{10,   5,   5,    1,    1,    5,  10,  10,  20,  30,  40,  50,  80,  90,  80,  50,  50,  50},
	/* Varela      */{10,   5,   5,    2,    2,    5,  10,  15,  20,  30,  40,  50,  90, 100,  90,  65,  50,  50},
	/* Medalla M   */{10,   7,   7,    5,    5,   10,  15,  20,  25,  30,  40,  50,  90, 100,  90,  65,  50,  50},
	/* Mitre       */{10,  10,  10,    7,    7,   10,  15,  20,  25,  30,  30,  30,  40,  60,  50,  40,  40,  40},
	/* Moreno      */{10,   7,   7,    5,    5,   10,  15,  20,  25,  30,  40,  50,  90, 100,  90,  65,  50,  50},
	/* La Plata    */{20,   20,  20,   20,  20,   30,  30,  30,  30,  30,  40,  40,  50,  50,  50,  40,  40,  40},
	/* Boedo       */{20,   20,  20,   20,  20,   30,  30,  30,  30,  30,  40,  40,  50,  50,  50,  40,  40,  40},
	/* Urquiza     */{25,   25,  25,   25,  25,   35,  30,  30,  30,  30,  30,  30,  40,  40,  40,  30,  30,  30},
	/* Jujuy       */{30,   30,  30,   30,  30,   40,  40,  40,  40,  30,  30,  30,  30,  30,  30,  40,  40,  40},
	/* Pichincha   */{50,   50,  50,   70,  70,   50,  90,  90,  90,  90,  80,  50,  60,  60,  20,  20,  20,  20},
	/* Entre Rios  */{50,   60,  60,   50,  50,   50,  50,  50,  40,  30,  30,  20,  20,  20,  30,  40,  50,  50},
	/* San Jose    */{60,   60,  70,   75,  75,   60,  50,  30,  30,  30,  30,  20,  20,  20,  20,  20,  20,  20},
	/* Independen  */{60,   60,  70,   75,  75,   60,  50,  30,  30,  30,  30,  20,  20,  20,  20,  20,  20,  20},
	/* Belgrano    */{70,   70,  70,   85,  85,   70,  70,  60,  50,  50,  50,  30,  20,  20,  30,  40,  50,  50},
	/* Bolivar     */{75,   85,  95,   95,  95,   85,  75,  60,  50,  40,  40,  20,  10,  10,  15,  20,  20,  20}
			};
		Integer[] xaxis = { 245, 265, 323, 365, 440, 494, 568, 605, 646,
			683, 722, 750, 765, 805, 842 };
		Integer[] yaxis = { 631, 590, 550, 508, 494, 494, 494, 494, 494,
			494, 494, 494, 436, 376, 340 };
		Integer[] lengths = { 50, 80, 60, 90, 50, 70, 40, 40, 40, 50, 35,
			75, 75, 50, 0 };
		Integer[] frequency = {460, 460, 355, 310, 310, 480, 480, 480, 480, 480, 480, 245, 245, 245, 245, 460, 460};

		e = new Line(names, xaxis, yaxis, lengths, frequency, SubwayMap.Lines.E, popularity);
		setPersonArrivalChance();
		setPopularityLineChange();
	}

	public void setPopularityLineChange(){
		e.lineChangePopularity.put(SubwayMap.Lines.H, 1);
		e.lineChangePopularity.put(SubwayMap.Lines.C, 20);
		e.lineChangePopularity.put(SubwayMap.Lines.A, 5);
		e.lineChangePopularity.put(SubwayMap.Lines.D, 5);
	}

	public void setCombinations(){
		Station source = Line.allStations.get(SubwayMap.Lines.E+"Jujuy");
		List<Station> target = new ArrayList<Station>();
		target.add(Line.allStations.get(SubwayMap.Lines.H+"Humberto 1"));
		Line.combinations.put(source, target);

		source = Line.allStations.get(SubwayMap.Lines.E+"Independencia");
		target = new ArrayList<Station>();
		target.add(Line.allStations.get(SubwayMap.Lines.C+"Independencia"));
		Line.combinations.put(source, target);

		source = Line.allStations.get(SubwayMap.Lines.E+"Bolivar");
		target = new ArrayList<Station>();
		target.add(Line.allStations.get(SubwayMap.Lines.A+"Peru"));
		target.add(Line.allStations.get(SubwayMap.Lines.D+"Catedral"));
		Line.combinations.put(source, target);
	}
	
	public void setPersonArrivalChance() throws Exception{
		// 100 ronda en los 3s, 1 en los 300s
		// Cuanto mas grande el numero, mas rapido llegan a la estacion!
		Integer[] vierreyes = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				100 , 200 , 500 , 1000, 1000, 500 , 300 , 200 , 100 , 50  , 50  , 50  , 30  , 30  , 30  , 20  , 10  , 10 };
		new PersonArrivalSimulator(vierreyes, Line.allStations.get(SubwayMap.Lines.E+"Plaza de los Virreyes")).start(0L);
		
	}
}
