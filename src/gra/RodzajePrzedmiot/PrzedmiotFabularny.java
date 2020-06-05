package gra.RodzajePrzedmiot;

import gra.RodzajePrzedmiot.Przedmiot;

public class  PrzedmiotFabularny extends Przedmiot {
    private boolean czyZuzyty;
    private final String wskazowka;
    private final double inteligencjaWymaganaDoWskazowki;

    public PrzedmiotFabularny(String nazwa, String opis, double wartosc, int szansaAtrybutu, boolean czyZuzyty,
                              String wskazowka,
                              double inteligencjaWymaganaDoWskazowki) {
        super(nazwa, opis, wartosc, szansaAtrybutu);
        this.czyZuzyty = czyZuzyty;
        this.wskazowka = wskazowka;
        this.inteligencjaWymaganaDoWskazowki = inteligencjaWymaganaDoWskazowki;
    }

    public String getWskazowka(double inteligencja) {
        if(inteligencja >= inteligencjaWymaganaDoWskazowki) {
            czyZuzyty = true;
            return wskazowka;
        } else {
            return "Nie jestes wystarczajaco inteligentny!";
        }
    }

    @Override
    public String toString() {
        return "(" + getNazwa() + ", " + inteligencjaWymaganaDoWskazowki + ")";
        /*
                return "PrzedmiotFabularny: " +
                "czyZuzyty: " + czyZuzyty +
                ", wskazowka: " + wskazowka +
                ", inteligencjaWymaganaDoWskazowki: " + inteligencjaWymaganaDoWskazowki;
         */
    }
}
