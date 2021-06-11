import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedianaTest {

    @Test
    void obliczSrednia() { //nieparzysta liczba elementow uporzadkowana
        Mediana med = new Mediana();
        int[] oceny = new int[5];
        for (int i = 0; i < oceny.length; i++) {
            oceny[i] = i + 2;
        }
        assertEquals(4, med.obliczSrednia(oceny));
    }

    @Test
    void obliczSrednia1() { //nieparzysta liczba elementow nieuporzadkowana
        Mediana med = new Mediana();
        int[] oceny = {11, -2, 4, 7, 9};
        assertEquals(7, med.obliczSrednia(oceny));
    }

    @Test
    void obliczSrednia2() { //parzysta liczba elementow nieuporzadkowana
        Mediana med = new Mediana();
        int[] oceny = {11, -2, 4, 7, 9, 12};
        assertEquals(8, med.obliczSrednia(oceny));
    }
}