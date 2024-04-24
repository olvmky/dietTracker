package finalproject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class DailyWeightTest {

    @Test
    void testConstructorAndGetters() {
        LocalDate date = LocalDate.of(2024, 4, 19);
        DailyWeight dailyWeight = new DailyWeight(1, date, 70.5);

        assertEquals(1, dailyWeight.getId());
        assertEquals(date, dailyWeight.getDate());
        assertEquals(70.5, dailyWeight.getWeight());
    }

    @Test
    void testSetWeight() {
        LocalDate date = LocalDate.of(2024, 4, 19);
        DailyWeight dailyWeight = new DailyWeight(1, date, 70.5);

        dailyWeight.setWeight(72.0);

        assertEquals(72.0, dailyWeight.getWeight());
    }

    @Test
    void testToString() {
        LocalDate date = LocalDate.of(2024, 4, 19);
        DailyWeight dailyWeight = new DailyWeight(1, date, 70.5);

        String expectedToString = "1,2024-04-19,70.5";
        assertEquals(expectedToString, dailyWeight.toString());
    }
}

