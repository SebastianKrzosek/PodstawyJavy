class MonteCarlo {
    public static void main(String[] args) {
        Kolo kolko = new Kolo(1);
        double count=0;
        int n=100;
        for(int i=0 ; i<n; i++)
        {
            if(kolko.czyPunktJestWewnatrz(Math.random(), Math.random()))
            {
                count += 1;
            }
        }
        count = (count*4) / n;
        System.out.println(count);
    }
}

class Kolo{
    private double r;

    Kolo(double r)
    {
        this.r = r;
    }

    boolean czyPunktJestWewnatrz(double x, double y)
    {
        double distance = Math.sqrt(Math.pow(x,2)+Math.pow(y,2)); //srodek kola znajduje się w (0,0)

        if(distance <= this.r)
            return true;
        else
            return false;
    }
}
