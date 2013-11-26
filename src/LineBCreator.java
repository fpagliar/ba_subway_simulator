import java.util.ArrayList;
import java.util.List;


public class LineBCreator {

	private Line b;
	public void create() throws Exception {
		String[] names = { "Juan Manuel de Rosas", "Echeverria",
			"Los Incas", "Tronador", "Lacroze", "Dorrego", "Malabia",
			"Angel Gallardo", "Medrano", "Carlos Gardel", "Pueyrredon",
			"Pasteur", "Callao", "Uruguay", "Carlos Pellegrini", "Florida",
			"Leandro N. Alem" };
		Integer[][] popularity = {
				//    5     6    7     8     9    10   11   12   13   14   15   16   17   18   19   20   21   22
	/* Rosas       */{10,   5,   5,    1,    1,    5,  10,  10,  20,  30,  40,  50,  80,  90,  80,  50,  50,  50},
	/* Echeverria  */{10,   5,   5,    2,    2,    5,  10,  15,  20,  30,  40,  50,  90, 100,  90,  65,  50,  50},
	/* Los Incas   */{10,   7,   7,    5,    5,   10,  15,  20,  25,  30,  40,  50,  90, 100,  90,  65,  50,  50},
	/* Tronador    */{10,  10,  10,    7,    7,   10,  15,  20,  25,  30,  30,  30,  40,  60,  50,  40,  40,  40},
	/* Lacroze     */{10,   7,   7,    5,    5,   10,  15,  20,  25,  30,  40,  50,  90, 100,  90,  65,  50,  50},
	/* Dorrego     */{20,   20,  20,   20,  20,   30,  30,  30,  30,  30,  40,  40,  50,  50,  50,  40,  40,  40},
	/* Malabia     */{20,   20,  20,   20,  20,   30,  30,  30,  30,  30,  40,  40,  50,  50,  50,  40,  40,  40},
	/* Angel Galla */{25,   25,  25,   25,  25,   35,  30,  30,  30,  30,  30,  30,  40,  40,  40,  30,  30,  30},
	/* Medrano     */{30,   30,  30,   30,  30,   40,  40,  40,  40,  30,  30,  30,  30,  30,  30,  40,  40,  40},
	/* Carlos Gar  */{50,   50,  50,   70,  70,   50,  90,  90,  90,  90,  80,  50,  60,  60,  20,  20,  20,  20},
	/* Pueyrredon  */{50,   60,  60,   50,  50,   50,  50,  50,  40,  30,  30,  20,  20,  20,  30,  40,  50,  50},
	/* Pasteur     */{60,   60,  70,   75,  75,   60,  50,  30,  30,  30,  30,  20,  20,  20,  20,  20,  20,  20},
	/* Callao      */{60,   60,  70,   75,  75,   60,  50,  30,  30,  30,  30,  20,  20,  20,  20,  20,  20,  20},
	/* Uruguay     */{70,   70,  70,   85,  85,   70,  70,  60,  50,  50,  50,  30,  20,  20,  30,  40,  50,  50},
	/* Carlos Pele */{70,   80,  90,   90,  90,   80,  70,  60,  50,  40,  40,  20,  20,  20,  25,  35,  40,  40},
	/* Florida     */{75,   85,  95,   95,  95,   85,  75,  60,  50,  40,  40,  20,  15,  15,  20,  30,  30,  30},
	/* Alem        */{75,   85,  95,   95,  95,   85,  75,  60,  50,  40,  40,  20,  10,  10,  15,  20,  20,  20}
			};
		
		Integer[] xaxis = { 98, 155, 220, 285, 318, 395, 470, 504, 545,
			602, 646, 685, 723, 750, 800, 840, 869 };
		Integer[] yaxis = { 165, 165, 165, 165, 165, 165, 207, 242, 243,
			243, 243, 243, 243, 243, 243, 243, 243 };
		Integer[] frequency = {360, 360, 245, 195, 195, 270, 270, 270, 270, 270, 270, 195, 195, 205, 205, 330, 330, 330};
		Integer[] lengths = { 70, 85, 100, 30, 120, 150, 80, 60, 45, 55,
			60, 40, 30, 70, 60, 50, 0 };
		
		b = new Line(names, xaxis, yaxis, lengths, frequency, SubwayMap.Lines.B, popularity);
		setPersonArrivalChance();
		setPopularityLineChange();
	}

	public void setPopularityLineChange(){
		b.lineChangePopularity.put(SubwayMap.Lines.C, 20);
		b.lineChangePopularity.put(SubwayMap.Lines.H, 1);
		b.lineChangePopularity.put(SubwayMap.Lines.D, 5);
	}

	public void setCombinations(){
		Station source = Line.allStations.get(SubwayMap.Lines.B+"Pueyrredon");
		List<Station> target = new ArrayList<Station>();
		target.add(Line.allStations.get(SubwayMap.Lines.H+"Corrientes"));
		Line.combinations.put(source, target);

		source = Line.allStations.get(SubwayMap.Lines.B+"Carlos Pellegrini");
		target = new ArrayList<Station>();
		target.add(Line.allStations.get(SubwayMap.Lines.D+"9 de Julio"));
		target.add(Line.allStations.get(SubwayMap.Lines.C+"Diagonal Norte"));
		Line.combinations.put(source, target);
	}

	public void setPersonArrivalChance() throws Exception{
		// 100 ronda en los 3s, 1 en los 300s
		// Cuanto mas grande el numero, mas rapido llegan a la estacion!
		Integer[] rosas = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				100 , 200 , 500 , 1000, 1000, 500 , 300 , 200 , 100 , 50  , 50  , 50  , 30  , 30  , 30  , 20  , 10  , 10 };
		new PersonArrivalSimulator(rosas, Line.allStations.get(SubwayMap.Lines.B+"Juan Manuel de Rosas")).start(0L);
		
	}
}
