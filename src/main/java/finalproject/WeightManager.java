package finalproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Manages weight data for users, including generating unique numeric IDs and retrieving existing IDs from files.
 */
public class WeightManager {
    protected String newUserWeightFilePath = "/Users/oliviamiuki/final-project-v2-finals/newuserweight.csv";
    private static Set<String> generatedIds = new HashSet<>();

    /**
     * Generate a unique numeric ID for a weight entry.
     *
     * @return A unique numeric ID
     */
    public int generateRandomNumericId() {
        Random random = new Random();
        String numericId;
        do {
            numericId = String.format("%05d", random.nextInt(10000)); // Generate 10-digit numeric ID
        } while (!generatedIds.add(numericId)); // Check uniqueness and add to set
        return Integer.parseInt(numericId);
    }

    /**
     * Retrieve a list of existing numeric IDs from the weight data file.
     *
     * @return A list of existing numeric IDs
     */
    public List<Integer> existingId() {
        Scanner scanner = null;
        List<Integer> list = new ArrayList<>();
        try {
            File file = new File(newUserWeightFilePath);
            scanner = new Scanner(file);
            scanner.next(); // Skip header line
            while (scanner.hasNext()) {
                String curr = scanner.next();
                String[] split = curr.split(",");
                int currId = Integer.parseInt(split[0]);
                list.add(currId);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("New User Weight File Not Found");
            e.printStackTrace();
        }
        return list;
    }
}
