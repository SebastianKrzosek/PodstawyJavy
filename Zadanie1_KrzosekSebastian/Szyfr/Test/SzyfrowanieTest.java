import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SzyfrowanieTest {

    @Test
    void encrypt() {
        assertEquals("Blb nb goxb", "Ala ma kota");
    }

    @Test
    void encrypt1() {
        assertEquals("Gox lzaf psb","Kot lubi psa");
    }
}