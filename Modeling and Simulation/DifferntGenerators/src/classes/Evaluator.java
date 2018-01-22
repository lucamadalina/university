package classes;


import interfaces.IGenerator;

public class Evaluator {
    public static final float PI = 3.14159265359f;
    public static final int noOfPointsGenerated = 255;
    IGenerator generator;

    Evaluator (IGenerator iGenerator){
        this.generator = iGenerator;
    }

    public double getError(){
        float numberRandom1;
        float numberRandom2;
        int noOfPoints = 0;
        for(int i=0; i<noOfPointsGenerated/2; i++){
            numberRandom1 = generator.nextFloat();
            numberRandom2 = generator.nextFloat();
            if(getStatus(numberRandom1, numberRandom2))
                noOfPoints++;
        }
        return PI - (float)4* noOfPoints/noOfPointsGenerated;
    }

    public boolean getStatus(double a, double b){
        float dist = (float) Math.sqrt((a*a+b*b));
        if(dist <= 1){
            return true;
        }
        else {
            return false;
        }
    }
}
