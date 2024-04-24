package finalproject;

import java.time.LocalDate;

/**
 * Provides methods for various calculations related to user's weight, nutrition, and consumption.
 */
public class Calculation {
    protected Weight weight;
    protected double protein;
    protected double carb;
    protected double calories;
    protected double fat;

    /**
     * Calculates the target consumption of calories, carbs, protein, and fat per day based on weight.
     *
     * @param weight The weight of the user
     * @return The target consumption for the user
     */
    public Target targetConsumption(double weight){
        carb = weight * 4;
        protein = weight * 2;
        fat = weight * 1;
        calories = ((weight * 48.5 + 2954.7) / 4.184) * 1.6 + 500;
        Target target = new Target(protein, carb, calories, fat);
        return target;
    }

    public Target minConsumption(double weight){
        carb = weight * 2;
        protein = weight * 1;
        fat = weight * 1;
        calories = weight * 10 + 167.5*6.25 - 29 * 5 - 161;
        Target target = new Target(protein, carb, calories, fat);
        return target;
    }

    /**
     * Calculates the difference between the target consumption and current consumption.
     *
     * @param target The target consumption
     * @param currentConsumption The current consumption
     * @return The difference between target and current consumption
     */
    public Target consumptionDiff(Target target, Target currentConsumption){
        double protein = target.getProtein() - currentConsumption.getProtein();
        protein = Double.parseDouble(String.format("%.2f", protein));
        double carb = target.getCarb() - currentConsumption.getCarb();
        carb = Double.parseDouble(String.format("%.2f", carb));
        double calories = target.getCalories() - currentConsumption.getCalories();
        calories = Double.parseDouble(String.format("%.2f", calories));
        double fat = target.getFat() - currentConsumption.getFat();
        fat = Double.parseDouble(String.format("%.2f", fat));
        Target diff = new Target(protein, carb, calories, fat);
        return diff;
    }


    /**
     * Calculates weight comparison based on weight changes.
     *
     * @param weightBefore Weight before the comparison period
     * @param currentWeight Current weight
     * @return A string representing weight comparison
     */
    public String weightComparison(int weightBefore, int currentWeight){
        int changes = currentWeight - weightBefore;
        StringBuilder comparison = new StringBuilder();
        if(changes < 0){
            comparison.append("You have lost " + changes + " lbs");
        } else{
            comparison.append("You have gained " + changes +" lbs");
        }
        return comparison.toString();
    }

    /**
     * Calculates food's nutrition based on weight and nutrition values.
     *
     * @param dailyFood The food consumed
     * @param food The nutrition values of the food
     * @return The consumed food with calculated nutrition
     */
    public FoodConsumed convertNutrition(DailyFood dailyFood, String[] food){
        double proteinMultiplier = Double.parseDouble(food[3]);
        double carbMultiplier = Double.parseDouble(food[2]);
        double caloriesMultiplier = Double.parseDouble(food[4]);
        double fatMultiplier = Double.parseDouble(food[5]);
        double multiplier = dailyFood.getWeight() / 100;
        LocalDate date = LocalDate.now();

        double weight = dailyFood.getWeight();
        weight = Double.parseDouble(String.format("%.2f", weight));
        double protein = proteinMultiplier * multiplier;
        protein = Double.parseDouble(String.format("%.2f", protein));
        double carb = carbMultiplier * multiplier;
        carb = Double.parseDouble(String.format("%.2f", carb));
        double fat = fatMultiplier * multiplier;
        fat = Double.parseDouble(String.format("%.2f", fat));
        double calories = caloriesMultiplier * multiplier;
        calories = Double.parseDouble(String.format("%.2f", calories));
        FoodConsumed foodConsumed = new FoodConsumed(dailyFood.getId(), date, dailyFood.getName(),
                dailyFood.getCategory(), weight, calories, protein, carb, fat);
        return foodConsumed;
    }

    /**
     * Converts weight from pounds to kilograms.
     *
     * @param weight Weight in pounds
     * @return Weight in kilograms
     */
    public double lbsToKg(double weight){
        return weight * 0.454;
    }

}
