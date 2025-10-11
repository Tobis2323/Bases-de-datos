package miContenido.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AgeGroupTest {

    @Test
    void exactValue_shouldMatchOnlyThatValue() {
        AgeGroup g = new AgeGroup();
        g.setCode("12");
        assertTrue(g.matchesAge(12));
        assertFalse(g.matchesAge(11));
        assertFalse(g.matchesAge(13));
    }

    @Test
    void closedRange_shouldMatchInsideAndBounds() {
        AgeGroup g = new AgeGroup();
        g.setCode("6-12");
        assertTrue(g.matchesAge(6));
        assertTrue(g.matchesAge(12));
        assertTrue(g.matchesAge(9));
        assertFalse(g.matchesAge(5));
        assertFalse(g.matchesAge(13));
    }

    @Test
    void openEnded_shouldMatchFromMinUpwards() {
        AgeGroup g = new AgeGroup();
        g.setCode("13+");
        assertTrue(g.matchesAge(13));
        assertTrue(g.matchesAge(30));
        assertFalse(g.matchesAge(12));
    }

    @Test
    void spacesAndReversedRange_shouldNormalize() {
        AgeGroup g = new AgeGroup();
        g.setCode(" 7 - 5 "); // debería normalizar a [5,7]
        assertTrue(g.matchesAge(5));
        assertTrue(g.matchesAge(6));
        assertTrue(g.matchesAge(7));
        assertFalse(g.matchesAge(4));
        assertFalse(g.matchesAge(8));
    }

    @Test
    void invalidOrEmptyCode_shouldReturnFalse() {
        AgeGroup g1 = new AgeGroup();
        g1.setCode("");
        assertFalse(g1.matchesAge(10));

        AgeGroup g2 = new AgeGroup();
        g2.setCode(null);
        assertFalse(g2.matchesAge(10));

        AgeGroup g3 = new AgeGroup();
        g3.setCode("abc");
        assertFalse(g3.matchesAge(10));

        AgeGroup g4 = new AgeGroup();
        g4.setCode("-3");
        assertFalse(g4.matchesAge(10));
    }
}

