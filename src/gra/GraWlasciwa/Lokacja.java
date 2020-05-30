package gra.GraWlasciwa;

import gra.GraWlasciwa.Wydarzenie;

import java.util.List;

public class Lokacja {
    private final String nazwa;
    private final String opis;
    private final List<Integer> sasiednieLokacje;
    private final Wydarzenie wydarzenieFabularne;
    private final List<Wydarzenie> wydarzeniaPoboczne;

    public Lokacja(String nazwa,
                   String opis,
                   Wydarzenie fabularne,
                   List<Wydarzenie> poboczne,
                   List<Integer> sasiednie) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.wydarzenieFabularne = fabularne;
        this.wydarzeniaPoboczne = poboczne;
        this.sasiednieLokacje = sasiednie;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public List<Integer> getSasiednieLokacje() {
        return sasiednieLokacje;
    }

    public Wydarzenie getWydarzenieFabularne() {
        return wydarzenieFabularne;
    }

    public List<Wydarzenie> getWydarzeniaPoboczne() {
        return wydarzeniaPoboczne;
    }
}
