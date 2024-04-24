package finalproject;

import java.time.LocalDate;

/**
 * Represents the daily food consumption of a user.
 */
public class DailyFood {
    /**
     * The unique identifier of the daily food entry.
     */
    protected int id;

    /**
     * The date of the daily food entry.
     */
    protected LocalDate localDate;

    /**
     * The name of the food item.
     */
    protected String name;

    /**
     * The category of the food item.
     */
    protected Category category;

    /**
     * The weight of the food item consumed.
     */
    protected double weight;

    /**
     * Constructs a new DailyFood object with the specified parameters.
     *
     * @param id       The unique identifier of the daily food entry.
     * @param date     The date of the daily food entry.
     * @param name     The name of the food item.
     * @param category The category of the food item.
     * @param weight   The weight of the food item consumed.
     */
    public DailyFood(int id, LocalDate date, String name, Category category, double weight) {
        this.id = id;
        this.localDate = date;
        this.name = name;
        this.category = category;
        this.weight = weight;
    }

    /**
     * Retrieves the date of the daily food entry.
     *
     * @return The date of the daily food entry.
     */
    public LocalDate getLocalDate() {
        return localDate;
    }

    /**
     * Sets the date of the daily food entry.
     *
     * @param localDate The date to be set.
     */
    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    /**
     * Retrieves the unique identifier of the daily food entry.
     *
     * @return The unique identifier of the daily food entry.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the daily food entry.
     *
     * @param id The identifier to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the food item.
     *
     * @return The name of the food item.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the food item.
     *
     * @param name The name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the category of the food item.
     *
     * @return The category of the food item.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the category of the food item.
     *
     * @param category The category to be set.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Retrieves the weight of the food item consumed.
     *
     * @return The weight of the food item consumed.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the food item consumed.
     *
     * @param weight The weight to be set.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Converts the DailyFood object to a string representation.
     *
     * @return A string representation of the DailyFood object.
     */
    @Override
    public String toString() {
        return id + "," + localDate + "," + name + "," + category + "," + weight;
    }
}

