import java.util.HashMap;

public class LineACreator {

	private Line a;
	public void create() throws Exception {
		String[] names = { "San Pedrito", "San Jose de Flores",
				"Carabobo", "Puan", "Primera Junta", "Acoyte",
				"Rio de Janeiro", "Castro Barros", "Loria", "Plaza Miserere",
				"Alberti-Pasco", "Congreso", "Saenz Peña", "Lima", "Piedras",
				"Peru", "Plaza de Mayo" };
		Integer[] xaxis = { 158, 206, 228, 267, 362, 440, 494, 536,
				592, 646, 668, 723, 750, 773, 816, 835, 868 };
		Integer[] yaxis = { 549, 500, 480, 440, 345, 319, 319, 319,
				319, 319, 319, 319, 319, 319, 319, 319, 319 };
		Integer[] lengths = { 90, 30, 60, 180, 100, 80, 50, 70, 60, 40,
				40, 30, 25, 40, 10, 30, 0 };
		Integer[] frequency = { 450, 450, 265, 175, 175, 265, 265, 265,
				265, 265, 265, 295, 295, 175, 175, 420, 420, 420 };
		
		a = new Line(names, xaxis, yaxis, lengths, frequency, SubwayMap.Lines.A);
//		a.getStations().get(0);
//		a.setPersonArrivalChance(personArrivalChance);
	}
	
	public void setPersonArrivalChance(){
		HashMap<Station, HashMap<Station, Integer>> personArrivalChance = new HashMap<Station, HashMap<Station, Integer>>();
		// San pedrito
		personArrivalChance.put(sanPedrito, new HashMap<Station, Integer>());
		personArrivalChance.get(sanPedrito).
		
	}
}