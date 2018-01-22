package classes;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Set;

/**
 * @author imssbora
 */
public class ScatterAdd extends JFrame {
    private static final long serialVersionUID = 6294689542092367723L;

    public ScatterAdd(String title) {
        super(title);
        XYDataset dataset = createDataset();

       //creeaza chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Random Numbers",
                "X-Axis", "Y-Axis", dataset);

        //schimba culoarea chart-ului
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(255, 228, 196));

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        //Boys (Age,weight) series
        XYSeries series1 = new XYSeries("Lcg");
        Lcg lcg = new Lcg(234, 456, 646);
        HashMap<Float, Float> map = lcg.get();
        Set<Float> keys = map.keySet();
        for(Float key: keys){
            series1.add(key, map.get(key));
        }

        MiddleSquare middleSquare = new MiddleSquare(456);
        HashMap<Float, Float> map2 = middleSquare.get();
        XYSeries series2 = new XYSeries("MiddleSquare");
        Set<Float> keys2 = map2.keySet();
        for(Float key: keys2){
            series2.add(key, map2.get(key));
        }

        DefaultGenerator defaultGenerator = new DefaultGenerator();
        HashMap<Float, Float> map3 = defaultGenerator.get();
        final XYSeries series3 = new XYSeries("DefaultGenerator");
        Set<Float> keys3 = map3.keySet();
        for(Float key: keys3){
            series3.add(key, map3.get(key));
        }

        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
        return dataset;
    }
}