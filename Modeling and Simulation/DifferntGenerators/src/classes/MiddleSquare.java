package classes;

/**
 * Created by madalina.luca on 10/17/2017.
 */

import interfaces.IGenerator;

import java.util.HashMap;
import java.util.Map;

public class MiddleSquare implements IGenerator {
    private static int seed;
    public static Map<Integer, Integer> list = new HashMap<>();
    private int countSeed;


    public void setSeed(int seed) {
        this.seed = seed;
    }

    //or
    MiddleSquare(int seed){
        this.seed = seed;
        countSeed = countNumber(seed);
    }

    public static int countNumber(int seed){
        int i=0;
        while(seed!=0){
            seed=seed/10;
            i++;
        }
        return i;
    }


    public int setValue(int n) {
        int dif = countSeed - countNumber(n);
        int count = countNumber(n);
        if (count < countSeed) {
            while (dif != 0) {
                if (dif == 1) {
                    n = (int) (n + Math.pow(10, countNumber(n)));
                    dif--;
                } else {
                    dif--;
                }
            }
        }
        return n;
    }

    private boolean duplicate(int n){
        boolean status = false;
        if(list.size() > 0){
            if(list.get(n) != null)
                status = true;
        }
        if(!status){
            list.put(n, n);
            status = false;
        }
        return status;
    }

    @Override
    public float nextFloat(){
        int result;
        int p;
        int x;
        result = seed*seed;
        p= (int) ((result/(Math.pow(10,countSeed/2)))%(Math.pow(10,countSeed)));
        if(p == 0) {
            p=1;
        }
        x = setValue(p);
        if (duplicate(x) == false) {
            seed = x ;
            return (float) (seed/ Math.pow(10, countSeed));
        }
        else {
            seed = x+2;
            return (float)(seed/ Math.pow(10, countSeed));
        }
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

