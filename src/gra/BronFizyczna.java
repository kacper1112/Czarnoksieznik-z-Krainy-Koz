package gra;

public class BronFizyczna extends Przedmiot implements bron {

    // ostrosc okresla jaki % obrazen bazowych zadajemy, zakres 0-1
    double ostrosc;
    double obrazeniaBazowe;
    double uderzenieKrytyczne;

    public BronFizyczna(String nazwa,
                        String opis,
                        double wartosc,
                        int szansaAtrybutu,
                        double obrazeniaBazowe,
                        double uderzenieKrytyczne) {
        super(nazwa, opis, wartosc, szansaAtrybutu);
        this.obrazeniaBazowe = obrazeniaBazowe;
        this.uderzenieKrytyczne = uderzenieKrytyczne;
    }

    @Override
    public double zadajObrazenia() {

        return 0;
    }

    @Override
    public double zadajObrazeniaSpecjalne() {
        return 0;
    }


}
