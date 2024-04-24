package finalproject;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WeightTest {

    @Test
    void testGetId() {
        int id = 123;
        Weight weight = new Weight(id, LocalDate.now(), "John", 180.0, 160.0, 150.0);
        assertEquals(id, weight.getId());
    }

    @Test
    void testSetId() {
        int id = 123;
        Weight weight = new Weight(0, LocalDate.now(), "John", 180.0, 160.0, 150.0);
        weight.setId(id);
        assertEquals(id, weight.getId());
    }

    @Test
    void testGetDate() {
        LocalDate date = LocalDate.now();
        Weight weight = new Weight(123, date, "John", 180.0, 160.0, 150.0);
        assertEquals(date, weight.getDate());
    }

    @Test
    void testSetDate() {
        LocalDate date = LocalDate.now();
        Weight weight = new Weight(123, null, "John", 180.0, 160.0, 150.0);
        weight.setDate(date);
        assertEquals(date, weight.getDate());
    }

    @Test
    void testGetName() {
        String name = "John";
        Weight weight = new Weight(123, LocalDate.now(), name, 180.0, 160.0, 150.0);
        assertEquals(name, weight.getName());
    }

    @Test
    void testSetName() {
        String name = "John";
        Weight weight = new Weight(123, LocalDate.now(), null, 180.0, 160.0, 150.0);
        weight.setName(name);
        assertEquals(name, weight.getName());
    }

    @Test
    void testGetHeight() {
        double height = 180.0;
        Weight weight = new Weight(123, LocalDate.now(), "John", height, 160.0, 150.0);
        assertEquals(height, weight.getHeight());
    }

    @Test
    void testSetHeight() {
        double height = 180.0;
        Weight weight = new Weight(123, LocalDate.now(), "John", 0.0, 160.0, 150.0);
        weight.setHeight(height);
        assertEquals(height, weight.getHeight());
    }

    @Test
    void testGetWeight() {
        double weightValue = 160.0;
        Weight weight = new Weight(123, LocalDate.now(), "John", 180.0, weightValue, 150.0);
        assertEquals(weightValue, weight.getWeight());
    }

    @Test
    void testSetWeight() {
        double weightValue = 160.0;
        Weight weight = new Weight(123, LocalDate.now(), "John", 180.0, 0.0, 150.0);
        weight.setWeight(weightValue);
        assertEquals(weightValue, weight.getWeight());
    }

    @Test
    void testGetTargetWeight() {
        double targetWeight = 150.0;
        Weight weight = new Weight(123, LocalDate.now(), "John", 180.0, 160.0, targetWeight);
        assertEquals(targetWeight, weight.getTargetWeight());
    }

    @Test
    void testSetTargetWeight() {
        double targetWeight = 150.0;
        Weight weight = new Weight(123, LocalDate.now(), "John", 180.0, 160.0, 0.0);
        weight.setTargetWeight(targetWeight);
        assertEquals(targetWeight, weight.getTargetWeight());
    }

    @Test
    void testToString() {
        int id = 123;
        LocalDate date = LocalDate.now();
        String name = "John";
        double height = 180.0;
        double weightValue = 160.0;
        double targetWeight = 150.0;
        String expectedString = id + "," + date + "," + name + "," + height + "," + weightValue + "," + targetWeight;
        Weight weight = new Weight(id, date, name, height, weightValue, targetWeight);
//        String expectedString = id + "," + date + "," + name + "," + height + "," + weightValue + "," + targetWeight;
//        Weight weight = new Weight(id, date, name, height, weightValue, targetWeight);
        assertEquals(expectedString, weight.toString());
    }
}
