import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    private final List<Horse> EMPTY_HORSES_LIST = new ArrayList<>();

    @Test
    public void ifFirstParamForConstructorNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void ifFirstParamForConstructorNullMessageValid() {
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            String validExceptionMessage = "Horses cannot be null.";
            assertEquals(validExceptionMessage, e.getMessage());
        }
    }

    @Test
    public void ifEmptyListForConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(EMPTY_HORSES_LIST));
    }

    @Test
    public void ifEmptyListForConstructorMessageValid() {
        try {
            new Hippodrome(EMPTY_HORSES_LIST);
        } catch (IllegalArgumentException e) {
            String validExceptionMessage = "Horses cannot be empty.";
            assertEquals(validExceptionMessage, e.getMessage());
        }
    }

    @Test
    public void areListsOfHorsesTheSame() {
        List<Horse> horses = horsesGenerator(30);
        Hippodrome hippodrome = new Hippodrome(horses);

        //assertSame(horses, hippodrome.getHorses());
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void isMoveCallsForEachHorse() {
        List<Horse> mockHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mockHorses.add(Mockito.mock(Horse.class));
        }
        Hippodrome mockHippodrome = new Hippodrome(mockHorses);

        mockHippodrome.move();
        //mockHorses.add(Mockito.mock(Horse.class));

        for (Horse horse : mockHorses) {
            verify(horse).move();
        }
    }

    @Test
    public void isGetWinnerReturnsRealWinner() {
        List<Horse> horses = horsesGenerator(50);
        Hippodrome hippodrome = new Hippodrome(horses);

        Horse winnerHorse = hippodrome.getWinner();
        double realMaxDistance = 0;
        for (Horse horse : hippodrome.getHorses()) {
            if (horse.getDistance() > realMaxDistance) realMaxDistance = horse.getDistance();
        }
        //realMaxDistance += 0.1;

        assertEquals(realMaxDistance, winnerHorse.getDistance());
    }

    public static List<Horse> horsesGenerator(int count) {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String name = "horse" + i;
            double speed = 0.1;
            double distance = 0.2;

            horses.add(new Horse(name, speed, distance));
        }
        return horses;
    }
}
