package mypackage;

import java.util.concurrent.locks.Lock;

/**
 * Created by madalina.luca on 3/14/2018.
 */
public class Producer extends Thread {

    @Override
    public void run() {
        while (true) {
            try {
                    Main.queue.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }
}