package lk.gayathma.oop.coursework;

public class Customer implements Runnable{
    private final int customerRetrievalRate;
    private final TicketPool ticketPool;
    private final int quantity;

    public Customer(int customerRetrievalRate, TicketPool ticketPool, int quantity) {
        this.customerRetrievalRate = customerRetrievalRate;
        this.ticketPool = ticketPool;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        for (int i = 0; i < quantity; i++){
            Ticket ticket = ticketPool.removeTicket();
            if (ticket == null){
                Logger.log(Thread.currentThread().getName() + "No more tickets available.");
                break;
            }

            try {
                Thread.sleep(customerRetrievalRate *1000);
            }catch (InterruptedException e){
                Logger.log(e.getMessage());
            }
        }
        Logger.log(Thread.currentThread().getName() + " has finished purchasing tickets!");
    }
}
