package edu.iutcs.cr.persons;

/**
 * @author Raian Rahman
 * @since 4/18/2024
 */
public class Seller extends Person {

    /**
     * Default constructor for deserialization
     */
    public Seller() {
        super();
    }

    /**
     * Constructor for lookup by ID
     */
    public Seller(String id) {
        super(id);
    }

    /**
     * Full constructor for creating a new seller
     */
    public Seller(String name, String id, String email) {
        super(name, id, email);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
