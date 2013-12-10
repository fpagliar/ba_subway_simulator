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
	/* Rosas       */{10,   5,   5,    1,    1,    5,  10,  10,  20,  30,  40,  50, 200, 200, 200,  50,  50,  50},
	/* Echeverria  */{10,   5,   5,    2,    2,    5,  10,  15,  20,  30,  40,  50,  90, 200, 200,  65,  50,  50},
	/* Los Incas   */{10,   7,   7,    5,    5,   10,  15,  20,  25,  30,  40,  50,  90, 200, 200,  65,  50,  50},
	/* Tronador    */{10,  10,  10,    7,    7,   10,  15,  20,  25,  30,  30,  30,  40, 100, 100,  40,  40,  40},
	/* Lacroze     */{10,   7,   7,    5,    5,   10,  15,  20,  25,  30,  40,  50,  90, 100, 100,  65,  50,  50},
	/* Dorrego     */{20,   20,  20,   20,  20,   30,  30,  30,  30,  30,  40,  40,  50,  50,  80,  40,  40,  40},
	/* Malabia     */{20,   20,  20,   20,  20,   30,  30,  30,  30,  30,  40,  40,  50,  50,  70,  40,  40,  40},
	/* Angel Galla */{25,   25,  25,   25,  25,   35,  30,  30,  30,  30,  30,  30,  40,  40,  60,  30,  30,  30},
	/* Medrano     */{30,   30,  30,   30,  30,   40,  40,  40,  40,  30,  30,  30,  30,  30,  30,  40,  40,  40},
	/* Carlos Gar  */{50,   50,  50,   70,  70,   50,  90,  90,  90,  90,  80,  50,  60,  60,  30,  20,  20,  20},
	/* Pueyrredon  */{50,   60,  60,   50,  50,   50,  50,  50,  40,  30,  30,  20,  20,  20,  30,  40,  50,  50},
	/* Pasteur     */{60,   60,  70,   75,  75,   60,  50,  30,  30,  30,  30,  20,  20,  20,  20,  20,  20,  20},
	/* Callao      */{60,   60,  70,   75,  75,   60,  50,  30,  30,  30,  30,  20,  20,  20,  20,  20,  20,  20},
	/* Uruguay     */{70,   70,  70,   85,  85,   70,  70,  60,  50,  50,  50,  30,  20,  20,  20,  40,  50,  50},
	/* Carlos Pele */{70,   80,  90,   90,  90,   80,  70,  60,  50,  40,  40,  20,  20,  20,  20,  35,  40,  40},
	/* Florida     */{75,   85,  95,   95,  95,   85,  75,  60,  50,  40,  40,  20,  15,  20,  20,  30,  30,  30},
	/* Alem        */{75,   85,  95,   95,  95,   85,  75,  60,  50,  40,  40,  20,  10,  10,  15,  20,  20,  20}
			};
		
		Integer[] xaxis = { 98, 155, 220, 285, 318, 395, 470, 504, 545,
			602, 646, 685, 723, 750, 800, 840, 869 };
		Integer[] yaxis = { 165, 165, 165, 165, 165, 165, 207, 242, 243,
			243, 243, 243, 243, 243, 243, 243, 243 };
		// 0 trenes
		Integer[] frequency = {360, 360, 245, 195, 195, 270, 270, 270, 270, 270, 270, 195, 195, 205, 205, 330, 330, 330};
		// 1 tren
//		Integer[] frequency = {360, 360, 245, 178, 178, 270, 270, 270, 270, 270, 270, 178, 178, 178, 205, 330, 330, 330};
		// 2 trenes
//		Integer[] frequency = {360, 360, 245, 162, 162, 270, 270, 270, 270, 270, 270, 162, 162, 165, 205, 330, 330, 330};
		// 3 trenes
//		Integer[] frequency = {360, 360, 245, 148, 148, 270, 270, 270, 270, 270, 270, 148, 148, 148, 205, 330, 330, 330};

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
			    65 ,  70 ,  80 , 9000, 9000, 200 ,  80 ,  40 ,  40 ,  40  , 35  , 35  , 25  , 15  , 10  , 20  , 20  , 10 };
		new PersonArrivalSimulator(rosas, Line.allStations.get(SubwayMap.Lines.B+"Juan Manuel de Rosas")).start(0L);
		
		Integer[] echeverria = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				50 ,  45 ,  65 ,  5000, 5000, 100 , 80 ,  40 ,  40 ,  40  , 35  , 25  , 15  , 10  , 15  , 20  , 20  , 10 };
		new PersonArrivalSimulator(echeverria, Line.allStations.get(SubwayMap.Lines.B+"Echeverria")).start(0L);

		Integer[] losIncas = {
//			    5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
			    45 ,  30 ,  55 ,  500,  500,  55 ,  50 ,  40 ,  40 ,  40  , 50  , 35  , 25  , 15  , 10  , 20  , 20  , 10 };
		new PersonArrivalSimulator(losIncas, Line.allStations.get(SubwayMap.Lines.B+"Los Incas")).start(0L);

		Integer[] tronador = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				25 ,  30 ,  35 ,  90,   90,  35 ,  40 ,  40 ,  40 ,  40  , 50  , 35  , 25  , 15  , 10  , 20  , 20  , 10 };
		new PersonArrivalSimulator(tronador, Line.allStations.get(SubwayMap.Lines.B+"Tronador")).start(0L);

		Integer[] lacroze = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				20 ,  25 ,  30 ,   60,   60,  25 ,  40 ,  40 ,  40 ,  40  , 50  , 35  , 25  , 15  , 10  , 20  , 20  , 10 };
		new PersonArrivalSimulator(lacroze, Line.allStations.get(SubwayMap.Lines.B+"Lacroze")).start(0L);

		Integer[] dorrego = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  20 ,  25 ,   40,   45,  20 ,  40 ,  40 ,  40 ,  40  , 50  , 50  , 30  , 30  , 30  , 20  , 10  , 10 };
		new PersonArrivalSimulator(dorrego, Line.allStations.get(SubwayMap.Lines.B+"Dorrego")).start(0L);
	
		Integer[] malabia = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  20 ,  25 ,   30,   35,  20 ,  40 ,  40 ,  40 ,  40  , 50  , 50  , 30  , 30  , 30  , 20  , 10  , 10 };
		new PersonArrivalSimulator(malabia, Line.allStations.get(SubwayMap.Lines.B+"Malabia")).start(0L);

		Integer[] gallardo = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  15 ,  25 ,   25,   25,  20 ,  40 ,  40 ,  40 ,  40  , 50  , 50  , 30  , 50  , 50  , 20  , 10  , 10 };
		new PersonArrivalSimulator(gallardo, Line.allStations.get(SubwayMap.Lines.B+"Angel Gallardo")).start(0L);

		Integer[] medrano = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  10 ,  23 ,   25,   25,  20 ,  40 ,  40 ,  40 ,  40  , 40  , 50  , 40  , 60  , 60  , 20  , 10  , 10 };
		new PersonArrivalSimulator(medrano, Line.allStations.get(SubwayMap.Lines.B+"Medrano")).start(0L);

		Integer[] gardel = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  10 ,  15 ,   30,   30,  30 ,  30 ,  30 ,  30 ,  40  , 50  , 50  , 40  , 40  , 60  , 25  , 20  , 10 };
		new PersonArrivalSimulator(gardel, Line.allStations.get(SubwayMap.Lines.B+"Carlos Gardel")).start(0L);

		Integer[] pueyrredon = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  15 ,  25 ,  30,   30,   40 ,  40 ,  30 ,  30 ,  40  , 40  , 40  , 50  , 50  , 50  , 25  , 20  , 10 };
		new PersonArrivalSimulator(pueyrredon, Line.allStations.get(SubwayMap.Lines.B+"Pueyrredon")).start(0L);

		Integer[] pasteur = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  15 ,  25 ,  30,   30,   40 ,  40 ,  40 ,  40 ,  40  , 40  , 40  , 50 ,  50 ,  50  , 30  , 20  , 10 };
		new PersonArrivalSimulator(pasteur, Line.allStations.get(SubwayMap.Lines.B+"Pasteur")).start(0L);

		Integer[] callao = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  15 ,  25 ,   30,   30,  30 ,  40 ,  40 ,  40 ,  40  , 50  , 50  , 80  ,75  ,75  , 45  , 20  , 10 };
		new PersonArrivalSimulator(callao, Line.allStations.get(SubwayMap.Lines.B+"Callao")).start(0L);

		Integer[] uruguay = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  15 ,  25 ,   35,   35,  20 ,  40 ,  40 ,  40 ,  40  , 50  , 50  , 90 , 90  , 80  , 60  , 30  , 10 };
		new PersonArrivalSimulator(uruguay, Line.allStations.get(SubwayMap.Lines.B+"Uruguay")).start(0L);

		Integer[] carlosPellegrini = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  15 ,  25 ,   35,   35,  20 ,  40 ,  40 ,  40 ,  40  , 45  , 55  , 100,   100,  90 , 70  , 50  , 10 };
		new PersonArrivalSimulator(carlosPellegrini, Line.allStations.get(SubwayMap.Lines.B+"Carlos Pellegrini")).start(0L);

		Integer[] florida = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  15 ,  25 ,   35,   35,  20 ,  40 ,  40 ,  40 ,  40  , 50  , 50  , 100 , 300, 300, 80  , 50  , 10 };
		new PersonArrivalSimulator(florida, Line.allStations.get(SubwayMap.Lines.B+"Florida")).start(0L);

		Integer[] alem = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  15 ,  25 ,   35,   35,  20 ,  40 ,  40 ,  40 ,  40  , 65  , 100 , 200,9000 ,9000 , 100 , 80  , 10 };
		new PersonArrivalSimulator(alem, Line.allStations.get(SubwayMap.Lines.B+"Leandro N. Alem")).start(0L);
	}
}
