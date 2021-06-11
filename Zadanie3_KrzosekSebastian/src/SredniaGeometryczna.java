public class SredniaGeometryczna implements Srednia {

    @Override
    public double obliczSrednia(int[] oceny) {
        double result = 1;
        int count = 0;
        for (int i = 0; i < oceny.length; i++) {
            if (oceny[i] == 0) {
                count++;
                continue;
            }
            result *= oceny[i];
        }
        if (count == oceny.length) return 0; //jesli tablica wypelniona jest zerami, to srednia jest rowna 0
        else
            return Math.round(Math.pow(result, 1.0 / oceny.length) * 1000.0) / 1000.0; //zaokraglamy do 4 msc po przecinku
    }
}
