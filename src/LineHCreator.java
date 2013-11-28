import java.util.ArrayList;
import java.util.List;


public class LineHCreator {

	private Line h;
	public void create() throws Exception {
		String[] names = { "Hospitales", "Parque Patricios", "Caseros",
			"Inclan", "Humberto 1", "Venezuela", "Once", "Corrientes" };
		Integer[][] popularity = {
				//    5     6    7     8     9    10   11   12   13   14   15   16   17   18   19   20   21   22
	/* Hospitales  */{10,   5,   5,    1,    1,    5,  10,  10,  20,  30,  40,  50,  80,  90,  80,  50,  50,  50},
	/* Patricios   */{10,  10,  10,    7,    7,   10,  15,  20,  25,  30,  30,  30,  40,  60,  50,  40,  40,  40},
	/* Caseros     */{20,   20,  20,   20,  20,   30,  30,  30,  30,  30,  40,  40,  50,  50,  50,  40,  40,  40},
	/* Inclan      */{50,   50,  50,   70,  70,   50,  90,  90,  90,  90,  80,  50,  60,  60,  20,  20,  20,  20},
	/* Humberto 1  */{60,   60,  70,   75,  75,   60,  50,  30,  30,  30,  30,  20,  20,  20,  20,  20,  20,  20},
	/* Venezuela   */{70,   70,  70,   85,  85,   70,  70,  60,  50,  50,  50,  30,  20,  20,  30,  40,  50,  50},
	/* Once        */{70,   80,  90,   90,  90,   80,  70,  60,  50,  40,  40,  20,  20,  20,  25,  35,  40,  40},
	/* Corrientes  */{75,   85,  95,   95,  95,   85,  75,  60,  50,  40,  40,  20,  10,  10,  15,  20,  20,  20}
			};
		Integer[] xaxis = { 592, 618, 646, 646, 646, 646, 646, 646 };
		Integer[] yaxis = { 658, 640, 629, 584, 480, 403, 303, 227 };
		Integer[] lengths = { 30, 40, 50, 120, 100, 80, 30, 0 };
		Integer[] frequency = {270, 270, 215, 215, 215, 265, 265, 265, 265, 265, 265, 215, 215, 215, 215, 270, 270, 270};

		h = new Line(names, xaxis, yaxis, lengths, frequency, SubwayMap.Lines.H, popularity);
		setPersonArrivalChance();
		setPopularityLineChange();
	}

	public void setPopularityLineChange(){
		h.lineChangePopularity.put(SubwayMap.Lines.B, 30);
//		h.lineChangePopularity.put(SubwayMap.Lines.A, 10);
		h.lineChangePopularity.put(SubwayMap.Lines.E, 10);
	}

	public void setCombinations(){
		Station source = Line.allStations.get(SubwayMap.Lines.H+"Humberto 1");
		List<Station> target = new ArrayList<Station>();
		target.add(Line.allStations.get(SubwayMap.Lines.E+"Jujuy"));
		Line.combinations.put(source, target);

		source = Line.allStations.get(SubwayMap.Lines.H+"Once");
		target = new ArrayList<Station>();
		target.add(Line.allStations.get(SubwayMap.Lines.A+"Plaza Miserere"));
		Line.combinations.put(source, target);

		source = Line.allStations.get(SubwayMap.Lines.H+"Corrientes");
		target = new ArrayList<Station>();
		target.add(Line.allStations.get(SubwayMap.Lines.B+"Pueyrredon"));
		Line.combinations.put(source, target);
	}

	public void setPersonArrivalChance() throws Exception{
		// 100 ronda en los 3s, 1 en los 300s
		// Cuanto mas grande el numero, mas rapido llegan a la estacion!
		Integer[] hospitales = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  10,   10,   10,   10,   10,  10 ,  10 ,  10 , 10  , 10  , 10  , 10  , 10  , 10  , 10  , 10  , 10 };
		new PersonArrivalSimulator(hospitales, Line.allStations.get(SubwayMap.Lines.H+"Hospitales")).start(0L);
		Integer[] patricios = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  10,   10,   10,   10,   10,  10 ,  10 ,  10 , 10  , 10  , 10  , 10  , 10  , 10  , 10  , 10  , 10 };
		new PersonArrivalSimulator(patricios, Line.allStations.get(SubwayMap.Lines.H+"Parque Patricios")).start(0L);
		Integer[] caseros = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  10,   10,   10,   10,   10,  10 ,  10 ,  10 , 10  , 10  , 10  , 10  , 10  , 10  , 10  , 10  , 10 };
		new PersonArrivalSimulator(caseros, Line.allStations.get(SubwayMap.Lines.H+"Caseros")).start(0L);
		Integer[] inclan = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  10,   10,   10,   10,   10,  10 ,  10 ,  10 , 10  , 10  , 10  , 10  , 10  , 10  , 10  , 10  , 10 };
		new PersonArrivalSimulator(inclan, Line.allStations.get(SubwayMap.Lines.H+"Inclan")).start(0L);
		Integer[] humberto = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  10,   10,   10,   10,   10,  10 ,  10 ,  10 , 10  , 10  , 10  , 10  , 10  , 10  , 10  , 10  , 10 };
		new PersonArrivalSimulator(humberto, Line.allStations.get(SubwayMap.Lines.H+"Humberto 1")).start(0L);
		Integer[] venezuela = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  10,   10,   10,   10,   10,  10 ,  10 ,  10 , 10  , 10  , 10  , 10  , 10  , 10  , 10  , 10  , 10 };
		new PersonArrivalSimulator(venezuela, Line.allStations.get(SubwayMap.Lines.H+"Venezuela")).start(0L);
		Integer[] once = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  10,   10,   10,   10,   10,  10 ,  10 ,  10 , 10  , 10  , 10  , 10  , 10  , 10  , 10  , 10  , 10 };
		new PersonArrivalSimulator(once, Line.allStations.get(SubwayMap.Lines.H+"Once")).start(0L);
		Integer[] corrientes = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  10,   10,   10,   10,   10,  10 ,  10 ,  10 , 10  , 10  , 10  , 10  , 10  , 10  , 10  , 10  , 10 };
		new PersonArrivalSimulator(corrientes, Line.allStations.get(SubwayMap.Lines.H+"Corrientes")).start(0L);
		
	}
}