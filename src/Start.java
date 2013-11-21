import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import org.jfree.ui.RefineryUtilities;

public class Start {
	
	static BarChart barChart = null;
	static LineChart lineChart = null;
	static HoursWaitingChart hoursWaitingChart = null;
	
	static String[] namesD = { "Congreso de Tucuman", "Juramento",
			"Jose Hernandez", "Olleros", "Ministro Carranza", "Palermo",
			"Plaza Italia", "Scalabrini Ortiz", "Bulnes", "Aguero",
			"Pueyrredon", "Facultad de Medicina", "Callao", "Tribunales",
			"9 de Julio", "Catedral" };
	static Integer[] xaxisD = { 152, 224, 297, 424, 492, 527, 551, 575, 590,
			612, 644, 679, 724, 760, 800, 841 };
	static Integer[] yaxisD = { 74, 74, 74, 74, 74, 74, 96, 118, 135, 155, 155,
			185, 185, 215, 257, 298 };
	// 100 ronda en los 3s, 1 en los 300s
	// Cuanto mas grande el numero, mas rapido llegan a la estacion!
//	static HashMap<Station, >
//	static Integer[][] personArrivalChanceD = {
//	//    5      6     7     8     9    10    11    12    13    14    15    16    17    18    19    20    21    22
//		{100 , 200 , 500 , 1000, 1000, 500 , 300 , 200 , 100 , 50  , 50  , 50  , 30  , 30  , 30  , 20  , 10  , 10  }, // Congreso
//		{80  , 180 , 350 , 80  , 80  , 300 , 150 , 200 , 100 , 50  , 50  , 50  , 30  , 30  , 30  , 20  , 10  , 10  }, // Juramento
//		{70  , 170 , 300 , 80  , 80  , 300 , 150 , 200 , 100 , 50  , 50  , 50  , 30  , 30  , 30  , 20  , 10  , 10  }, // Jose Hernandez
//		{80  , 180 , 350 , 80  , 80  , 300 , 150 , 200 , 100 , 50  , 50  , 50  , 30  , 30  , 30  , 20  , 10  , 10  }, // Olleros
//		{50  , 50  , 30  , 20  , 20  , 30  , 50  , 60  , 100 , 100 , 100 , 100 , 100 , 100 , 100 , 100 , 1000, 1000}, // Carranza
//		{50  , 50  , 30  , 20  , 20  , 30  , 50  , 60  , 100 , 100 , 100 , 100 , 100 , 100 , 100 , 100 , 1000, 1000}, // Palermo
//		{50  , 50  , 30  , 20  , 20  , 30  , 50  , 60  , 100 , 100 , 100 , 100 , 100 , 100 , 100 , 100 , 1000, 1000}, // Pza Italia
//		{50  , 50  , 30  , 20  , 20  , 30  , 50  , 60  , 100 , 100 , 100 , 100 , 100 , 100 , 100 , 100 , 1000, 1000}, // Scalabrini Ortiz
//		{50  , 50  , 30  , 20  , 20  , 30  , 50  , 60  , 100 , 100 , 100 , 100 , 100 , 100 , 100 , 100 , 1000, 1000}, // Bulnes
//		{50  , 50  , 30  , 20  , 20  , 30  , 50  , 60  , 100 , 100 , 100 , 100 , 100 , 100 , 100 , 100 , 1000, 1000}, // Aguero
//		{50  , 50  , 30  , 20  , 20  , 30  , 50  , 60  , 100 , 100 , 100 , 100 , 100 , 100 , 100 , 100 , 1000, 1000}, // Pueyrredon
//		{50  , 50  , 30  , 20  , 20  , 30  , 50  , 60  , 100 , 100 , 100 , 100 , 100 , 100 , 100 , 100 , 1000, 1000}, // Fac Medicina
//		{50  , 50  , 30  , 20  , 20  , 30  , 50  , 60  , 100 , 100 , 100 , 100 , 100 , 100 , 100 , 100 , 1000, 1000}, // Callao
//		{50  , 50  , 30  , 20  , 20  , 30  , 50  , 60  , 100 , 100 , 100 , 100 , 100 , 100 , 100 , 100 , 1000, 1000}, // Tribunales
//		{50  , 50  , 30  , 20  , 20  , 30  , 50  , 60  , 100 , 100 , 100 , 100 , 100 , 100 , 100 , 100 , 1000, 1000}, // 9 de Julio
//		{50  , 1000, 1000, 1000, 1000, 1000, 1000, 800 , 1000, 1000, 100 , 100 , 100 , 1000, 1000, 1000, 1000, 1000}};// Catedral
	static Integer[][] destiniesD = {};
	static Integer[] lengthsD = { 100, 65, 80, 95, 110, 80, 80, 65, 65, 70, 115,
			65, 90, 65, 60, 0 };
	// frecuencias, por hora, desde las 5 hasta las 23
	static Integer[] frequencyD = {300, 300, 190, 175, 175, 210, 210, 210, 210, 210, 210, 175, 175, 195, 195, 320, 320, 320};

	static String[] namesB = { "Juan Manuel de Rosas", "Echeverria",
			"Los Incas", "Tronador", "Lacroze", "Dorrego", "Malabia",
			"Angel Gallardo", "Medrano", "Carlos Gardel", "Pueyrredon",
			"Pasteur", "Callao", "Uruguay", "Carlos Pellegrini", "Florida",
			"Leandro N. Alem" };
	static Integer[] xaxisB = { 98, 155, 220, 285, 318, 395, 470, 504, 545,
			602, 646, 685, 723, 750, 800, 840, 869 };
	static Integer[] yaxisB = { 165, 165, 165, 165, 165, 165, 207, 242, 243,
			243, 243, 243, 243, 243, 243, 243, 243 };
	static Integer[][] personArrivalChanceB = {
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6} };
	static Integer[] lengthsB = { 70, 85, 100, 30, 120, 150, 80, 60, 45, 55,
			60, 40, 30, 70, 60, 50, 0 };
	static Integer[] frequencyB = {360, 360, 245, 195, 195, 270, 270, 270, 270, 270, 270, 195, 195, 205, 205, 330, 330, 330};

	static String[] namesC = { "Retiro", "San Martin", "Lavalle",
			"Diagonal Norte", "Avenida de Mayo", "Moreno", "Independencia",
			"San Juan", "Constitucion" };
	static Integer[] xaxisC = { 870, 844, 821, 812, 791, 791, 791, 791, 791 };
	static Integer[] yaxisC = { 137, 163, 222, 270, 319, 359, 436, 493, 575 };
	static Integer[][] personArrivalChanceC = {
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6}};
	static Integer[] lengthsC = { 40, 70, 60, 80, 40, 100, 80, 100, 0 };
	static Integer[] frequencyC = {270, 270, 215, 215, 215, 265, 265, 265, 265, 265, 265, 215, 215, 215, 215, 270, 270, 270};

	static String[] namesA = { "San Pedrito", "San Jose de Flores", "Carabobo",
			"Puan", "Primera Junta", "Acoyte", "Rio de Janeiro",
			"Castro Barros", "Loria", "Plaza Miserere", "Alberti-Pasco",
			"Congreso", "Saenz Peña", "Lima", "Piedras", "Peru",
			"Plaza de Mayo" };
	static Integer[] xaxisA = { 158, 206, 228, 267, 362, 440, 494, 536, 592,
			646, 668, 723, 750, 773, 816, 835, 868 };
	static Integer[] yaxisA = { 549, 500, 480, 440, 345, 319, 319, 319, 319,
			319, 319, 319, 319, 319, 319, 319, 319 };
	static Integer[][] personArrivalChanceA = {
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6}
		};
	static Integer[] lengthsA = { 90, 30, 60, 180, 100, 80, 50, 70, 60, 40, 40,
			30, 25, 40, 10, 30, 0 };
	static Integer[] frequencyA = {450, 450, 265, 175, 175, 265, 265, 265, 265, 265, 265, 295, 295, 175, 175, 420, 420, 420};

	static String[] namesE = { "Plaza de los Virreyes", "Varela",
			"Medalla Milagrosa", "Emilio Mitre", "Jose M. Moreno",
			"Avenida La Plata", "Boedo", "General Urquiza", "Jujuy",
			"Pichincha", "Entre Rios", "San Jose", "Independencia", "Belgrano",
			"Bolivar" };
	static Integer[] xaxisE = { 245, 265, 323, 365, 440, 494, 568, 605, 646,
			683, 722, 750, 765, 805, 842 };
	static Integer[] yaxisE = { 631, 590, 550, 508, 494, 494, 494, 494, 494,
			494, 494, 494, 436, 376, 340 };
	static Integer[][] personArrivalChanceE = {
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6}};
	static Integer[] lengthsE = { 50, 80, 60, 90, 50, 70, 40, 40, 40, 50, 35,
			75, 75, 50, 0 };
	static Integer[] frequencyE = {460, 460, 355, 310, 310, 480, 480, 480, 480, 480, 480, 245, 245, 245, 245, 460, 460};

	static String[] namesH = { "Hospitales", "Parque Patricios", "Caseros",
			"Inclan", "Humberto 1", "Venezuela", "Once", "Corrientes" };
	static Integer[] xaxisH = { 592, 618, 646, 646, 646, 646, 646, 646 };
	static Integer[] yaxisH = { 658, 640, 629, 584, 480, 403, 303, 227 };
	static Integer[][] personArrivalChanceH = {
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6},
		{1, 3, 5, 10, 10, 10, 10, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6}};
	static Integer[] lengthsH = { 30, 40, 50, 120, 100, 80, 30, 0 };
	static Integer[] frequencyH = {270, 270, 215, 215, 215, 265, 265, 265, 265, 265, 265, 215, 215, 215, 215, 270, 270, 270};
	
	static Line[] lines;
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		SubwayMap.getInstance().start();
		new LineACreator().create();
//		lines = new Line[] { new Line(namesA, xaxisA, yaxisA, personArrivalChanceA, lengthsA, frequencyA, SubwayMap.Lines.A),
//				new Line(namesB, xaxisB, yaxisB, personArrivalChanceB, lengthsB, frequencyB, SubwayMap.Lines.B),
//				new Line(namesC, xaxisC, yaxisC, personArrivalChanceC, lengthsC, frequencyC, SubwayMap.Lines.C),
//				new Line(namesD, xaxisD, yaxisD, personArrivalChanceD, lengthsD, frequencyD, SubwayMap.Lines.D),
//				new Line(namesE, xaxisE, yaxisE, personArrivalChanceE, lengthsE, frequencyE, SubwayMap.Lines.E),
//				new Line(namesH, xaxisH, yaxisH, personArrivalChanceH, lengthsH, frequencyH, SubwayMap.Lines.H) };

		generateChartGraphs();
		SubwayMap.getInstance().buildBasicGraphics();
		
		for (int i = 0; i < 1000000; i++) {
			if (i % 500 == 1) {
				generateChartGraphs();
//				lineChart.save("lineChart" + i + ".jpg");
//				barChart.save("barChart " + i + ".jpg");
			}
			SimulatorScheduler.getInstance().advanceTime();
		}
		
//		lineChart.save("lineChart.jpg");
//		barChart.save("barChart.jpg");
		
	}
	
	private static void generateChartGraphs () {
		if(barChart == null) {
			barChart = new BarChart("Pasajeros no transportados");
	        barChart.pack();
	        RefineryUtilities.centerFrameOnScreen(barChart);
	        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
			Point location = new Point(size.width / 2 + 250, size.height / 2 - 100);
			barChart.setLocation(location);
		}
		barChart.setVisible(false);
		barChart.refresh();
		barChart.setVisible(true);
		
		if(lineChart == null) {
			lineChart = new LineChart("Pasajeros en el sistema");
			lineChart.pack();
	        RefineryUtilities.centerFrameOnScreen(lineChart);
	        Dimension size2 = Toolkit.getDefaultToolkit().getScreenSize();
			Point location2 = new Point(size2.width / 2 + 250, size2.height / 2);
			lineChart.setLocation(location2);
		}
		lineChart.setVisible(false);
		lineChart.refresh();
		lineChart.setVisible(true);
		
		if(hoursWaitingChart == null) {
			hoursWaitingChart = new HoursWaitingChart("Horas hombre esperadas");
			hoursWaitingChart.pack();
	        RefineryUtilities.centerFrameOnScreen(hoursWaitingChart);
	        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
			Point location = new Point(size.width / 2 + 250, size.height / 2 + 100);
			hoursWaitingChart.setLocation(location);
		}
		hoursWaitingChart.setVisible(false);
		hoursWaitingChart.refresh();
		hoursWaitingChart.setVisible(true);
	}
}
