package gra;

public class PrzedmiotFabularny {
    boolean czyZuzyty;
    String wskazowka;
    double inteligencjaWymaganaDoWskazowki;

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
}
