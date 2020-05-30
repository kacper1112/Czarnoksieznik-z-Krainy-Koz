package gra.RodzajePrzedmiot;

import gra.RodzajePrzedmiot.Przedmiot;

public class  PrzedmiotFabularny extends Przedmiot {
    private final boolean czyZuzyty;
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
            return wskazowka;
        } else {
            return "Nie jestes wystarczajaco inteligentny!";
        }
    }

    public boolean isCzyZuzyty() {
        return czyZuzyty;
    }

    public String getWskazowka() {
        return wskazowka;
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
