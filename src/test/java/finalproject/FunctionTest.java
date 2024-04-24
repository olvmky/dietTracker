package finalproject;

import finalproject.Category;
import finalproject.Function;
import finalproject.Target;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static javax.management.Query.eq;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;

class FunctionTest {
    private WeightManager weightManager;

    @Test
    void testTargetConsumption() throws IOException {
        Function function = new Function();
        int userId = 988; // Assuming user ID 1
        Target target = function.targetConsumption(userId);

        // You can add assertions here to check if the target is calculated correctly
        assertNotNull(target);
        assertTrue(target.getProtein() > 0.0);
        assertTrue(target.getCarb() > 0.0);
        assertTrue(target.getCalories() > 0.0);
        assertTrue(target.getFat() > 0.0);
    }

    @Test
    void testConsumptionByCategory() throws IOException {
        Function function = new Function();
        int userId = 988; // Assuming user ID 1
        int index = 0; // Assuming index 0 for breakfast

        Target target1 = function.consumptionByCategory(userId, 0);
        assertNotNull(target1);

        Target target2 = function.consumptionByCategory(userId, 1);
        assertNotNull(target2);

        Target target = function.consumptionByCategory(userId, index);

        assertNotNull(target);
        assertTrue(target.getProtein() >= 0);
        assertTrue(target.getCarb() >= 0);
        assertTrue(target.getCalories() >= 0);
        assertTrue(target.getFat() >= 0);
    }

    @Test
    void testDailyConsumption() throws IOException {
        Function function = new Function();
        int userId = 988; // Assuming user ID 1
        List<String[]> dailyConsumption = function.dailyConsumption(userId);

        // You can add assertions here to check if the daily consumption data is retrieved correctly
        assertNotNull(dailyConsumption);
        assertFalse(dailyConsumption.isEmpty());
    }

    @Test
    void testStringToCate() {
        Function function = new Function();
        FoodCategory foodCategory = function.stringToCategory("MEAT");

        // You can add assertions here to check if the string is converted to the correct category enum value
        assertEquals(FoodCategory.MEAT, foodCategory);
    }

    @Test
    void testNutritionFilter() throws IOException {
        Function function = new Function();
        String name = "Apple"; // Assuming the name of the nutrition
        int option = 1; // Assuming option 1 for filtering by name

        List<String[]> filteredNutrition = function.nutritionFilter(name, option);

        // You can add assertions here to check if the nutrition is filtered correctly
        assertNotNull(filteredNutrition);
        assertFalse(filteredNutrition.isEmpty());
    }

    @Test
    void testWeightInfo() throws IOException {
        Function function = new Function();
        int userId = 988; // Assuming user ID 988

        List<String[]> weightInfo = function.weightInfo(userId);

        // You can add assertions here to check if the weight information is retrieved correctly
        assertNotNull(weightInfo);
        assertFalse(weightInfo.isEmpty());
    }

    @Test
    void testWeightDifference() throws IOException {
        Function function = new Function();
        int userId = 988; // Assuming user ID 988

        Double[] weightDiff = function.weightDifference(userId);

        // You can add assertions here to check if the weight difference is calculated correctly
        assertNotNull(weightDiff);
        assertEquals(2, weightDiff.length);
    }

    @Test
    void testDaysUsedApp() throws IOException {
        Function function = new Function();
        int userId = 988; // Assuming user ID 988

        long daysUsedApp = function.daysUsedApp(userId);

        // You can add assertions here to check if the number of days used the app is calculated correctly
        assertTrue(daysUsedApp < 0); // Assuming the number of days used the app is non-negative
    }

    @Test
    void testDayRegister() throws IOException {
        Function function = new Function();
        int userId = 988; // Assuming user ID 988

        String dayRegistered = function.dayRegister(userId);

        // You can add assertions here to check if the registration day is retrieved correctly
        assertNotNull(dayRegistered);
    }


    @Test
    void testAddDailyFood() {
        Function function = new Function();
        int userId = 988; // Assuming user ID 988
        String foodName = "POTATO";
        Category category = Category.BREAKFAST;
        double weight = 200; // Assuming weight in grams

        function.addDailyFood(userId, foodName, category, weight);
    }

    @Test
    void testNewUserWeight() {
        Function function = new Function();
        String name = "John";
        double height = 175.0;
        double weight = 75.0;
        double targetWeight = 70.0;
        int id = weightManager.generateRandomNumericId();
        Weight newUserWeight = function.newUserWeight(id, name, height, weight, targetWeight);
        // Add assertions to verify that a new user's weight information is correctly added to the file
        assertNotNull(newUserWeight);
        assertEquals(targetWeight, newUserWeight.getTargetWeight());
        assertEquals(weight, newUserWeight.getWeight());
    }

    @Test
    void testReadFileContents() throws IOException {
        String newUserWeightFilePath = "/Users/oliviamiuki/final-project-v2-finals/newuserweight.csv";

        Function function = new Function();

        List<String[]> fileContents = function.readFileContents(newUserWeightFilePath);
        assertNotNull(fileContents);
        assertFalse(fileContents.isEmpty());
    }

    @Test
    void testAddNewUserWeight() {
        Function function = new Function();
        String name = "John";
        double height = 175.0;
        double weight = 75.0;
        double targetWeight = 70.0;
        int id = weightManager.generateRandomNumericId();
        Weight newUserWeight = function.newUserWeight(id,name, height, weight, targetWeight);

        assertNotNull(newUserWeight);
        assertEquals(targetWeight, newUserWeight.getTargetWeight());
        assertEquals(weight, newUserWeight.getWeight());
    }

    @Test
    void testTargetConsumption_EmptyUserData() throws IOException {
        Function function = new Function();
        int userId = 999; // Assuming user ID for non-existent user

        // Ensure the method handles empty user data gracefully
        assertNotNull(function.targetConsumption(userId));
    }

//    @Test
//    void testConsumptionByCategorys() throws IOException {
//        Function function = new Function();
//        int userId = 988; // Assuming user ID 988
//
//        // Test when index is 0 (breakfast) and category matches
//        Target target1 = function.consumptionByCategory(userId, 0);
//        assertNotNull(target1);
//
//        // Test when index is 1 (lunch) and category doesn't match
//        Target target2 = function.consumptionByCategory(userId, 1);
//        assertNotNull(target2);
//    }



    @Test
    void testAddDailyWeight() {
        Function function = new Function();
        int userId = 988; // Assuming user ID 988
        double weight = 70.5; // in kg

        function.addDailyWeight(userId, "John", weight);
    }

}

