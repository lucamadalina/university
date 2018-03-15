package mypackage;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

/**
 * Created by madalina.luca on 3/14/2018.
 */
public class Queue {

    private static LinkedList<Integer> queue = new LinkedList<>();
    final static Semaphore semFull = new Semaphore(0);
    final static Semaphore semFree = new Semaphore(Main.DIM_MAX);

    public synchronized void remove() throws InterruptedException{

        while (queue.isEmpty()) {
                wait();
        }
        try {
            semFull.acquire();
        }
        catch(InterruptedException e) {
           e.printStackTrace();
        }
        queue.remove();
        System.out.println("Am sters un element" );
        semFree.release();
        notify();
    }


    public synchronized void add() throws InterruptedException{

        while (queue.size() == Main.DIM_MAX) {
            wait();
        }
        try {
            semFree.acquire();
        } catch(InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
        queue.add(new Random().nextInt(100));
        System.out.println("Am adaugat un element");
        semFull.release();
        notify();
    }
}

