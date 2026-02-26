package edu.iutcs.cr.persons;

/**
 * @author Raian Rahman
 * @since 4/18/2024
 */
public class Buyer extends Person {

    private String paymentMethod;

    /**
     * Default constructor for deserialization
     */
    public Buyer() {
        super();
    }

    /**
     * Constructor for lookup by ID
     */
    public Buyer(String id) {
        super(id);
    }

    /**
     * Full constructor for creating a new buyer
     */
    public Buyer(String name, String id, String email, String paymentMethod) {
        super(name, id, email);
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", paymentMethod='" + paymentMethod + '\'';
    }
}
