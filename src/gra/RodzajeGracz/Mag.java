package gra.RodzajeGracz;

import gra.RodzajePrzedmiot.BronMagiczna;
import gra.ElementyPomocnicze.Ekwipunek;
import gra.RodzajePrzedmiot.PrzedmiotPozywienie;
import gra.ElementyPomocnicze.TYP_POSIADACZA_EKWIPUNKU;

import java.util.Random;

public class Mag extends Gracz {
    public Mag() {
        super(100, 10, 20);
    }

    // dodac w zaleznosci od przedmiotu i atrybutow
    public double zadajObrazenia() {
        return this.getInteligencja() + this.getEkwipunek().getWyekwipowanaBron().zadajObrazenia();
    }

    public double zadajObrazeniaSpecjalne() {
        return this.getInteligencja() + this.getEkwipunek().getWyekwipowanaBron().zadajObrazeniaSpecjalne();
    }

    // obsluzyc jak gracza ginie
    public void otrzymajObrazenia(double wartosc) {
        // mozwliwosc uniku
        if(Math.random() >= .1) {
            // obrazenia zostaja pomniejszone o tyle % ile sily ma wojownik
            double obrazenia = (1 - this.getInteligencja() / 100) * wartosc;
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

    /**
     * Mag na poczatku dostaje:
     * podstawowa bron magiczna
     * potion + potion lub jedzenie - szansa 50%
     */
    @Override
    public Ekwipunek generujEkwipunek() {
        Ekwipunek ekwipunekTMP = new Ekwipunek(TYP_POSIADACZA_EKWIPUNKU.MAG);
        Random rand = new Random();
        ekwipunekTMP.wlozBronMagiczna(new BronMagiczna(
                "poczatkowa bron magiczna",
                "opis",
                10,
                10,
                10,
                10
        ));

        ekwipunekTMP.wlozPozywienie(new PrzedmiotPozywienie(
                "podstawowa pota",
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
        ekwipunekTMP.zmienWyekwipowanaBronNaMagiczna(0);
        return  ekwipunekTMP;
    }
}