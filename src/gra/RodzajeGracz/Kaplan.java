package gra.RodzajeGracz;

import gra.ElementyPomocnicze.Ekwipunek;
import gra.ElementyPomocnicze.TYP_POSIADACZA_EKWIPUNKU;
import gra.RodzajePrzedmiot.BronFizyczna;
import gra.RodzajePrzedmiot.BronMagiczna;
import gra.RodzajePrzedmiot.PrzedmiotPozywienie;

import java.util.Random;

public class Kaplan extends Gracz {
    public Kaplan() {
        super(110, 15, 15);
    }

    // dodac w zaleznosci od przedmiotu i atrybutow
    public double zadajObrazenia() {
        return this.ekwipunek.getWyekwipowanaBron().zadajObrazenia();
    }

    public double zadajObrazeniaSpecjalne() {
        return this.getEkwipunek().getWyekwipowanaBron().zadajObrazeniaSpecjalne();
    }

    // obsluzyc jak gracza ginie
    public void otrzymajObrazenia(double wartosc) {
        // sprawdz czy moze sie uleczyc
        if(Math.random() < .1) {
            this.zwiekszPunktyZycia(wartosc);
        } else {
            // obrazenia zostaja pomniejszone o tyle % ile sily ma wojownik
            double obrazenia = (1 - getSila() / 100) * wartosc;
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

    /**
     * Kapalan na poczatku dostaje:
     * podstawowa bron magiczna lub z fizyczna - szansa 50% na kazda
     * jedzenie lub potion *2 - szansa 25 na kazda kombiancje:  JJ JP PJ PP
     */
    @Override
    public Ekwipunek generujEkwipunek() {
        Ekwipunek ekwipunekTMP = new Ekwipunek(TYP_POSIADACZA_EKWIPUNKU.KAPLAN);
        Random rand = new Random();
        if(rand.nextDouble() < 0.5){
            ekwipunekTMP.wlozBronFizyczna(new BronFizyczna(
                    "poczatkowa bron fizyczna",
                    "opis",
                    10,
                    10,
                    10,
                    10
            ));
        }else{
            ekwipunekTMP.wlozBronMagiczna(new BronMagiczna(
                    "poczatkowa bron magiczna",
                    "opis",
                    10,
                    10,
                    10,
                    10
            ));
        }
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