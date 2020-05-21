package gra;

public class Mag extends Gracz {
    public Mag() {
        super(100, 10, 20);
    }

    // dodac w zaleznosci od przedmiotu i atrybutow
    public double zadajObrazenia() {
        return this.getInteligencja() + this.ekwipunek.getWyekwipowanaBron().wartosc;
    }

    // obsluzyc jak gracza ginie
    public void otrzymajObrazenia(double wartosc) {
        // mozwliwosc uniku
        if(Math.random() >= .1) {
            // obrazenia zostaja pomniejszone o tyle % ile sily ma wojownik
            double obrazenia = (1 - inteligencja / 100) * wartosc;
            zmniejszPunktyZycia(obrazenia);
        }
    }

    public void zwiekszLevel() {
        if(this.getPunktyDoswiadczenia() > 100) {
            this.setPoziom(this.getPoziom() + 1);
            this.setPunktyDoswiadczenia(this.getPunktyDoswiadczenia() % 100);
            this.setInteligencja(this.getInteligencja() + 10);
        }
    }
}