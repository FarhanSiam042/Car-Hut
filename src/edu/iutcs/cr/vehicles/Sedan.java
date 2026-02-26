package edu.iutcs.cr.vehicles;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class Sedan extends Vehicle {

    private boolean hasSunroof;

    /**
     * Default constructor for deserialization
     */
    public Sedan() {
        super();
    }

    /**
     * Full constructor for creating a new sedan
     */
    public Sedan(String registrationNumber, String make, String model, String year, double price, boolean hasSunroof) {
        super(registrationNumber, make, model, year, price);
        this.hasSunroof = hasSunroof;
    }

    public boolean hasSunroof() {
        return hasSunroof;
    }

    public void setHasSunroof(boolean hasSunroof) {
        this.hasSunroof = hasSunroof;
    }

    @Override
    public String toString() {
        return "Sedan{" + super.toString() + ", " +
                "hasSunroof=" + hasSunroof() +
                "}";
    }
}
