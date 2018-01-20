package com.company;

import java.sql.Time;
import java.util.*;

public class Ghiseu extends Thread{

    private LinkedList<Client> clients = new LinkedList<>();
    private ArrayList<String> listaServicii = new ArrayList<>();
    private int currentTime;

    private HashMap<Client, ArrayList<String>> totalList = new HashMap<>();
    ArrayList<Integer> nrOfClients = new ArrayList<>();
    ArrayList<Integer> waitingTime = new ArrayList<>();
    ArrayList<Integer> systemTime= new ArrayList<>();

    public Ghiseu(String name){
        setName(name);
        setListServicii();
        currentTime = 8*60; //ora 8 in minute
}

    public void setListServicii() {
        String serviciu;
        Random random = new Random();
        int nrActiuni = random.nextInt(3) + 1;
        for ( int i = 0; i<nrActiuni; i++) {
            serviciu = Main.getServicii().get(random.nextInt(Main.getServicii().size()));
            if (exist(serviciu) == false) {
                listaServicii.add(serviciu);
            } else {
                while (exist(serviciu) == true) {
                    serviciu = Main.getServicii().get(random.nextInt(Main.getServicii().size()));
                }
                listaServicii.add(serviciu);
            }
        }
    }
    public ArrayList<String> getListServicii() {

        return listaServicii;
    }

    public void addClient(Client client, ArrayList<String> actions){
        for(int i=0; i<clients.size(); i++){
            if(clients.get(i) == client){
                return;
            }
        }
        clients.add(client);
        totalList.put(client, actions);
    }

    public LinkedList<Client> getClients() {
        return clients;
    }

    public HashMap<Client, ArrayList<String>> getTotalList(){
        return totalList;
    }

    public void run() {
        Client client;
        while (true) {
            try {
                while (getTotalList().size() == 0 || getClients().size() == 0 ) {
                    currentTime += new Random().nextInt(20) + 1;
                }
                client = getClients().getFirst();
                if(client.busy == false) {
                    client.busy = true;
                    if(client.gettSosire() > currentTime){ //caz in care ghiseul asteapta pana la venirea clientului
                        currentTime = client.gettSosire() + 1;
                    }
                    exec(client);
                    sterge_client();
                    client.deleteActions(totalList.get(client));
                    client.busy = false;
                } else {
                    sterge_client();
                    clients.add(1, client);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void exec(Client client) {
        for (int i = 0; i < totalList.get(client).size(); i++) {
            String actiune = totalList.get(client).get(i);
            if (client.getList().get(actiune) != null) {
                currentTime += client.getList().get(actiune);
                System.out.println("Client " + client.getId() + " a fost deservit la " + this.getName() + " operatia:  " + actiune);
                waitingTime.add((currentTime - client.gettSosire()) % 60 );
                systemTime.add((currentTime - client.gettSosire()) % 60  + client.getList().get(actiune));
            }
        }
    }


    public synchronized void adauga_client( Client c, Ghiseu g, ArrayList<String> actions) throws InterruptedException
    {
        for(Client client : clients){
            if(c == client){
                return;
            }
        }
        addClient(c, actions);
        //System.out.println("Clientul "+c.getId() + " a fost adaugat la "+g.getName());
        nrOfClients.add(clients.size());
        notifyAll();
    }

    public synchronized void sterge_client() throws InterruptedException
    {
        clients.removeFirst();
        notifyAll();
    }

    public synchronized long lungime_coada() throws InterruptedException{
        notifyAll();
        long size = clients.size();
        return size;
    }
    private boolean exist(String serviciu){
        for (int i = 0; i<listaServicii.size(); i++ ) {
            if(serviciu.equalsIgnoreCase(listaServicii.get(i))){
                return true;
            }
        }
        return false;
    }

    public void detAverageTimes(){
        int sum = 0;
        int max = 0;
        int i;
        System.out.println(this.getName());
        for(i = 0; i<nrOfClients.size(); i++){
            sum += nrOfClients.get(i);
        }
        System.out.println("Nr mediu de clienti: " + sum/nrOfClients.size());

        sum = 0;
        for(i = 0; i<waitingTime.size(); i++){
            sum += waitingTime.get(i);
        }
        System.out.println("Timp mediu de asteptare: " + sum/waitingTime.size());

        sum = 0;
        for(i = 0; i<systemTime.size(); i++){
            sum += systemTime.get(i);
        }
        System.out.println("Timp mediu de raspuns: " + sum/systemTime.size());

        max = nrOfClients.get(0);
        for(i = 1; i<nrOfClients.size(); i++){
           if(max < nrOfClients.get(i)){
               max = nrOfClients.get(i);
            }
        }
        System.out.println("Lungimea maxima a cozii de asteptare: " + max);
    }

}
