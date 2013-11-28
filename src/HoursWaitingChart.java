import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

@SuppressWarnings("serial")
public class HoursWaitingChart extends ApplicationFrame {

	private CategoryDataset dataset;
	private JFreeChart chart;
	private ChartPanel chartPanel;

	public HoursWaitingChart(final String title) {
		super(title);
		refresh();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void refresh() {
		dataset = createDataset();
		chart = createChart(dataset);
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(300, 300));
		setContentPane(chartPanel);
	}

	private CategoryDataset createDataset() {
		// row keys...
		final String seriesA = "A";
		final String seriesB = "B";
		final String seriesC = "C";
		final String seriesD = "D";
		final String seriesE = "E";
		final String seriesH = "H";

		// column keys...
		final String category1 = "";

		// create the dataset...
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(Line.lines.get(SubwayMap.Lines.A).getTotalHoursWaiting() / 3600,
				seriesA, category1);
		dataset.addValue(Line.lines.get(SubwayMap.Lines.B).getTotalHoursWaiting() / 3600,
				seriesB, category1);
		dataset.addValue(Line.lines.get(SubwayMap.Lines.C).getTotalHoursWaiting() / 3600,
				seriesC, category1);
		dataset.addValue(Line.lines.get(SubwayMap.Lines.D).getTotalHoursWaiting() / 3600,
				seriesD, category1);
		dataset.addValue(Line.lines.get(SubwayMap.Lines.E).getTotalHoursWaiting() / 3600,
				seriesE, category1);
		dataset.addValue(Line.lines.get(SubwayMap.Lines.H).getTotalHoursWaiting() / 3600,
				seriesH, category1);

		return dataset;

	}

	private JFreeChart createChart(final CategoryDataset dataset) {

		// create the chart...
		final JFreeChart chart = ChartFactory.createBarChart("", // chart title
				"Linea", // domain axis label
				"Horas", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips?
				false // URLs?
				);

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

		// set the background color for the chart...
		chart.setBackgroundPaint(Color.white);

		// get a reference to the plot for further customization...
		final CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		// set the range axis to display integers only...
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		// disable bar outlines...
		final BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);

		// set up gradient paints for series...
		final GradientPaint gp0 = new GradientPaint(0.0f, 0.0f,
				Color.blue.brighter(), 0.0f, 0.0f, Color.lightGray);
		final GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.red,
				0.0f, 0.0f, Color.lightGray);
		final GradientPaint gp2 = new GradientPaint(0.0f, 0.0f,
				Color.blue.darker(), 0.0f, 0.0f, Color.lightGray);
		final GradientPaint gp3 = new GradientPaint(0.0f, 0.0f, Color.green,
				0.0f, 0.0f, Color.lightGray);
		final GradientPaint gp4 = new GradientPaint(0.0f, 0.0f,
				Color.magenta.darker(), 0.0f, 0.0f, Color.lightGray);
		final GradientPaint gp5 = new GradientPaint(0.0f, 0.0f, Color.yellow,
				0.0f, 0.0f, Color.lightGray);
		renderer.setSeriesPaint(0, gp0);
		renderer.setSeriesPaint(1, gp1);
		renderer.setSeriesPaint(2, gp2);
		renderer.setSeriesPaint(3, gp3);
		renderer.setSeriesPaint(4, gp4);
		renderer.setSeriesPaint(5, gp5);

		final CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions
				.createUpRotationLabelPositions(Math.PI / 6.0));
		// OPTIONAL CUSTOMIZATION COMPLETED.

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
