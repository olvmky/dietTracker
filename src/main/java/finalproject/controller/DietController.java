/**
 * This class represents the controller for managing diet-related functionalities.
 * It provides methods to update the diet table and text displayed in the diet panel based on user data.
 */
package finalproject.controller;

import finalproject.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class DietController extends JFrame {
    protected Function function = new Function();
    protected Calculation calculation = new Calculation();

    /**
     * Updates the diet table with data for the current user and date.
     *
     * @param userId The ID of the user.
     * @param dietFilePath The file path of the diet data.
     * @param dietBFTable The JTable to update with the diet data.
     */
    public void updateDietTable(int userId, String dietFilePath, JTable dietBFTable) {
        DefaultTableModel dietTable = (DefaultTableModel) dietBFTable.getModel();
        dietTable.setRowCount(0);
        List<String[]> lines = function.readFileContents(dietFilePath);
        LocalDate current = LocalDate.now();
        for(String[] i:lines){
            if(userId == Integer.parseInt(i[0]) && current.toString().equals(i[1])){
                dietTable.addRow(new Object[]{i[2], i[3], i[4], i[5], i[6], i[7], i[8]});
            }
        }
    }

    /**
     * Updates the text displayed in the diet panel based on user data.
     *
     * @param userId The ID of the user.
     * @param dietMidRightPanel The JPanel to update with the diet text.
     */
    public void updateDietText(int userId, JPanel dietMidRightPanel) {
        dietMidRightPanel.removeAll();
        Target target = function.targetConsumption(userId, 0);

        // Today's today consumption
        Target currTotalConsumption = function.consumptionByCategory(userId, 4);
        Target targetDiff = calculation.consumptionDiff(target, currTotalConsumption);
        double targetWeight = function.targetWeight(userId);

        JLabel targetHeading = new JLabel("ACCORDING TO YOUR TARGET WEIGHT " + targetWeight + " LBS");
        JLabel targetHeader = new JLabel("TARGET CONSUMPTION: ");
        targetHeading.setAlignmentX(Component.CENTER_ALIGNMENT);
        targetHeading.setFont(new Font("Arial", Font.BOLD, 12));
        targetHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        targetHeader.setFont(new Font("Arial", Font.BOLD, 12));
        dietMidRightPanel.add(targetHeading);
        dietMidRightPanel.add(targetHeader);

        double targetCarbVal = targetDiff.getCarb();
        double targetProteinVal = targetDiff.getProtein();
        double targetCaloriesVal = targetDiff.getCalories();
        double targetFatVal = targetDiff.getFat();
        String targetCarbStr = "CARB: " + targetCarbVal;
        String targetProteinStr = "PROTEIN: " + targetProteinVal;
        String targetCaloriesStr = "CALORIES: " + targetCaloriesVal;
        String targetFatStr = "FAT: " + targetFatVal;

        if(targetCarbVal <= 0)  targetCarbStr = "REACHED TARGET CARB";
        if(targetProteinVal <= 0)  targetProteinStr = "REACHED TARGET PROTEIN";
        if(targetCaloriesVal <= 0) targetCaloriesStr = "REACHED TARGET CALORIES";
        if(targetFatVal <= 0) targetFatStr = "REACHED TARGET FAT";

        if(targetCarbVal <= 0 && targetProteinVal <= 0 && targetCaloriesVal <= 0){
            targetCarbStr = "REACHED DAILY TARGET DIET!";
            targetProteinStr = "";
            targetCaloriesStr = "";
        }
        JLabel targetCarb = new JLabel(targetCarbStr);
        JLabel targetProtein = new JLabel(targetProteinStr);
        JLabel targetCalories = new JLabel(targetCaloriesStr);
        JLabel targetFat = new JLabel(targetFatStr);
        targetCarb.setAlignmentX(Component.CENTER_ALIGNMENT);
        targetProtein.setAlignmentX(Component.CENTER_ALIGNMENT);
        targetCalories.setAlignmentX(Component.CENTER_ALIGNMENT);
        targetFat.setAlignmentX(Component.CENTER_ALIGNMENT);

        dietMidRightPanel.add(targetCarb);
        dietMidRightPanel.add(targetProtein);
        dietMidRightPanel.add(targetCalories);
        dietMidRightPanel.add(targetFat);

        JLabel minHeading = new JLabel("MINIMUM CONSUMPTION FOR THE DAY: ");
        minHeading.setAlignmentX(Component.CENTER_ALIGNMENT);
        minHeading.setFont(new Font("Arial", Font.BOLD, 12));
        dietMidRightPanel.add(minHeading);

        Target minTarget = function.targetConsumption(userId, 1);
        Target minDiff = calculation.consumptionDiff(minTarget, currTotalConsumption);

        String miniCarb = "CARB: " + minDiff.getCarb();
        String miniProtein = "PROTEIN: " + minDiff.getProtein();
        String miniCalories = "CALORIES: " + minDiff.getCalories();
        String miniFat = "FAT: " + minDiff.getFat();
        if(minDiff.getCarb() < 0) miniCarb = "REACHED MIN CARB";
        if(minDiff.getProtein() < 0) miniProtein = "REACHED MIN PROTEIN";
        if(minDiff.getCalories() < 0) miniCalories = "REACHED MIN CALORIES";
        if(minDiff.getFat() < 0) miniFat = "REACHED MIN FAT";
        if(minDiff.getCarb() <= 0 && minDiff.getProtein() <= 0 && minDiff.getCalories() <= 0){
            miniCarb = "REACHED DAILY MIN DIET!";
            miniProtein = "";
            miniCalories = "";
        }
        JLabel minCarb = new JLabel(miniCarb);
        JLabel minProtein = new JLabel(miniProtein);
        JLabel minCalories = new JLabel(miniCalories);
        JLabel minFat = new JLabel(miniFat);

        minCarb.setAlignmentX(Component.CENTER_ALIGNMENT);
        minProtein.setAlignmentX(Component.CENTER_ALIGNMENT);
        minCalories.setAlignmentX(Component.CENTER_ALIGNMENT);
        minFat.setAlignmentX(Component.CENTER_ALIGNMENT);

        dietMidRightPanel.add(minCarb);
        dietMidRightPanel.add(minProtein);
        dietMidRightPanel.add(minCalories);
        dietMidRightPanel.add(minFat);
    }
}
