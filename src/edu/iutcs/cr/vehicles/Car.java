package edu.iutcs.cr.vehicles;

/**
 * @author Raian Rahman
 * @since 4/18/2024
 */
public class Car extends Vehicle {

    private int seatingCapacity;

    /**
     * Default constructor for deserialization
     */
    public Car() {
        super();
    }

    /**
     * Full constructor for creating a new car
     */
    public Car(String registrationNumber, String make, String model, String year, double price, int seatingCapacity) {
        super(registrationNumber, make, model, year, price);
        this.seatingCapacity = seatingCapacity;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public String toString() {
        return "Car{" + super.toString() + ", " +
                "seatingCapacity=" + getSeatingCapacity() +
                "}";
    }
}
