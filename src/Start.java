import java.util.concurrent.TimeUnit;

public class Start {

	static String[] namesD = { "Congreso de Tucuman", "Juramento", "Jose Hernandez", "Olleros", "Ministro Carranza", "Palermo", "Plaza Italia",
			"Scalabrini Ortiz", "Bulnes", "Aguero", "Pueyrredon", "Facultad de Medicina", "Callao", "Tribunales", "9 de Julio", "Catedral" };
	static Integer[] xaxisD = { 152, 224, 297, 424, 492, 527, 551, 575, 590, 612, 644, 679, 724, 760, 800, 841 };
	static Integer[] yaxisD = { 74, 74, 74, 74, 74, 74, 96, 118, 135, 155, 155, 185, 185, 215, 257, 298 };
	static Integer[] lengthsD = {100, 80, 150, 80, 30, 45, 45, 30, 38, 45, 60, 60, 130, 80, 20, 0};
	
	static String[] namesB = { "Juan Manuel de Rosas", "Echeverria", "Los Incas", "Tronador", "Lacroze", "Dorrego", "Malabia", "Angel Gallardo",
			"Medrano", "Carlos Gardel", "Pueyrredon", "Pasteur", "Callao", "Uruguay", "Carlos Pellegrini", "Florida", "Leandro N. Alem" };
	static Integer[] xaxisB = { 98, 155, 220, 285, 318, 395, 470, 504, 545, 602, 646, 685, 723, 750, 800, 840, 869 };
	static Integer[] yaxisB = { 165, 165, 165, 165, 165, 165, 207, 242, 243, 243, 243, 243, 243, 243, 243, 243, 243 };
	static Integer[] lengthsB = { 70, 85, 100, 30, 120, 150, 80, 60, 45, 55, 60, 40, 30, 70, 60, 50, 0 };
	
	static String[] namesC = { "Retiro", "San Martin", "Lavalle", "Diagonal Norte", "Avenida de Mayo", "Moreno", "Independencia", "San Juan",
			"Constitucion" };
	static Integer[] xaxisC = { 870, 844, 821, 812, 791, 791, 791, 791, 791 };
	static Integer[] yaxisC = { 137, 163, 222, 270, 319, 359, 436, 493, 575 };
	static Integer[] lengthsC = { 40, 70, 60, 80, 40, 100, 80, 100, 0 };
	
	static String[] namesA = { "San Pedrito", "San Jose de Flores", "Carabobo", "Puan", "Primera Junta", "Acoyte", "Rio de Janeiro", "Castro Barros",
			"Loria", "Plaza Miserere", "Alberti-Pasco", "Congreso", "Saenz Peña", "Lima", "Piedras", "Peru", "Plaza de Mayo" };
	static Integer[] xaxisA = { 158, 206, 228, 267, 362, 440, 494, 536, 592, 646, 668, 723, 750, 773, 816, 835, 868 };
	static Integer[] yaxisA = { 549, 500, 480, 440, 345, 319, 319, 319, 319, 319, 319, 319, 319, 319, 319, 319, 319 };
	static Integer[] lengthsA = { 90, 30, 60, 180, 100, 80, 50, 70, 60, 40, 40, 30, 25, 40, 10, 30, 0 };
	
	static String[] namesE = { "Plaza de los Virreyes", "Varela", "Medalla Milagrosa", "Emilio Mitre", "Jose M. Moreno", "Avenida La Plata", "Boedo",
			"General Urquiza", "Jujuy", "Pichincha", "Entre Rios", "San Jose", "Independencia", "Belgrano", "Bolivar" };
	static Integer[] xaxisE = { 245, 265, 323, 365, 440, 494, 568, 605, 646, 683, 722, 750, 765, 805, 842 };
	static Integer[] yaxisE = { 631, 590, 550, 508, 494, 494, 494, 494, 494, 494, 494, 494, 436, 376, 340 };
	static Integer[] lengthsE = { 50, 80, 60, 90, 50, 70, 40, 40, 40, 50, 35, 75, 75, 50, 0};
	
	static String[] namesH = { "Hospitales", "Parque Patricios", "Caseros", "Inclan", "Humberto 1", "Venezuela", "Once", "Corrientes" };
	static Integer[] xaxisH = {592, 618, 646, 646, 646, 646, 646, 646};
	static Integer[] yaxisH = {658, 640, 629, 584, 480, 403, 303, 227};
	static Integer[] lengthsH = { 30, 40, 50, 120, 100, 80, 30, 0};
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		SubwayMap.getInstance().start();
				
		Line[] lines = {new Line(namesA, xaxisA, yaxisA, lengthsA), new Line(namesB, xaxisB, yaxisB, lengthsB), 
				new Line(namesC, xaxisC, yaxisC, lengthsC), new Line(namesD, xaxisD, yaxisD, lengthsD), 
				new Line(namesE, xaxisE, yaxisE, lengthsE), new Line(namesH, xaxisH, yaxisH, lengthsH) };
//		Line linea = new Line(namesH, xaxisH, yaxisH);
//		Station first = linea.stations.get(0);
		

//		Train train = new Train("Start");
//		for (int i = 0; i < 70; i++) {
//			random = (int) (Math.random() * linea.stations.size());
//			p = new Person(linea.stations.get(random));
//			train.passengerIn(p);
//		}

//		first.trainArrival(train, 0L);
		Person p = null;
		int random = 0;
		int k = 1;
		Train train;
		for (int i = 0; i < 1000000; i++) {
			// System.out.println("tick");
//			TimeUnit.MILLISECONDS.sleep(10);
			if (i % 500 == 1 && i < 5000 ) {
				for(Line line: lines){
					k++;
					train = new Train("Start#" + k);
					for (int j = 0; j < 70; j++) {
						random = (int) (Math.random() * line.stations.size());
						p = new Person(line.stations.get(random));
						train.passengerIn(p);
					}
					line.stations.get(0).trainArrival(train, SimulatorScheduler.getInstance().getTime() + 1);					
				}
				System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
				System.out.println("Trains in the system:" + k);
			}

			SimulatorScheduler.getInstance().advanceTime();
		}
	}
}
