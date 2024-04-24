package finalproject;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class FoodConsumedTest {

    @Test
    void testConstructorAndGetters() {
        int id = 1;
        LocalDate date = LocalDate.now();
        String name = "Apple";
        Category category = Category.SNACK;
        double weight = 100.0;
        double calories = 95.0;
        double protein = 0.5;
        double carb = 25.0;
        double fat = 0.3;

        FoodConsumed foodConsumed = new FoodConsumed(id, date, name, category, weight, calories, protein, carb, fat);

        assertEquals(id, foodConsumed.getId());
        assertEquals(date, foodConsumed.getLocalDate());
        assertEquals(name, foodConsumed.getName());
        assertEquals(category, foodConsumed.getCategory());
        assertEquals(weight, foodConsumed.getWeight());
        assertEquals(calories, foodConsumed.getCalories());
        assertEquals(protein, foodConsumed.getProtein());
        assertEquals(carb, foodConsumed.getCarb());
        assertEquals(fat, foodConsumed.getFat());
    }

    @Test
    void testSetters() {
        FoodConsumed foodConsumed = new FoodConsumed(1, LocalDate.now(), "Apple", Category.SNACK, 100.0, 95.0, 0.5, 25.0, 0.3);

        foodConsumed.setId(2);
        foodConsumed.setLocalDate(LocalDate.of(2024, 4, 19));
        foodConsumed.setName("Banana");
        foodConsumed.setCategory(Category.SNACK);
        foodConsumed.setWeight(150);
        foodConsumed.setCalories(105.0);
        foodConsumed.setProtein(0.6);
        foodConsumed.setCarb(30.0);
        foodConsumed.setFat(0.4);

        assertEquals(2, foodConsumed.getId());
        assertEquals(LocalDate.of(2024, 4, 19), foodConsumed.getLocalDate());
        assertEquals("Banana", foodConsumed.getName());
        assertEquals(Category.SNACK, foodConsumed.getCategory());
        assertEquals(150.0, foodConsumed.getWeight());
        assertEquals(105.0, foodConsumed.getCalories());
        assertEquals(0.6, foodConsumed.getProtein());
        assertEquals(30.0, foodConsumed.getCarb());
        assertEquals(0.4, foodConsumed.getFat());
    }

    @Test
    void testToString() {
        FoodConsumed foodConsumed = new FoodConsumed(1, LocalDate.of(2024, 4, 19),
                "Apple", Category.SNACK, 100.0, 95.0, 0.5, 25.0, 0.3);

        String expectedToString = "1,2024-04-19,Apple,SNACK,100.0,95.0,0.5,25.0,0.3";
        assertEquals(expectedToString, foodConsumed.toString());
    }
}

