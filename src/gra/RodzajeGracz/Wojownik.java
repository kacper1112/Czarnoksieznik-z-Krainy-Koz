package gra.RodzajeGracz;

import gra.RodzajePrzedmiot.BronFizyczna;
import gra.ElementyPomocnicze.Ekwipunek;
import gra.RodzajePrzedmiot.PrzedmiotPozywienie;
import gra.ElementyPomocnicze.TYP_POSIADACZA_EKWIPUNKU;

import java.util.Random;

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

    /**
     * Wojownik na poczatku dostaje:
     * podstawowa bron fizyczna
     * jedzenie + potion lub jedzenie - szansa 50%
     */
    @Override
    public Ekwipunek generujEkwipunek() {
        Ekwipunek ekwipunekTMP = new Ekwipunek(TYP_POSIADACZA_EKWIPUNKU.WOJOWNIK);
        Random rand = new Random();
        ekwipunekTMP.wlozBronFizyczna(new BronFizyczna(
                "poczatkowa bron fizyczna",
                "opis",
                10,
                10,
                10,
                10
        ));
        ekwipunekTMP.wlozPozywienie(new PrzedmiotPozywienie(
                "podstawowe mieso",
                "opis",
                10,
                10,
                10
        ));

        if(rand.nextDouble() < 0.5){
            ekwipunekTMP.wlozPozywienie(new PrzedmiotPozywienie(
                    "podstawowa pota",
                    "opis",
                    10,
                    10,
                    10
            ));
        }else {
            ekwipunekTMP.wlozPozywienie(new PrzedmiotPozywienie(
                    "podstawowe mieso",
                    "opis",
                    10,
                    10,
                    10
            ));
        }

        return ekwipunekTMP;
    }
}
