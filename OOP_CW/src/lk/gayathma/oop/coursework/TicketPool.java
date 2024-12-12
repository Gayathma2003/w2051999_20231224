package lk.gayathma.oop.coursework;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final int maximumTicketCapacity;
    private final Queue<Ticket> ticketQueue;

    public TicketPool(int maximumTicketCapacity) {
        this.maximumTicketCapacity = maximumTicketCapacity;
        this.ticketQueue = new LinkedList<>();
        loadTicketsFromFile();
    }

    public synchronized void addTicket(Ticket ticket) {
        while (ticketQueue.size() >= maximumTicketCapacity) {
            try {
                Logger.log("Pool is full. Waiting to release tickets.");
                wait(); //wait until there is a space in the ticket queue
            } catch (InterruptedException e) {
                Logger.log("Thread interrupted: "+e.getMessage());
            }
        }
        this.ticketQueue.add(ticket);
        Logger.log("Ticket added by - "+ Thread.currentThread().getName() + " Current Size - " + ticketQueue.size());
        saveTicketToFile(); // save ticket to the file
        notifyAll();
    }

    public synchronized Ticket removeTicket() {
        while (ticketQueue.isEmpty()) {
            try {
                Logger.log("Pool is empty. Waiting to purchase.");
                wait(); // wait until a ticket is available
            } catch (InterruptedException e) {
                Logger.log("Thread interrupted: "+e.getMessage());
            }
        }
        Ticket ticket = ticketQueue.poll();
        Logger.log("Ticket bought by - " + Thread.currentThread().getName() + " Current Size - " + ticketQueue.size() + " Ticket Details - " + ticket);
        saveTicketToFile(); // save ticket to the file
        notifyAll();
        return ticket;
    }

    private void saveTicketToFile(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ticketPool.dat"))){
            oos.writeObject(ticketQueue);
            Logger.log("Ticket Pool saved to file");
        } catch (IOException e) {
            Logger.log("Error saving "+e.getMessage());
        }
    }

    private void loadTicketsFromFile(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ticketPool.dat"))){
            Object obj = ois.readObject();
            if (obj instanceof Queue){
                ticketQueue.addAll((Queue<Ticket>) obj); // load saved tickets info
                Logger.log("Ticket pool loaded from file");
            }
        }catch (FileNotFoundException e){
            Logger.log("No previous ticket pool to display");
        }catch (IOException | ClassNotFoundException e){
            Logger.log("Error loading ticket pool: "+e.getMessage());
        }
    }


}