package classes;

/**
 * Created by madalina.luca on 10/24/2017.
 */

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.awt.*;
import java.util.HashMap;
import java.util.Set;

public class ChartTest extends javax.swing.JFrame {

    public ChartTest() {

        Lcg lcg = new Lcg(234, 456, 646);
        HashMap<Float, Integer> map = lcg.getList();
        final XYSeries series1 = new XYSeries("Lcg");
        Set<Float> keys = map.keySet();
        for(Float key: keys){
            series1.add(key, map.get(key));
        }

        MiddleSquare middleSquare = new MiddleSquare(456);
        HashMap<Float, Integer> map2 = middleSquare.getList();
        final XYSeries series2 = new XYSeries("MiddleSquare");
        Set<Float> keys2 = map2.keySet();
        for(Float key: keys2){
            series2.add(key, map2.get(key));
        }

        DefaultGenerator defaultGenerator = new DefaultGenerator();
        HashMap<Float, Integer> map3 = defaultGenerator.getList();
        final XYSeries series3 = new XYSeries("DefaultGenerator");
        Set<Float> keys3 = map3.keySet();
        for(Float key: keys3){
            series3.add(key, map3.get(key));
        }

        BinomialDistribution binomial = new BinomialDistribution(lcg);
        HashMap<Float, Integer> map4 = binomial.getList();
        final XYSeries series4 = new XYSeries("BinomialDistribution");
        Set<Float> keys4 = map4.keySet();
        for(Float key: keys4){
            series4.add(key, map4.get(key));
        }

        PoissonDistribution poisson = new PoissonDistribution(lcg);
        HashMap<Float, Integer> map5 = poisson.getList();
        final XYSeries series5 = new XYSeries("PoissonDistribution");
        Set<Float> keys5 = map5.keySet();
        for(Float key: keys5){
            series5.add(key, map5.get(key));
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
        dataset.addSeries(series4);
        dataset.addSeries(series5);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Random Numbers", "", "",
                dataset, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel cp = new ChartPanel(chart) {

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(320, 240);
            }
        };

        cp.setMouseWheelEnabled(true);
        add(cp);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }
}
