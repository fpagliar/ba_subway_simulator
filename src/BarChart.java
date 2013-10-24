import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
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
public class BarChart extends ApplicationFrame {

	 /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public BarChart(final String title) {

        super(title);

        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 350));
        setContentPane(chartPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Returns a sample dataset.
     * 
     * @return The dataset.
     */
    private CategoryDataset createDataset() {
        
        // row keys...
        final String seriesA = "A";
        final String seriesB = "B";
        final String seriesC = "C";
        final String seriesD = "D";
        final String seriesE = "E";
        final String seriesH = "H";

        // column keys...
        final String category1 = "1 Formacion";
        final String category2 = "2 Formaciones";
        final String category3 = "3 Formaciones";
        final String category4 = "4 Formaciones";
        final String category5 = "5 Formaciones";

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedA(), seriesA, category1);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedA(), seriesA, category2);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedA(), seriesA, category3);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedA(), seriesA, category4);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedA(), seriesA, category5);

        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedB(), seriesB, category1);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedB(), seriesB, category2);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedB(), seriesB, category3);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedB(), seriesB, category4);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedB(), seriesB, category5);

        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedC(), seriesC, category1);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedC(), seriesC, category2);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedC(), seriesC, category3);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedC(), seriesC, category4);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedC(), seriesC, category5);
        
        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedD(), seriesD, category1);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedD(), seriesD, category2);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedD(), seriesD, category3);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedD(), seriesD, category4);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedD(), seriesD, category5);
        
        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedE(), seriesE, category1);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedE(), seriesE, category2);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedE(), seriesE, category3);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedE(), seriesE, category4);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedE(), seriesE, category5);
        
        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedH(), seriesH, category1);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedH(), seriesH, category2);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedH(), seriesH, category3);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedH(), seriesH, category4);
//        dataset.addValue(SubwayMap.getInstance().getPassengersNotLoadedH(), seriesH, category5);
        
        return dataset;
        
    }
    
    /**
     * Creates a sample chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return The chart.
     */
    private JFreeChart createChart(final CategoryDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
            "Pasajeros no subidos",         // chart title
            "Linea",               // domain axis label
            "Cantidad",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
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
        final GradientPaint gp0 = new GradientPaint(
            0.0f, 0.0f, Color.blue.brighter(), 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp1 = new GradientPaint(
            0.0f, 0.0f, Color.red, 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp2 = new GradientPaint(
            0.0f, 0.0f, Color.blue.darker(), 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp3 = new GradientPaint(
                0.0f, 0.0f, Color.green, 
                0.0f, 0.0f, Color.lightGray
            );
        final GradientPaint gp4 = new GradientPaint(
                0.0f, 0.0f, Color.magenta.darker(), 
                0.0f, 0.0f, Color.lightGray
            );
        final GradientPaint gp5 = new GradientPaint(
                0.0f, 0.0f, Color.yellow, 
                0.0f, 0.0f, Color.lightGray
            );
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);
        renderer.setSeriesPaint(3, gp3);
        renderer.setSeriesPaint(4, gp4);
        renderer.setSeriesPaint(5, gp5);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
        
    }
	
}
