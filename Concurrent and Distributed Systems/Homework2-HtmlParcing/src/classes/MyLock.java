package classes;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock {

    private final Lock queueLock = new ReentrantLock();
    MyLock() {}

    public void addInList(Produs produs){
        boolean status = false;
        queueLock.lock();
        try {
                if (Main.myList.size() > 0) {
                    for (int k = 0; k < Main.myList.size(); k++) {
                        if (Main.myList.get(k).getId().equals(produs.getId())) {
                            Main.myList.get(k).setList(produs.getLink(), produs.getPrice());
                            status = true;
                        }
                    }
                }
                if (status == false) {
                    Main.myList.add(produs);
                }
            } finally {
                queueLock.unlock();
            }
    }
}
