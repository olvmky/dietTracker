package finalproject;

import java.io.*;
import java.time.LocalDate;

/**
 * Manages food-related operations such as adding new food nutrition and recording daily food consumption.
 */
public class FoodManager {
    // Path to the file containing food nutrition data
    protected final String foodNutritionFilePath = "/Users/oliviamiuki/final-project-v2-finals/foodnutrition.csv";

    /**
     * Adds new food nutrition information and updates the corresponding file.
     *
     * @param foodCategory The category of the food.
     * @param name         The name of the food.
     * @param carb         The carbohydrate content of the food.
     * @param protein      The protein content of the food.
     * @param calories     The calorie content of the food.
     * @param fat          The fat content of the food.
     */
    public void addNewFoodNutrition(FoodCategory foodCategory, String name,
                                    double carb, double protein, double calories, double fat) {
        // Convert the name to uppercase
        name = name.toUpperCase();
        // Create a new Food object
        Food newFood = new Food(foodCategory, name, carb, protein, calories, fat);
        // Add new food info to the local file
        try (FileWriter writer = new FileWriter(foodNutritionFilePath, true);
             BufferedWriter bw = new BufferedWriter(writer);
             PrintWriter out = new PrintWriter(bw)) {
            // Check if the file exists
            if (!new File(foodNutritionFilePath).exists()) {
                out.println(foodNutritionFilePath);
            }
            // Write the food info to the file
            out.println(newFood.toString());
            System.out.println("Food information has been added successfully!");
            System.out.println(newFood.toString());
        } catch (IOException e) {
            System.out.println("Error occurred while adding new food: " + e.getMessage());
        }
    }

    /**
     * Records the consumption of food for each meal.
     *
     * @param id       The unique identifier of the user.
     * @param name     The name of the food consumed.
     * @param category The category of the food consumed.
     * @param weight   The weight of the food consumed.
     */
    public void addDailyFood(int id, String name, Category category, int weight) {
        // Get the current date
        LocalDate localDate = LocalDate.now();
        // Convert the name to lowercase
        name = name.toLowerCase();
        // Create a new DailyFood object
        DailyFood dailyFood = new DailyFood(id, localDate, name, category, weight);
        // Additional logic for handling daily food consumption (not provided in the original code)
    }

    /**
     * Converts a string representation of a category to the corresponding Category enum value.
     * Default value is SNACK.
     *
     * @param category A string representing the category.
     * @return The corresponding Category enum value.
     */
    public Category strToCategory(String category) {
        if (category.equals("BREAKFAST")) return Category.BREAKFAST;
        else if (category.equals("LUNCH")) return Category.LUNCH;
        else if (category.equals("DINNER")) return Category.DINNER;
        else return Category.SNACK;
    }
}
