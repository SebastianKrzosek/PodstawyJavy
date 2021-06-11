public class SredniaHarmoniczna implements Srednia {

    @Override
    public double obliczSrednia(int[] oceny) {
        double result = 0;
        for (int i1 : oceny) result += 1.0 / i1;
        return Math.round((oceny.length / result) * 1000) / 1000.0;
    }
}
