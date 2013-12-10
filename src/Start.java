import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.jfree.ui.RefineryUtilities;

public class Start {
	
	static BarChart barChart = null;
	static LineChart lineChart = null;
	static HoursWaitingChart hoursWaitingChart = null;
	
	public static Random random = new Random(1);
	
	static Line[] lines;
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
				
//		SubwayMap.getInstance().start();
//		LineACreator a = new LineACreator();
//		a.create();
//		LineBCreator b = new LineBCreator();
//		b.create();
//		LineCCreator c = new LineCCreator();
//		c.create();
//		LineDCreator d = new LineDCreator();
//		d.create();
//		LineECreator e = new LineECreator();
//		e.create();
//		LineHCreator h = new LineHCreator();
//		h.create();
//			
//		a.setCombinations();
//		b.setCombinations();
//		c.setCombinations();
//		d.setCombinations();
//		e.setCombinations();
//		h.setCombinations();
		
//		generateChartGraphs();
//		SubwayMap.getInstance().buildBasicGraphics();
		
//		for (int i = 0; i < (23-5)*60*60; i++) {
//			if (i % 3600 == 1) {
////				generateChartGraphs();
////				lineChart.save("lineChart" + i + ".jpg");
////				barChart.save("barChart " + i + ".jpg");
//			}
//			SimulatorScheduler.getInstance().advanceTime();
//		}
		
//		System.out.println("Results!!");
//		System.out.println("------ HOURS WAITING ----------");
//		for(Line l : Line.lines.values())
//			System.out.println(l.getLineLetter() + ":" + (l.getTotalHoursWaiting() / 3600));
//		System.out.println("-------------------------------");
//		System.out.println("\n---- NOT LOADED PASSANGERS ----");
//		System.out.println("A:" + SubwayMap.getInstance().getPassengersNotLoadedA());
//		System.out.println("B:" + SubwayMap.getInstance().getPassengersNotLoadedB());
//		System.out.println("C:" + SubwayMap.getInstance().getPassengersNotLoadedC());
//		System.out.println("D:" + SubwayMap.getInstance().getPassengersNotLoadedD());
//		System.out.println("E:" + SubwayMap.getInstance().getPassengersNotLoadedE());
//		System.out.println("H:" + SubwayMap.getInstance().getPassengersNotLoadedH());
//		System.out.println("-------------------------------");
		
//		lineChart.save("lineChart.jpg");
//		barChart.save("barChart.jpg");
		makeARun();
	}
	
	
	private static void manyRuns() throws Exception{
		Long time = System.currentTimeMillis();
		Integer n = 50;
		HashMap<SubwayMap.Lines, List<Integer>> hoursWaiting = new HashMap<SubwayMap.Lines, List<Integer>>();
		HashMap<SubwayMap.Lines, List<Integer>> passengersNotLoaded = new HashMap<SubwayMap.Lines, List<Integer>>();

		for(int i = 0; i < n; i++){
			makeARun();
			if(hoursWaiting.keySet().isEmpty())
				for(Line l: Line.lines.values()){
					hoursWaiting.put(l.getLineLetter(), new ArrayList<Integer>());
					passengersNotLoaded.put(l.getLineLetter(), new ArrayList<Integer>());
				}
			
			for(Line l: Line.lines.values()){
				hoursWaiting.get(l.getLineLetter()).add((int)(l.getTotalHoursWaiting() / 3600));
				passengersNotLoaded.get(l.getLineLetter()).add(SubwayMap.getInstance().getPassengersNotLoaded(l.getLineLetter()));
			}
			SubwayMap.restart();
			SimulatorScheduler.restart();
//			random = new Random(1);
		}
		System.out.println("************ FINAL VALUES ************");
		System.out.println(" HOURS WAITING: ");
		for(Line l: Line.lines.values()){
			List<Integer> hours = hoursWaiting.get(l.getLineLetter());
			Integer avg = 0;
			double s = 0.0;
			for(Integer i: hours)
				avg += i;
			avg /= n;
			for(Integer i: hours)
				s = s + (i - avg) * (i - avg);
			s /= n;
			s = Math.sqrt(s);
			System.out.println(l.getLineLetter() + " avg:" + avg + " sigma:" + (int)s + " sigma%:" + s*100/avg);
		}
		System.out.println("************ ******** ************");
		System.out.println(" PASSANGERS NOT LOADED: ");
		for(Line l: Line.lines.values()){
			List<Integer> passangers = passengersNotLoaded.get(l.getLineLetter());
			Integer avg = 0;
			double s = 0.0;
			for(Integer i: passangers)
				avg += i;
			avg /= n;
			for(Integer i: passangers)
				s = s + (i - avg) * (i - avg);
			s /= n;
			s = Math.sqrt(s);
			if(avg != 0)
				System.out.println(l.getLineLetter() + " avg:" + avg + " sigma:" + (int)s + " sigma%:" + s*100/avg);
		}
		System.out.println("************ ******** ************");
		System.out.println("TIME: " + (time - System.currentTimeMillis())/1000/60 + ":" + (time - System.currentTimeMillis())/1000);
//		System.out.println("D:" + SubwayMap.getInstance().getPassengersNotLoadedD());

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
	
	private static void makeARun() throws Exception{
		SubwayMap.getInstance().start();
		LineACreator a = new LineACreator();
		a.create();
		LineBCreator b = new LineBCreator();
		b.create();
		LineCCreator c = new LineCCreator();
		c.create();
		LineDCreator d = new LineDCreator();
		d.create();
		LineECreator e = new LineECreator();
		e.create();
		LineHCreator h = new LineHCreator();
		h.create();
			
//		lines = new Line[] { new Line(namesA, xaxisA, yaxisA, personArrivalChanceA, lengthsA, frequencyA, SubwayMap.Lines.A),
//				new Line(namesB, xaxisB, yaxisB, personArrivalChanceB, lengthsB, frequencyB, SubwayMap.Lines.B),
//				new Line(namesC, xaxisC, yaxisC, personArrivalChanceC, lengthsC, frequencyC, SubwayMap.Lines.C),
//				new Line(namesD, xaxisD, yaxisD, personArrivalChanceD, lengthsD, frequencyD, SubwayMap.Lines.D),
//				new Line(namesE, xaxisE, yaxisE, personArrivalChanceE, lengthsE, frequencyE, SubwayMap.Lines.E),
//				new Line(namesH, xaxisH, yaxisH, personArrivalChanceH, lengthsH, frequencyH, SubwayMap.Lines.H) };

		a.setCombinations();
		b.setCombinations();
		c.setCombinations();
		d.setCombinations();
		e.setCombinations();
		h.setCombinations();
		
		generateChartGraphs();
		SubwayMap.getInstance().buildBasicGraphics();
		
		for (int i = 0; i < (23-5)*60*60; i++) {
			if (i % 3600 == 1) {
				generateChartGraphs();
//				lineChart.save("lineChart" + i + ".jpg");
//				barChart.save("barChart " + i + ".jpg");
			}
			SimulatorScheduler.getInstance().advanceTime();
		}
		
		System.out.println("Results!!");
		System.out.println("------ HOURS WAITING ----------");
		for(Line l : Line.lines.values())
			System.out.println(l.getLineLetter() + ":" + (l.getTotalHoursWaiting() / 3600));
		System.out.println("-------------------------------");
		System.out.println("\n---- NOT LOADED PASSANGERS ----");
		System.out.println("A:" + SubwayMap.getInstance().getPassengersNotLoadedA());
		System.out.println("B:" + SubwayMap.getInstance().getPassengersNotLoadedB());
		System.out.println("C:" + SubwayMap.getInstance().getPassengersNotLoadedC());
		System.out.println("D:" + SubwayMap.getInstance().getPassengersNotLoadedD());
		System.out.println("E:" + SubwayMap.getInstance().getPassengersNotLoadedE());
		System.out.println("H:" + SubwayMap.getInstance().getPassengersNotLoadedH());
		System.out.println("-------------------------------");
	}
}
