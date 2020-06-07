package gra.RodzajePrzedmiot;

import gra.ElementyPomocnicze.bron;

public class BronMagiczna extends Przedmiot implements bron {

    // moc zwoju okresla jaki % obrazen bazowych zadajemy, zakres 0-100
    private int mocZwoju;
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
        mocZwoju -= 1;

        if(czyKrytyczne) {
            obrazenia *= mocUderzeniaKrytycznego;
            System.out.println("Uderzenie krytyczne!");
        }
        return obrazenia;
    }

    @Override
    public double zadajMocneObrazenia() {
       if(Math.random() < 0.6) {
           System.out.println("Twoje zaklecie nie trafilo wroga!");
           return 0;
       } else {
           // czar uzyty dwukrotnie
           return zadajObrazenia() + zadajObrazenia();
       }
    }

    @Override
    public String toString() {
        return getNazwa()  + " [obrazenia:" + obrazeniaBazowe + ", moc zwoju:" + mocZwoju + ", mod ud. krytycznego:" + mocUderzeniaKrytycznego + "]";
        /*
                return "BronMagiczna: " +
                "mocZwoju: " + mocZwoju +
                ", obrazeniaBazowe: " + obrazeniaBazowe +
                ", mocUderzeniaKrytycznego: " + mocUderzeniaKrytycznego;
         */
    }
}
