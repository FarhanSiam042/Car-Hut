package edu.iutcs.cr.system;

import edu.iutcs.cr.Invoice;
import edu.iutcs.cr.persons.Buyer;
import edu.iutcs.cr.persons.Person;
import edu.iutcs.cr.persons.Seller;
import edu.iutcs.cr.vehicles.Vehicle;
import java.io.Serializable;
import static java.util.Objects.isNull;
import java.util.Optional;
import java.util.Set;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 * 
 * Singleton database for managing all system entities.
 * WARNING: Singleton pattern makes testing difficult. Consider using dependency injection.
 */
public class SystemDatabase implements Serializable {

    private Set<Buyer> buyers;
    private Set<Seller> sellers;
    private Set<Vehicle> vehicles;
    private Set<Invoice> invoices;

    private static SystemDatabase instance;
    private static final DataStore dataStore = new DataStore();

    private SystemDatabase() {
        buyers = dataStore.loadBuyers();
        sellers = dataStore.loadSellers();
        vehicles = dataStore.loadVehicles();
        invoices = dataStore.loadInvoices();
    }

    public static SystemDatabase getInstance() {
        if (isNull(instance)) {
            instance = new SystemDatabase();
        }
        return instance;
    }

    public void saveSystem() {
        dataStore.saveBuyers(buyers);
        dataStore.saveSellers(sellers);
        dataStore.saveVehicles(vehicles);
        dataStore.saveInvoices(invoices);
    }

    public Set<Buyer> getBuyers() {
        return buyers;
    }

    public Set<Seller> getSellers() {
        return sellers;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    /**
     * Generic display method to eliminate code duplication
     */
    private <T> void displayCollection(String emptyMessage, Set<T> collection) {
        if (collection.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }
        collection.forEach(System.out::println);
    }

    public void showInventory() {
        displayCollection("No vehicles is present in system", vehicles);
    }

    public void showBuyerList() {
        displayCollection("No buyer is present in system", buyers);
    }

    public void showSellerList() {
        displayCollection("No seller is present in system", sellers);
    }

    public void showInvoices() {
        if (invoices.isEmpty()) {
            System.out.println("No invoice found in system");
            return;
        }
        invoices.forEach(invoice -> {
            invoice.printInvoice();
            System.out.println("\n\n\n");
        });
    }

    /**
     * Generic find method to eliminate code duplication
     */
    private <T> Optional<T> findById(Set<T> collection, String id) {
        return collection.stream()
                .filter(item -> item.equals(new Person(id)))
                .map(item -> (T) item)
                .findFirst();
    }

    public Optional<Vehicle> findVehicleByRegistrationNumber(String registrationNumber) {
        return vehicles.stream()
                .filter(vehicle -> vehicle.equals(new Vehicle(registrationNumber)))
                .findFirst();
    }

    public Optional<Buyer> findBuyerById(String id) {
        return buyers.stream()
                .filter(buyer -> buyer.equals(new Buyer(id)))
                .findFirst();
    }

    public Optional<Seller> findSellerById(String id) {
        return sellers.stream()
                .filter(seller -> seller.equals(new Seller(id)))
                .findFirst();
    }
}

// Eliminated Code Duplication - Created generic displayCollection()  method to replace showInventory(), showBuyerList(), showSellerList()
// Replaced Null Returns with Optional
//Changed return types from nullable objects to Optional<T> findVehicleByRegistrationNumber(), findBuyerById(), findSellerById() now return Optional