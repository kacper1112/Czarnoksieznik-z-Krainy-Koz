package gra;

public class Wojownik extends Gracz {
    public Wojownik() {
        super(120, 20, 10);
    }

    // dodac w zaleznosci od przedmiotu i atrybutow
    public double zadajObrazenia() {
        return this.getEkwipunek().getWyekwipowanaBron().zadajObrazenia();
    }

    public double zadajObrazeniaSpecjalne() {
        return this.getEkwipunek().getWyekwipowanaBron().zadajObrazeniaSpecjalne();
    }

    // obsluzyc jak gracza ginie
    public void otrzymajObrazenia(double wartosc) {
        // obrazenia zostaja pomniejszone o tyle % ile sily ma wojownik
        double obrazenia = (1 - this.getSila() / 100) * wartosc;
        zmniejszPunktyZycia(obrazenia);
    }

    @Override
    public void zwiekszLevel() {

    }

    //TODO generator ekwipunku
}
