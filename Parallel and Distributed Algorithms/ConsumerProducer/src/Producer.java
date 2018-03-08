
import java.util.Random;


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
