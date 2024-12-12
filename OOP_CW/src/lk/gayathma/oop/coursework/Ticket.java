package lk.gayathma.oop.coursework;

import java.io.Serializable;
import java.math.BigDecimal;

public class Ticket implements Serializable {
    private int ticketId;
    private BigDecimal ticketPrice;

    public Ticket(int ticketId, BigDecimal ticketPrice) {
        this.ticketId = ticketId;
        this.ticketPrice = ticketPrice;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
