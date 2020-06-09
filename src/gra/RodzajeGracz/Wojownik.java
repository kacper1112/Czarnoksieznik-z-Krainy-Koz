package gra.RodzajeGracz;

import gra.ElementyPomocnicze.Ekwipunek;
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
    public void otrzymajObrazenia(double wartosc) {
        // obrazenia zostaja pomniejszone o tyle % ile sily ma wojownik
        double sumaSily = getSila() + getSumaBonusowDoSily();

        if (sumaSily >= 100) {
            sumaSily = 90;
        }

        double obrazenia = (1 - sumaSily / 100) * wartosc;
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
        ekwipunekTMP.zmienWyekwipowanaBronNaFizyczna(0);
        return ekwipunekTMP;
    }
}
