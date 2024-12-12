package com.gayathma.oop.coursework.backend.OOP_CW_BE.service;

import com.gayathma.oop.coursework.backend.OOP_CW_BE.model.TicketConfig;
import com.gayathma.oop.coursework.backend.OOP_CW_BE.model.TicketPool;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TicketService {

    private final TicketPool ticketPool;
    private TicketConfig ticketConfig;

    public TicketService(TicketPool ticketPool, TicketConfig ticketConfig) {
        this.ticketPool = ticketPool;
        this.ticketConfig = ticketConfig;
    }

    public void setTicketConfig(TicketConfig ticketConfig) {
        this.ticketConfig = ticketConfig;
    }

    private final ConcurrentHashMap<String, VendorTask> vendorThreads = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Thread> customerThreads = new ConcurrentHashMap<>();
    private final AtomicInteger availableTickets = new AtomicInteger(0);

    private int maxVendors = 5;
    private int maxCustomers = 10;
    private boolean isRunning = false;

    private class VendorTask implements Runnable {
        private final String vendorId;
        private volatile boolean stopFlag = false;
        private int releasedTickets = 0;

        VendorTask(String vendorId) {
            this.vendorId = vendorId;
        }

        @Override
        public void run() {
            while (!stopFlag && releasedTickets < ticketConfig.getMaxTicketCapacity() && isRunning) {
                ticketPool.releaseTickets(1);
                releasedTickets++;

                try {
                    Thread.sleep(ticketConfig.getReleaseRateInSeconds() * 1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        public void stop() {
            stopFlag = true;
        }
    }

    private class CustomerTask implements Runnable {
        private final String customerId;
        private volatile boolean stopFlag = false;
        private int purchasedTickets = 0;

        CustomerTask(String customerId) {
            this.customerId = customerId;
        }

        @Override
        public void run() {
            while (!stopFlag &&purchasedTickets < ticketConfig.getTicketsPerCustomer() && isRunning) {
                if (ticketPool.purchaseTickets(1)) {
                    purchasedTickets++;
                }
                try {
                    Thread.sleep(ticketConfig.getRetrieveRateInSeconds() * 1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        public void stop() {
            stopFlag = true;
        }
    }

    public void startThreads() {
        if(ticketConfig == null) {
            System.out.println("TicketConfig is not set");
            return;
        }
        isRunning = true;
        for(int i = 0; i < maxVendors; i++) {
            startVendorThread();
        }

        try {
            Thread.sleep(5000);
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        for (int i = 0; i < maxCustomers; i++) {
            startCustomerThread();
        }
    }

    public void stopThreads() {
        isRunning = false;
        vendorThreads.values().forEach(VendorTask::stop);
        customerThreads.values().forEach(customerThreads::remove);
        vendorThreads.clear();
        customerThreads.clear();
    }

    public synchronized void startVendorThread() {
        String vendorId = "Vendor-" +(vendorThreads.size() + 1);
        VendorTask vendorTask = new VendorTask(vendorId);
        Thread vendorThread = new Thread(vendorTask);
        vendorThread.setName(vendorId);
        vendorThread.start();
        vendorThreads.put(vendorId,vendorTask);
    }

    public synchronized void startCustomerThread() {
        String customerId = "Customer-" +(customerThreads.size() + 1);
        CustomerTask customerTask = new CustomerTask(customerId);
        Thread customerThread = new Thread(customerTask);
        customerThread.setName(customerId);
        customerThread.start();
        customerThreads.put(customerId, customerThread);
    }
}

