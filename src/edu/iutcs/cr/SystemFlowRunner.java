package edu.iutcs.cr;

import edu.iutcs.cr.persons.Buyer;
import edu.iutcs.cr.persons.Seller;
import edu.iutcs.cr.system.SystemDatabase;
import edu.iutcs.cr.vehicles.*;
import java.util.Optional;
import java.util.Scanner;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 * 
 * UI Controller for managing the car sales system flow.
 * Responsibilities: Gather user input and coordinate between UI and business logic.
 */
public class SystemFlowRunner {
    
    private static final Scanner scanner = new Scanner(System.in);
    private static final SystemDatabase database = SystemDatabase.getInstance();
    private static final MainMenu mainMenu = new MainMenu(scanner);

    public static void run() {
        System.out.println("Welcome to Car Hut");
        System.out.println("Loading existing system");
        System.out.println("Existing system loaded");

        while (true) {
            System.out.println("\n\n\n");
            int selectedOperation = mainMenu.showAndSelectOperation();

            if (selectedOperation == 9) {
                database.saveSystem();
                return;
            }

            handleMenuOperation(selectedOperation);
        }
    }

    /**
     * Route menu selection to appropriate handler
     */
    private static void handleMenuOperation(int operation) {
        switch (operation) {
            case 1 -> addNewSeller();
            case 2 -> addNewBuyer();
            case 3 -> addNewVehicle();
            case 4 -> showInventory();
            case 5 -> showSellerList();
            case 6 -> showBuyerList();
            case 7 -> createOrder();
            case 8 -> showInvoices();
        }
    }

    private static void addNewSeller() {
        System.out.println("\n\n\nAdd new seller");
        System.out.print("Enter seller name: ");
        String name = scanner.nextLine();
        System.out.print("Enter seller ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter seller email: ");
        String email = scanner.nextLine();

        Seller seller = new Seller(name, id, email);
        database.getSellers().add(seller);
        System.out.println("Seller added successfully!");
        promptToViewMainMenu();
    }

    private static void addNewBuyer() {
        System.out.println("\n\n\nAdd new customer");
        System.out.print("Enter buyer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter buyer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter buyer email: ");
        String email = scanner.nextLine();
        System.out.print("Enter payment method: ");
        String paymentMethod = scanner.nextLine();

        Buyer buyer = new Buyer(name, id, email, paymentMethod);
        database.getBuyers().add(buyer);
        System.out.println("Buyer added successfully!");
        promptToViewMainMenu();
    }

    private static void addNewVehicle() {
        System.out.println("\n\n\nAdd new vehicle");
        int vehicleType = selectVehicleType();

        String registrationNumber = getInput("Enter registration number: ");
        String make = getInput("Enter make: ");
        String model = getInput("Enter model: ");
        String year = getInput("Enter year: ");
        double price = getDoubleInput("Enter price: ");

        Vehicle newVehicle = createVehicleByType(vehicleType, registrationNumber, make, model, year, price);
        
        if (newVehicle != null) {
            database.getVehicles().add(newVehicle);
            System.out.println("Vehicle added successfully!");
        }
        
        promptToViewMainMenu();
    }

    private static int selectVehicleType() {
        System.out.println("Please select vehicle type:");
        System.out.println("1. Bus");
        System.out.println("2. Car");
        System.out.println("3. Hatchback");
        System.out.println("4. Sedan");
        System.out.println("5. SUV");

        int vehicleType = -1;
        while (vehicleType < 1 || vehicleType > 5) {
            System.out.print("Enter your choice (1-5): ");
            vehicleType = getIntInput(1, 5);
        }
        return vehicleType;
    }

    private static Vehicle createVehicleByType(int type, String regNum, String make, String model, String year, double price) {
        return switch (type) {
            case 1 -> {
                int capacity = getIntInput("Enter passenger capacity: ");
                yield new Bus(regNum, make, model, year, price, capacity);
            }
            case 2 -> {
                int capacity = getIntInput("Enter seating capacity: ");
                yield new Car(regNum, make, model, year, price, capacity);
            }
            case 3 -> {
                boolean isCompact = getBooleanInput("Is compact (true/false): ");
                yield new Hatchback(regNum, make, model, year, price, isCompact);
            }
            case 4 -> {
                boolean hasSunroof = getBooleanInput("Has sunroof (true/false): ");
                yield new Sedan(regNum, make, model, year, price, hasSunroof);
            }
            case 5 -> {
                boolean isOffRoad = getBooleanInput("Is off-road (true/false): ");
                yield new SUV(regNum, make, model, year, price, isOffRoad);
            }
            default -> null;
        };
    }

    private static void showInventory() {
        System.out.println("\n\n\nInventory list");
        database.showInventory();
        promptToViewMainMenu();
    }

    private static void showSellerList() {
        System.out.println("\n\n\nSeller's list");
        database.showSellerList();
        promptToViewMainMenu();
    }

    private static void showBuyerList() {
        System.out.println("\n\n\nCustomer's list");
        database.showBuyerList();
        promptToViewMainMenu();
    }

    private static void showInvoices() {
        System.out.println("\n\n\nInvoice list");
        database.showInvoices();
        promptToViewMainMenu();
    }

    private static void createOrder() {
        ShoppingCart cart = new ShoppingCart();

        while (true) {
            System.out.println("Please enter operation:");
            System.out.println("1. Add vehicle to cart");
            System.out.println("2. Remove vehicle from cart");
            System.out.println("3. View cart");
            System.out.println("4. Confirm purchase");
            System.out.println("5. Return to main menu");

            int operation = getIntInput(1, 5);

            switch (operation) {
                case 1 -> {
                    String regNum = getInput("Enter registration number: ");
                    if (cart.addItem(regNum)) {
                        System.out.println("Vehicle added to cart!");
                    } else {
                        System.out.println("Vehicle not available!");
                    }
                }
                case 2 -> {
                    String regNum = getInput("Enter registration number: ");
                    if (cart.removeItem(regNum)) {
                        System.out.println("Vehicle removed from cart!");
                    } else {
                        System.out.println("Vehicle not found in cart!");
                    }
                }
                case 3 -> cart.viewCart();
                case 4 -> {
                    createInvoice(cart);
                    return;
                }
                case 5 -> {
                    return;
                }
            }
        }
    }

    private static void createInvoice(ShoppingCart cart) {
        Buyer buyer = selectBuyer();
        if (buyer == null) return;

        Seller seller = selectSeller();
        if (seller == null) return;

        boolean isPaid = getBooleanInput("Is payment done (true/false): ");

        Invoice invoice = new Invoice(buyer, seller, cart, isPaid);
        invoice.printInvoice();
        database.getInvoices().add(invoice);
        System.out.println("Invoice created successfully!");
    }

    private static Buyer selectBuyer() {
        Buyer buyer = null;
        while (buyer == null) {
            String buyerId = getInput("Enter buyer ID: ");
            Optional<Buyer> result = database.findBuyerById(buyerId);
            
            if (result.isPresent()) {
                buyer = result.get();
            } else {
                System.out.println("Buyer not found. Try again!");
            }
        }
        return buyer;
    }

    private static Seller selectSeller() {
        Seller seller = null;
        while (seller == null) {
            String sellerId = getInput("Enter seller ID: ");
            Optional<Seller> result = database.findSellerById(sellerId);
            
            if (result.isPresent()) {
                seller = result.get();
            } else {
                System.out.println("Seller not found. Try again!");
            }
        }
        return seller;
    }

    private static void promptToViewMainMenu() {
        System.out.print("\n\nEnter 0 to view main menu: ");
        int val = -1;
        do {
            val = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        } while (val != 0);
    }

    // ===== Helper Input Methods =====

    private static String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    private static int getIntInput(int min, int max) {
        int value = -1;
        while (value < min || value > max) {
            System.out.print("Enter your choice (" + min + "-" + max + "): ");
            value = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        }
        return value;
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        double value = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        return value;
    }

    private static boolean getBooleanInput(String prompt) {
        System.out.print(prompt);
        boolean value = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline
        return value;
    }
}


// Broke Up God Class - Split massive methods into smaller, single-purpose methods
// Fixed Long Methods, addnewVehicle(), selectedVehicleType(),createVehicleByType(), createOrder(), createInvoice() were all broken down into smaller methods
// Replaced If-Else Chains with Switch Expression
//  Added Input Helper Methods