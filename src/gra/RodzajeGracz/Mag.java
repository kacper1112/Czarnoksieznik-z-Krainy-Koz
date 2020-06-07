package gra.RodzajeGracz;

import gra.ElementyPomocnicze.Ekwipunek;
import gra.ElementyPomocnicze.TYP_POSIADACZA_EKWIPUNKU;
import gra.RodzajePrzedmiot.BronMagiczna;
import gra.RodzajePrzedmiot.PrzedmiotPozywienie;

import java.util.Random;

public class Mag extends Gracz {
    public Mag() {
        super(100, 10, 20);
    }

    public double zadajObrazenia() {
        double sumaInteligencji = getInteligencja() + getSumaBonusowDoInteligencji();
        return this.getEkwipunek().getWyekwipowanaBron().zadajObrazenia() + sumaInteligencji;
    }

    public double zadajMocneObrazenia() {
        double obrazenia = this.ekwipunek.getWyekwipowanaBron().zadajMocneObrazenia();
        double sumaInteligencji = getInteligencja() + getSumaBonusowDoInteligencji();

        if (obrazenia == 0) {
            return 0;
        } else {
            return this.getEkwipunek().getWyekwipowanaBron().zadajMocneObrazenia() + sumaInteligencji;
        }
    }

    // obsluzyc jak gracza ginie
    public void otrzymajObrazenia(double wartosc) {
        // mozwliwosc uniku
        if (Math.random() >= .1) {
            // obrazenia zostaja pomniejszone o tyle % ile sily ma wojownik
            double obrazenia = (1 - this.getInteligencja() / 100) * wartosc;
            zmniejszPunktyZycia(obrazenia);
        }
    }

    public void zwiekszLevel() {
        if (this.getPunktyDoswiadczenia() > 100) {
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

        if (rand.nextDouble() < 0.5) {
            ekwipunekTMP.wlozPozywienie(new PrzedmiotPozywienie(
                    "podstawowa pota",
                    "opis",
                    10,
                    10,
                    10
            ));
        } else {
            ekwipunekTMP.wlozPozywienie(new PrzedmiotPozywienie(
                    "podstawowe mieso",
                    "opis",
                    10,
                    10,
                    10
            ));
        }
        ekwipunekTMP.zmienWyekwipowanaBronNaMagiczna(0);
        return ekwipunekTMP;
    }
}