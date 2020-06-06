package gra.NPC;

import gra.ElementyPomocnicze.Ekwipunek;
import gra.ElementyPomocnicze.generatorEkwipunku;
import gra.RodzajePrzedmiot.BronFizyczna;
import gra.RodzajePrzedmiot.BronMagiczna;
import gra.RodzajePrzedmiot.PrzedmiotFabularny;
import gra.RodzajePrzedmiot.PrzedmiotPozywienie;

public abstract class NPC implements generatorEkwipunku {
    private String imie;
    private Ekwipunek ekwipunek;

    public NPC(String imie) {
        this.imie = imie;
        this.ekwipunek = generujEkwipunek();
    }

    public void wlozBronFizycznaDoEkwipunku(BronFizyczna bronFizyczna) {
        this.ekwipunek.wlozBronFizyczna(bronFizyczna);
    }

    public void wlozBronMagicznaDoEwkipunku(BronMagiczna bronMagiczna) {
        this.ekwipunek.wlozBronMagiczna(bronMagiczna);
    }

    public void wlozPozywienieDoEkwipunku(PrzedmiotPozywienie pozywienie) {
        this.ekwipunek.wlozPozywienie(pozywienie);
    }

    public void wlozPrzedmiotFabularnyDoEkwipunku(PrzedmiotFabularny przedmiotFabularny) {
        this.ekwipunek.wlozFabularne(przedmiotFabularny);
    }

    /*
        public int pokazEkwipunek() {
        int indeks = 1;
        if(ekwipunek.isEmpty()) {
            System.out.println("Twoj ekwipunek jest pusty!");
            return 0;
        }

        System.out.println("Twoje przedmioty:");
        if(!ekwipunek.getEkwipunekPozywienie().isEmpty()) {
            System.out.println("Pozywienie:");
            for(PrzedmiotPozywienie p: ekwipunek.getEkwipunekPozywienie()) {
                System.out.println(indeks++ + ". " + p.getNazwa());
            }
        }
        if(!ekwipunek.getEkwipunekBronFizyczna().isEmpty()) {
            System.out.println("Bron fizyczna:");
            for(BronFizyczna bron: ekwipunek.getEkwipunekBronFizyczna()) {
                System.out.println(indeks++ + ". " + bron.getNazwa());
            }
        }
        if(!ekwipunek.getEkwipunekBronMagiczna().isEmpty()) {
            System.out.println("Bron magiczna:");
            for(BronMagiczna bron: ekwipunek.getEkwipunekBronMagiczna()) {
                System.out.println(indeks++ + ". " + bron.getNazwa());
            }
        }
        if(!ekwipunek.getEkwipunekFabularne().isEmpty()) {
            System.out.println("Przedmioty fabularne:");
            for(PrzedmiotFabularny p: ekwipunek.getEkwipunekFabularne()) {
                System.out.println(indeks++ + ". " + p.getNazwa());
            }
        }
        return indeks - 1;
    }

     */

    //WYCIAGANIE Z EQ DODAC

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public Ekwipunek getEkwipunek() {
        return ekwipunek;
    }

    public void setEkwipunek(Ekwipunek ekwipunek) {
        this.ekwipunek = ekwipunek;
    }
}
