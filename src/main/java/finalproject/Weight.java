package finalproject;

import java.time.LocalDate;

/**
 * Represents a user's weight data, extending the DailyWeight class.
 */
public class Weight{
    protected int id;
    protected String name;
    protected LocalDate date;// The name of the user
    protected double height;
    protected double weight;        // The weight of the user
    protected double targetWeight;  // The target weight of the user

    /**
     * Constructs a new Weight object.
     *
     * @param id           The unique identifier of the weight entry.
     * @param date         The date of the weight entry.
     * @param name         The name of the user.
     * @param height       The height of the user.
     * @param weight       The weight of the user.
     * @param targetWeight The target weight of the user.
     */
    public Weight(int id, LocalDate date, String name, double height, double weight, double targetWeight) {
        this.id = id;
        this.date = date;
        this.weight = weight;
        this.name = name;
        this.height = height;
        this.targetWeight = targetWeight;
    }

    /**
     * Retrieves the date
     *
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date
     *
     * @param date The date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Retrieves the id of the user.
     *
     * @return The id of the user.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the user.
     *
     * @param id The id of the user.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the weight of the user.
     *
     * @return The weight of the user.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the user.
     *
     * @param weight weight of the user.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Retrieves the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the height of the user.
     *
     * @return The height of the user.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height of the user.
     *
     * @param height The height of the user.
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Retrieves the target weight of the user.
     *
     * @return The target weight of the user.
     */
    public double getTargetWeight() {
        return targetWeight;
    }

    /**
     * Sets the target weight of the user.
     *
     * @param targetWeight The target weight of the user.
     */
    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }

    /**
     * Converts the Weight object to a string representation.
     *
     * @return A string representation of the Weight object.
     */
    @Override
    public String toString() {
        return id + "," + date + "," + name + "," + height + "," + weight + "," + targetWeight;
    }
}
