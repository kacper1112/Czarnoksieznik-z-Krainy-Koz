package gra.RodzajePrzedmiot;

import gra.ElementyPomocnicze.bron;

public class BronMagiczna extends Przedmiot implements bron {

    // moc zwoju okresla jaki % obrazen bazowych zadajemy, zakres 0-100
    private final int mocZwoju;
    private final double obrazeniaBazowe;
    private final double mocUderzeniaKrytycznego;

    public BronMagiczna(String nazwa,
                        String opis,
                        double wartosc,
                        int szansaAtrybutu,
                        double obrazeniaBazowe,
                        double mocUderzeniaKrytycznego) {
        super(nazwa, opis, wartosc, szansaAtrybutu);
        this.obrazeniaBazowe = obrazeniaBazowe;
        this.mocUderzeniaKrytycznego = mocUderzeniaKrytycznego;
        mocZwoju = 100;
    }

    @Override
    public double zadajObrazenia() {
        double obrazenia = obrazeniaBazowe * mocZwoju / 100;
        boolean czyKrytyczne = (Math.random() < this.getAtrybut().getSzansaNaKrytyczne());

        if(czyKrytyczne) {
            obrazenia *= mocUderzeniaKrytycznego;
            System.out.println("Uderzenie krytyczne!");
        }
        return obrazenia;
    }

    @Override
    public double zadajObrazeniaSpecjalne() {
        // 40% szans na powodzenie podwojnego czaru
        boolean czyPodwojne = (Math.random() < 0.4);

        if(czyPodwojne) {
            return zadajObrazenia() + zadajObrazenia();
        } else {
            // ujemne obrazenia zadzialaja jak leczenie
            return -zadajObrazenia();
        }
    }

    @Override
    public String toString() {
        return "(" + getNazwa()  + ", " + obrazeniaBazowe + ", " + mocZwoju + ", " + mocUderzeniaKrytycznego + ")";
        /*
                return "BronMagiczna: " +
                "mocZwoju: " + mocZwoju +
                ", obrazeniaBazowe: " + obrazeniaBazowe +
                ", mocUderzeniaKrytycznego: " + mocUderzeniaKrytycznego;
         */
    }
}
