package finalproject;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeightManagerTest {

    @Test
    void testGenerateRandomNumericId() {
        WeightManager weightManager = new WeightManager();
        int id1 = weightManager.generateRandomNumericId();
        int id2 = weightManager.generateRandomNumericId();
        assertNotEquals(id1, id2);
    }

    @Test
    void testExistingId() {
        WeightManager weightManager = new WeightManager();
        List<Integer> existingIds = weightManager.existingId();
        assertNotNull(existingIds);
        assertFalse(existingIds.isEmpty());
    }

    @Test
    void testExistingIdFileNotFound() {
        // Test when the file does not exist
        WeightManager weightManager = new WeightManager();
        weightManager.newUserWeightFilePath = "nonexistent.csv"; // Set the file path to a nonexistent file
        List<Integer> existingIds = weightManager.existingId();
        assertNotNull(existingIds);
        assertTrue(existingIds.isEmpty());
    }

    @Test
    void testExistingIdEmptyFile() {
        // Test when the file exists but is empty
        WeightManager weightManager = new WeightManager();
        weightManager.newUserWeightFilePath = "empty.csv"; // Create an empty file for testing
        List<Integer> existingIds = weightManager.existingId();
        assertNotNull(existingIds);
        assertTrue(existingIds.isEmpty());
    }

    @Test
    void testExistingIdFileWithInvalidData() {
        // Test when the file exists but contains invalid data
        WeightManager weightManager = new WeightManager();
        weightManager.newUserWeightFilePath = "invalid_data.csv"; // Create a file with invalid data for testing
        List<Integer> existingIds = weightManager.existingId();
        assertNotNull(existingIds);
        assertTrue(existingIds.isEmpty()); // Even if data is invalid, the method should still return a list
    }
}

