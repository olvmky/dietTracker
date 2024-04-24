package finalproject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class DailyFoodTest {

    @Test
    void testConstructorAndGetters() {
        LocalDate date = LocalDate.of(2024, 4, 19);
        Category category = Category.SNACK; // Assume Category enum is defined elsewhere
        DailyFood dailyFood = new DailyFood(1, date, "Apple", category, 100);

        assertEquals(1, dailyFood.getId());
        assertEquals(date, dailyFood.getLocalDate());
        assertEquals("Apple", dailyFood.getName());
        assertEquals(category, dailyFood.getCategory());
        assertEquals(100, dailyFood.getWeight());
    }

    @Test
    void testSetters() {
        LocalDate date = LocalDate.of(2024, 4, 19);
        Category category = Category.SNACK; // Assume Category enum is defined elsewhere
        DailyFood dailyFood = new DailyFood(1, date, "Apple", category, 100);

        LocalDate newDate = LocalDate.of(2024, 4, 20);
        Category newCategory = Category.SNACK; // Assume Category enum is defined elsewhere

        dailyFood.setId(2);
        dailyFood.setLocalDate(newDate);
        dailyFood.setName("Banana");
        dailyFood.setCategory(newCategory);
        dailyFood.setWeight(150);

        assertEquals(2, dailyFood.getId());
        assertEquals(newDate, dailyFood.getLocalDate());
        assertEquals("Banana", dailyFood.getName());
        assertEquals(newCategory, dailyFood.getCategory());
        assertEquals(150, dailyFood.getWeight());
    }

    @Test
    void testToString() {
        LocalDate date = LocalDate.of(2024, 4, 19);
        Category category = Category.LUNCH; // Assume Category enum is defined elsewhere
        DailyFood dailyFood = new DailyFood(1, date, "Rice", category, 200);

        String expectedToString = "1,2024-04-19,Rice,LUNCH,200.0";
        assertEquals(expectedToString, dailyFood.toString());
    }
}

