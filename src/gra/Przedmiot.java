package gra;

public abstract class Przedmiot {
    final String nazwa;
    final String opis;
    double wartosc;
    Atrybut atrybut;

    Przedmiot() {
        nazwa = "";
        opis = "";
        wartosc = 0;
        atrybut = new Atrybut();
    }

    public Przedmiot(String nazwa, String opis, double wartosc, int szansaAtrybutu) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.wartosc = wartosc;
        this.atrybut = new Atrybut(szansaAtrybutu);
    }
}
