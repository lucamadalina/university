

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
