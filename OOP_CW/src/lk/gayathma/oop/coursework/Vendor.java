package lk.gayathma.oop.coursework;

import java.math.BigDecimal;

public class Vendor implements Runnable{

    private final int ticketReleaseRate;
    private final TicketPool ticketPool;
    private final int totalTicketCount;

    public Vendor(int ticketReleaseRate, TicketPool ticketPool, int totalTicketCount) {
        this.ticketReleaseRate = ticketReleaseRate;
        this.ticketPool = ticketPool;
        this.totalTicketCount = totalTicketCount;
    }

    @Override
    public void run() {
        for (int i =1; i <= totalTicketCount; i++){
            Ticket ticket = new Ticket(i, new BigDecimal(i*5000));
            ticketPool.addTicket(ticket);

            try {
                Thread.sleep(ticketReleaseRate *1000);
            }catch (InterruptedException e){
                Logger.log(e.getMessage());
            }
        }
        Logger.log(Thread.currentThread().getName() + " has finished releasing tickets!");

    }
}
