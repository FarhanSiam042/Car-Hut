package edu.iutcs.cr.system;

import edu.iutcs.cr.Invoice;
import edu.iutcs.cr.persons.Buyer;
import edu.iutcs.cr.persons.Seller;
import edu.iutcs.cr.vehicles.Vehicle;
import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class DataStore {
    
    private static final Logger LOGGER = Logger.getLogger(DataStore.class.getName());

    /**
     * Generic save method to eliminate code duplication
     */
    private <T> void save(String filename, Set<T> data) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(data);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to save data to " + filename, e);
        }
    }

    /**
     * Generic load method to eliminate code duplication
     */
    private <T> Set<T> load(String filename) {
        Set<T> data = new HashSet<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            data = (Set<T>) inputStream.readObject();
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "File not found or corrupted: " + filename + ". Creating new empty set.", e);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Failed to deserialize data from " + filename, e);
        }
        return data;
    }

    public void saveInvoices(Set<Invoice> invoices) {
        save("invoices.txt", invoices);
    }

    public Set<Invoice> loadInvoices() {
        return load("invoices.txt");
    }

    public void saveBuyers(Set<Buyer> buyers) {
        save("buyers.txt", buyers);
    }

    public Set<Buyer> loadBuyers() {
        return load("buyers.txt");
    }

    public void saveSellers(Set<Seller> sellers) {
        save("sellers.txt", sellers);
    }

    public Set<Seller> loadSellers() {
        return load("sellers.txt");
    }

    public void saveVehicles(Set<Vehicle> vehicles) {
        save("cars.txt", vehicles);
    }

    public Set<Vehicle> loadVehicles() {
        return load("cars.txt");
    }
}

//  Eliminated Code Duplication - Created generic save() and load() methods using Java Generics
// Simplified Public API - All save/load methods now just delegate to generic methods