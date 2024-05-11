/**
 * This class represents the main controller for managing user commands in the final project.
 * It extends JFrame and provides methods for updating existing user commands.
 */
package finalproject.controller;

import finalproject.Function;

import javax.swing.*;
import java.util.List;

public class MainController extends JFrame {
    protected Function function = new Function();

    /**
     * Updates the existing user command by reading user data from a file.
     *
     * @param newUserWeightFilePath The file path of the user weight data.
     * @param existingUserComboBox The JComboBox to update with existing user data.
     */
    public void existingUserCommand(String newUserWeightFilePath, JComboBox<String> existingUserComboBox) {
        List<String[]> lines = function.readFileContents(newUserWeightFilePath);
        existingUserComboBox.removeAllItems();
        existingUserComboBox.addItem("EXISTING USER");
        for(String[] i:lines){
            existingUserComboBox.addItem(i[0] + " " + i[2]);
        }
    }

}
