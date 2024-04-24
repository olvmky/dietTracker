package finalproject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Manages various functions related to user data, such as creating new users, adding daily weight and food records,
 * retrieving target consumption, filtering nutrition data, and providing weight-related information.
 */
public class Function {
    protected static final String newUserWeightFilePath = "/Users/oliviamiuki/dietTracker/newuserweight.csv";
    protected static final String dailyWeightFilePath = "/Users/oliviamiuki/dietTracker/dailyweight.csv";
    protected static final String dailyFoodFilePath = "/Users/oliviamiuki/dietTracker/dailyfood.csv";
    protected static final String nutritionFilPath = "/Users/oliviamiuki/dietTracker/foodnutrition.csv";

    protected static Calculation calculation = new Calculation();

    /**
     * Creates a new user with body information, and adds their weight data to the user weight file.
     *
     * @param id           The unique identifier of the user.
     * @param name         The name of the user.
     * @param height       The height of the user.
     * @param weight       The weight of the user.
     * @param targetWeight The target weight of the user.
     * @return The newly created Weight object representing the user's weight data.
     */
    public static Weight newUserWeight(int id, String name, double height, double weight, double targetWeight) {
        LocalDate currentDate = LocalDate.now(); // Get today's date
        Weight newUser = new Weight(id, currentDate, name, height, weight, targetWeight);
        try (FileWriter writer = new FileWriter(newUserWeightFilePath, true);
             BufferedWriter bw = new BufferedWriter(writer);
             PrintWriter out = new PrintWriter(bw)) {
            if (!new File(newUserWeightFilePath).exists()) {
                out.println(newUserWeightFilePath);
            }
            out.println(newUser.toString());
            System.out.println("New user's weight info has been added successfully!");
            System.out.println(newUser.toString());
        } catch (IOException e) {
            System.out.println("Error occurred while adding new User: " + e.getMessage());
            e.printStackTrace();
        }
        return newUser;
    }

    /**
     * Adds daily weight data for a user to the daily weight file.
     *
     * @param id     The unique identifier of the user.
     * @param name   The name of the user.
     * @param weight The weight of the user for the current day.
     */
    public void addDailyWeight(int id, String name, double weight) {
        DailyWeight dailyWeight = null;
        try (FileWriter writer = new FileWriter(dailyWeightFilePath, true);
             BufferedWriter bw = new BufferedWriter(writer);
             PrintWriter out = new PrintWriter(bw)) {
            if (!new File(newUserWeightFilePath).exists()) {
                out.println(newUserWeightFilePath);
            }
            LocalDate date = LocalDate.now();
            dailyWeight = new DailyWeight(id, date, weight);
            out.println(dailyWeight.toString());
        } catch (IOException e) {
            System.out.println("Can't find daily weight file");
            e.printStackTrace();
        }
    }

    /**
     * Adds daily food consumption data for a user to the daily food file.
     *
     * @param userId   The unique identifier of the user.
     * @param ingre    The name of the ingredient consumed.
     * @param category The category of the consumed food.
     * @param weight   The weight of the consumed food.
     */
    public static void addDailyFood(int userId, String ingre, Category category, double weight) {
        DailyFood dailyFood = null;

        try (FileWriter writer = new FileWriter(dailyFoodFilePath, true);
             BufferedWriter bw = new BufferedWriter(writer);
             PrintWriter out = new PrintWriter(bw)) {
            if (!new File(dailyFoodFilePath).exists()) {
                out.println(dailyFoodFilePath);
            }
            LocalDate date = LocalDate.now();
            dailyFood = new DailyFood(userId, date, ingre, category, weight);
            List<String[]> foodNutrition = readFileContents(nutritionFilPath);
            String[] food = null;
            for (String[] i : foodNutrition) {
                if (i[1].equals(ingre) || i[1].contains(ingre)) {
                    food = i;
                    break;
                }
            }
            FoodConsumed foodConsumed = calculation.convertNutrition(dailyFood, food);
            out.println(foodConsumed.toString());
        } catch (IOException e) {
            System.out.println("Can't find daily weight file");
            e.printStackTrace();
        }
    }

    /**
     * Reads the contents of a file and returns them as a list of string arrays.
     *
     * @param filePath The path to the file to be read.
     * @return A list of string arrays containing the file contents.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static List<String[]> readFileContents(String filePath) throws IOException {
        List<String[]> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) { // Skip the header line
                    firstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                list.add(parts);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath + "\n" + e.getMessage());
        }
        return list;
    }

    /**
     * Retrieves the target consumption per day for a user based on their weight data.
     *
     * @param userId The unique identifier of the user.
     * @return The Target object representing the target consumption per day.
     * @throws IOException If an I/O error occurs while reading the user weight file.
     */
    public Target targetConsumption(int userId) throws IOException {
        List<String[]> data = readFileContents(newUserWeightFilePath);
        double targetWeight = 0;
        for(String[] i: data){
            if(i[0].equals(String.valueOf(userId))){
                targetWeight = Double.parseDouble(i[5]);
                break;
            }
        }
        double weight = calculation.lbsToKg(targetWeight);
        Target target = calculation.targetConsumption(weight);
        return target;
    }

    /**
     * Retrieves the consumption by a specific category (e.g., breakfast, lunch) for a user on the current day.
     *
     * @param userId The unique identifier of the user.
     * @param index  The index representing the category: 0 for breakfast, 1 for lunch, 2 for dinner, 3 for snack,
     *               and 4 for total consumption.
     * @return The Target object representing the consumption for the specified category.
     * @throws IOException If an I/O error occurs while reading the daily food file.
     */
    public Target consumptionByCategory(int userId, int index) throws IOException {
        List<String[]> data = readFileContents(dailyFoodFilePath);
        LocalDate currentDate = LocalDate.now();
        List<String[]> filteredData = new ArrayList<>();
        for(String[] i: data){
            if(i[0].equals(String.valueOf(userId)) && currentDate.toString().equals(i[1])){
                filteredData.add(i);
            }
        }
        double protein = 0, carb = 0, calories = 0, fat = 0;
        String category = null;
        if(index == 0) category = "BREAKFAST";
        else if(index == 1) category = "LUNCH";
        else if(index == 2) category = "DINNER";
        else if(index == 3) category = "SNACK";
        for(String[] i:filteredData){
            if(category != null && i[3].equals(category)){
                protein += Double.parseDouble(i[6]);
                carb += Double.parseDouble(i[7]);
                calories += Double.parseDouble(i[5]);
                fat += Double.parseDouble(i[8]);
            } else{
                protein += Double.parseDouble(i[6]);
                carb += Double.parseDouble(i[7]);
                calories += Double.parseDouble(i[5]);
                fat += Double.parseDouble(i[8]);
            }
        }
        Target newTarget = new Target(protein, carb, calories, fat);
        return newTarget;
    }

    /**
     * Retrieves the daily consumption data for a user.
     *
     * @param userId The unique identifier of the user.
     * @return A list of string arrays containing the daily consumption data for the user.
     * @throws IOException If an I/O error occurs while reading the daily food file.
     */
    public List<String[]> dailyConsumption(int userId) throws IOException {
        List<String[]> data = readFileContents(dailyFoodFilePath);
        List<String[]> filteredData = new ArrayList<>();
        for(String[] i: data){
            if(i[0].equals(String.valueOf(userId))){
                filteredData.add(i);
            }
        }
        return filteredData;
    }

    /**
     * Converts a string representation of a food category to the corresponding FoodCategory enum value.
     *
     * @param text The string representation of the food category.
     * @return The FoodCategory enum value corresponding to the input string.
     */
    public FoodCategory stringToCategory(String text){
        if(text.equals("MAIN")) return FoodCategory.MAIN;
        else if(text.equals("MEAT")) return FoodCategory.MEAT;
        else if(text.equals("DRINK")) return FoodCategory.DRINK;
        else return FoodCategory.MISC;
    }

    /**
     * Filters the nutrition data based on the given criteria.
     *
     * @param name   The criteria for filtering (e.g., category name).
     * @param option The option for filtering: 0 for category filter, 1 for name, 2 for carb > 15%, 3 for protein > 15%.
     * @return A list of string arrays containing the filtered nutrition data.
     * @throws IOException If an I/O error occurs while reading the nutrition file.
     */
    public List<String[]> nutritionFilter(String name, int option) throws IOException {
        name = name.toUpperCase();
        List<String[]> res = new ArrayList<>();
        List<String[]> list = readFileContents(nutritionFilPath);
        for(String[] i:list){
            if(option == 2 || option == 3){
                double curr = Double.parseDouble(i[option]);
                if(curr > 15) res.add(i);
            } else if(i[option].contains(name)) res.add(i);
            else if(option == 0 && name.equals("CATEGORY")){
                return list;
            }
        }
        return res;
    }

    /**
     * Retrieves the weight information for a user.
     *
     * @param userId The unique identifier of the user.
     * @return A list of string arrays containing the weight information for the user.
     * @throws IOException If an I/O error occurs while reading the daily weight file.
     */
    public List<String[]> weightInfo(int userId) throws IOException {
        List<String[]> data = readFileContents(dailyWeightFilePath);
        List<String[]> list = new ArrayList<>();
        for(String[] i:data){
            if(userId == Integer.parseInt(i[0])){
                list.add(i);
            }
        }
        return list;
    }

    /**
     * Calculates the difference in weight for a user between the first recorded day and the most recent day.
     *
     * @param userId The unique identifier of the user.
     * @return An array containing the weight difference between the first recorded day and the most recent day.
     *         Index 0 represents the difference from the first day, and index 1 represents the difference from the previous day.
     * @throws IOException If an I/O error occurs while reading the daily weight file.
     */
    public Double[] weightDifference(int userId) throws IOException {
        List<String[]> data = weightInfo(userId);
        if(data.size() < 2) return new Double[]{0.00, 0.00};
        double firstDayWeight = Double.parseDouble(data.get(0)[2]);
        double yesterdayWeight = Double.parseDouble(data.get(data.size() - 2)[2]);
        double currentWeight = Double.parseDouble(data.get(data.size() - 1)[2]);
        Double[] diff = new Double[2];
        diff[0] = Double.parseDouble(String.format("%.2f",currentWeight - firstDayWeight));
        diff[1] = Double.parseDouble(String.format("%.2f",currentWeight - yesterdayWeight));
        return diff;
    }

    /**
     * Calculates the number of days the user has used the application based on the recorded weight data.
     *
     * @param userId The unique identifier of the user.
     * @return The number of days the user has used the application.
     * @throws IOException If an I/O error occurs while reading the daily weight file.
     */
    public long daysUsedApp(int userId) throws IOException {
        List<String[]> data = weightInfo(userId);
        LocalDate currentDate = LocalDate.now();
        String firstDay = data.get(0)[1];
        LocalDate firstDayDate = LocalDate.parse(firstDay);
        long diff = ChronoUnit.DAYS.between(currentDate, firstDayDate);
        return diff;
    }

    /**
     * Retrieves the registration date of the user.
     *
     * @param userId The unique identifier of the user.
     * @return The registration date of the user.
     * @throws IOException If an I/O error occurs while reading the daily weight file.
     */
    public String dayRegister(int userId) throws IOException {
        List<String[]> data = weightInfo(userId);
        return data.get(0)[1];
    }
}
