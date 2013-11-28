import java.util.ArrayList;
import java.util.List;


public class LineCCreator {

	private Line c;
	public void create() throws Exception {
		String[] names = { "Retiro", "San Martin", "Lavalle",
			"Diagonal Norte", "Avenida de Mayo", "Moreno", "Independencia",
			"San Juan", "Constitucion" };
		Integer[][] popularity = {
				//    5     6    7     8     9    10   11   12   13   14   15   16   17   18   19   20   21   22
	/* Retiro      */{10,   5,   5,    1,    1,    5,  10,  10,  20,  30,  40,  50,  80,  90,  80,  50,  50,  50},
	/* San Martin  */{10,  10,  10,    7,    7,   10,  15,  20,  25,  30,  30,  30,  40,  60,  50,  40,  40,  40},
	/* Lavalle     */{20,   20,  20,   20,  20,   30,  30,  30,  30,  30,  40,  40,  50,  50,  50,  40,  40,  40},
	/* Diagonal N  */{50,   50,  50,   70,  70,   50,  90,  90,  90,  90,  80,  50,  60,  60,  20,  20,  20,  20},
	/* AvenidadeM  */{60,   60,  70,   75,  75,   60,  50,  30,  30,  30,  30,  20,  20,  20,  20,  20,  20,  20},
	/* Moreno      */{70,   70,  70,   85,  85,   70,  70,  60,  50,  50,  50,  30,  20,  20,  30,  40,  50,  50},
	/* Independen  */{70,   80,  90,   90,  90,   80,  70,  60,  50,  40,  40,  20,  20,  20,  25,  35,  40,  40},
	/* San Juan    */{75,   85,  95,   95,  95,   85,  75,  60,  50,  40,  40,  20,  15,  15,  20,  30,  30,  30},
	/* Constitucion*/{75,   85,  95,   95,  95,   85,  75,  60,  50,  40,  40,  20,  10,  10,  15,  20,  20,  20}
			};
		Integer[] xaxis = { 870, 844, 821, 812, 791, 791, 791, 791, 791 };
		Integer[] yaxis = { 137, 163, 222, 270, 319, 359, 436, 493, 575 };
		Integer[] lengths = { 40, 70, 60, 80, 40, 100, 80, 100, 0 };
		Integer[] frequency = {270, 270, 215, 215, 215, 265, 265, 265, 265, 265, 265, 215, 215, 215, 215, 270, 270, 270};		
		c = new Line(names, xaxis, yaxis, lengths, frequency, SubwayMap.Lines.C, popularity);
		setPersonArrivalChance();
		setPopularityLineChange();
	}

	public void setPopularityLineChange(){
		c.lineChangePopularity.put(SubwayMap.Lines.D, 10);
		c.lineChangePopularity.put(SubwayMap.Lines.B, 10);
		c.lineChangePopularity.put(SubwayMap.Lines.A, 10);
		c.lineChangePopularity.put(SubwayMap.Lines.E, 5);
	}

	public void setCombinations(){
		Station source = Line.allStations.get(SubwayMap.Lines.C+"Diagonal Norte");
		List<Station> target = new ArrayList<Station>();
		target.add(Line.allStations.get(SubwayMap.Lines.D+"9 de Julio"));
		target.add(Line.allStations.get(SubwayMap.Lines.B+"Carlos Pellegrini"));
		Line.combinations.put(source, target);

		source = Line.allStations.get(SubwayMap.Lines.C+"Avenida de Mayo");
		target = new ArrayList<Station>();
		target.add(Line.allStations.get(SubwayMap.Lines.A+"Lima"));
		Line.combinations.put(source, target);

		source = Line.allStations.get(SubwayMap.Lines.C+"Independencia");
		target = new ArrayList<Station>();
		target.add(Line.allStations.get(SubwayMap.Lines.E+"Independencia"));
		Line.combinations.put(source, target);
	}
	
	public void setPersonArrivalChance() throws Exception{
//		// 100 ronda en los 3s, 1 en los 300s
//		// Cuanto mas grande el numero, mas rapido llegan a la estacion!
		Integer[] retiro = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
			    40 ,  50 ,  70 ,  90,   90,   80 ,  50 ,  40 ,  30 ,  20  , 15  , 15  , 15  , 15  , 10  , 20  , 20  , 10 };
		new PersonArrivalSimulator(retiro, Line.allStations.get(SubwayMap.Lines.C+"Retiro")).start(0L);
//		
		Integer[] sanMartin = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				40 ,  40 ,  40 ,  40,   40,   40 ,  40 ,  40 ,  40 ,  40  , 35  , 25  , 45  , 40  , 45  , 40  , 40  , 10 };
		new PersonArrivalSimulator(sanMartin, Line.allStations.get(SubwayMap.Lines.C+"San Martin")).start(0L);
//
		Integer[] lavalle = {
//			    5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
			    45 ,  35 ,  55 ,   40,   40,  45 ,  50 ,  40 ,  40 ,  40  , 50  , 35  , 25  , 15  , 10  , 20  , 20  , 10 };
		new PersonArrivalSimulator(lavalle, Line.allStations.get(SubwayMap.Lines.C+"Lavalle")).start(0L);
//
		Integer[] diagonalNorte = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				25 ,  40 ,  35 ,  50,   50,   35 ,  40 ,  40 ,  40 ,  40  , 30  , 35  , 25  , 15  , 10  , 20  , 20  , 10 };
		new PersonArrivalSimulator(diagonalNorte, Line.allStations.get(SubwayMap.Lines.C+"Diagonal Norte")).start(0L);
//
		Integer[] avenidaDeMayo = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				20 ,  35 ,  40 ,   40,   40,  25 ,  40 ,  40 ,  40 ,  40  , 30  , 35  , 25  , 45  , 40  , 20  , 20  , 10 };
		new PersonArrivalSimulator(avenidaDeMayo, Line.allStations.get(SubwayMap.Lines.C+"Avenida de Mayo")).start(0L);
//
		Integer[] moreno = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				20 ,  35 ,  45 ,   40,   40,  40 ,  40 ,  40 ,  40 ,  40  , 40  , 40  , 30  , 30  , 30  , 20  , 10  , 10 };
		new PersonArrivalSimulator(moreno, Line.allStations.get(SubwayMap.Lines.C+"Moreno")).start(0L);
//	
		Integer[] independencia = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  25 ,  40 ,   35,   35,  20 ,  40 ,  40 ,  40 ,  40  , 40  , 40  , 30  , 30  , 30  , 20  , 10  , 10 };
		new PersonArrivalSimulator(independencia, Line.allStations.get(SubwayMap.Lines.C+"Independencia")).start(0L);
//
		Integer[] sanJuan = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  25 ,  45 ,   40,   32,  20 ,  40 ,  40 ,  40 ,  40  , 40  , 40  , 30  , 35  , 35  , 20  , 10  , 10 };
		new PersonArrivalSimulator(sanJuan, Line.allStations.get(SubwayMap.Lines.C+"San Juan")).start(0L);
//
		Integer[] constitucion = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  25 ,  45 ,   45,   45,  20 ,  40 ,  40 ,  40 ,  40  , 40  , 30 ,  30,   30,   30,  20 , 20  , 10 };
		new PersonArrivalSimulator(constitucion, Line.allStations.get(SubwayMap.Lines.C+"Constitucion")).start(0L);
	}
}
