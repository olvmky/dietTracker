package finalproject;

import finalproject.controller.DietController;
import finalproject.controller.MainController;
import finalproject.controller.NutritionController;
import finalproject.controller.WeightController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Main class for application, have few pages
 * Able to Add new user information, add in nutrition, add in daily diet and return corresponding nutrion consumed
 *  add in daily weight and keep track of the difference.
 */
public class App extends JFrame {
    protected static final String newUserWeightFilePath =
            "/Users/oliviamiuki/dietTracker/newuserweight.csv";
    protected static final String foodNutritionFilePath =
            "/Users/oliviamiuki/dietTracker/foodnutrition.csv";
    protected static final String weightFilePath =
            "/Users/oliviamiuki/dietTracker/dailyweight.csv";
    protected static final String dietFilePath =
            "/Users/oliviamiuki/dietTracker/dailyfood.csv";

    protected static final String weight = "WEIGHT";
    protected static final String nutrition = "NUTRITION";
    protected static final String main = "MAIN PAGE";

    private final JButton newUserButton;
    private final JButton backFrontButton;
    private final JButton registerSubmitButton;
    private final JButton nutritionButton;
    private final JButton dietButton;
    private final JButton registerNutritionButton;
    private final JTextField nameInput;
    private final JTextField weightInput;
    private final JTextField heightInput;
    private final JTextField targetInput;
    private final JComboBox<String> existingUserComboBox;
    private final JPanel userRegisterPanel;
    private final JPanel centerWrapperPanel;
    private final JPanel nutritionPanel;

    private final JButton nutDietButtom;
    private final JButton nutInputSubmitButton;
    private final JTextField nutFoodInput;
    //after selecting the category, another jcombobox will filter only showing the selected category food
    private final JComboBox<String> nutCategorySelection;
    private final JComboBox<String> nutFilterBox;

    private final JButton nutMainButton;
    private final JButton nutAddButton;
    private final JButton nutWeightTrackButton;
    private final JTable nutritionTable;
    private final JTextField nutNameInput;
    private final JComboBox<String> nutCategoryInput;
    private final JTextField nutCarbInput;
    private final JTextField nutProteinInput;
    private final JTextField nutCaloriesInput;
    private final JTextField nutFatInput;

    private final JPanel dietPanel;
    private final JPanel dietMidRightPanel;
    private final JButton dietWeightTrackButton;
    private final JButton dietMainButton;
    private final JButton dietNutButton;
    private final JButton dietSubmitButton;
    private final JTextField dietIngreInput;
    private final JTextField dietWeightInput;
    private final JComboBox<String> dietCategoryBox;
    private final JTable dietBFTable;

    private final JPanel weightPanel;
    private final JPanel weightMidLeftPanel;
    private final JPanel weightMidRightPanel;
    private final JButton weightMainButton;
    private final JButton weightDietButton;
    private final JButton weightNutritionButton;
    private final JButton weightSubmitButton;
    private final JTextField dailyWeightInput;

    private final Function function = new Function();
    private final FoodManager foodManager = new FoodManager();
    private final Calculation calculation = new Calculation();
    private int userId;
    private final WeightManager weightManager = new WeightManager();
    private final DietController dietController = new DietController();
    private final MainController mainController = new MainController();
    private final NutritionController nutritionController = new NutritionController();
    private final WeightController weightController = new WeightController();
    private String userName;

    /**
     * Layout for the application
     */
    public App(){
        setTitle("Diet Tracker");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        /**
         * Codes below are for the front page
         */
        centerWrapperPanel = new JPanel();
        centerWrapperPanel.setLayout(new BoxLayout(centerWrapperPanel, BoxLayout.Y_AXIS));
        JPanel frontPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        newUserButton = new JButton("NEW USER");
        existingUserComboBox = new JComboBox<>();
        nutritionButton = new JButton(nutrition);
        mainController.existingUserCommand(newUserWeightFilePath, existingUserComboBox);

        frontPanel.add(newUserButton);
        frontPanel.add(nutritionButton);
        frontPanel.add(existingUserComboBox);
        frontPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerWrapperPanel.add(Box.createVerticalGlue()); // Add glue to center components vertically
        centerWrapperPanel.add(frontPanel); // Add frontPanel to centerWrapperPanel
        centerWrapperPanel.add(Box.createVerticalGlue()); // Add glue to center components vertically
        add(centerWrapperPanel, BorderLayout.CENTER);


        /**
         * Codes below are for the user register page
         */
        userRegisterPanel = new JPanel();
        userRegisterPanel.setLayout(new BorderLayout());

        //Create a top panel for userRegisterPanel, add to north
        JPanel userRegisterTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        userRegisterPanel.add(userRegisterTopPanel, BorderLayout.NORTH);

        //Create a scroll panel for the output of userRegisterPanel, add to center
        JTextArea outputTextArea = new JTextArea();
        outputTextArea.setEditable(false); // read-only
        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);
        PrintStream printStream = new PrintStream(new CustomOutputStream(outputTextArea));
        System.setOut(printStream);
        System.setErr(printStream);
        userRegisterPanel.add(outputScrollPane, BorderLayout.CENTER);

        //Create a bottom panel for userRegisterPanel, add to the south
        JPanel userRegisterBottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        userRegisterPanel.add(userRegisterBottomPanel, BorderLayout.SOUTH);
        JLabel nameLabel = new JLabel("NAME");
        JLabel weightLabel = new JLabel(weight);
        JLabel heightLabel = new JLabel("HEIGHT");
        JLabel targetLabel = new JLabel("TARGET WEIGHT");
        nameInput = new JTextField(4);
        weightInput = new JTextField(4);
        heightInput = new JTextField(4);
        targetInput = new JTextField(4);
        backFrontButton = new JButton(main);
        registerSubmitButton = new JButton("SUBMIT");
        dietButton = new JButton("DAILY DIET");
        registerNutritionButton = new JButton(nutrition);

        JLabel newUserRegisterOutput = new JLabel();
        newUserRegisterOutput.setPreferredSize(new Dimension(200, 20));
        newUserRegisterOutput.setFont(new Font("Arial", Font.PLAIN, 12));
        newUserRegisterOutput.setBackground(UIManager.getColor("Panel.background"));

        userRegisterTopPanel.add(nameLabel);
        userRegisterTopPanel.add(nameInput);
        userRegisterTopPanel.add(weightLabel);
        userRegisterTopPanel.add(weightInput);
        userRegisterTopPanel.add(heightLabel);
        userRegisterTopPanel.add(heightInput);
        userRegisterTopPanel.add(targetLabel);
        userRegisterTopPanel.add(targetInput);
        userRegisterTopPanel.add(registerSubmitButton);
        userRegisterBottomPanel.add(backFrontButton);
        userRegisterBottomPanel.add(registerNutritionButton);
        userRegisterBottomPanel.add(dietButton);

        /**
         * Code below are for the nutrition page
         */
        nutritionPanel = new JPanel();
        nutritionPanel.setLayout(new BorderLayout());
        JPanel nutritionTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel nutritionMidPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel nutritionBottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nutritionPanel.add(nutritionTopPanel, BorderLayout.NORTH);
        nutritionPanel.add(nutritionMidPanel, BorderLayout.CENTER);
        nutritionPanel.add(nutritionBottomPanel, BorderLayout.SOUTH);

        nutDietButtom = new JButton("DIET TRACK");
        nutMainButton = new JButton(main);
        nutWeightTrackButton = new JButton("WEIGHT TRACK");
        nutritionBottomPanel.add(nutMainButton);
        nutritionBottomPanel.add(nutDietButtom);
        nutritionBottomPanel.add(nutWeightTrackButton);

        JLabel textLabel = new JLabel("ENTER OR SELECT");
        nutritionTopPanel.add(textLabel);
        nutInputSubmitButton = new JButton("LOOK UP");
        nutFoodInput = new JTextField(5);
        nutCategorySelection =  new JComboBox<>();
        nutCategorySelection.removeAllItems();
        nutCategorySelection.addItem("CATEGORY");
        nutCategorySelection.addItem("MAIN");
        nutCategorySelection.addItem("MEAT");
        nutCategorySelection.addItem("DRINK");
        nutCategorySelection.addItem("MISC");
        nutFilterBox = new JComboBox<>();
        nutFilterBox.addItem("FILTER BY");
        nutFilterBox.addItem("PROTEIN > 15%");
        nutFilterBox.addItem("CARBS > 15%");
        nutritionTopPanel.add(nutFoodInput);
        nutritionTopPanel.add(nutInputSubmitButton);
        nutritionTopPanel.add(nutCategorySelection);
        nutritionTopPanel.add(nutFilterBox);

        String[] nutritionColNames = {"CATEGORY", "NAME", "CARB", "PROTEIN", "CALROIES", "FAT"};
        DefaultTableModel nutritionModel = new DefaultTableModel(nutritionColNames, 0);
        nutritionTable = new JTable(nutritionModel);
        Font headerFont = nutritionTable.getTableHeader().getFont();
        Font largerHeaderFont = headerFont.deriveFont(headerFont.getSize() + 2.0f);
        nutritionTable.getTableHeader().setFont(largerHeaderFont);
        JScrollPane scrollPane = new JScrollPane(nutritionTable);

        JPanel nutMidLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nutMidLeftPanel.add(scrollPane);
        nutMidLeftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel addNutLabel = new JLabel("ADD NEW FOOD");
        addNutLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel nutMidRightPanel = new JPanel();
        nutMidRightPanel.setLayout(new BoxLayout(nutMidRightPanel, BoxLayout.Y_AXIS));

        JLabel nutNameLabel = new JLabel("NAME");
        nutNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nutNameInput = new JTextField(2);
        JLabel nutCarbLabel = new JLabel("CARB");
        nutCarbLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel nutProteinLabel = new JLabel("PROTEIN");
        nutProteinLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel nutCaloriesLabel = new JLabel("CALORIES");
        nutCaloriesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel nutFatLabel = new JLabel("FAT");
        nutFatLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nutProteinInput = new JTextField(1);
        nutCaloriesInput = new JTextField(1);
        nutCarbInput = new JTextField(1);
        nutFatInput = new JTextField(1);
        nutAddButton = new JButton("ADD");
        nutAddButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        nutProteinInput.setAlignmentX(Component.CENTER_ALIGNMENT);

        nutCategoryInput = new JComboBox<>();
        nutCategoryInput.addItem("CATEGORY");
        nutCategoryInput.addItem("MAIN");
        nutCategoryInput.addItem("MEAT");
        nutCategoryInput.addItem("DRINK");
        nutCategoryInput.addItem("MISC");
        nutCategoryInput.setAlignmentX(Component.CENTER_ALIGNMENT);

        nutMidRightPanel.add(addNutLabel);
        nutMidRightPanel.add(Box.createVerticalStrut(10));
        nutMidRightPanel.add(nutNameLabel);
        nutMidRightPanel.add(Box.createVerticalStrut(3));
        nutMidRightPanel.add(nutNameInput);
        nutMidRightPanel.add(Box.createVerticalStrut(6));
        nutMidRightPanel.add(nutCategoryInput);
        nutMidRightPanel.add(Box.createVerticalStrut(3));
        nutMidRightPanel.add(nutCarbLabel);
        nutMidRightPanel.add(Box.createVerticalStrut(3));
        nutMidRightPanel.add(nutCarbInput);
        nutMidRightPanel.add(Box.createVerticalStrut(3));
        nutMidRightPanel.add(nutProteinLabel);
        nutMidRightPanel.add(Box.createVerticalStrut(3));
        nutMidRightPanel.add(nutProteinInput);
        nutMidRightPanel.add(Box.createVerticalStrut(3));
        nutMidRightPanel.add(nutCaloriesLabel);
        nutMidRightPanel.add(Box.createVerticalStrut(3));
        nutMidRightPanel.add(nutCaloriesInput);
        nutMidRightPanel.add(Box.createVerticalStrut(3));
        nutMidRightPanel.add(nutFatLabel);
        nutMidRightPanel.add(Box.createVerticalStrut(3));
        nutMidRightPanel.add(nutFatInput);
        nutMidRightPanel.add(Box.createVerticalStrut(5));
        nutMidRightPanel.add(nutAddButton);
        nutMidRightPanel.add(Box.createVerticalStrut(40));

        nutritionMidPanel.add(nutMidLeftPanel, BorderLayout.WEST);
        nutritionMidPanel.add(nutMidRightPanel, BorderLayout.EAST);

        /**
         * Codes below are for diet page
         */
        dietPanel = new JPanel();
        dietPanel.setLayout(new BorderLayout());
        JPanel dietTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel dietMidPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel dietBottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dietPanel.add(dietTopPanel, BorderLayout.NORTH);
        dietPanel.add(dietMidPanel, BorderLayout.CENTER);
        dietPanel.add(dietBottomPanel, BorderLayout.SOUTH);
        //Diet panel left of middle panel
        JPanel dietMidLeftPanel = new JPanel();
        dietMidPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Set layout manager with left alignment
        dietMidLeftPanel.setLayout(new BoxLayout(dietMidLeftPanel, BoxLayout.Y_AXIS));
        dietMidLeftPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        //Diet panel center of middle panel
        JPanel dietMidCenterPanel = new JPanel();
        dietMidCenterPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Set layout manager with left alignment
        dietMidCenterPanel.setLayout(new BoxLayout(dietMidCenterPanel, BoxLayout.Y_AXIS));
        dietMidCenterPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        //Diet panel right of middle panel
        dietMidRightPanel = new JPanel();
        dietMidRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Set layout manager with left alignment
        dietMidRightPanel.setLayout(new BoxLayout(dietMidRightPanel, BoxLayout.Y_AXIS));
        dietMidRightPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JLabel breakfastLabel = new JLabel("FOOD CONSUMED");
        breakfastLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel lunchLabel = new JLabel("LUNCH");
        lunchLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] dailyFoodCol = {"NAME", "CATEGORY", weight, "CALORIES", "PROTEIN", "CARB", "FAT"};
        DefaultTableModel breakfastModel = new DefaultTableModel(dailyFoodCol, 0);
        dietBFTable = new JTable(breakfastModel);
        Font breakfastFont = dietBFTable.getTableHeader().getFont();
        Font largerBreakfastFont = breakfastFont.deriveFont(breakfastFont.getSize() + 2.0f);
        dietBFTable.getTableHeader().setFont(largerBreakfastFont);
        JScrollPane bfScrollPane = new JScrollPane(dietBFTable);
        bfScrollPane.setPreferredSize(new Dimension(450, 250));
        dietMidLeftPanel.add(Box.createVerticalStrut(20));
        dietMidLeftPanel.add(breakfastLabel);
        dietMidLeftPanel.add(bfScrollPane);

        JLabel dietHeader = new JLabel("TODAY'S DIET");
        JLabel dietIngreLabel = new JLabel("INGREDIENT");
        JLabel dietWeightLabel = new JLabel(weight);

        dietMainButton = new JButton(main);
        dietWeightTrackButton = new JButton("WEIGHT TRACK");
        dietNutButton = new JButton(nutrition);
        dietIngreInput = new JTextField(4);
        dietWeightInput = new JTextField(4);

        dietCategoryBox = new JComboBox<>();
        dietCategoryBox.addItem("CATEGORY");
        dietCategoryBox.addItem("BREAKFAST");
        dietCategoryBox.addItem("LUNCH");
        dietCategoryBox.addItem("DINNER");
        dietCategoryBox.addItem("SNACK");

        dietSubmitButton = new JButton("SUBMIT");

        dietTopPanel.add(dietHeader);
        dietTopPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        dietTopPanel.add(dietIngreLabel);
        dietTopPanel.add(dietIngreInput);
        dietTopPanel.add(dietCategoryBox);
        dietTopPanel.add(dietWeightLabel);
        dietTopPanel.add(dietWeightInput);
        dietTopPanel.add(dietSubmitButton);

        dietMidPanel.add(dietMidLeftPanel, BorderLayout.WEST);
        dietMidPanel.add(dietMidCenterPanel, BorderLayout.CENTER);

        // Add extra horizontal space between dietMidRightPanel and dietMidCenterPanel
        dietMidPanel.add(Box.createHorizontalStrut(10), BorderLayout.LINE_START); // Adjust the width as needed
        dietMidPanel.add(dietMidRightPanel, BorderLayout.LINE_END); // Add dietMidRightPanel to LINE_END

        dietBottomPanel.add(dietMainButton);
        dietBottomPanel.add(dietNutButton);
        dietBottomPanel.add(dietWeightTrackButton);

        /**
         * Codes below are for daily weight panel
         */
        weightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        weightPanel.setLayout(new BorderLayout());

        JPanel weightTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel weightMidPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        weightMidPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Set layout manager with left alignment
        JPanel weightBottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        weightMainButton = new JButton(main);
        weightNutritionButton = new JButton(nutrition);
        weightDietButton = new JButton("DIET");

        weightBottomPanel.add(weightMainButton);
        weightBottomPanel.add(weightNutritionButton);
        weightBottomPanel.add(weightDietButton);

        JLabel weightHeader = new JLabel("TODAY'S WEIGHT");
        dailyWeightInput = new JTextField(5);
        weightSubmitButton = new JButton("SUBMIT");

        weightTopPanel.add(weightHeader);
        weightTopPanel.add(dailyWeightInput);
        weightTopPanel.add(weightSubmitButton);
        weightPanel.add(weightTopPanel, BorderLayout.NORTH);
        weightPanel.add(weightMidPanel, BorderLayout.CENTER);
        weightPanel.add(weightBottomPanel, BorderLayout.SOUTH);

        weightMidLeftPanel = new JPanel();
        weightMidLeftPanel.setLayout(new BoxLayout(weightMidLeftPanel, BoxLayout.Y_AXIS));
        weightMidLeftPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        weightMidPanel.add(weightMidLeftPanel, BorderLayout.WEST);
        weightMidRightPanel = new JPanel();
        weightMidRightPanel.setLayout(new BoxLayout(weightMidRightPanel, BoxLayout.Y_AXIS));
        weightMidRightPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        weightMidPanel.add(weightMidRightPanel, BorderLayout.EAST);
        setVisible(true);
        initComponents();
    }

    /**
     * The main entry point for the application.
     * Initializes and displays the application GUI.
     * If an IOException occurs during initialization, it is wrapped and rethrown as a RuntimeException.
     * @param args The command line arguments (unused)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app;
            app = new App();
            app.setVisible(true);
        });
    }

    /**
     * Initializes all the components and their corresponding action listeners for the GUI.
     * Actions include switching panels, updating tables, submitting user inputs, and handling filter options.
     * If any IOException occurs during the execution of an action, it is wrapped and rethrown as a RuntimeException.
     */
    private void initComponents(){
        newUserButton.addActionListener(e -> {
            getContentPane().removeAll();
            getContentPane().add(userRegisterPanel);
            revalidate();
            repaint();
        });

        dietButton.addActionListener(e -> backToDiet());

        /**
         * When press the button of submit, there should be an output panel
         *  printing out all successful registered output.
         */
        registerSubmitButton.addActionListener(e -> {
            String name = nameInput.getText();
            double weight = Double.parseDouble(weightInput.getText());
            double height = Double.parseDouble(heightInput.getText());
            double targetWeight = Double.parseDouble(targetInput.getText());
            int id = weightManager.generateRandomNumericId();
            userId = id;
            function.newUserWeight(id, name, height, weight, targetWeight);
            function.addDailyWeight(id, name, weight);
        });

        /**
         * back buttom for going back to main page
         */
        backFrontButton.addActionListener(e -> backToMain());

        /**
         * change to nutrition panel
         */
        nutritionButton.addActionListener(e -> {
            backToNutrition();
            nutritionController.updateNutritionTable(foodNutritionFilePath, nutritionTable);
        });

        /**
         * change to nutrition panel
         */
        registerNutritionButton.addActionListener(e -> {
            backToNutrition();
            nutritionController.updateNutritionTable(foodNutritionFilePath, nutritionTable);
        });

        nutDietButtom.addActionListener(e -> backToDiet());

        nutMainButton.addActionListener(e -> backToMain());

        dietMainButton.addActionListener(e -> backToMain());

        dietNutButton.addActionListener(e -> {
            backToNutrition();
            nutritionController.updateNutritionTable(foodNutritionFilePath, nutritionTable);
        });

        nutAddButton.addActionListener(e -> {
            String name = nutNameInput.getText();
            String category = (String)nutCategoryInput.getSelectedItem();
            FoodCategory foodCategory = function.stringToCategory(category);
            double carb = Double.parseDouble(nutCarbInput.getText());
            double protein = Double.parseDouble(nutProteinInput.getText());
            double calories = Double.parseDouble(nutCaloriesInput.getText());
            double fat = Double.parseDouble(nutFatInput.getText());
            foodManager.addNewFoodNutrition(foodCategory, name, carb, protein, calories, fat);
            nutritionController.updateNutritionTable(foodNutritionFilePath, nutritionTable);
        });

        /**
         * Getting nutrition info by name
         */
        nutInputSubmitButton.addActionListener(e -> {
            String text = nutFoodInput.getText();
            nutritionController.updateNutritionTableByName(text, 1, nutritionTable);
        });

        /**
         * Filter the nutrition table by category
         */
        nutCategorySelection.addActionListener(e -> {
            String text = (String) nutCategorySelection.getSelectedItem();
            nutritionController.updateNutritionTableByName(text, 0, nutritionTable);
        });

        /**
         * Filter nutrition table by high protein or high carb
         * TODO: Set up if category and filter both have been selected, return selected string
         */
        nutFilterBox.addActionListener(e -> {
            String text = (String) nutFilterBox.getSelectedItem();
            int option = text.equals("PROTEIN > 15%") ? 3:2;
            if(text.equals("FILTER BY")) {
                nutritionController.updateNutritionTable(foodNutritionFilePath, nutritionTable);
            } else {
                nutritionController.updateNutritionTableByName("", option, nutritionTable);
            }
        });

        dietSubmitButton.addActionListener(e -> {
            String ingredient = dietIngreInput.getText();
            ingredient = ingredient.toUpperCase();
            String text = (String) dietCategoryBox.getSelectedItem();
            Category category = foodManager.strToCategory(text);
            double weight = Double.parseDouble(dietWeightInput.getText());
            Function.addDailyFood(userId, ingredient, category, weight);
            dietController.updateDietText(userId, dietMidRightPanel);
            dietController.updateDietTable(userId, dietFilePath, dietBFTable);
        });

        /**
         * Set id and name to corresponding selected item and turn to daily weight page
         *  Make sure the weightPanel and daily Diet panel in only showing corresponding user info
         */
        existingUserComboBox.addActionListener(e ->{
            String curr = (String) existingUserComboBox.getSelectedItem();
            if (curr != null && !curr.equals("EXISTING USER")){
                String[] split = curr.split(" ");
                userId = Integer.parseInt(split[0]);
                userName = split[1];
                weightMidLeftPanel.removeAll();
                weightMidRightPanel.removeAll();
                weightController.updateWeightText(userId, weightMidRightPanel);
                weightController.weightGraph(weightFilePath, userId, weightMidLeftPanel);

                getContentPane().removeAll();
                getContentPane().add(weightPanel);
                revalidate();
                repaint();
            }
        });

        weightMainButton.addActionListener(e -> backToMain());

        weightNutritionButton.addActionListener(e -> backToNutrition());

        weightDietButton.addActionListener(e -> backToDiet());

        nutWeightTrackButton.addActionListener(e -> backToWeight());

        dietWeightTrackButton.addActionListener(e -> backToWeight());

        /**
         * Whenever add in new weight, will return the days user have been used for this app
         *  The weight comparison from first day and yesterday.
         */
        weightSubmitButton.addActionListener(e -> {
            double weight = Double.parseDouble(dailyWeightInput.getText());
            function.addDailyWeight(userId, userName, weight);
            weightMidLeftPanel.removeAll();
            weightMidRightPanel.removeAll();
            weightController.weightGraph(weightFilePath, userId, weightMidLeftPanel);
            weightController.updateWeightText(userId, weightMidRightPanel);

            revalidate();
            repaint();
        });
    }

    /**
     * Provides methods for navigating back to different panels in the GUI and updating their content.
     * These methods are primarily used for switching between different views and updating the displayed information.
     */
    private void backToDiet() {
        getContentPane().removeAll();
        dietController.updateDietTable(userId, dietFilePath, dietBFTable);
        getContentPane().add(dietPanel);
        dietController.updateDietText(userId, dietMidRightPanel);
        revalidate();
        repaint();
    }

    /**
     * Navigates back to the weight panel in the GUI.
     */
    private void backToWeight(){
        getContentPane().removeAll();
        getContentPane().add(weightPanel);
        revalidate();
        repaint();
    }

    /**
     * Navigates back to the nutrition panel in the GUI.
     */
    private void backToNutrition(){
        getContentPane().removeAll();
        getContentPane().add(nutritionPanel);
        revalidate();
        repaint();
    }

    /**
     * Navigates back to the main panel in the GUI.
     * This method also triggers the update of existing user commands.
     */
    private void backToMain() {
        getContentPane().removeAll();
        mainController.existingUserCommand(newUserWeightFilePath, existingUserComboBox);
        getContentPane().add(centerWrapperPanel);
        revalidate();
        repaint();
    }

    /**
     * Custom output stream class used for redirecting output to a JTextArea.
     */
    protected static class CustomOutputStream extends OutputStream {
        private final JTextArea textArea;
        public CustomOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }
        @Override
        public void write(int b){
            textArea.append(String.valueOf((char) b));
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }
}
