package gra;

import java.util.List;

public class Wydarzenie {
    final String nazwa;
    final String opis;
    List<Wrog> wrogowie;
    List<Fabularny> postacieFabularne;

    public Wydarzenie(String nazwa, String opis) {
        this.nazwa = nazwa;
        this.opis = opis;
    }

    public void dodajWroga(Wrog wrog) {
        this.wrogowie.add(wrog);
    }

    public void dodajPostacFabularna(Fabularny fabularny) {
        this.postacieFabularne.add(fabularny);
    }
}
