package gra;

public class PrzedmiotFabularny extends Przedmiot {
    private final boolean czyZuzyty;
    private final String wskazowka;
    private final double inteligencjaWymaganaDoWskazowki;

    public PrzedmiotFabularny(boolean czyZuzyty,
                              String wskazowka,
                              double inteligencjaWymaganaDoWskazowki) {
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
        return "PrzedmiotFabularny: " +
                "czyZuzyty: " + czyZuzyty +
                ", wskazowka: " + wskazowka +
                ", inteligencjaWymaganaDoWskazowki: " + inteligencjaWymaganaDoWskazowki;
    }
}
