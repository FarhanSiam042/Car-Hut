package edu.iutcs.cr;

import edu.iutcs.cr.persons.Buyer;
import edu.iutcs.cr.persons.Seller;
import edu.iutcs.cr.vehicles.Vehicle;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class Invoice implements Serializable {

    private final Buyer buyer;
    private final Seller seller;
    private final ShoppingCart shoppingCart;
    private boolean isPaid;
    private final LocalDateTime dateTime;

    /**
     * Constructor for creating a new invoice with payment status
     */
    public Invoice(Buyer buyer, Seller seller, ShoppingCart shoppingCart, boolean isPaid) {
        this.buyer = buyer;
        this.seller = seller;
        this.shoppingCart = shoppingCart;
        this.isPaid = isPaid;
        this.dateTime = LocalDateTime.now();
        markVehiclesAsUnavailable();
    }

    /**
     * Constructor for deserialization
     */
    public Invoice() {
        this.buyer = null;
        this.seller = null;
        this.shoppingCart = null;
        this.dateTime = LocalDateTime.now();
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Seller getSeller() {
        return seller;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaymentStatus(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void printInvoice() {
        System.out.println("Buyer: " + this.buyer.toString());
        System.out.println("Seller: " + this.seller.toString());
        System.out.println("Payment Status: " + (isPaid ? "Paid" : "Due"));
        System.out.println("Date: " + dateTime.toLocalDate() + " Time: " + dateTime.toLocalTime());

        this.shoppingCart.viewCart();
    }

    /**
     * Marks all vehicles in the shopping cart as unavailable
     */
    private void markVehiclesAsUnavailable() {
        for (Vehicle vehicle : shoppingCart.getVehicles()) {
            vehicle.setUnavailable();
        }
    }
}

// Deleted Scanner that was never closed
// Removed takePayment() call from constructor
//  Created setPaymentStatus(boolean isPaid) method (SRP)
// Added Full Constructor - Invoice(buyer, seller, shoppingCart, isPaid)
// Added Getter Methods - For accessing all fields