package lk.gayathma.oop.coursework;

import java.io.Serializable;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Configuration implements Serializable {

    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "totalTickets=" + totalTickets +
                ", ticketReleaseRate=" + ticketReleaseRate +
                ", customerRetrievalRate=" + customerRetrievalRate +
                ", maxTicketCapacity=" + maxTicketCapacity +
                '}';
    }

    public void saveInJson (String filePath){
        Map<String, Integer> configMap = new HashMap<>();
        configMap.put("Total tickets - ", totalTickets);
        configMap.put("Ticket release rate - ", ticketReleaseRate);
        configMap.put("Customer retrieval rate - ", customerRetrievalRate);
        configMap.put("Maximum Ticket Capacity - ", maxTicketCapacity);

        StringBuilder json = new StringBuilder("{\n");
        for (Map.Entry<String, Integer> entry : configMap.entrySet()){
            json.append(" \"").append(entry.getKey()).append("\": ").append(entry.getValue()).append(" \n");
        }
        json.replace(json.length() - 2, json.length(), "\n}");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            writer.write(json.toString());
            Logger.log("Configuration saved to JSON file successfully!");
        }catch (IOException e){
            Logger.log("Error saving: " +e.getMessage());
        }
    }
}
