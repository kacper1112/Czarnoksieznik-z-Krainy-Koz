package gra.RodzajeGracz;

import gra.ElementyPomocnicze.Ekwipunek;
import gra.ElementyPomocnicze.KolorTekstu;
import gra.ElementyPomocnicze.TYP_POSIADACZA_EKWIPUNKU;
import gra.RodzajePrzedmiot.BronFizyczna;
import gra.RodzajePrzedmiot.BronMagiczna;
import gra.RodzajePrzedmiot.PrzedmiotPozywienie;

import java.util.Random;

public class Kaplan extends Gracz {
    public Kaplan() {
        super(110, 15, 15);
    }

    public double zadajObrazenia() {
        return this.ekwipunek.getWyekwipowanaBron().zadajObrazenia();
    }

    public double zadajMocneObrazenia() {
        return this.ekwipunek.getWyekwipowanaBron().zadajMocneObrazenia();
    }

    public void otrzymajObrazenia(double wartosc) {
        if (Math.random() < 0.3) {
            KolorTekstu.printCzerwony( "Atak wroga leczy Cie!");
            zwiekszPunktyZycia(wartosc);
        } else {
            otrzymajObrazenia(wartosc);
        }
    }

    public void zwiekszLevel() {
        if (this.getPunktyDoswiadczenia() > 100) {
            this.setPoziom(this.getPoziom() + 1);
            this.setPunktyDoswiadczenia(this.getPunktyDoswiadczenia() % 100);
            this.setSila(this.getSila() + 5);
            this.setInteligencja(this.getInteligencja() + 5);
        }
    }

    /**
     * Kapalan na poczatku dostaje:
     * podstawowa bron magiczna lub z fizyczna - szansa 50% na kazda
     * jedzenie lub potion *2 - szansa 25 na kazda kombiancje:  JJ JP PJ PP
     */
    @Override
    public Ekwipunek generujEkwipunek() {
        Ekwipunek ekwipunekTMP = new Ekwipunek(TYP_POSIADACZA_EKWIPUNKU.KAPLAN);
        Random rand = new Random();
        if (rand.nextDouble() < 0.5) {
            ekwipunekTMP.wlozBronFizyczna(new BronFizyczna(
                    "poczatkowa bron fizyczna",
                    "opis",
                    10,
                    10,
                    10,
                    10
            ));
        } else {
            ekwipunekTMP.wlozBronMagiczna(new BronMagiczna(
                    "poczatkowa bron magiczna",
                    "opis",
                    10,
                    10,
                    10,
                    10
            ));
        }
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

        if (ekwipunekTMP.getEkwipunekBronMagiczna().size() > 0) {
            ekwipunekTMP.zmienWyekwipowanaBronNaMagiczna(0);
        } else {
            ekwipunekTMP.zmienWyekwipowanaBronNaFizyczna(0);
        }

        return ekwipunekTMP;
    }
}