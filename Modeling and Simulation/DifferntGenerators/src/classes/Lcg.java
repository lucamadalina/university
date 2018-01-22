package classes;

import interfaces.IGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Lcg implements IGenerator{

    int a;
    int b ;
    static int m ;
    int x;
    public static List<Double> list = new ArrayList<>();

    private int countNumber(int number){
        int i = 0;
        while(number != 0){
            number =  number/10;
            i++;
        }
        return i;
    }

    public Lcg(int a, int b, int m) {
        this.a = a;
        this.b = b;
        this.m = m;
    }

    private float generateNumber() {
        x = (a * x + b) % m;
        return ((float) (x/Math.pow(10, countNumber(x))));
    }
    @Override
    public float nextFloat(){
        float x = generateNumber();
        while(add(x) == true || x == 0.0) {
            setM(++m);
            x = generateNumber();
        }
        return x;
    }

    private boolean add(double x) {
        boolean status = false;
        for(int i=0; i<list.size(); i++){
            if (x == list.get(i)){
                status = true;
            }
        }
        if (!status){
            list.add(x);
            return status;
        } else {
            return status;
        }
    }

    private void setM(int m) {
        this.m = m;
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
        for(int j=0; j<100; j++) {
            float nr = nextFloat();
            float nr2 = nextFloat();
            map.put(nr, nr2);
        }
        return map;
    }
    }

