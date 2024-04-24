package finalproject;

import java.time.LocalDate;

/**
 * Represents food consumed by a user on a specific date, extending the DailyFood class.
 */
public class FoodConsumed extends DailyFood {
    protected double calories;
    protected double protein;
    protected double carb;
    protected double fat;

    /**
     * Constructs a new FoodConsumed object with specified parameters.
     *
     * @param id       The unique identifier of the user.
     * @param date     The date when the food was consumed.
     * @param name     The name of the food.
     * @param category The category of the food.
     * @param weight   The weight of the food consumed.
     * @param calories The calorie content of the food.
     * @param protein  The protein content of the food.
     * @param carb     The carbohydrate content of the food.
     * @param fat      The fat content of the food.
     */
    public FoodConsumed(int id, LocalDate date, String name, Category category,
                        double weight, double calories, double protein, double carb, double fat) {
        super(id, date, name, category, weight);
        this.calories = calories;
        this.protein = protein;
        this.carb = carb;
        this.fat = fat;
    }

    /**
     * Retrieves the calorie content of the food.
     *
     * @return The calorie content of the food.
     */
    public double getCalories() {
        return calories;
    }

    /**
     * Sets the calorie content of the food.
     *
     * @param calories The calorie content of the food.
     */
    public void setCalories(double calories) {
        this.calories = calories;
    }

    /**
     * Retrieves the protein content of the food.
     *
     * @return The protein content of the food.
     */
    public double getProtein() {
        return protein;
    }

    /**
     * Sets the protein content of the food.
     *
     * @param protein The protein content of the food.
     */
    public void setProtein(double protein) {
        this.protein = protein;
    }

    /**
     * Retrieves the carbohydrate content of the food.
     *
     * @return The carbohydrate content of the food.
     */
    public double getCarb() {
        return carb;
    }

    /**
     * Sets the carbohydrate content of the food.
     *
     * @param carb The carbohydrate content of the food.
     */
    public void setCarb(double carb) {
        this.carb = carb;
    }

    /**
     * Retrieves the fat content of the food.
     *
     * @return The fat content of the food.
     */
    public double getFat() {
        return fat;
    }

    /**
     * Sets the fat content of the food.
     *
     * @param fat The fat content of the food.
     */
    public void setFat(double fat) {
        this.fat = fat;
    }

    /**
     * Converts the FoodConsumed object to a string representation.
     *
     * @return A string representation of the FoodConsumed object.
     */
    @Override
    public String toString() {
        return id + "," + localDate + "," + name + "," + category +
                "," + weight + "," + calories + "," + protein +
                "," + carb + "," + fat;
    }
}
