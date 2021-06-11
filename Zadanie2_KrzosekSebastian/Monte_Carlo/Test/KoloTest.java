import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KoloTest {

    @Test
    void czyPunktJestWewnatrz() {
        Kolo kolko = new Kolo(1);
        assertEquals(true, kolko.czyPunktJestWewnatrz(0,0)); //pkt w srodku
    }

    @Test
    void czyPunktJestWewnatrz1() {
        Kolo kolko = new Kolo(1);
        assertEquals(true, kolko.czyPunktJestWewnatrz(1,0)); //pkt na krawedzi
    }

    @Test
    void czyPunktJestWewnatrz2() {
        Kolo kolko = new Kolo(1);
        assertEquals(false, kolko.czyPunktJestWewnatrz(1,1));
    }
}