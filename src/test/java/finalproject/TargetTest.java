package finalproject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TargetTest {

    @Test
    void testGetProtein() {
        double protein = 50.0;
        Target target = new Target(protein, 0.0, 0.0, 0.0);
        assertEquals(protein, target.getProtein());
    }

    @Test
    void testSetProtein() {
        double protein = 50.0;
        Target target = new Target(0.0, 0.0, 0.0, 0.0);
        target.setProtein(protein);
        assertEquals(protein, target.getProtein());
    }

    @Test
    void testGetCarb() {
        double carb = 200.0;
        Target target = new Target(0.0, carb, 0.0, 0.0);
        assertEquals(carb, target.getCarb());
    }

    @Test
    void testSetCarb() {
        double carb = 200.0;
        Target target = new Target(0.0, 0.0, 0.0, 0.0);
        target.setCarb(carb);
        assertEquals(carb, target.getCarb());
    }

    @Test
    void testGetCalories() {
        double calories = 2500.0;
        Target target = new Target(0.0, 0.0, calories, 0.0);
        assertEquals(calories, target.getCalories());
    }

    @Test
    void testSetCalories() {
        double calories = 2500.0;
        Target target = new Target(0.0, 0.0, 0.0, 0.0);
        target.setCalories(calories);
        assertEquals(calories, target.getCalories());
    }

    @Test
    void testGetFat() {
        double fat = 70.0;
        Target target = new Target(0.0, 0.0, 0.0, fat);
        assertEquals(fat, target.getFat());
    }

    @Test
    void testSetFat() {
        double fat = 70.0;
        Target target = new Target(0.0, 0.0, 0.0, 0.0);
        target.setFat(fat);
        assertEquals(fat, target.getFat());
    }

    @Test
    void testToString() {
        double protein = 50.0;
        double carb = 200.0;
        double calories = 2500.0;
        double fat = 70.0;
        String expectedString = "50.0,200.0,2500.0,70.0";
        Target target = new Target(protein, carb, calories, fat);
        assertEquals(expectedString, target.toString());
    }
}

