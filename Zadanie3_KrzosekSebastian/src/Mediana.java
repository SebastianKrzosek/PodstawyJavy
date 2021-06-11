public class Mediana implements Srednia {

    @Override
    public double obliczSrednia(int[] oceny) {
        java.util.Arrays.sort(oceny);
        int index = oceny.length / 2;

        if(oceny.length % 2 == 0)
            return (oceny[index - 1] + oceny[index]) / 2.0;
        else
            return oceny[index];
    }
}
