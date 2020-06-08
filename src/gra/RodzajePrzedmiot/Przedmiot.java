package gra.RodzajePrzedmiot;

public abstract class Przedmiot {
    private final String nazwa;
    private final String opis;
    private final int wartosc;
    private final Atrybut atrybut;

    public Przedmiot(String nazwa, String opis, int wartosc, int szansaAtrybutu) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.wartosc = wartosc;
        this.atrybut = new Atrybut(szansaAtrybutu);
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public int getWartosc() {
        return wartosc;
    }

    public Atrybut getAtrybut() {
        return atrybut;
    }
}
