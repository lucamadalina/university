import java.util.LinkedList;
import java.util.Random;

public class MyQueue {

    private static LinkedList<Integer> queue = new LinkedList<>();

    public MyQueue() {
    }

    public synchronized void remove() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Coada este goala!");
            wait();
        }
        sleep(100);
        queue.remove();
        System.out.println("Am sters un element");
        notify();
    }

    public synchronized void add() throws InterruptedException{
        queue.add(new Random().nextInt(100));
        while (queue.size() == Main.DIM_MAX) {
            System.out.println("Coada este plina!");
            wait();
        }
	sleep(100);
        queue.add(new Random().nextInt(100));
        System.out.println("Am adaugat un element");
        notify();
    }
}
