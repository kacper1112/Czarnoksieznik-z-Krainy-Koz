package gra.GraWlasciwa;

import gra.NPC.Handlarz;

import java.util.List;

public class Lokacja {
    private final String nazwa;
    private final String opis;
    private final List<Integer> sasiednieLokacje;
    private final Wydarzenie wydarzenieFabularne;
    private final List<Wydarzenie> wydarzeniaPoboczne;
    private final Handlarz handlarz;

    public Lokacja(String nazwa,
                   String opis,
                   Wydarzenie fabularne,
                   List<Wydarzenie> poboczne,
                   List<Integer> sasiednie,
                   Handlarz handlarz) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.wydarzenieFabularne = fabularne;
        this.wydarzeniaPoboczne = poboczne;
        this.sasiednieLokacje = sasiednie;
        this.handlarz = handlarz;
    }

    //GETTERY
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

    public Handlarz getHandlarz() {
        return handlarz;
    }
}
