import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
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

	private List<Point> totalPoints = null;
	private List<Point> actualPoints = null;

	private XYDataset dataset;
	private JFreeChart chart;
	private ChartPanel chartPanel;

	public LineChart(final String title) {
		super(title);
		totalPoints = new ArrayList<Point>();
		actualPoints = new ArrayList<Point>();
		refresh();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void refresh() {
		totalPoints.add(new Point((int) SimulatorScheduler.getInstance()
				.getTime(), (int)Person.getPeople()));
		actualPoints.add(new Point((int) SimulatorScheduler.getInstance()
				.getTime(), (int)Person.getActualPeople()));
		dataset = createDataset();
		chart = createChart(dataset);
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(300, 300));
		setContentPane(chartPanel);
	}

	private XYDataset createDataset() {
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries total = new XYSeries("Total");
		XYSeries actual = new XYSeries("Actual");
		for (Point p : totalPoints)
			total.add(p.x, p.y);
		for (Point p : actualPoints)
			actual.add(p.x, p.y);
		dataset.addSeries(total);
		dataset.addSeries(actual);
		return dataset;
	}

	private JFreeChart createChart(final XYDataset dataset) {

		// create the chart...
		final JFreeChart chart = ChartFactory.createXYLineChart(
				"Pasajeros en el sistema", // chart title
				"Tiempo", // x axis label
				"Pasajeros", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL, true, // include legend
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
		renderer.setSeriesLinesVisible(1, true);
		renderer.setSeriesShapesVisible(1, false);
		plot.setRenderer(renderer);

		return chart;
	}
	
	
	public void save(String name) {
		try {
			ChartUtilities.saveChartAsJPEG(new File(name), chart, 500, 300);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
