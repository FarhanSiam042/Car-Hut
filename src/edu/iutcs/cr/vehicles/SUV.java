package edu.iutcs.cr.vehicles;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class SUV extends Vehicle {

    private boolean isOffRoad;

    /**
     * Default constructor for deserialization
     */
    public SUV() {
        super();
    }

    /**
     * Full constructor for creating a new SUV
     */
    public SUV(String registrationNumber, String make, String model, String year, double price, boolean isOffRoad) {
        super(registrationNumber, make, model, year, price);
        this.isOffRoad = isOffRoad;
    }

    public boolean isOffRoad() {
        return isOffRoad;
    }

    public void setOffRoad(boolean isOffRoad) {
        this.isOffRoad = isOffRoad;
    }

    @Override
    public String toString() {
        return "SUV{" + super.toString() + ", " +
                "isOffRoad=" + isOffRoad() +
                "}";
    }
}
