package gra;

public class Kaplan extends Gracz {
    public Kaplan() {
        super(110, 15, 15);
    }

    // dodac w zaleznosci od przedmiotu i atrybutow
    public double zadajObrazenia() {
        return this.ekwipunek.getWyekwipowanaBron().wartosc;
    }

    // obsluzyc jak gracza ginie
    public void otrzymajObrazenia(double wartosc) {
        // sprawdz czy moze sie uleczyc
        if(Math.random() < .1) {
            this.zwiekszPunktyZycia(wartosc);
        } else {
            // obrazenia zostaja pomniejszone o tyle % ile sily ma wojownik
            double obrazenia = (1 - sila / 100) * wartosc;
            zmniejszPunktyZycia(obrazenia);
        }
    }

    public void zwiekszLevel() {
        if(this.getPunktyDoswiadczenia() > 100) {
            this.setPoziom(this.getPoziom() + 1);
            this.setPunktyDoswiadczenia(this.getPunktyDoswiadczenia() % 100);
            this.setSila(this.getSila() + 5);
            this.setInteligencja(this.getInteligencja() + 5);
        }
    }
}