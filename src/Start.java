import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import org.jfree.ui.RefineryUtilities;

public class Start {
	
	static BarChart barChart = null;
	static LineChart lineChart = null;
	
	static String[] namesD = { "Congreso de Tucuman", "Juramento",
			"Jose Hernandez", "Olleros", "Ministro Carranza", "Palermo",
			"Plaza Italia", "Scalabrini Ortiz", "Bulnes", "Aguero",
			"Pueyrredon", "Facultad de Medicina", "Callao", "Tribunales",
			"9 de Julio", "Catedral" };
	static Integer[] xaxisD = { 152, 224, 297, 424, 492, 527, 551, 575, 590,
			612, 644, 679, 724, 760, 800, 841 };
	static Integer[] yaxisD = { 74, 74, 74, 74, 74, 74, 96, 118, 135, 155, 155,
			185, 185, 215, 257, 298 };
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
	static Integer[] lengthsB = { 70, 85, 100, 30, 120, 150, 80, 60, 45, 55,
			60, 40, 30, 70, 60, 50, 0 };
	static Integer[] frequencyB = {360, 360, 245, 195, 195, 270, 270, 270, 270, 270, 270, 195, 195, 205, 205, 330, 330, 330};

	static String[] namesC = { "Retiro", "San Martin", "Lavalle",
			"Diagonal Norte", "Avenida de Mayo", "Moreno", "Independencia",
			"San Juan", "Constitucion" };
	static Integer[] xaxisC = { 870, 844, 821, 812, 791, 791, 791, 791, 791 };
	static Integer[] yaxisC = { 137, 163, 222, 270, 319, 359, 436, 493, 575 };
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
	static Integer[] lengthsE = { 50, 80, 60, 90, 50, 70, 40, 40, 40, 50, 35,
			75, 75, 50, 0 };
	static Integer[] frequencyE = {460, 460, 355, 310, 310, 480, 480, 480, 480, 480, 480, 245, 245, 245, 245, 460, 460};

	static String[] namesH = { "Hospitales", "Parque Patricios", "Caseros",
			"Inclan", "Humberto 1", "Venezuela", "Once", "Corrientes" };
	static Integer[] xaxisH = { 592, 618, 646, 646, 646, 646, 646, 646 };
	static Integer[] yaxisH = { 658, 640, 629, 584, 480, 403, 303, 227 };
	static Integer[] lengthsH = { 30, 40, 50, 120, 100, 80, 30, 0 };
	static Integer[] frequencyH = {270, 270, 215, 215, 215, 265, 265, 265, 265, 265, 265, 215, 215, 215, 215, 270, 270, 270};

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		
//		JFrame window = new Window();
//			window.setVisible(true);
//		if(true)
//			return;
		SubwayMap.getInstance().start();
		generateChartGraphs();

		Line[] lines = { new Line(namesA, xaxisA, yaxisA, lengthsA, frequencyA, SubwayMap.Lines.A),
				new Line(namesB, xaxisB, yaxisB, lengthsB, frequencyB, SubwayMap.Lines.B),
				new Line(namesC, xaxisC, yaxisC, lengthsC, frequencyC, SubwayMap.Lines.C),
				new Line(namesD, xaxisD, yaxisD, lengthsD, frequencyD, SubwayMap.Lines.D),
				new Line(namesE, xaxisE, yaxisE, lengthsE, frequencyE, SubwayMap.Lines.E),
				new Line(namesH, xaxisH, yaxisH, lengthsH, frequencyH, SubwayMap.Lines.H) };

		SubwayMap.getInstance().buildBasicGraphics();
		
		for (int i = 0; i < 1000000; i++) {
//			 System.out.println("tick");
			// TimeUnit.MILLISECONDS.sleep(10);
			if (i % 500 == 1 && i < 5000) {
				generateChartGraphs();
			}
			SimulatorScheduler.getInstance().advanceTime();
		}
		
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
			lineChart = new LineChart("Pasajeros no transportados");
			lineChart.pack();
	        RefineryUtilities.centerFrameOnScreen(lineChart);
	        Dimension size2 = Toolkit.getDefaultToolkit().getScreenSize();
			Point location2 = new Point(size2.width / 2 + 250, size2.height / 2);
			lineChart.setLocation(location2);
		}
		lineChart.setVisible(false);
		lineChart.refresh();
		lineChart.setVisible(true);
	}
}
