package finalproject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FoodManagerTest {

    @Test
    void testAddNewFoodNutrition() {
        FoodManager foodManager = new FoodManager();

        String name = "Apple";
        double carb = 25.0;
        double protein = 0.5;
        double calories = 95.0;
        double fat = 0.3;

        foodManager.addNewFoodNutrition(FoodCategory.MISC, name, carb, protein, calories, fat);

    }

    @Test
    void testAddDailyFood() {
        FoodManager foodManager = new FoodManager();

        int id = 1;
        String name = "Apple";
        Category category = Category.SNACK;
        int weight = 100;

        foodManager.addDailyFood(id, name, category, weight);

    }

    @Test
    void testStrToCategory() {
        FoodManager foodManager = new FoodManager();

        String breakfastStr = "BREAKFAST";
        String lunchStr = "LUNCH";
        String dinnerStr = "DINNER";
        String snackStr = "SNACK";

        // Test converting string to category
        assertEquals(Category.BREAKFAST, foodManager.strToCategory(breakfastStr));
        assertEquals(Category.LUNCH, foodManager.strToCategory(lunchStr));
        assertEquals(Category.DINNER, foodManager.strToCategory(dinnerStr));
        assertEquals(Category.SNACK, foodManager.strToCategory(snackStr));

        // Test default value
        String unknownCategory = "UNKNOWN";
        assertEquals(Category.SNACK, foodManager.strToCategory(unknownCategory));
    }
}

