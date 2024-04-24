package finalproject;

/**
 * Represents the target nutrition values for a user, including protein, carbohydrates, calories, and fat.
 */
public class Target {
    protected double protein;  // The target protein value
    protected double carb;     // The target carbohydrate value
    protected double calories; // The target calorie value
    protected double fat;      // The target fat value

    /**
     * Constructs a new Target object with specified nutrition values.
     *
     * @param protein   The target protein value.
     * @param carb      The target carbohydrate value.
     * @param calories  The target calorie value.
     * @param fat       The target fat value.
     */
    public Target(double protein, double carb, double calories, double fat) {
        this.protein = protein;
        this.carb = carb;
        this.calories = calories;
        this.fat = fat;
    }

    /**
     * Retrieves the target protein value.
     *
     * @return The target protein value.
     */
    public double getProtein() {
        return protein;
    }

    /**
     * Sets the target protein value.
     *
     * @param protein The target protein value.
     */
    public void setProtein(double protein) {
        this.protein = protein;
    }

    /**
     * Retrieves the target carbohydrate value.
     *
     * @return The target carbohydrate value.
     */
    public double getCarb() {
        return carb;
    }

    /**
     * Sets the target carbohydrate value.
     *
     * @param carb The target carbohydrate value.
     */
    public void setCarb(double carb) {
        this.carb = carb;
    }

    /**
     * Retrieves the target calorie value.
     *
     * @return The target calorie value.
     */
    public double getCalories() {
        return calories;
    }

    /**
     * Sets the target calorie value.
     *
     * @param calories The target calorie value.
     */
    public void setCalories(double calories) {
        this.calories = calories;
    }

    /**
     * Retrieves the target fat value.
     *
     * @return The target fat value.
     */
    public double getFat() {
        return fat;
    }

    /**
     * Sets the target fat value.
     *
     * @param fat The target fat value.
     */
    public void setFat(double fat) {
        this.fat = fat;
    }

    /**
     * Converts the Target object to a string representation.
     *
     * @return A string representation of the Target object, containing protein, carb, calories, and fat values.
     */
    @Override
    public String toString() {
        return protein + "," + carb + "," + calories + "," + fat;
    }
}
