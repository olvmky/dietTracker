/**
 * This class represents the controller for managing weight-related functionalities.
 * It provides methods to update the text displayed in the weight panel based on user data,
 * as well as to generate and display a graph representing the user's weight changes over time.
 */
package finalproject.controller;

import finalproject.Function;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.TickUnits;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class WeightController extends JFrame {
    protected Function function = new Function();

    /**
     * Updates the text displayed in the weight panel based on user data.
     *
     * @param userId The ID of the user.
     * @param weightMidRightPanel The JPanel to update with the weight text.
     */
    public void updateWeightText(int userId, JPanel weightMidRightPanel){
        Double[] diff = function.weightDifference(userId);
        double firstDay = diff[0];
        double yesterday = diff[1];
        long days = -1 * function.daysUsedApp(userId);

        String daysDiff = "YOU HAVE USED THE APP FOR " + days + " DAYS";
        JLabel weightDays = new JLabel(daysDiff);
        weightMidRightPanel.add(weightDays);
        weightDays.setAlignmentX(Component.CENTER_ALIGNMENT);

        String comparisonFirstDay;
        String firstDate = function.dayRegister(userId);
        if(firstDay < 0) comparisonFirstDay = "YOU HAVE LOST " + -1*firstDay + " LBS SINCE " + firstDate;
        else comparisonFirstDay = "YOU HAVE GAINED " + firstDay + " LBS SINCE " + firstDate;
        JLabel weightFirstDayDiff = new JLabel(comparisonFirstDay);
        weightMidRightPanel.add(weightFirstDayDiff);
        weightFirstDayDiff.setAlignmentX(Component.CENTER_ALIGNMENT);

        String comparisonYesterday;
        if(yesterday < 0) comparisonYesterday = "YOU HAVE LOST " + -1*yesterday + " lbs compared to yesterday";
        else comparisonYesterday = "You have gained " + yesterday + " lbs compared to yesterday.";
        comparisonYesterday = comparisonYesterday.toUpperCase();
        JLabel weightYesterdayDiff = new JLabel(comparisonYesterday);
        weightMidRightPanel.add(weightYesterdayDiff);
        weightYesterdayDiff.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Generates and displays a graph representing the user's weight changes over time.
     *
     * @param weightFilePath The file path of the weight data.
     * @param userId The ID of the user.
     * @param weightMidLeftPanel The JPanel to display the weight graph.
     */
    public void weightGraph(String weightFilePath, int userId, JPanel weightMidLeftPanel){
        List<String[]> data = function.readFileContents(weightFilePath);
        XYSeries series = new XYSeries("WEIGHT TRACK");
        LocalDate current = LocalDate.now();
        double min = Double.MAX_VALUE;
        double max = 0.0;
        boolean dataFound = false;
        for(String[] i:data){
            if(userId == Integer.parseInt(i[0])){
                String[] split = i[1].split("-");
                int month = Integer.parseInt(split[1]);
                if(current.getMonthValue() == month && current.getYear() == Integer.parseInt(split[0])){
                    String day = split[2];
                    double weight = Double.parseDouble(i[2]);
                    min = Math.min(min, weight);
                    max = Math.max(max, weight);
                    series.add(Integer.parseInt(day), weight);
                    dataFound = true;
                }
            }
        }
        //If false, then there's no data for current month, return empty graph
        if (!dataFound) {
            min = 0;
            max = 0;
        }
        String monthName = Month.of(current.getMonthValue()).
                getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.getDefault());
        monthName = monthName.toUpperCase();
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        JFreeChart chart = ChartFactory.createScatterPlot(
                "WEIGHT CHANGES", // Chart title
                "FOR " + monthName,               // X-axis label
                "WEIGHTS IN LBS",            // Y-axis label
                dataset,             // Dataset
                PlotOrientation.VERTICAL,
                true,                // Show legend
                true,               // Use tooltips
                false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true); // Show lines for series 0
        plot.setRenderer(renderer);

        NumberAxis yAxis = (NumberAxis) chart.getXYPlot().getRangeAxis();
        yAxis.setRange(min - 0.5, max + 0.5);

        ValueAxis xAxis = chart.getXYPlot().getDomainAxis();
        xAxis.setStandardTickUnits(createStandardTickUnits());

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 350));
        weightMidLeftPanel.add(chartPanel);
    }

    /**
     * Creates standard tick units for the x-axis of a chart.
     * These tick units are typically used to define the spacing of tick marks on the x-axis.
     * Adjustments can be made based on specific requirements.
     *
     * @return The standard tick units for the x-axis.
     */
    public TickUnits createStandardTickUnits() {
        TickUnits units = new TickUnits();
        units.add(new NumberTickUnit(1)); // Adjust this based on your specific requirements
        units.add(new NumberTickUnit(2));
        return units;
    }

}
