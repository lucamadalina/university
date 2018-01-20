package classes;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Main {
   public static List<Produs> myList = new ArrayList<>();
   public final static int X = 96;
   public static int nr = 0;

    public static void main(String[] args) throws InterruptedException {

        List<Thread> listThreads = new ArrayList<>();
        Thread t;
        MyLock myLock = new MyLock();
        Filter filter = new Filter(X);

        for(int i=1; i<=96; i++){
            t = new Thread(new MyThread(i, myLock, filter));
            t.start();
            listThreads.add(t);
        }

        try {
            for(int i=0; i<listThreads.size(); i++)
            listThreads.get(i).join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        PrintWriter writer = null;
        try {
            writer = new PrintWriter("lista.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (int i=0; i<myList.size(); i++){
            Produs produs = myList.get(i);
            writer.println(i+1);
            writer.println(produs.getName());
            writer.println("ID: " + produs.getId());
            if (produs.getList().size() == 1) {
                String key1 = (String) produs.getList().keySet().toArray()[0];
                writer.println("Link: " + key1);
                writer.println("Price: " + produs.getList().get(key1)+" lei");
            } else  if (produs.getList().size() > 1){
                String key1 = (String) produs.getList().keySet().toArray()[0];
                String key2 = (String) produs.getList().keySet().toArray()[1];
                if (Double.parseDouble(produs.getList().get(key1)) > Double.parseDouble(produs.getList().get(key2))) {
                    writer.println("Link: " + key2);
                    writer.println("Price: " + produs.getList().get(key2)+ " lei");
                } else {
                    writer.println("Link: " + key1);
                    writer.println("Price: " + produs.getList().get(key1)+" lei");
                }
            }
            writer.println();
        }
        writer.close();
    }
}

