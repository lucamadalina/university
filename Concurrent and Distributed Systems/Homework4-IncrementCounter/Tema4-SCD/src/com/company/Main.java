package com.company;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {
    public final static int MYNUMBER = 96;
    public static volatile int count = 0;
    public static final Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        ArrayList<Thread> listWithThreads = new ArrayList<>();
        Thread myThread;
        int noOfTreads = 4;

        for(int i=0; i<noOfTreads ; i++){
            myThread = new MyThread(i, semaphore);
            myThread.start();
            listWithThreads.add(myThread);
        }

        for(int i=0; i<noOfTreads ; i++){
            try {
                listWithThreads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Numarul cautat este " + count);
    }
}
