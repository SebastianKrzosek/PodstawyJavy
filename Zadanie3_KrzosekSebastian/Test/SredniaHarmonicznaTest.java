import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SredniaHarmonicznaTest {

    @Test
    void obliczSrednia1() {
        SredniaHarmoniczna har = new SredniaHarmoniczna();
        int[] oceny = {1,2,4};
        assertEquals(1.714, har.obliczSrednia(oceny));
    }
    @Test
    void obliczSrednia2() {
        SredniaHarmoniczna har = new SredniaHarmoniczna();
        int[] oceny = {3,9,27,81};
        assertEquals(8.1, har.obliczSrednia(oceny));
    }

}