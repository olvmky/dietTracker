package finalproject;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

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
    private JButton newUserButton;
    private JButton backFrontButton;
    private JButton registerSubmitButton;
    private JButton nutritionButton;
    private JButton dietButton;
    private JButton registerNutritionButton;
    private JTextField nameInput;
    private JTextField weightInput;
    private JTextField heightInput;
    private JTextField targetInput;
    private JComboBox<String> existingUserComboBox;
    private JPanel userRegisterPanel;
    private JPanel frontPanel;
    private JPanel centerWrapperPanel;
    private JPanel nutritionPanel;

    private JPanel nutritionTopPanel;
    private JPanel nutritionMidPanel;
    private JPanel nutritionBottomPanel;
    private JButton nutDietButtom;
    private JButton nutInputSubmitButton;
    private JTextField nutFoodInput;
    //after selecting the category, another jcombobox will filter only showing the selected category food
    private JComboBox<String> nutCategorySelection;
    private JComboBox<String> nutFilterBox;

    private JLabel newUserRegisterOutput;
    private JTextArea outputTextArea;
    private JButton nutNewUserButton;
    private JButton nutMainButton;
    private JButton nutAddButton;
    private JButton nutWeightTrackButton;
    private JTable nutritionTable;
    private JTextField nutNameInput;
    private JComboBox<String> nutCategoryInput;
    private JTextField nutCarbInput;
    private JTextField nutProteinInput;
    private JTextField nutCaloriesInput;
    private JTextField nutFatInput;

    private JPanel dietPanel;
    private JPanel dietTopPanel;
    private JPanel dietMidPanel;
    private JPanel dietBottomPanel;
    private JPanel dietMidLeftPanel;
    private JPanel dietMidRightPanel;
    private JButton dietWeightTrackButton;
    private JButton dietMainButton;
    private JButton dietNutButton;
    private JButton dietSubmitButton;
    private JTextField dietIngreInput;
    private JTextField dietWeightInput;
    private JComboBox<String> dietCategoryBox;
    private JTable dietBFTable;

    private JPanel weightPanel;
    private JPanel weightTopPanel;
    private JPanel weightMidPanel;
    private JPanel weightBottomPanel;
    private JPanel weightMidLeftPanel;
    private JPanel weightMidRightPanel;
    private JButton weightMainButton;
    private JButton weightDietButton;
    private JButton weightNutritionButton;
    private JButton weightSubmitButton;
    private JLabel weightYesterdayDiff;
    private JLabel weightFirstDayDiff;
    private JLabel weightDays;
    private JTextField dailyWeightInput;
    private XYSeries series;

    private Function function = new Function();
    private FoodManager foodManager = new FoodManager();
    private Calculation calculation = new Calculation();
    private WeightManager weightManager = new WeightManager();
    private int userId;
    private String userName;

    /**
     * Layout for the application
     * @throws IOException throws exception
     */
    public App() throws IOException {
        setTitle("Diet Tracker");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        /**
         * Codes below are for the front page
         */
        centerWrapperPanel = new JPanel();
        centerWrapperPanel.setLayout(new BoxLayout(centerWrapperPanel, BoxLayout.Y_AXIS));
        frontPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        newUserButton = new JButton("NEW USER");
        existingUserComboBox = new JComboBox<>();
        nutritionButton = new JButton("NUTRITION");
        existingUserCommand();

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
        outputTextArea = new JTextArea();
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
        JLabel weightLabel = new JLabel("WEIGHT");
        JLabel heightLabel = new JLabel("HEIGHT");
        JLabel targetLabel = new JLabel("TARGET WEIGHT");
        nameInput = new JTextField(4);
        weightInput = new JTextField(4);
        heightInput = new JTextField(4);
        targetInput = new JTextField(4);
        backFrontButton = new JButton("MAIN PAGE");
        registerSubmitButton = new JButton("SUBMIT");
        dietButton = new JButton("DAILY DIET");
        registerNutritionButton = new JButton("NUTRITION");

        newUserRegisterOutput = new JLabel();
        newUserRegisterOutput.setPreferredSize(new Dimension(200, 20));
        newUserRegisterOutput.setFont(new Font("Arial", Font.PLAIN, 12));
        newUserRegisterOutput.setBackground(UIManager.getColor("Panel.background"));
        JScrollPane filePathScrollPane = new JScrollPane(newUserRegisterOutput);

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
        nutritionTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nutritionMidPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nutritionBottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nutritionPanel.add(nutritionTopPanel, BorderLayout.NORTH);
        nutritionPanel.add(nutritionMidPanel, BorderLayout.CENTER);
        nutritionPanel.add(nutritionBottomPanel, BorderLayout.SOUTH);

        nutDietButtom = new JButton("DIET TRACK");
        nutNewUserButton = new JButton("NEW USER");
        nutMainButton = new JButton("MAIN PAGE");
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
        dietTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dietMidPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dietBottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dietPanel.add(dietTopPanel, BorderLayout.NORTH);
        dietPanel.add(dietMidPanel, BorderLayout.CENTER);
        dietPanel.add(dietBottomPanel, BorderLayout.SOUTH);
        //Diet panel left of middle panel
        dietMidLeftPanel = new JPanel();
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

        String[] dailyFoodCol = {"NAME", "CATEGORY", "WEIGHT", "CALORIES", "PROTEIN", "CARB", "FAT"};
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
        JLabel dietWeightLabel = new JLabel("WEIGHT");

        dietMainButton = new JButton("MAIN PAGE");
        dietWeightTrackButton = new JButton("WEIGHT TRACK");
        dietNutButton = new JButton("NUTRITION");
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

        weightTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        weightMidPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        weightMidPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Set layout manager with left alignment
        weightBottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        weightMainButton = new JButton("MAIN PAGE");
        weightNutritionButton = new JButton("NUTRITION");
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
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                App app = null;
                try {
                    app = new App();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                app.setVisible(true);
            }
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

        dietButton.addActionListener(e -> {
            backToDiet();
        });

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
        backFrontButton.addActionListener(e -> {
            backToMain();
        });

        /**
         * change to nutrition panel
         */
        nutritionButton.addActionListener(e -> {
            backToNutrition();
            updateNutritionTable();
        });

        /**
         * change to nutrition panel
         */
        registerNutritionButton.addActionListener(e -> {
            backToNutrition();
            updateNutritionTable();
        });

        nutDietButtom.addActionListener(e -> {
            backToDiet();
        });

        nutMainButton.addActionListener(e -> {
            backToMain();
        });

        dietMainButton.addActionListener(e -> {
            backToMain();
        });

        dietNutButton.addActionListener(e -> {
            backToNutrition();
            updateNutritionTable();
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
            updateNutritionTable();
        });

        /**
         * Getting nutrition info by name
         */
        nutInputSubmitButton.addActionListener(e -> {
            String text = nutFoodInput.getText();
            updateNutritionTableByName(text, 1);
        });

        /**
         * Filter the nutrition table by category
         */
        nutCategorySelection.addActionListener(e -> {
            String text = (String) nutCategorySelection.getSelectedItem();
            updateNutritionTableByName(text, 0);
        });

        /**
         * Filter nutrition table by high protein or high carb
         * TODO: Set up if category and filter both have been selected, return selected string
         */
        nutFilterBox.addActionListener(e -> {
            String text = (String) nutFilterBox.getSelectedItem();
            int option = text.equals("PROTEIN > 15%") ? 3:2;
            if(text.equals("FILTER BY")) {
                updateNutritionTable();
            }
            else {
                updateNutritionTableByName("", option);
            }
        });


        dietSubmitButton.addActionListener(e -> {
            String ingredient = dietIngreInput.getText();
            ingredient = ingredient.toUpperCase();
            String text = (String) dietCategoryBox.getSelectedItem();
            Category category = foodManager.strToCategory(text);
            double weight = Double.parseDouble(dietWeightInput.getText());
            function.addDailyFood(userId, ingredient, category, weight);
            updateDietText();
            updateDietTable();

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
                try {
                    updateWeightText();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    weightGraph();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                getContentPane().removeAll();
                getContentPane().add(weightPanel);
                revalidate();
                repaint();
            }
        });

        weightMainButton.addActionListener(e -> {
            backToMain();
        });

        weightNutritionButton.addActionListener(e -> backToNutrition());

        weightDietButton.addActionListener(e -> {
            backToDiet();
        });

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
            try {
                weightGraph();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                updateWeightText();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
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
        updateDietTable();
        getContentPane().add(dietPanel);
        updateDietText();
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
     * @throws IOException if an I/O error occurs while updating existing user commands.
     */
    private void backToMain() {
        getContentPane().removeAll();
        existingUserCommand();
        getContentPane().add(centerWrapperPanel);
        revalidate();
        repaint();
    }

    /**
     * Updates the text displayed in the diet panel based on user data.
     */
    private void updateDietText() {
        dietMidRightPanel.removeAll();
        Target target = function.targetConsumption(userId, 0);

        //Today's today consumption
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

    /**
     * Updates the text displayed in the weight panel based on user data.
     * @throws IOException if an I/O error occurs while updating the weight text.
     */
    private void updateWeightText() throws IOException {
        Double[] diff = function.weightDifference(userId);
        double firstDay = diff[0];
        double yesterday = diff[1];
        long days = -1 * function.daysUsedApp(userId);

        String daysDiff = "YOU HAVE USED THE APP FOR " + days + " DAYS";
        weightDays = new JLabel(daysDiff);
        weightMidRightPanel.add(weightDays);
        weightDays.setAlignmentX(Component.CENTER_ALIGNMENT);

        String comparisonFirstDay;
        String firstDate = function.dayRegister(userId);
        if(firstDay < 0) comparisonFirstDay = "YOU HAVE LOST " + -1*firstDay + " LBS SINCE " + firstDate;
        else comparisonFirstDay = "YOU HAVE GAINED " + firstDay + " LBS SINCE " + firstDate;
        weightFirstDayDiff = new JLabel(comparisonFirstDay);
        weightMidRightPanel.add(weightFirstDayDiff);
        weightFirstDayDiff.setAlignmentX(Component.CENTER_ALIGNMENT);

        String comparisonYesterday;
        if(yesterday < 0) comparisonYesterday = "YOU HAVE LOST " + -1*yesterday + " lbs compared to yesterday";
        else comparisonYesterday = "You have gained " + yesterday + " lbs compared to yesterday.";
        comparisonYesterday = comparisonYesterday.toUpperCase();
        weightYesterdayDiff = new JLabel(comparisonYesterday);
        weightMidRightPanel.add(weightYesterdayDiff);
        weightYesterdayDiff.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Generates and displays a graph representing the user's weight changes over time.
     * @throws IOException if an I/O error occurs while generating the weight graph.
     */
    private void weightGraph() throws IOException {
        List<String[]> data = function.readFileContents(weightFilePath);
        series = new XYSeries("WEIGHT TRACK");
        LocalDate current = LocalDate.now();
        double min = Double.MAX_VALUE, max = 0.0;
        for(String[] i:data){
            if(userId == Integer.parseInt(i[0])){
                String[] split = i[1].split("-");
                int month = Integer.parseInt(split[1]);
                if(current.getMonthValue() == month && current.getYear() == Integer.parseInt(split[0])){
                    String day = split[2];
                    double weight = Double.parseDouble(i[2]);
                    min = Math.min(min, weight);
                    max = Math.max(max, weight);
                    series.add(Integer.parseInt(day), weight);
                }
            }
        }
        String monthName = Month.of(current.getMonthValue()).
                getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.getDefault());
        monthName = monthName.toUpperCase();
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        JFreeChart chart = ChartFactory.createScatterPlot(
                "WEIGHT CHANGES", // Chart title
                "FOR " + monthName,               // X-axis label
                "WEIGHTS IN LBS",            // Y-axis label
                dataset,             // Dataset
                PlotOrientation.VERTICAL,
                true,                // Show legend
                true,               // Use tooltips
                false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true); // Show lines for series 0
        plot.setRenderer(renderer);

        NumberAxis yAxis = (NumberAxis) chart.getXYPlot().getRangeAxis();
        yAxis.setRange(min - 0.5, max + 0.5);

        ValueAxis xAxis = chart.getXYPlot().getDomainAxis();
        xAxis.setStandardTickUnits(createStandardTickUnits());

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 350));
        weightMidLeftPanel.add(chartPanel);
    }

    /**
     * Creates standard tick units for the x-axis of a chart.
     * These tick units are typically used to define the spacing of tick marks on the x-axis.
     * Adjustments can be made based on specific requirements.
     * @return The standard tick units for the x-axis.
     */
    private TickUnits createStandardTickUnits() {
        TickUnits units = new TickUnits();
        units.add(new NumberTickUnit(1)); // Adjust this based on your specific requirements
        units.add(new NumberTickUnit(2));
        return units;
    }

    /**
     * Updates the nutrition table by filtering the data based on the specified name and option.
     * @param text The text to filter by.
     * @param option The filtering option.
     * @throws IOException if an I/O error occurs while updating the nutrition table.
     */
    private void updateNutritionTableByName(String text, int option){
        List<String[]> lines = function.nutritionFilter(text, option);
        DefaultTableModel nutTable = (DefaultTableModel) nutritionTable.getModel();
        nutTable.setRowCount(0);
        for(String[] i:lines){
            nutTable.addRow(new Object[]{i[0], i[1], i[2], i[3], i[4], i[5]});
        }
    }

    /**
     * Updates the nutrition table with all available data.
     * @throws IOException if an I/O error occurs while updating the nutrition table.
     */
    private void updateNutritionTable(){
        DefaultTableModel nutTable = (DefaultTableModel) nutritionTable.getModel();
        nutTable.setRowCount(0);
        List<String[]> lines = function.readFileContents(foodNutritionFilePath);
        for(String[] i:lines){
            nutTable.addRow(new Object[]{i[0], i[1], i[2], i[3], i[4], i[5]});
        }
    }

    /**
     * Updates the diet table with data for the current user and date.
     * @throws IOException if an I/O error occurs while updating the diet table.
     */
    private void updateDietTable() {
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
     * Updates the existing user command by reading user data from a file.
     * @throws IOException if an I/O error occurs while updating the existing user command.
     */
    private void existingUserCommand() {
        List<String[]> lines = function.readFileContents(newUserWeightFilePath);
        existingUserComboBox.removeAllItems();
        existingUserComboBox.addItem("EXISTING USER");
        for(String[] i:lines){
            existingUserComboBox.addItem(i[0] + " " + i[2]);
        }
    }

    /**
     * Custom output stream class used for redirecting output to a JTextArea.
     */
    protected static class CustomOutputStream extends OutputStream {
        private JTextArea textArea;
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
