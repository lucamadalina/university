package classes;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Filter implements Lock {

    private AtomicInteger[] level;
    private AtomicInteger[] victim;

    private int n;

    public Filter(int n) {
        this.n = n;
        level = new AtomicInteger[n];
        victim = new AtomicInteger[n];
        for (int i = 0; i < n; i++) {
            level[i] = new AtomicInteger();
            victim[i] = new AtomicInteger();
        }
    }

    @Override
    public void lock() {
        int processId = (int) Thread.currentThread().getId();
        for (int i = 1; i < n; i++) {
            level[processId].set(i);
            victim[i].set(processId);
            for (int k = 0; k < n; k++) {
                while ((k != processId) && (level[k].get() >= i && victim[i].get() == processId)) {
                    // wait
                }
            }
        }
    }
    @Override
    public void unlock() {
        int me = (int) Thread.currentThread().getId();
        level[me].set(0);
    }

    public void addInList(Produs produs){
        boolean status = false;
        lock();
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
        unlock();
    }


    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }
}
