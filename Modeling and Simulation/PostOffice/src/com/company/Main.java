package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    static ArrayList<String> list = new ArrayList<>();
    public static void main( String args[] ) {
        int i;
        int nr = new Random().nextInt(5)+1;
        ArrayList<Ghiseu> listaGhisee = new ArrayList<>();
        Ghiseu ghiseu;
        setListServicii();
        for (i = 0; i < nr; i++) {
            ghiseu = new Ghiseu("Ghiseu " + Integer.toString(i));
            listaGhisee.add(ghiseu);
        }
        AgentiePostala p = new AgentiePostala(listaGhisee, "Agentie Postala");
        p.start();
       for (i = 0; i < nr; i++) {
           listaGhisee.get(i).start();
       }
       for(i = 0; i<nr ; i++){
           ghiseu = listaGhisee.get(i);
           try {
               ghiseu.join(2000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }

        for(i = 0; i<nr ; i++){
           listaGhisee.get(i).detAverageTimes();
        }

    }

    public static ArrayList<String> getServicii(){
        return list;
    }
    private static void setListServicii(){
        list.add("preluare colet");
        list.add("livrare colet");
        list.add("scrisori recomandate");
        list.add("plati facturi");
        list.add("mandate postale");
    }
}
