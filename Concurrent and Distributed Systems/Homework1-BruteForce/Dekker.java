package mypackage;

/**
 * Created by madalina.luca on 11/6/2017.
 */
public class Dekker{
    private int id;
    volatile boolean[] waiting = new boolean[96];
    volatile long lock, key;// initialized to 0
    boolean status = false;

    public Dekker(int i) {
        id = i;
    }

    ; // waiting[i] is true when Ti is waiting to enter


    public void run1(Casti casti) {
        int j;
        int k;
        while (true && casti != null) {
            waiting[id] = true;
            key = 1;
            while (key == 1 && waiting[id]) {
                key = 0;
            }
            waiting[id] = false;
            if (Main.myList.size() > 0) {
                for (k = 0; k < Main.myList.size(); k++) {
                    if (Main.myList.get(k).getId().equals(casti.getId())) {
                        Main.myList.get(k).setList(casti.getLink(), casti.getPrice());
                        status = true;
                    }
                }
            }
            if (status == false) {
                Main.myList.add(casti);
            }
            status = false;
            casti = null;

            j = (id + 1) % 96;
            while ((j != id) && !waiting[j]) {
                j = (j + 1) % 96;
            }
            if (j == id)
                lock = 0;
            else
                waiting[j] = false;
            // non-critical section
        }
    }



    public void run(Casti casti) {

            if(Main.myList.size() > 0){
                for (int j = 0; j < Main.myList.size(); j++) {
                    if (Main.myList.get(j).getId().equals(id)) {
                        Main.myList.get(j).setList(casti.getLink(), casti.getPrice());
                        status = true;
                    }
                }
            }
            if(status == false ){
                Main.myList.add(casti);

            }
            status = false;

    }
}