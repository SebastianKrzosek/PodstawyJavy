public class SredniaArytmetyczna implements Srednia {

    @Override
    public double obliczSrednia(int[] oceny) {
        int result = 0;
        for (int i = 0; i < oceny.length; i++) result += oceny[i];
        return 1.0 * result / oceny.length;
    }
}
