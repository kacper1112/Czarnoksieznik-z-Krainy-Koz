package gra.GraWlasciwa;

import gra.GraWlasciwa.Wydarzenie;

public class Lokacja {
    private final String nazwa;
    private final String opis;
    private final int[] sasiednieLokacje;
    private final Wydarzenie wydarzenieFabularne;
    private final Wydarzenie[] wydarzeniaPoboczne;

    public Lokacja(String nazwa,
                   String opis,
                   Wydarzenie fabularne,
                   Wydarzenie[] poboczne,
                   int[] sasiednie) {
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

    public int[] getSasiednieLokacje() {
        return sasiednieLokacje;
    }

    public Wydarzenie getWydarzenieFabularne() {
        return wydarzenieFabularne;
    }

    public Wydarzenie[] getWydarzeniaPoboczne() {
        return wydarzeniaPoboczne;
    }
}
