package com.company;

import java.util.concurrent.Semaphore;

public class MyThread extends Thread{


    private Semaphore semaphore ;
    int id;

    public MyThread(int id, Semaphore semaphore) {
        this.id = id;
        this.semaphore = semaphore;
    }

    @Override
    public void run()
    {
        while(Main.count < Main.MYNUMBER){
            increment();
        }
    }

    private void increment(){
        try {
            semaphore.acquire();
            Main.count++;
        } catch(InterruptedException iex) {
            iex.printStackTrace();
        }
        finally {
           semaphore.release();
        }
    }
}
