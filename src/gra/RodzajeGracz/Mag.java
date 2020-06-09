package gra.RodzajeGracz;

import gra.ElementyPomocnicze.Ekwipunek;
import gra.ElementyPomocnicze.KolorTekstu;
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

    public double otrzymajObrazenia(double wartosc) {
        // maks 50% szans na wykonanie uniku przy duzej inteligencji
        if(Math.random() < ((getSumaBonusowDoInteligencji() + getInteligencja()) / 100) * 0.5) {
            return 0;
        }

        zmniejszPunktyZycia(wartosc);
        return wartosc;
    }

    public void zwiekszLevel() {

        this.setPoziom(this.getPoziom() + 1);
        this.setPunktyDoswiadczenia(this.getPunktyDoswiadczenia() % 300);
        this.setInteligencja(this.getInteligencja() + 10);
        KolorTekstu.printZielony("Zdobywasz kolejny poziom!");
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
        ekwipunekTMP.wlozDoEkwipunku(new BronMagiczna(
                "Rozdzka z krainy ciemnosci",
                "Ta rozdzka nalezala do wielkiego czarodzieja, brzemie w niej ogromna moc",
                10,
                10,
                10,
                10
        ));

        ekwipunekTMP.wlozDoEkwipunku(new PrzedmiotPozywienie(
                "Jablko",
                "Jabluszko z owocowego sadu",
                10,
                10,
                10
        ));

        if (rand.nextDouble() < 0.5) {
            ekwipunekTMP.wlozDoEkwipunku(new PrzedmiotPozywienie(
                    "Bakalie",
                    "Garsc bakalii, zdobyta od nieznajmojego",
                    10,
                    10,
                    10
            ));
        } else {
            ekwipunekTMP.wlozDoEkwipunku(new PrzedmiotPozywienie(
                    "Udko kurczaka",
                    "Pyszne mieso zdobyte z lokalnego gospodarstwa",
                    10,
                    10,
                    10
            ));
        }
        ekwipunekTMP.zmienWyekwipowanaBronNaMagiczna(0, true);
        return ekwipunekTMP;
    }
}