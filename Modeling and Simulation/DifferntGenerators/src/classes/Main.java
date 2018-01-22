package classes;

/**
 * Created by madalina.luca on 10/17/2017.
 */
public class Main {

    public static void main(String[] args) {
        int seed = 2500;
        Lcg lcg = new Lcg(8999, 6745, 1458);
        System.out.println ("Number generated with LCG: "+lcg.nextFloat());
        System.out.println ("Number generated with LCG: "+lcg.nextFloat());
        System.out.println ("Number generated with LCG: "+lcg.nextFloat());
        System.out.println ("Number generated with LCG: "+lcg.nextFloat());

        MiddleSquare generator = new MiddleSquare(seed);
        System.out.println("Numbers generated with MiddleSqaure: "+ generator.nextFloat());
        System.out.println("Numbers generated with MiddleSqaure: "+ generator.nextFloat());
        System.out.println("Numbers generated with MiddleSqaure: "+ generator.nextFloat());
        System.out.println("Numbers generated with MiddleSqaure: "+ generator.nextFloat());

        BinomialDistribution generator1 = new BinomialDistribution(lcg);
        System.out.println("Numbers generated with BinomialDistribution: "+ generator1.nextFloat());
        System.out.println("Numbers generated with BinomialDistribution: "+ generator1.nextFloat());
        System.out.println("Numbers generated with BinomialDistribution: "+ generator1.nextFloat());
        System.out.println("Numbers generated with BinomialDistribution: "+ generator1.nextFloat());

        PoissonDistribution generator2 = new PoissonDistribution(lcg);
        System.out.println("Numbers generated with PoissonDistribution: "+ generator2.nextFloat());
        System.out.println("Numbers generated with PoissonDistribution: "+ generator2.nextFloat());
        System.out.println("Numbers generated with PoissonDistribution: "+ generator2.nextFloat());
        System.out.println("Numbers generated with PoissonDistribution: "+ generator2.nextFloat());

        DefaultGenerator generator3 = new DefaultGenerator();
        System.out.println("Numbers generated with DefaultGenerator: "+ generator3.nextFloat());
        System.out.println("Numbers generated with DefaultGenerator: "+ generator3.nextFloat());
        System.out.println("Numbers generated with DefaultGenerator: "+ generator3.nextFloat());
        System.out.println("Numbers generated with DefaultGenerator: "+ generator3.nextFloat());


        Evaluator evaluator = new Evaluator(generator3);
        System.out.println("Error (DefaultGenerator): "+evaluator.getError());
        evaluator = new Evaluator(lcg);
        System.out.println("Error (LcgGenerator): "+evaluator.getError());
        evaluator = new Evaluator(generator);
        System.out.println("Error (MiddleSquareGenerator): "+evaluator.getError());
        evaluator = new Evaluator(generator1);
        System.out.println("Error (BinomialDistribution): "+evaluator.getError());
        evaluator = new Evaluator(generator2);
        System.out.println("Error (PoissonDistribution): "+evaluator.getError());


        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChartTest().setVisible(true);
            }
        });

         /*Output
        Number generated with LCG: 0.913
        Number generated with LCG: 0.117
        Number generated with LCG: 0.67
        Number generated with LCG: 0.234
        Numbers generated with MiddleSqaure: 0.25
        Numbers generated with MiddleSqaure: 0.2502
        Numbers generated with MiddleSqaure: 0.26
        Numbers generated with MiddleSqaure: 0.76
        Numbers generated with BinomialDistribution: 0.7372549
        Numbers generated with BinomialDistribution: 0.6862745
        Numbers generated with BinomialDistribution: 0.7647059
        Numbers generated with BinomialDistribution: 0.8039216
        Numbers generated with PoissonDistribution: 0.06666667
        Numbers generated with PoissonDistribution: 0.07058824
        Numbers generated with PoissonDistribution: 0.07450981
        Numbers generated with PoissonDistribution: 0.078431375
        Numbers generated with DefaultGenerator: 0.57094085
        Numbers generated with DefaultGenerator: 0.19087106
        Numbers generated with DefaultGenerator: 0.62291235
        Numbers generated with DefaultGenerator: 0.9379688
        Error (DefaultGenerator): 1.651396632194519
        Error (LcgGenerator): 1.180808424949646
        Error (MiddleSquareGenerator): 1.196494698524475
        Error (BinomialDistribution): 2.1376709938049316
        Error (PoissonDistribution): 1.8866907358169556
         */

    }
}
