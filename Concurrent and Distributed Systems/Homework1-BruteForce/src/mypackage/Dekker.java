package mypackage;

/**
 * Created by madalina.luca on 11/6/2017.
 */
public class Dekker {
    private int id;
    boolean status = false;
    private int[] flags = new int[Main.X];
    private int[] turn = new int[Main.X-1];

    public Dekker(int i) {
        id = i;
    }

    ; // waiting[i] is true when Ti is waiting to enter


    public void run(Produs casti) {
        do {
            for (int count = 1; count < Main.X; count++) {
                flags[id] = count;
                turn[count] = id;
                boolean conflicts_exist = true;
                while (conflicts_exist) {
                    conflicts_exist = false;
                    for (int k = 1; k < Main.X; k++) {
                        if (k != id && flags[k] >= count && turn[count] == id) {
                            conflicts_exist = true;
                            break;
                        }
                    }
                }
            }
            if (Main.myList.size() > 0) {
                for (int k = 0; k < Main.myList.size(); k++) {
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
            //casti = null;
            flags[id] = 0;
        } while(true);

    }
}
