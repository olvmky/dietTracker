# Final Project 📖📚✍️✅💯🎓

Objectives:

In this project you will:

* Demonstrate your mastery of OOD to solve a challenging problem
* Design a solution with MVC architecture with Swing
* Apply all (or most) of the programming concepts used in this course
* Use design patterns to raise cohesion and lower coupling as well as to avoid re-inventing the wheel
* Meet objectives of prior assignments

## On Groups

This project can be completed individually or with a group of up to three (3) people. If the latter, the work done must be worthy of the additional personnel i.e. if one student could have completed this by themselves then the final grade will be halved (or by two-thirds).

## Part 1: The Idea

Think of three (3) real problems that excite you. Consider for example:

* a problem that you're encountering at work
* an issue that you run into daily in your usual routine
* a CS problem that sparks your curiosity
* a challenge from when you were an undergraduate
* a new unique game you want to develope

This project is a culmination of all of you've learned in this class including Object-Oriented Design, Testing, UML, Design Patterns, and most importantly is a showcase for MVC requiring Swing.

Should you want to do some data visualization (like with NumPy or Pandas in Python) a widely used tool for Java is [JFreeChart](https://www.jfree.org/jfreechart/samples.html) or [Figma](https://www.figma.com/). These tools are not a replacement for Swing but can be used to create mock up of what you want your final visual to look like.

If you're working with a team - each member should come up with three (3) unique ideas.

Share your ideas on a private Piazza post, visible to the Instructors and the other members of your team, *by the date recommended in Canvas*. We will discuss with you the feasiblity and difficulty of implementing each idea, raising the bar if the problem is too simple, or lowering it if too complicated. If an idea you selected already has many examples online, like on StackOverflow, we will veto it. Finally, we'll recommend to the group which idea would make for the best project. If you dislike any requirements that we add, your group can suggest new ideas until all parties are satisfied.

You cannot proceed to Part 2 until Part 1 is approved.

## Part 2: The Design

This is where you will design a UML Class Diagram that your team will submit here on this README.md written in Mermaid. Use every tool to ensure that the system that you design follows proper OOD principles, is well organized, appears highly cohesive, and lously coupled. Your use of Design Patterns needs to be indicated with UML Notes as part of the diagram.

The second half of this part is a mock-up of what you want your application to look like using a tool like [SceneBuilder](https://gluonhq.com/products/scene-builder/). Add these files to the repo and include screenshots of your renderings below.
```mermaid
classDiagram
    DailyFood <|-- FoodConsumed
    JFrame <|-- App
    Target <|-- Food
    DailyWeight <|-- Weight
    class Weight{
      #WeightManager weightManager
      #int id
      #LocalDate date
      #String name
      #double height
      #double weight
      #double targetWeight
      +Weight(int, LocalDate, String, double, double, double)
      +getId()
      +setId()
      +getDate()
      +setDate(LocalDate)
      +getName()
      +setName(String)
      +getHeight()
      +setHeight(double)
      +getWeight()
      +setWeight(double)
      +getTargetWeight()
      +setTargetWeight(double)
      +toString()
    }
    class WeightManager{
        -String newUserWeightFilePath
        -Set<String> generateIds
        +generateRandomNumericIds()
        +existingId()
    }
    class Target{
        #double protein
        #double carb
        #double calories
        #double fat
        +Target(double, double, double, double)
        +getProtein()
        +setProtein(double)
        +getCarb()
        +setCarb(double)
        +getCalories()
        +setCalories(double)
        +getFat()
        +setFat(double)
    }
    class Function{
        #String newUserWeightFilePath
        #String dailyWeightFilePath
        #String dailyFoodFilePath
        #String nutritionFilPath
        #Calculation calculation
        +newUserWeight(int, String, double, double, double)
        +addDailyWeight(int, String, double)
        +createNewDailyWeightCSVFile(Weight)
        +addDailyFood(int, String, Category, double)
        +readFileContents(String)
        +targetConsumption(int)
        +consumptionByCategory(int, int)
        +dailyConsumption(int)
        +stringToCategory(String)
        +nutritionFilter(String, int)
        +weightInfo(int)
        +weightDifference(int)
        +daysUsedApp(int)
        +dayRegister(int)
    }
    class FoodManager{
        #String foodNutritionFilePath
        +addNewFoodNutrition(FoodCategory, String, double, double, double, double)
        +addDailyFood(int, String, Category, int)
        +strToCategory(String)
    }
    class DailyFood{
        #int id
        #LocalDate localDate
        #String name
        #Category category
        #double weight
        +DailyFood(int, String, Category, double)
        +getLocalDate()
        +setLocalDate(LocalDate)
        +getId()
        +setId(int)
        +getName()
        +setName(String)
        +getCategory()
        +setCategory(Category)
        +getWeight()
        +setWeight(double)
        +toString()
    }
    class DailyWeight{
        #int id
        #LocalDate date
        #double todayWeight
        +DailyWeight(int, LocalDate, double)
        +getId()
        +getDate()
        +getWeight()
        +setWeight(double)
        +toString()
    }
    class Calculation{
        #Weight weight;
        #int protein
        #int carb
        #int calories
        #int fat
        +targetConsumption(double)
        +consumptionDiff(Target, Target)
        +weightComparison(int, int)
        +convertNutrition(DailyFood, String[])
        +lbsToKg(double)
    }
    class Category{
        <<Enumeration>>
        BREAKFAST,
        LUNCH,
        DINNER,
        SNACK
    }
    class Food{
        #FoodCategory foodCategory
        #String name
        #double protein 
        #double carb
        #double calories
        #double fat
        +Food(FoodCategory, String, double, double, double, double)
        +getFoodCategory()
        +setFoodCategory(FoodCategory)
        +getName()
        +setName(String)
        +getProtein()
        +setProtein(double)
        +getCarb()
        +setCarb(double)
        +getCalories()
        +setCalories(double)
        +getFat()
        +setFat(double)
        +toString()
    }
    class FoodConsumed{
        #double calories
        #double protein
        #double carb
        #double fat
        +FoodConsumed(int, LocalDate, String, Category, double, double, double, double, double)
        +getCalories()
        +setCalories(double)
        +getProtein()
        +setProtein(double)
        +getCarb()
        +setCarb(double)
        +getFat()
        +setFat(double)
    }
    class FoodCategory{
        <<Enumeration>>
        MAIN,
        MEAT,
        DRINK,
        MISC
    }
    class App{
        #String newUserWeightFilePath
        #String foodNutritionFilePath
        #String weightFilePath
        #String dietFilePath
        -JButton newUserButton
        -JButton backFrontButton
        -JButton registerSubmitButton
        -JButton nutritionButton
        -JButton dietButton
        -JButton registerNutritionButton
        -JTextField nameInput
        -JTextField weightInput
        -JTextField heightInput
        -JTextField targetInput
        -JComboBox<String> existingUserComboBox
        -JPanel userRegisterPanel
        -JPanel frontPanel
        -JPanel centerWrapperPanel
        -JPanel nutritionPanel
        -JPanel nutritionTopPanel
        -JPanel nutritionMidPanel
        -JPanel nutritionBottomPanel
        -JButton nutDietButtom
        -JButton nutInputSubmitButton
        -JTextField nutFoodInput
        -JComboBox<String> nutCategorySelection
        -JComboBox<String> nutFilterBox
        -JLabel newUserRegisterOutput
        -JTextArea outputTextArea
        -JButton nutNewUserButton
        -JButton nutMainButton
        -JButton nutAddButton
        -JButton nutWeightTrackButton
        -JTable nutritionTable
        -JTextField nutNameInput
        -JComboBox<String> nutCategoryInput
        -JTextField nutCarbInput
        -JTextField nutProteinInput
        -JTextField nutCaloriesInput
        -JTextField nutFatInput
        -JPanel dietPanel
        -JPanel dietTopPanel
        -JPanel dietMidPanel
        -JPanel dietBottomPanel
        -JPanel dietMidLeftPanel
        -JPanel dietMidRightPanel
        -JButton dietWeightTrackButton
        -JButton dietMainButton
        -JButton dietNutButton
        -JButton dietSubmitButton
        -JTextField dietIngreInput
        -JTextField dietWeightInput
        -JComboBox<String> dietCategoryBox
        -JTable dietBFTable
        -JPanel weightPanel
        -JPanel weightTopPanel
        -JPanel weightMidPanel
        -JPanel weightBottomPanel
        -JPanel weightMidLeftPanel
        -JPanel weightMidRightPanel
        -JButton weightMainButton
        -JButton weightDietButton
        -JButton weightNutritionButton
        -JButton weightSubmitButton
        -JLabel weightYesterdayDiff
        -JLabel weightFirstDayDiff
        -JLabel weightDays
        -JTextField dailyWeightInput
        -XYSeries series
        -Function function
        -FoodManager
        -Calculation
        -WeightManager
        -int userId
        -String userName
        +App()
        +main(String[] args)
        -initComponents()
        -backToDiet()
        -backToWeight()
        -backToNutrition()
        -backToMain()
        -updateDietText()
        -updateWeightText()
        -weightGraph()
        -createStandardTickUnits()
        -updateNutritionTableByName(String, int)
        -updateNutritionTable()
        -updateDietTable()
        -existingUserCommand()
        #CustomOutputStream()
    }
```
// your screenshots here

No actual Java code will be written for this part. Submit everything here on GitHub by the date recommended on Canvas. The teaching teach will provide rapid feedback on your diagram and Views. You can move on to Part 3, just be aware that you may need to change things if the Teaching Team finds issues.

## Part 3: Implementation

A failure to plan is a plan to fail -- but you've done all of the planning, now is the time to build it 😎

There is also a codewalk where you will meet with the Instructors to go over your mostly completed application and get feedback all aspects, including design, Views, missing components, etc., that will need to be addressed before submitting a final result.

## Documentation and Testing

Your project needs to follow the Google style format and reach 70% code coverage from your testing on components not related to the View/Control. You are expected to show a screenshot from your JaCoCo coverage report below, identifying the level of code coverage.

![Alt Text](jacoco.png)
![Alt Text](jacoco1.png)

<b> The test coverage excluede the App.java class, which is mainly for the swing application model and controller</b>

// add your screenshot here

## UML & Design Patterns

// add final UML Class Diagram written in Mermaid here. This is the new version of the UML but leave the old version in the earlier section.

```mermaid
classDiagram
    DailyFood <|-- FoodConsumed
    JFrame <|-- App
    Target <|-- Food
    DailyWeight <|-- Weight
    class Weight{
      #WeightManager weightManager
      #int id
      #LocalDate date
      #String name
      #double height
      #double weight
      #double targetWeight
      +Weight(int, LocalDate, String, double, double, double)
      +getId()
      +setId()
      +getDate()
      +setDate(LocalDate)
      +getName()
      +setName(String)
      +getHeight()
      +setHeight(double)
      +getWeight()
      +setWeight(double)
      +getTargetWeight()
      +setTargetWeight(double)
      +toString()
    }
    class WeightManager{
        -String newUserWeightFilePath
        -Set<String> generateIds
        +generateRandomNumericIds()
        +existingId()
    }
    class Target{
        #double protein
        #double carb
        #double calories
        #double fat
        +Target(double, double, double, double)
        +getProtein()
        +setProtein(double)
        +getCarb()
        +setCarb(double)
        +getCalories()
        +setCalories(double)
        +getFat()
        +setFat(double)
    }
    class Function{
        #String newUserWeightFilePath
        #String dailyWeightFilePath
        #String dailyFoodFilePath
        #String nutritionFilPath
        #Calculation calculation
        +newUserWeight(int, String, double, double, double)
        +addDailyWeight(int, String, double)
        +createNewDailyWeightCSVFile(Weight)
        +addDailyFood(int, String, Category, double)
        +readFileContents(String)
        +targetConsumption(int)
        +consumptionByCategory(int, int)
        +dailyConsumption(int)
        +stringToCategory(String)
        +nutritionFilter(String, int)
        +weightInfo(int)
        +weightDifference(int)
        +daysUsedApp(int)
        +dayRegister(int)
    }
    class FoodManager{
        #String foodNutritionFilePath
        +addNewFoodNutrition(FoodCategory, String, double, double, double, double)
        +addDailyFood(int, String, Category, int)
        +strToCategory(String)
    }
    class DailyFood{
        #int id
        #LocalDate localDate
        #String name
        #Category category
        #double weight
        +DailyFood(int, String, Category, double)
        +getLocalDate()
        +setLocalDate(LocalDate)
        +getId()
        +setId(int)
        +getName()
        +setName(String)
        +getCategory()
        +setCategory(Category)
        +getWeight()
        +setWeight(double)
        +toString()
    }
    class DailyWeight{
        #int id
        #LocalDate date
        #double todayWeight
        +DailyWeight(int, LocalDate, double)
        +getId()
        +getDate()
        +getWeight()
        +setWeight(double)
        +toString()
    }
    class Calculation{
        #Weight weight;
        #int protein
        #int carb
        #int calories
        #int fat
        +targetConsumption(double)
        +consumptionDiff(Target, Target)
        +weightComparison(int, int)
        +convertNutrition(DailyFood, String[])
        +lbsToKg(double)
    }
    class Category{
        <<Enumeration>>
        BREAKFAST,
        LUNCH,
        DINNER,
        SNACK
    }
    class Food{
        #FoodCategory foodCategory
        #String name
        #double protein 
        #double carb
        #double calories
        #double fat
        +Food(FoodCategory, String, double, double, double, double)
        +getFoodCategory()
        +setFoodCategory(FoodCategory)
        +getName()
        +setName(String)
        +getProtein()
        +setProtein(double)
        +getCarb()
        +setCarb(double)
        +getCalories()
        +setCalories(double)
        +getFat()
        +setFat(double)
        +toString()
    }
    class FoodConsumed{
        #double calories
        #double protein
        #double carb
        #double fat
        +FoodConsumed(int, LocalDate, String, Category, double, double, double, double, double)
        +getCalories()
        +setCalories(double)
        +getProtein()
        +setProtein(double)
        +getCarb()
        +setCarb(double)
        +getFat()
        +setFat(double)
    }
    class FoodCategory{
        <<Enumeration>>
        MAIN,
        MEAT,
        DRINK,
        MISC
    }
    class App{
        #String newUserWeightFilePath
        #String foodNutritionFilePath
        #String weightFilePath
        #String dietFilePath
        -JButton newUserButton
        -JButton backFrontButton
        -JButton registerSubmitButton
        -JButton nutritionButton
        -JButton dietButton
        -JButton registerNutritionButton
        -JTextField nameInput
        -JTextField weightInput
        -JTextField heightInput
        -JTextField targetInput
        -JComboBox<String> existingUserComboBox
        -JPanel userRegisterPanel
        -JPanel frontPanel
        -JPanel centerWrapperPanel
        -JPanel nutritionPanel
        -JPanel nutritionTopPanel
        -JPanel nutritionMidPanel
        -JPanel nutritionBottomPanel
        -JButton nutDietButtom
        -JButton nutInputSubmitButton
        -JTextField nutFoodInput
        -JComboBox<String> nutCategorySelection
        -JComboBox<String> nutFilterBox
        -JLabel newUserRegisterOutput
        -JTextArea outputTextArea
        -JButton nutNewUserButton
        -JButton nutMainButton
        -JButton nutAddButton
        -JButton nutWeightTrackButton
        -JTable nutritionTable
        -JTextField nutNameInput
        -JComboBox<String> nutCategoryInput
        -JTextField nutCarbInput
        -JTextField nutProteinInput
        -JTextField nutCaloriesInput
        -JTextField nutFatInput
        -JPanel dietPanel
        -JPanel dietTopPanel
        -JPanel dietMidPanel
        -JPanel dietBottomPanel
        -JPanel dietMidLeftPanel
        -JPanel dietMidRightPanel
        -JButton dietWeightTrackButton
        -JButton dietMainButton
        -JButton dietNutButton
        -JButton dietSubmitButton
        -JTextField dietIngreInput
        -JTextField dietWeightInput
        -JComboBox<String> dietCategoryBox
        -JTable dietBFTable
        -JPanel weightPanel
        -JPanel weightTopPanel
        -JPanel weightMidPanel
        -JPanel weightBottomPanel
        -JPanel weightMidLeftPanel
        -JPanel weightMidRightPanel
        -JButton weightMainButton
        -JButton weightDietButton
        -JButton weightNutritionButton
        -JButton weightSubmitButton
        -JLabel weightYesterdayDiff
        -JLabel weightFirstDayDiff
        -JLabel weightDays
        -JTextField dailyWeightInput
        -XYSeries series
        -Function function
        -FoodManager
        -Calculation
        -WeightManager
        -int userId
        -String userName
        +App()
        +main(String[] args)
        -initComponents()
        -backToDiet()
        -backToWeight()
        -backToNutrition()
        -backToMain()
        -updateDietText()
        -updateWeightText()
        -weightGraph()
        -createStandardTickUnits()
        -updateNutritionTableByName(String, int)
        -updateNutritionTable()
        -updateDietTable()
        -existingUserCommand()
        #CustomOutputStream()
    }
```

Also fill in the table below explaining the design patterns that you used in your application.

| Pattern Name | Class(es) | Justification |
| :--------: | ------- | :------- |
| Singleton Pattern | `WeightManager` | The WeightManager class contains a generateRandomNumericId() method that generates unique numeric IDs for tasks. The class also maintains a Set of generated IDs to ensure uniqueness. This resembles the Singleton pattern where a single instance of a class is responsible for managing a global resource or state, in this case, the generation of unique IDs. |
| Factory Method Pattern | `Function`| The Function class contains various methods for creating and manipulating user data, such as newUserWeight() for creating a new user and addDailyWeight() for adding a new daily weight entry. These methods can be seen as factory methods responsible for creating instances of Weight and DailyWeight objects, respectively. The Factory Method pattern encapsulates the instantiation of objects, allowing subclasses to alter the type of objects that will be created. |
| Template Method Pattern | `Calculation` | The Calculation class defines several methods for calculating nutritional targets, consumption differences, and weight comparisons. These methods follow a common structure where specific steps (calculations) are defined in the base class (Calculation), while the subclasses (if any) can override certain steps to provide specialized behavior. This resembles the Template Method pattern, which defines the skeleton of an algorithm in the superclass but allows subclasses to redefine certain steps. |
| Decorator Pattern | `Function` | The Function class contains methods for reading, filtering, and processing data from various files (readFileContents(), nutritionFilter()). These methods can be seen as decorators that add additional functionality to the core behavior of reading and processing files. For example, nutritionFilter() adds the functionality of filtering data based on certain criteria (e.g., category, name), enhancing the basic file reading functionality. This resembles the Decorator pattern, where objects are wrapped to add new behaviors dynamically. |

## Reflection

Each member of your group needs to provide an answer to *each* question and be sure to clearly identify whose response is whose.

1. Describe one lesson from this course and how it impacted this project.<br>
Start early and plan out everything in details. I actually did not have any ideas when I first started out the project. I only know what I want it does as a very blurred idea. If I planned out more details, it can be much better. <br>
2. What part of this assignment did you find the most challenging and why?<br>
The most challenge part for this assignment would be sticking with all the details. Because there's a lot of similar components in the application. It would be very easy to get confused. <br>
3. Assume that you are doing this project over again, what element(s) would you change and how?<br>
I would change the output panel of register page into text.<br>
4. How did the process of this assignment, specifically completing Part 2 before starting Part 3, impact your learning?<br>
I do have a better view of how my application will be look like.<br>
5. Think back on what you knew before starting this course and what you know now. What advice would you tell your younger self having completed this project?<br>
I would tell myself to start early, and spend more time, to make the applciation better version. 

## Accountability

In this section, identify who worked on what parts.<br>
personal project. 
