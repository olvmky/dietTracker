package finalproject;

import java.time.LocalDate;

/**
 * Represents the daily weight entry of a user.
 */
public class DailyWeight {
    /**
     * The unique identifier of the daily weight entry.
     */
    protected int id;

    /**
     * The date of the daily weight entry.
     */
    protected LocalDate date;

    /**
     * The weight recorded for the day.
     */
    protected double todayWeight;

    /**
     * Constructs a new DailyWeight object with the specified parameters.
     *
     * @param id          The unique identifier of the daily weight entry.
     * @param date        The date of the daily weight entry.
     * @param todayWeight The weight recorded for the day.
     */
    public DailyWeight(int id, LocalDate date, double todayWeight) {
        this.id = id;
        this.date = date;
        this.todayWeight = todayWeight;
    }

    /**
     * Retrieves the unique identifier of the daily weight entry.
     *
     * @return The unique identifier of the daily weight entry.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the date of the daily weight entry.
     *
     * @return The date of the daily weight entry.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Retrieves the weight recorded for the day.
     *
     * @return The weight recorded for the day.
     */
    public double getWeight() {
        return todayWeight;
    }

    /**
     * Sets the weight recorded for the day.
     *
     * @param weight The weight to be set.
     */
    public void setWeight(double weight) {
        this.todayWeight = weight;
    }

    /**
     * Converts the DailyWeight object to a string representation.
     *
     * @return A string representation of the DailyWeight object.
     */
    @Override
    public String toString() {
        return id + "," + date + "," + todayWeight;
    }
}
