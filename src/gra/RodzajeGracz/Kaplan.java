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

    public double otrzymajObrazenia(double wartosc) {
        if (Math.random() < 0.3) {
            zwiekszPunktyZycia(wartosc);
            return -1;
        } else {
            zmniejszPunktyZycia(wartosc);
            return wartosc;
        }
    }

    public void zwiekszLevel() {
        this.setMaksymalnePunktyZycia(this.getMaksymalnePunktyZycia() + 50);
        this.uleczWPelni();
        this.setPoziom(this.getPoziom() + 1);
        this.setPunktyDoswiadczenia(this.getPunktyDoswiadczenia() % 300);
        this.setSila(this.getSila() + 5);
        this.setInteligencja(this.getInteligencja() + 5);
        KolorTekstu.printZielony("Zdobywasz kolejny poziom!");
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
            ekwipunekTMP.wlozDoEkwipunku(new BronFizyczna(
                    "Elfi noz",
                    "Prastary noz zdobyty od jednego z twoich eflich znajomych",
                    10,
                    10,
                    10,
                    10
            ));
        } else {
            ekwipunekTMP.wlozDoEkwipunku(new BronMagiczna(
                    "Kostur otchlani",
                    "Kostur zdobyty podczas nielegalnych walk na arenie",
                    10,
                    10,
                    10,
                    10
            ));
        }
        if (rand.nextDouble() < 0.5) {
            ekwipunekTMP.wlozDoEkwipunku(new PrzedmiotPozywienie(
                    "Cukrowa lalka",
                    "Slodka i pyszna przekaska",
                    10,
                    10,
                    10
            ));
        } else {
            ekwipunekTMP.wlozDoEkwipunku(new PrzedmiotPozywienie(
                    "Chleb z szynka",
                    "Kanapka podarowana ci przez twojaj babunie",
                    10,
                    10,
                    10
            ));
        }
        if (rand.nextDouble() < 0.5) {
            ekwipunekTMP.wlozDoEkwipunku(new PrzedmiotPozywienie(
                    "podstawowa pota",
                    "opis",
                    10,
                    10,
                    10
            ));
        } else {
            ekwipunekTMP.wlozDoEkwipunku(new PrzedmiotPozywienie(
                    "podstawowe mieso",
                    "opis",
                    10,
                    10,
                    10
            ));
        }

        if (ekwipunekTMP.getEkwipunekBronMagiczna().size() > 0) {
            ekwipunekTMP.zmienWyekwipowanaBronNaMagiczna(0, true);
        } else {
            ekwipunekTMP.zmienWyekwipowanaBronNaFizyczna(0, true);
        }

        return ekwipunekTMP;
    }
}