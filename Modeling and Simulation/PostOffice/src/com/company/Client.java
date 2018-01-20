package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Client {

    private int id;
    private HashMap<String, Integer> listaOperatii = new HashMap<>();
    private int tSosire;
    public static boolean busy = false;

    public Client(int id, int tSosire){
        this.id = id;
        settSosire(tSosire);
        setList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int gettSosire() {
        return tSosire;
    }

    public void settSosire(int tSosire) {
       this.tSosire = tSosire;
    }

    public HashMap<String, Integer> getList() {
        return listaOperatii;
    }

    public void setList() {
        String action;
        Random random = new Random();

        int timeAction;
        int nrOp = random.nextInt(3) + 1;
        for ( int i = 0; i<nrOp; i++) {
            action = Main.getServicii().get(random.nextInt(Main.getServicii().size()));
            timeAction = generateTimeAction();
            if (exist(action) == false) {
                listaOperatii.put(action, timeAction);
            } else {
                while (exist(action) == true) {
                    action = Main.getServicii().get(random.nextInt(Main.getServicii().size()));
                }
                listaOperatii.put(action, timeAction);
            }
        }
    }

    private int generateTimeAction(){
        Random random = new Random();
        int timeMin = 1;
        int timeMax = 5; //timpul exprimat in minute
        return (random.nextInt(timeMax)+timeMin);
    }

    private boolean exist(String serviciu){
        boolean status = false;
        for ( String key : listaOperatii.keySet() ) {
            if(serviciu.equalsIgnoreCase(key)){
                status = true;
                break;
            }
        }
        return status;
    }

    public void deleteActions(ArrayList<String> list){
        for(String actiune : list)
        getList().remove(actiune);
    }
}
