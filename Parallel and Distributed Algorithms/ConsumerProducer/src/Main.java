import java.util.ArrayList;
import java.util.LinkedList;


public class Main {

    public static final int DIM_MAX = 10;
    public static final int NUMBERCONSUMER = 1;
    public static final int NUMBERPRODUCER = 1;

    private static ArrayList<Producer> listProducers = new ArrayList<>();
    private static ArrayList<Consumer> listConsumers = new ArrayList<>();
    public  static MyQueue queue = new MyQueue();

    public static void main(String args[]){
        int i;
        Producer producer;
        Consumer consumer;

        for(i=0; i<NUMBERCONSUMER; i++){
            consumer = new Consumer();
            consumer.start();
            listConsumers.add(consumer);
        }
        for(i=0; i<NUMBERPRODUCER; i++){
            producer = new Producer();
            producer.start();
            listProducers.add(producer);
        }

        for(i=0; i<listProducers.size(); i++){
            try {
                listProducers.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(i=0; i<listConsumers.size(); i++){
            try {
                listConsumers.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
