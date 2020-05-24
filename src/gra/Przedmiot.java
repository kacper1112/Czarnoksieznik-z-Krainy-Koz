package gra;

public abstract class Przedmiot {
    private final String nazwa;
    private final String opis;
    private final double wartosc;
    private final Atrybut atrybut;

    public Przedmiot() {
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

    public String getNazwa() {
        return nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public double getWartosc() {
        return wartosc;
    }

    public Atrybut getAtrybut() {
        return atrybut;
    }
}
