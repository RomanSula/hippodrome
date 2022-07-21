import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {
    private final double TEST_SPEED = 45.0;
    private final double TEST_DISTANCE = 12.5;
    private final String TEST_NAME = "HorseName";

    @Test
    public void ifFirstParamForConstructorsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, TEST_SPEED, TEST_DISTANCE));
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, TEST_SPEED));
    }

    @Test
    public void ifFirstParamForConstructorNullMessageValid() {
        try {
            new Horse(null, TEST_SPEED);
            new Horse(null, TEST_SPEED, TEST_DISTANCE);
        } catch (IllegalArgumentException e) {
            String VALID_NULL_MESSAGE = "Name cannot be null.";
            assertEquals(VALID_NULL_MESSAGE, e.getMessage());
        }

    }

    @ParameterizedTest
    @ValueSource(strings = {"", "\t", "\n", "\t\n", "\n\t", "\n\n\n\n\t\t\t\t", "    \t"})
    public void ifFirstParamForConstructorEmpty(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, TEST_SPEED));
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, TEST_SPEED, TEST_DISTANCE));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "\t", "\n", "\t\n", "\n\t", "\n\n\n\n\t\t\t\t", "    \t"})
    public void ifFirstParamForConstructorEmptyBlankMessageValid(String name) {
        try {
            assertThrows(IllegalArgumentException.class, () -> new Horse(name, TEST_SPEED));
            assertThrows(IllegalArgumentException.class, () -> new Horse(name, TEST_SPEED, TEST_DISTANCE));
        } catch (Exception e) {
            String VALID_BLANK_MESSAGE = "Name cannot be blank.";
            assertEquals(VALID_BLANK_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void ifSpeedLessZero() {
        double unrealSpeed = -0.5;
        assertThrows(IllegalArgumentException.class, () -> new Horse(TEST_NAME, unrealSpeed, TEST_DISTANCE));
        assertThrows(IllegalArgumentException.class, () -> new Horse(TEST_NAME, unrealSpeed));
    }

    @Test
    public void ifSpeedLessZeroValidMessage() {
        double unrealSpeed = -0.5;
        try {
            new Horse(TEST_NAME, unrealSpeed, TEST_DISTANCE);
            new Horse(TEST_NAME, unrealSpeed);
        } catch (Exception e) {
            String validNegativeSpeedMessage = "Speed cannot be negative.";
            assertEquals(validNegativeSpeedMessage, e.getMessage());
        }
    }

    @Test
    public void ifDistanceLessZero() {
        double unrealDistance = -15;
        assertThrows(IllegalArgumentException.class, () -> new Horse(TEST_NAME, TEST_SPEED, unrealDistance));
    }

    @Test
    public void ifDistanceLessZeroValidMessage() {
        double unrealDistance = -89;
        try {
            new Horse(TEST_NAME, TEST_SPEED, unrealDistance);
        } catch (Exception e) {
            String validNegativeDistanceMessage = "Distance cannot be negative.";
            assertEquals(validNegativeDistanceMessage, e.getMessage());
        }
    }

    @Test
    public void getNameReturnCorrect() {
        Horse testHorse = new Horse(TEST_NAME, TEST_SPEED, TEST_DISTANCE);
        assertEquals(TEST_NAME, testHorse.getName());
    }

    @Test
    public void getSpeedReturnCorrect() {
        Horse testHorse = new Horse(TEST_NAME, TEST_SPEED, TEST_DISTANCE);
        assertEquals(TEST_SPEED, testHorse.getSpeed());
    }

    @Test
    public void getDistanceReturnCorrect() {
        Horse testHorse = new Horse(TEST_NAME, TEST_SPEED, TEST_DISTANCE);
        assertEquals(TEST_DISTANCE, testHorse.getDistance());
    }

    @Test
    public void getDistanceReturnZero() {
        Horse testHorse = new Horse(TEST_NAME, TEST_SPEED);
        assertEquals(0, testHorse.getDistance());
    }

    @Test
    public void isGetRandomDoubleCalls() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse(TEST_NAME, TEST_SPEED, TEST_DISTANCE).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 1.4, 10.2, 99999.999})
    public void isDistanceCalculatingCorrect(double doubles){
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            Horse horse = new Horse(TEST_NAME, TEST_SPEED, TEST_DISTANCE);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(doubles);
            horse.move();
            assertEquals(TEST_DISTANCE + TEST_SPEED * doubles, horse.getDistance());
        }
    }
}
