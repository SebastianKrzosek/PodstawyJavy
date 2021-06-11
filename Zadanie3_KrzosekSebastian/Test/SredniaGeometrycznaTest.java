import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SredniaGeometrycznaTest {

    @Test
    void obliczSrednia1() {
        SredniaGeometryczna geo = new SredniaGeometryczna();
        int[] oceny = new int[2];
        oceny[0] = 3;
        oceny[1] = 12;

        assertEquals(6.0, geo.obliczSrednia(oceny));
    }

    @Test
    void obliczSrednia2() {
        SredniaGeometryczna geo = new SredniaGeometryczna();
        int[] oceny = new int[3];
        oceny[0] = 3;
        oceny[1] = 27;
        oceny[2] = 9;

        assertEquals(9.0, geo.obliczSrednia(oceny));
    }


    @Test
    void obliczSrednia3() { //same zera
        SredniaGeometryczna geo = new SredniaGeometryczna();
        int[] oceny = new int[3];
        assertEquals(0, geo.obliczSrednia(oceny));
    }

}