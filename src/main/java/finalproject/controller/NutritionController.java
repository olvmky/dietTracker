/**
 * This class represents the controller for managing nutrition-related functionalities.
 * It provides methods to update the nutrition table with available data and to filter the data based on specified criteria.
 */
package finalproject.controller;

import finalproject.Function;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class NutritionController {
    protected Function function = new Function();

    /**
     * Updates the nutrition table with all available data.
     *
     * @param foodNutritionFilePath The file path of the food nutrition data.
     * @param nutritionTable The JTable to update with the nutrition data.
     */
    public void updateNutritionTable(String foodNutritionFilePath, JTable nutritionTable){
        DefaultTableModel nutTable = (DefaultTableModel) nutritionTable.getModel();
        nutTable.setRowCount(0);
        List<String[]> lines = function.readFileContents(foodNutritionFilePath);
        for(String[] i:lines){
            nutTable.addRow(new Object[]{i[0], i[1], i[2], i[3], i[4], i[5]});
        }
    }

    /**
     * Updates the nutrition table by filtering the data based on the specified name and option.
     *
     * @param text The text to filter by.
     * @param option The filtering option.
     * @param nutritionTable The JTable to update with the filtered nutrition data.
     */
    public void updateNutritionTableByName(String text, int option, JTable nutritionTable){
        List<String[]> lines = function.nutritionFilter(text, option);
        DefaultTableModel nutTable = (DefaultTableModel) nutritionTable.getModel();
        nutTable.setRowCount(0);
        for(String[] i:lines){
            nutTable.addRow(new Object[]{i[0], i[1], i[2], i[3], i[4], i[5]});
        }
    }
}
