import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

@SuppressWarnings("serial")
public class LineChart extends ApplicationFrame {

	private List<Point> notLoaded = null;
	private int qtty = 0;

	private XYDataset dataset;
	private JFreeChart chart;
	private ChartPanel chartPanel;

	public LineChart(final String title) {
		super(title);
		notLoaded = new ArrayList<Point>();
		refresh();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void refresh() {
//		this.repaint();
		SubwayMap s = SubwayMap.getInstance();
		int sum = s.getPassengersNotLoadedA() + s.getPassengersNotLoadedB()
				+ s.getPassengersNotLoadedC() + s.getPassengersNotLoadedD()
				+ s.getPassengersNotLoadedE() + s.getPassengersNotLoadedH();
		qtty = Math.abs(sum - qtty);
		notLoaded.add(new Point((int) SimulatorScheduler.getInstance()
				.getTime(), qtty));
		dataset = createDataset();
		chart = createChart(dataset);
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(300, 300));
		setContentPane(chartPanel);
	}

	private XYDataset createDataset() {
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries data = new XYSeries("data");
		for (Point p : notLoaded)
			data.add(p.x, p.y);
		dataset.addSeries(data);
		return dataset;
	}

	private JFreeChart createChart(final XYDataset dataset) {

		// create the chart...
		final JFreeChart chart = ChartFactory.createXYLineChart(
				"Pasajeros no transportados", // chart title
				"Tiempo", // x axis label
				"Pasajeros", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL, false, // include legend
				true, // tooltips
				false // urls
				);

		chart.setBackgroundPaint(Color.white);

		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesLinesVisible(0, true);
		renderer.setSeriesShapesVisible(0, false);
		plot.setRenderer(renderer);

		return chart;
	}
}
