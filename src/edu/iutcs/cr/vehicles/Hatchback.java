package edu.iutcs.cr.vehicles;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class Hatchback extends Vehicle {

    private boolean isCompact;

    /**
     * Default constructor for deserialization
     */
    public Hatchback() {
        super();
    }

    /**
     * Full constructor for creating a new hatchback
     */
    public Hatchback(String registrationNumber, String make, String model, String year, double price, boolean isCompact) {
        super(registrationNumber, make, model, year, price);
        this.isCompact = isCompact;
    }

    public boolean isCompact() {
        return isCompact;
    }

    public void setCompact(boolean isCompact) {
        this.isCompact = isCompact;
    }

    @Override
    public String toString() {
        return "Hatchback{" + super.toString() + ", " +
                "isCompact=" + isCompact() +
                "}";
    }
}
