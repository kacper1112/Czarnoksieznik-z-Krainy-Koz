package gra;

public class Wojownik extends Gracz {
    public Wojownik() {
        super(120, 20, 10);
    }

    // dodac w zaleznosci od przedmiotu i atrybutow
    public double zadajObrazenia() {
        return this.getSila();
    }

    // obsluzyc jak gracza ginie
    public void otrzymajObrazenia(double wartosc) {
        // obrazenia zostaja pomniejszone o tyle % ile sily ma wojownik
        double obrazenia = (1 - sila / 100) * wartosc;
        zmniejszPunktyZycia(obrazenia);
    }
}
