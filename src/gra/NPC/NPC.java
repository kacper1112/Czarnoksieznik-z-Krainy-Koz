package gra.NPC;

import gra.ElementyPomocnicze.Ekwipunek;
import gra.ElementyPomocnicze.generatorEkwipunku;
import gra.RodzajePrzedmiot.BronFizyczna;
import gra.RodzajePrzedmiot.BronMagiczna;
import gra.RodzajePrzedmiot.PrzedmiotFabularny;
import gra.RodzajePrzedmiot.PrzedmiotPozywienie;

public abstract class NPC implements generatorEkwipunku {
    private final String imie;
    private Ekwipunek ekwipunek;

    public NPC(String imie) {
        this.imie = imie;
        this.ekwipunek = generujEkwipunek();
    }

    //WYCIAGANIE Z EQ DODAC

    public String getImie() {
        return imie;
    }

    public Ekwipunek getEkwipunek() {
        return ekwipunek;
    }

    public void setEkwipunek(Ekwipunek ekwipunek) {
        this.ekwipunek = ekwipunek;
    }
}
