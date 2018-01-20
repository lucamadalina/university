package mypackage;

/**
 * Created by madalina.luca on 11/6/2017.
 */
public class Peterson  {

    public static void run(int i){
        boolean [] status = new boolean[96];
        int turn ;
        do {
            status[i] = true;
            while (turn != i){
                if(status[turn] == false){
                    turn
                }
            }
        }
    }
}