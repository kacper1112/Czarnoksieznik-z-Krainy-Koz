package gra.RodzajePrzedmiot;

import gra.ElementyPomocnicze.bron;

public class BronFizyczna extends Przedmiot implements bron {

    // ostrosc okresla jaki % obrazen bazowych zadajemy, zakres 0-100
    private int ostrosc;
    private final double obrazeniaBazowe;
    private final double mocUderzeniaKrytycznego;

    public BronFizyczna(String nazwa,
                        String opis,
                        double wartosc,
                        int szansaAtrybutu,
                        double obrazeniaBazowe,
                        double mocUderzeniaKrytycznego) {
        super(nazwa, opis, wartosc, szansaAtrybutu);
        this.obrazeniaBazowe = obrazeniaBazowe;
        this.mocUderzeniaKrytycznego = mocUderzeniaKrytycznego;
        ostrosc = 100;
    }

    @Override
    public double zadajObrazenia() {
        double obrazenia = obrazeniaBazowe * ostrosc / 100;
        boolean czyKrytyczne = (Math.random() < this.getAtrybut().getSzansaNaKrytyczne());
        ostrosc -= 1;

        if(czyKrytyczne) {
            obrazenia *= mocUderzeniaKrytycznego;
            System.out.println("Uderzenie krytyczne!");
        }
        return obrazenia;
    }

    @Override
    public double zadajMocneObrazenia() {
        // 60% szans na powodzenie uderzenia
        if(Math.random() < 0.4) {
            System.out.println("Nie trafiles wroga!");
            return 0;
        }
        // mocne uderzenie jest o 30% mocniejsze od zwyklego
        return zadajObrazenia() * 1.3;
    }

    public int getOstrosc() {
        return ostrosc;
    }

    public double getObrazeniaBazowe() {
        return obrazeniaBazowe;
    }

    public double getMocUderzeniaKrytycznego() {
        return mocUderzeniaKrytycznego;
    }

    @Override
    public String toString() {
        return "[" + getNazwa() + ", obrażenia: " + obrazeniaBazowe + ", ostrosc: " + ostrosc + ", moc ud. krytycznego: " + (mocUderzeniaKrytycznego + 100)  + "]";
        /*
         return "BronFizyczna: " +
                "ostrosc: " + ostrosc +
                ", obrazeniaBazowe: " + obrazeniaBazowe +
                ", mocUderzeniaKrytycznego: " + mocUderzeniaKrytycznego;
         */
    }
}
