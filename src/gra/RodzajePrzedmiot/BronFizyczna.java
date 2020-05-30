package gra.RodzajePrzedmiot;

import gra.ElementyPomocnicze.bron;

public class BronFizyczna extends Przedmiot implements bron {

    // ostrosc okresla jaki % obrazen bazowych zadajemy, zakres 0-100
    private final int ostrosc;
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

        if(czyKrytyczne) {
            obrazenia *= mocUderzeniaKrytycznego;
            System.out.println("Uderzenie krytyczne!");
        }
        return obrazenia;
    }

    @Override
    public double zadajObrazeniaSpecjalne() {
        // 60% szans na powodzenie uderzenia
        boolean czyUdaneUderzenie = (Math.random() < 0.6);

        if(!czyUdaneUderzenie) {
            return 0;
        }

        // mocne uderzenie jest o 60% mocniejsze od zwyklego
        return zadajObrazenia() * 1.6;
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
        return "(" + getNazwa() + ", " + obrazeniaBazowe + ", " + ostrosc + ", " + mocUderzeniaKrytycznego + ")";
        /*
         return "BronFizyczna: " +
                "ostrosc: " + ostrosc +
                ", obrazeniaBazowe: " + obrazeniaBazowe +
                ", mocUderzeniaKrytycznego: " + mocUderzeniaKrytycznego;
         */

    }
}
