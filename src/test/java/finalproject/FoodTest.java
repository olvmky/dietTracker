package finalproject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    @Test
    void testConstructorAndGetters() {
        FoodCategory foodCategory = FoodCategory.MISC;
        String name = "Apple";
        double protein = 0.5;
        double carb = 25.0;
        double calories = 95.0;
        double fat = 0.3;

        Food food = new Food(foodCategory, name, protein, carb, calories, fat);

        assertEquals(foodCategory, food.getFoodCategory());
        assertEquals(name, food.getName());
        assertEquals(protein, food.getProtein());
        assertEquals(carb, food.getCarb());
        assertEquals(calories, food.getCalories());
        assertEquals(fat, food.getFat());
    }

    @Test
    void testSetters() {
        Food food = new Food(FoodCategory.MISC, "Apple", 0.5, 25.0, 95.0, 0.3);

        food.setFoodCategory(FoodCategory.MISC);
        food.setName("Spinach");
        food.setProtein(2.0);
        food.setCarb(1.0);
        food.setCalories(23.0);
        food.setFat(0.4);

        assertEquals(FoodCategory.MISC, food.getFoodCategory());
        assertEquals("Spinach", food.getName());
        assertEquals(2.0, food.getProtein());
        assertEquals(1.0, food.getCarb());
        assertEquals(23.0, food.getCalories());
        assertEquals(0.4, food.getFat());
    }

    @Test
    void testToString() {
        Food food = new Food(FoodCategory.MISC, "Apple", 0.5, 25.0, 95.0, 0.3);

        String expectedToString = "MISC,Apple,0.5,25.0,95.0,0.3";
        assertEquals(expectedToString, food.toString());
    }
}

