package classes;

import interfaces.IGenerator;

import java.util.HashMap;

/**
 * Created by madalina.luca on 10/30/2017.
 */
public class BinomialDistribution implements IGenerator {
   public static final int N = 255;
    int x;
    float u;
    float p = 0.6f;
    IGenerator generator;

    BinomialDistribution(IGenerator g){
        this.generator = g;
    }

    @Override
    public float nextFloat() {
        x = 0;
        for (int i=0; i<N; i++){
            u = generator.nextFloat();
            if(u < p){
                x++;
            }
        }
        return (float)x/N;
    }

    public HashMap<Float, Integer> getList(){
        HashMap<Float, Integer> map = new HashMap<>();
        for (float i = 0; i <= 1; i =  i + 0.1f) {
            map.put(i, 1);
        }
        for(int j=0; j<100; j++) {
            float nr = nextFloat();
            for (float i = 0; i <= 1; i = (float) (i + 0.1)) {
                float key = i;
                if (nr > key && nr <= key+0.1f) {
                    map.put(key, map.get(key) + 1);
                    break;
                }
            }
        }
        return map;
    }
}
