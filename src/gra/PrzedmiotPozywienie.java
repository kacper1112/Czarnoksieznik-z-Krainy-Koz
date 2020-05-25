package gra;

public class PrzedmiotPozywienie extends Przedmiot {
    private final double przywracaneZycie;

    public PrzedmiotPozywienie(String nazwa,
                               String opis,
                               double wartosc,
                               int szansaAtrybutu,
                               double przywracaneZycie) {
        super(nazwa, opis, wartosc, szansaAtrybutu);
        this.przywracaneZycie = przywracaneZycie;
    }

    public double getPrzywracaneZycie() {
        return przywracaneZycie;
    }

    @Override
    public String toString() {
        return "PrzedmiotPozywienie: " +
                "przywracaneZycie: " + przywracaneZycie;
    }
}
