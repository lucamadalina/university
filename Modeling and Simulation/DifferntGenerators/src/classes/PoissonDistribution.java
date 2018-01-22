package classes;

import interfaces.IGenerator;

import java.util.HashMap;

/**
 * Created by madalina.luca on 10/30/2017.
 */
public class PoissonDistribution implements IGenerator{

    public static final double E = 2.71828;
    public static final double A = 23;
    public static final int NumberTotal = 255;
    double L = 1/Math.pow(E, A);
    int k = 0;
    float p = 1;
    float u;
    IGenerator generator;

    PoissonDistribution(IGenerator g){
        this.generator = g;
    }

    @Override
    public float nextFloat() {
       do {
           k++;
           u = generator.nextFloat();
           p *= u;
       } while (p>L && k<NumberTotal);
       return (float) (k-1)/ NumberTotal;
    }


    public HashMap<Float, Integer> getList(){
        HashMap<Float, Integer> map = new HashMap<>();
        for (float i = 0; i <= 1; i = i + 0.1f) {
            map.put(i, 1);
        }
        for(int j=0; j<100; j++) {
            float nr = nextFloat();
            for (float i = 0; i <= 1; i = i + 0.1f) {
                float key = i;
                if (nr > key && nr <= key+0.1) {
                    map.put(key, map.get(key) + 1);
                }
            }
        }
        return map;
    }
}
