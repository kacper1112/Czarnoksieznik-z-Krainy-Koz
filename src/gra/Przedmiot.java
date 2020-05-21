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
}
