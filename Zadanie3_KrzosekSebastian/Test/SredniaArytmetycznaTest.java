import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SredniaArytmetycznaTest {

    @Test
    void obliczSrednia1() {
        SredniaArytmetyczna art = new SredniaArytmetyczna();
        int[] oceny = new int[5];
        for (int i = 0; i < oceny.length; i++) {
            oceny[i] = i + 2;
        }
        assertEquals(4.0, art.obliczSrednia(oceny));
    }

    @Test
    void obliczSrednia2() {
        SredniaArytmetyczna art = new SredniaArytmetyczna();
        int[] oceny = new int[3];
        for (int i = 0; i < oceny.length; i++) {
            oceny[i] = i+4;
        }
        assertEquals(5.0, art.obliczSrednia(oceny));
    }

    @Test
    void obliczSrednia3() {
        SredniaArytmetyczna art = new SredniaArytmetyczna();
        int[] oceny = new int[15];
        for (int i = 0; i < oceny.length; i++) {
            oceny[i] = 6;
        }
        assertEquals(6.0, art.obliczSrednia(oceny));
    }
}