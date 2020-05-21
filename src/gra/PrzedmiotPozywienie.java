package gra;

public class PrzedmiotPozywienie extends Przedmiot {
    double przywracaneZycie;

    public PrzedmiotPozywienie(double przywracaneZycie) {
        this.przywracaneZycie = przywracaneZycie;
    }

    public double getPrzywracaneZycie() {
        return przywracaneZycie;
    }
}
