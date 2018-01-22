package classes;

import interfaces.IGenerator;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by madalina.luca on 10/24/2017.
 */
public class DefaultGenerator implements IGenerator {
    Random random;

    DefaultGenerator(){
        random = new Random();
    }

    @Override
    public float nextFloat(){
        return random.nextFloat();
    }

    public HashMap<Float, Integer> getList(){
        HashMap<Float, Integer> map = new HashMap<>();
        for (float i = 0; i <= 1; i = (float) (i + 0.1)) {
            map.put(i, 1);
        }
        for(int j=0; j<100; j++) {
            float nr = nextFloat();
            for (float i = 0; i <= 1; i = (float) (i + 0.1)) {
                float key = i;
                if (nr > key && nr < key+0.1) {
                    map.put(key, map.get(key) + 1);
                }
            }
        }
        return map;
    }

    public HashMap<Float, Float> get(){
        HashMap<Float, Float> map = new HashMap<>();
        for(int j=0; j<1000; j++) {
            float nr = nextFloat();
            float nr2 = nextFloat();
            map.put(nr, nr2);
        }
        return map;
    }
}
