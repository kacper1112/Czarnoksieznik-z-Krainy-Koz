package gra;

public class Wojownik extends Gracz {
    void otrzymajObrazenia(double wartosc) {
        // obrazenia zostaja pomniejszone o tyle % ile sily ma wojownik



        double obrazenia = (1 - sila / 100) * wartosc;
        zmniejszPunktyZycia(obrazenia);
    }
}
