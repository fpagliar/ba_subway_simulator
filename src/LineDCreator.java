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
	/* Congreso    */{10,   5,   5,    1,    1,    5,  10,  10,  20,  30,  40,  50, 200, 200, 200,  50,  50,  50},
	/* Juramento   */{10,   5,   5,    2,    2,    5,  10,  15,  20,  30,  40,  50,  90, 200, 200,  65,  50,  50},
	/* Hernandez   */{10,   7,   7,    5,    5,   10,  15,  20,  25,  30,  40,  50,  90, 200, 200,  65,  50,  50},
	/* Olleros     */{10,  10,  10,    7,    7,   10,  15,  20,  25,  30,  30,  30,  40, 100, 100,  40,  40,  40},
	/* Carranza    */{10,   7,   7,    5,    5,   10,  15,  20,  25,  30,  40,  50,  90, 100, 100,  65,  50,  50},
	/* Palermo     */{20,   20,  20,   20,  20,   30,  30,  30,  30,  30,  40,  40,  50,  50,  50,  40,  40,  40},
	/* Plaza Italia*/{20,   20,  20,   20,  20,   30,  30,  30,  30,  30,  40,  40,  50,  50,  50,  40,  40,  40},
	/* Scal Ortiz  */{25,   25,  25,   25,  25,   35,  30,  30,  30,  30,  30,  30,  40,  30,  30,  30,  30,  30},
	/* Bulnes      */{30,   30,  30,   30,  30,   40,  40,  40,  40,  30,  30,  30,  30,  20,  20,  40,  40,  40},
	/* Aguero      */{50,   50,  50,   70,  70,   50,  90,  90,  90,  90,  80,  50,  60,  20,  20,  20,  20,  20},
	/* Pueyrredon  */{50,   60,  60,   50,  50,   50,  50,  50,  40,  30,  30,  20,  20,  20,  20,  40,  50,  50},
	/* Facultad M  */{60,   60,  70,   75,  75,   60,  50,  30,  30,  30,  30,  20,  20,  20,  20,  20,  20,  20},
	/* Callao      */{60,   60,  70,   75,  75,   60,  50,  30,  30,  30,  30,  20,  20,  20,  20,  20,  20,  20},
	/* Tribunales  */{70,   70,  70,   85,  85,   70,  70,  60,  50,  50,  50,  30,  20,  20,  20,  40,  50,  50},
	/* 9 de Julio  */{70,   80,  90,   90,  90,   80,  70,  60,  50,  40,  40,  20,  20,  10,  15,  35,  40,  40},
	/* Catedral    */{75,   85,  95,   95,  95,   85,  75,  60,  50,  40,  40,  20,  10,  10,  15,  20,  20,  20}
			};
		Integer[] xaxis = { 152, 224, 297, 424, 492, 527, 551, 575, 590,
			612, 644, 679, 724, 760, 800, 841 };
		Integer[] yaxis = { 74, 74, 74, 74, 74, 74, 96, 118, 135, 155, 155,
			185, 185, 215, 257, 298 };
		Integer[] lengths = { 100, 65, 80, 95, 110, 80, 80, 65, 65, 70, 115,
			65, 90, 65, 60, 0 };
		// frecuencias, por hora, desde las 5 hasta las 23
		// 0 trenes
		Integer[] frequency = {300, 300, 190, 175, 175, 210, 210, 210, 210, 210, 210, 175, 175, 195, 195, 320, 320, 320};
		// 1 tren
//		Integer[] frequency = {300, 300, 190, 160, 160, 210, 210, 210, 210, 210, 210, 160, 160, 195, 195, 320, 320, 320};
		// 2 trenes
//		Integer[] frequency = {300, 300, 190, 147, 147, 210, 210, 210, 210, 210, 210, 147, 147, 195, 195, 320, 320, 320};
		// 3 trenes
//		Integer[] frequency = {300, 300, 190, 136, 136, 210, 210, 210, 210, 210, 210, 136, 136, 195, 195, 320, 320, 320};
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
//		Integer[] congreso = {
////				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
//				100 , 200 , 500 , 1000, 1000, 500 , 300 , 200 , 100 , 50  , 50  , 50  , 30  , 30  , 30  , 20  , 10  , 10 };
//		new PersonArrivalSimulator(congreso, Line.allStations.get(SubwayMap.Lines.D+"Congreso de Tucuman")).start(0L);
		// 100 ronda en los 3s, 1 en los 300s
		// Cuanto mas grande el numero, mas rapido llegan a la estacion!
		Integer[] congreso = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
			    65 ,  70 , 100 , 9000, 9000, 200 ,  80 ,  40 ,  40 ,  40  , 35  , 35  , 25  , 15  , 10  , 20  , 20  , 10 };
		new PersonArrivalSimulator(congreso, Line.allStations.get(SubwayMap.Lines.D+"Congreso de Tucuman")).start(0L);
		
		Integer[] juramento = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				50 ,  45 ,  85 ,  5000, 5000, 100 , 80 ,  40 ,  40 ,  40  , 35  , 25  , 15  , 10  , 15  , 20  , 20  , 10 };
		new PersonArrivalSimulator(juramento, Line.allStations.get(SubwayMap.Lines.D+"Juramento")).start(0L);

		Integer[] joseHernandez = {
//			    5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
			    45 ,  30 ,  80 , 1000, 1000,  55 ,  50 ,  40 ,  40 ,  40  , 50  , 35  , 25  , 15  , 10  , 20  , 20  , 10 };
		new PersonArrivalSimulator(joseHernandez, Line.allStations.get(SubwayMap.Lines.D+"Jose Hernandez")).start(0L);

		Integer[] olleros = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				25 ,  30 ,  75 ,  500,  400,  35 ,  40 ,  40 ,  40 ,  40  , 50  , 35  , 25  , 15  , 10  , 20  , 20  , 10 };
		new PersonArrivalSimulator(olleros, Line.allStations.get(SubwayMap.Lines.D+"Olleros")).start(0L);

		Integer[] carranza = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				20 ,  25 ,  70 ,  80,  80,  25 ,  40 ,  40 ,  40 ,  40  , 50  , 35  , 25  , 15  , 10  , 20  , 20  , 10 };
		new PersonArrivalSimulator(carranza, Line.allStations.get(SubwayMap.Lines.D+"Ministro Carranza")).start(0L);

		Integer[] palermo = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  20 ,  50 ,   70,   70,  20 ,  40 ,  40 ,  40 ,  40  , 50  , 50  , 30  , 30  , 30  , 20  , 10  , 10 };
		new PersonArrivalSimulator(palermo, Line.allStations.get(SubwayMap.Lines.D+"Palermo")).start(0L);
	
		Integer[] plazaItalia = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  20 ,  25 ,   30,   30,  20 ,  40 ,  40 ,  40 ,  40  , 50  , 50  , 30  , 30  , 30  , 20  , 10  , 10 };
		new PersonArrivalSimulator(plazaItalia, Line.allStations.get(SubwayMap.Lines.D+"Plaza Italia")).start(0L);

		Integer[] scalabriniOrtiz = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  15 ,  25 ,   25,   25,  20 ,  40 ,  40 ,  40 ,  40  , 50  , 50  , 30  , 80  , 80  , 20  , 10  , 10 };
		new PersonArrivalSimulator(scalabriniOrtiz, Line.allStations.get(SubwayMap.Lines.D+"Scalabrini Ortiz")).start(0L);

		Integer[] bulnes = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  10 ,  15 ,   20,   20,  30 ,  30 ,  30 ,  30 ,  40  , 50  , 50  , 60  , 80  , 80  , 25  , 20  , 10 };
		new PersonArrivalSimulator(bulnes, Line.allStations.get(SubwayMap.Lines.D+"Bulnes")).start(0L);

		Integer[] aguero = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  15 ,  25 ,  30,   30,   40 ,  40 ,  30 ,  30 ,  40  , 40  , 40  , 80  , 90  , 90  , 25  , 20  , 10 };
		new PersonArrivalSimulator(aguero, Line.allStations.get(SubwayMap.Lines.D+"Aguero")).start(0L);

		Integer[] pueyrredon = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  15 ,  25 ,  30,   30,   40 ,  40 ,  40 ,  40 ,  40  , 40  , 40  , 80 , 150 , 150  , 30  , 20  , 10 };
		new PersonArrivalSimulator(pueyrredon, Line.allStations.get(SubwayMap.Lines.D+"Pueyrredon")).start(0L);

		Integer[] facultaddeMedicina = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  15 ,  25 ,   30,   30,  30 ,  40 ,  40 ,  40 ,  40  , 50  , 50  , 90  ,200  ,200  , 50  , 20  , 10 };
		new PersonArrivalSimulator(facultaddeMedicina, Line.allStations.get(SubwayMap.Lines.D+"Facultad de Medicina")).start(0L);

		Integer[] callao = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  15 ,  25 ,   35,   35,  20 ,  40 ,  40 ,  40 ,  40  , 50  , 50  , 100 , 500 , 500 , 80  , 30  , 10 };
		new PersonArrivalSimulator(callao, Line.allStations.get(SubwayMap.Lines.D+"Callao")).start(0L);

		Integer[] tribunales = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  15 ,  25 ,   35,   35,  20 ,  40 ,  40 ,  40 ,  40  , 45  , 55  , 200, 1000, 1000 , 80  , 50  , 10 };
		new PersonArrivalSimulator(tribunales, Line.allStations.get(SubwayMap.Lines.D+"Tribunales")).start(0L);

		Integer[] julio9de = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  15 ,  25 ,   35,   35,  20 ,  40 ,  40 ,  40 ,  40  , 50  , 50  , 500 , 5000, 5000, 80  , 50  , 10 };
		new PersonArrivalSimulator(julio9de, Line.allStations.get(SubwayMap.Lines.D+"9 de Julio")).start(0L);

		Integer[] catedral = {
//				5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
				10 ,  15 ,  25 ,   35,   35,  20 ,  40 ,  40 ,  40 ,  40  , 65  , 100 , 2000,9000 ,9000 , 200 , 80  , 10 };
		new PersonArrivalSimulator(catedral, Line.allStations.get(SubwayMap.Lines.D+"Catedral")).start(0L);
		
	}
}
