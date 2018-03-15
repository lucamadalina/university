package mypackage;

import java.util.concurrent.locks.Lock;

/**
 * Created by madalina.luca on 3/14/2018.
 */
public class Consumer extends Thread {


    @Override
    public synchronized void run() {
        while (true) {

                try {
                    Main.queue.remove();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }

}
