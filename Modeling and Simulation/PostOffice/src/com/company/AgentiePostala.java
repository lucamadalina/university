package com.company;

import java.util.*;

public class AgentiePostala extends Thread{
    public final static int NUMARCLIENTI = 100;
    private static int currentTimeHour  = 8;
    private static int currentTimeMinutes = 0;
    private static ArrayList<Ghiseu> listaGhisee = new ArrayList<>();
    private static HashMap<String, ArrayList<Ghiseu>>  list = new HashMap<>();

    public AgentiePostala(ArrayList<Ghiseu> listaGhisee, String name ){
        setName(name);
        this.listaGhisee = listaGhisee;
        createHashMap();
    }

    private int minIndex(String actiune){
        int index = 0;
        try
        {
            long min = list.get(actiune).get(0).lungime_coada();
                for (int i = 1; i < list.get(actiune).size(); i++) {
                    long lung = list.get(actiune).get(i).lungime_coada();
                    if (lung < min) {
                        min = lung;
                        index = i;
                    }
                }
        }
        catch( InterruptedException e ){
            System.out.println( e.toString());
        }
        return index;
    }

    public void run(){
        int i = 0;
        Ghiseu ghiseu;
        int randomMinutes;
        while (i < NUMARCLIENTI ) {
            i++;
            randomMinutes = new Random().nextInt(5);
            Client client = new Client(i, generateTime(randomMinutes));
            //adauga client
            for (String actiune : client.getList().keySet()) {
                int m = minIndex(actiune);
                ghiseu = list.get(actiune).get(m);
                try {
                    ghiseu.adauga_client(client, ghiseu, getOpComune(ghiseu, client));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void createHashMap(){
        String actiune;
        Ghiseu ghiseu;
        ArrayList<Ghiseu> list1;
        for(int j=0; j<Main.getServicii().size(); j++) {
            list1 = new ArrayList<>();
            actiune = Main.getServicii().get(j);
            for (int i = 0; i < listaGhisee.size(); i++) {
                ghiseu = listaGhisee.get(i);
                for(int k=0 ; k<ghiseu.getListServicii().size() ; k++){
                    if(actiune.equalsIgnoreCase(ghiseu.getListServicii().get(k))){
                        list1.add(ghiseu);
                        break;
                }
                }
            }
            this.list.put(actiune, list1);
        }

        for (String key: list.keySet()) {
            list1 = new ArrayList<>();
            if (list.get(key).size() == 0) {
                list1.add(listaGhisee.get(getMin()));
                list.put(key, list1);
            }
        }
    }

    public int getMin(){
        int min = listaGhisee.get(0).getListServicii().size();
        for(int i=1; i<listaGhisee.size(); i++){
            if(min > listaGhisee.get(i).getListServicii().size()){
                min = listaGhisee.get(i).getListServicii().size();
            }
        }
        return min;
    }

     ArrayList<String> getOpComune(Ghiseu ghiseu, Client client){
        ArrayList<String> opComune = new ArrayList<>();
        boolean exist = false;
        for(String action1 : client.getList().keySet()){
            for(String action2 : ghiseu.getListServicii()) {
                if (action1.equalsIgnoreCase(action2)) {
                    opComune.add(action1);
                }
            }
        }
        return  opComune;
    }

    public int generateTime(int minutes){
        currentTimeMinutes = currentTimeMinutes + minutes;
        if (currentTimeMinutes >= 60) {
            currentTimeHour ++;
            currentTimeMinutes = currentTimeMinutes % 60;
        }
        int totalMinutes = (currentTimeHour * 60) + currentTimeMinutes;
        return totalMinutes;
    }

}


