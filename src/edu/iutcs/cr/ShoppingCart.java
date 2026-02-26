package edu.iutcs.cr;

import edu.iutcs.cr.system.SystemDatabase;
import edu.iutcs.cr.vehicles.Vehicle;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class ShoppingCart implements Serializable {

    private final Set<Vehicle> vehicles;
    private final SystemDatabase database;

    /**
     * Constructor for creating a new shopping cart
     */
    public ShoppingCart() {
        this.vehicles = new HashSet<>();
        database = SystemDatabase.getInstance();
    }

    public Set<Vehicle> getVehicles() {
        return this.vehicles;
    }

    /**
     * Add a vehicle to the cart by registration number
     * 
     * @param registrationNumber the registration number of the vehicle
     * @return true if item was added, false if vehicle not available or not found
     */
    public boolean addItem(String registrationNumber) {
        Optional<Vehicle> vehicle = database.findVehicleByRegistrationNumber(registrationNumber);

        if (vehicle.isEmpty() || !vehicle.get().isAvailable()) {
            return false;
        }

        vehicles.add(vehicle.get());
        return true;
    }

    /**
     * Remove a vehicle from the cart by registration number
     * 
     * @param registrationNumber the registration number of the vehicle
     * @return true if item was removed, false if not found in cart
     */
    public boolean removeItem(String registrationNumber) {
        Vehicle vehicleToRemove = new Vehicle(registrationNumber);
        return vehicles.remove(vehicleToRemove);
    }

    /**
     * Display the contents of the shopping cart
     */
    public void viewCart() {
        System.out.println("\n\nShopping cart\n\n");

        if (vehicles.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        vehicles.forEach(System.out::println);
    }

    /**
     * Get the number of items in the cart
     */
    public int getCartSize() {
        return vehicles.size();
    }

    /**
     * Clear all items from the cart
     */
    public void clearCart() {
        vehicles.clear();
    }
}

//  Deleted all Scanner objects from addItem() and removeItem()
//   Separated I/O from business logic .
// addItem() now accepts registration number as parameter instead of reading from console
// removeItem() now accepts registration number as parameter instead of reading from console
// Added boolean return types
// Added Utility Methods


