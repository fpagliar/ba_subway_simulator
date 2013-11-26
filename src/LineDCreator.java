import java.util.ArrayList;
import java.util.List;


public class LineDCreator {

	private Line d;
	public void create() throws Exception {
		String[] names = { "Congreso de Tucuman", "Juramento",
			"Jose Hernandez", "Olleros", "Ministro Carranza", "Palermo",
			"Plaza Italia", "Scalabrini Ortiz", "Bulnes", "Aguero",
			"Pueyrredon", "Facultad de Medicina", "Callao", "Tribunales",
			"9 de Julio", "Catedral" };
		Integer[][] popularity = {
				//    5     6    7     8     9    10   11   12   13   14   15   16   17   18   19   20   21   22
	/* Congreso    */{10,   5,   5,    1,    1,    5,  10,  10,  20,  30,  40,  50,  80,  90,  80,  50,  50,  50},
	/* Juramento   */{10,   5,   5,    2,    2,    5,  10,  15,  20,  30,  40,  50,  90, 100,  90,  65,  50,  50},
	/* Hernandez   */{10,   7,   7,    5,    5,   10,  15,  20,  25,  30,  40,  50,  90, 100,  90,  65,  50,  50},
	/* Olleros     */{10,  10,  10,    7,    7,   10,  15,  20,  25,  30,  30,  30,  40,  60,  50,  40,  40,  40},
	/* Carranza    */{10,   7,   7,    5,    5,   10,  15,  20,  25,  30,  40,  50,  90, 100,  90,  65,  50,  50},
	/* Palermo     */{20,   20,  20,   20,  20,   30,  30,  30,  30,  30,  40,  40,  50,  50,  50,  40,  40,  40},
	/* Plaza Italia*/{20,   20,  20,   20,  20,   30,  30,  30,  30,  30,  40,  40,  50,  50,  50,  40,  40,  40},
	/* Scal Ortiz  */{25,   25,  25,   25,  25,   35,  30,  30,  30,  30,  30,  30,  40,  40,  40,  30,  30,  30},
	/* Bulnes      */{30,   30,  30,   30,  30,   40,  40,  40,  40,  30,  30,  30,  30,  30,  30,  40,  40,  40},
	/* Aguero      */{50,   50,  50,   70,  70,   50,  90,  90,  90,  90,  80,  50,  60,  60,  20,  20,  20,  20},
	/* Pueyrredon  */{50,   60,  60,   50,  50,   50,  50,  50,  40,  30,  30,  20,  20,  20,  30,  40,  50,  50},
	/* Facultad M  */{60,   60,  70,   75,  75,   60,  50,  30,  30,  30,  30,  20,  20,  20,  20,  20,  20,  20},
	/* Callao      */{60,   60,  70,   75,  75,   60,  50,  30,  30,  30,  30,  20,  20,  20,  20,  20,  20,  20},
	/* Tribunales  */{70,   70,  70,   85,  85,   70,  70,  60,  50,  50,  50,  30,  20,  20,  30,  40,  50,  50},
	/* 9 de Julio  */{70,   80,  90,   90,  90,   80,  70,  60,  50,  40,  40,  20,  20,  20,  25,  35,  40,  40},
	/* Catedral    */{75,   85,  95,   95,  95,   85,  75,  60,  50,  40,  40,  20,  10,  10,  15,  20,  20,  20}
			};
		Integer[] xaxis = { 152, 224, 297, 424, 492, 527, 551, 575, 590,
			612, 644, 679, 724, 760, 800, 841 };
		Integer[] yaxis = { 74, 74, 74, 74, 74, 74, 96, 118, 135, 155, 155,
			185, 185, 215, 257, 298 };
		Integer[] lengths = { 100, 65, 80, 95, 110, 80, 80, 65, 65, 70, 115,
			65, 90, 65, 60, 0 };
		// frecuencias, por hora, desde las 5 hasta las 23
		Integer[] frequency = {300, 300, 190, 175, 175, 210, 210, 210, 210, 210, 210, 175, 175, 195, 195, 320, 320, 320};
		d = new Line(names, xaxis, yaxis, lengths, frequency, SubwayMap.Lines.D, popularity);
		setPersonArrivalChance();
		setPopularityLineChange();
	}
	
	public void setPopularityLineChange(){
		d.lineChangePopularity.put(SubwayMap.Lines.B, 10);
		d.lineChangePopularity.put(SubwayMap.Lines.C, 20);
		d.lineChangePopularity.put(SubwayMap.Lines.A, 5);
		d.lineChangePopularity.put(SubwayMap.Lines.E, 5);
	}

	public void setCombinations(){
		Station source = Line.allStations.get(SubwayMap.Lines.D+"9 de Julio");
		List<Station> target = new ArrayList<Station>();
		target.add(Line.allStations.get(SubwayMap.Lines.C+"Diagonal Norte"));
		target.add(Line.allStations.get(SubwayMap.Lines.B+"Carlos Pellegrini"));
		Line.combinations.put(source, target);

		source = Line.allStations.get(SubwayMap.Lines.D+"Catedral");
		target = new ArrayList<Station>();
		target.add(Line.allStations.get(SubwayMap.Lines.A+"Peru"));
		target.add(Line.allStations.get(SubwayMap.Lines.E+"Bolivar"));
		Line.combinations.put(source, target);

	}
	
	public void setPersonArrivalChance() throws Exception{
		// 100 ronda en los 3s, 1 en los 300s
		// Cuanto mas grande el numero, mas rapido llegan a la estacion!
		Integer[] congreso = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				100 , 200 , 500 , 1000, 1000, 500 , 300 , 200 , 100 , 50  , 50  , 50  , 30  , 30  , 30  , 20  , 10  , 10 };
		new PersonArrivalSimulator(congreso, Line.allStations.get(SubwayMap.Lines.D+"Congreso de Tucuman")).start(0L);
		
	}
}
