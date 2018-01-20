package mypackage;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {
   public static List<Produs> myList = new ArrayList<>();
   public final static int X = 96;

    public static void main(String[] args) throws InterruptedException {

        List<Thread> listThreads = new ArrayList<>();
        Thread t;

       // for(int i=25; i<=96; i++){
          //UrlReader.getFile("https://www.emag.ro/casti-pc/p"+i+"/c", "C:\\Users\\madalina.luca\\Desktop\\htmlEmag\\file"+i+".txt");
          // UrlReader.getFile("http://www.cel.ro/casti/0a-"+i, "C:\\Users\\madalina.luca\\Desktop\\htmlCel\\file"+i+".txt");
        //}

        for(int i=1; i<=Main.X; i++){
            t = new Thread(new MyThread(i));
            t.start();
            listThreads.add(t);
        }

        //let all threads finish execution before finishing main thread
        try {
            for(int i=0; i<listThreads.size(); i++)
            listThreads.get(i).join();
        } catch(InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("All threads are dead, exiting main thread");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("lista.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(myList.size());
        for (int i=0; i<myList.size(); i++){
            Produs produs = myList.get(i);
            writer.println(i+1);
            writer.println(produs.getName());
            writer.println(produs.getId());
            Set<String> keys = produs.getList().keySet();
            for(String key :keys) {
                    writer.println("link: " + key);
                    writer.println("price: " + produs.getList().get(key));
            }
            writer.println();
        }
        writer.close();
    }
}

