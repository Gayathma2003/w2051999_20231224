package lk.gayathma.oop.coursework;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Logger.log("Application started!");
        Scanner scanner = new Scanner(System.in);

        try {

            int totalTickets = getValidInput(scanner, "Enter total number of tickets: ");
            int ticketReleaseRate = getValidInput(scanner, "Enter ticket release rate: ");
            int ticketRetrievalRate = getValidInput(scanner, "Enter customer retrieval rate: ");
            int maxTicketCapacity = getValidInput(scanner, "Enter maximum ticket capacity: ");
            int ticketsPerCustomer = getValidInput(scanner, "How many tickets one customer can buy: ");

            Configuration c = new Configuration();
            c.setTotalTickets(totalTickets);
            c.setTicketReleaseRate(ticketReleaseRate);
            c.setCustomerRetrievalRate(ticketRetrievalRate);
            c.setMaxTicketCapacity(maxTicketCapacity);

            c.saveInJson("Configuration.json");
            Logger.log("Configuration Saved successfully");

            TicketPool ticketPool = new TicketPool(maxTicketCapacity);

            Logger.log("Initializing vendors and customers");
            Vendor[] vendor = new Vendor[4];
            for (int i = 0; i < vendor.length; i++) {
                vendor[i] = new Vendor(ticketReleaseRate, ticketPool, totalTickets);
                Thread vendorThread = new Thread(vendor[i], "Vendor ID: " + i);
                vendorThread.start();
            }

            Customer[] customers = new Customer[8];
            for (int i = 0; i < customers.length; i++) {
                customers[i] = new Customer(ticketRetrievalRate, ticketPool, ticketsPerCustomer);
                Thread customerThread = new Thread(customers[i], "Customer ID: " + i);
                customerThread.start();
            }
            scanner.close();
        }catch (Exception e){
            Logger.log("Unexpected Error occurred: " + e.getMessage());
        }
    }

    private static int getValidInput(Scanner scanner, String  prompt){
        int input = 0;
        boolean isValid = false;
        while (!isValid){
            System.out.print(prompt);
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input > 0){
                    isValid = true;
                }else {
                    Logger.log("Invalid Input.");
                    System.out.println("Enter a number greater than 0.");
                }
            }catch (NumberFormatException e){
                Logger.log("Invalid Input");
                System.out.println("Invalid! Enter a valid number.");
            }
        }return input;
    }
}

