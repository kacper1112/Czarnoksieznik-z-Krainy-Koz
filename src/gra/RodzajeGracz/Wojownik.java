package gra.RodzajeGracz;

import gra.ElementyPomocnicze.Ekwipunek;
import gra.ElementyPomocnicze.KolorTekstu;
import gra.ElementyPomocnicze.TYP_POSIADACZA_EKWIPUNKU;
import gra.RodzajePrzedmiot.BronFizyczna;
import gra.RodzajePrzedmiot.PrzedmiotPozywienie;

import java.util.Random;

public class Wojownik extends Gracz {
    public Wojownik() {
        super(120, 20, 10);
    }

    // dodac w zaleznosci od przedmiotu i atrybutow
    public double zadajObrazenia() {
        return this.getEkwipunek().getWyekwipowanaBron().zadajObrazenia();
    }

    public double zadajMocneObrazenia() {
        return this.getEkwipunek().getWyekwipowanaBron().zadajMocneObrazenia();
    }

    // obsluzyc jak gracza ginie
    public double otrzymajObrazenia(double wartosc) {
        // obrazenia zostaja pomniejszone o tyle % ile sily ma wojownik
        double sumaSily = getSila() + getSumaBonusowDoSily();

        // redukujemy o maks 90%
        if (sumaSily > 90) {
            sumaSily = 90;
        }

        double obrazenia = (1 - sumaSily / 100) * wartosc;
        zmniejszPunktyZycia(obrazenia);
        return obrazenia;
    }

    @Override
    public void zwiekszLevel() {
        this.setPoziom(this.getPoziom() + 1);
        this.setPunktyDoswiadczenia(this.getPunktyDoswiadczenia() % 300);
        this.setSila(this.getSila() + 8);
        this.setInteligencja(this.getInteligencja() + 3);
        KolorTekstu.printZielony("Zdobywasz kolejny poziom!");
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
        ekwipunekTMP.wlozDoEkwipunku(new BronFizyczna(
                "Drewniany miecz",
                "Miecz wystrugany z drewna",
                10,
                10,
                10,
                10
        ));
        ekwipunekTMP.wlozDoEkwipunku(new PrzedmiotPozywienie(
                "Miodek",
                "Sloiczek miodu, prosto z pasieki od Seredy",
                10,
                10,
                10
        ));

        if (rand.nextDouble() < 0.5) {
            ekwipunekTMP.wlozDoEkwipunku(new PrzedmiotPozywienie(
                    "Mala mikstura",
                    "Mniejsza mikstura uzdrawiajaca twoje punkty zycia",
                    10,
                    10,
                    10
            ));
        } else {
            ekwipunekTMP.wlozDoEkwipunku(new PrzedmiotPozywienie(
                    "Krwiste mieso",
                    "Mieso zdobyte z jakiegos duzego zwierzecia, nikt nie wie jendak z jakiego",
                    10,
                    10,
                    10
            ));
        }
        ekwipunekTMP.zmienWyekwipowanaBronNaFizyczna(0, true);
        return ekwipunekTMP;
    }
}
