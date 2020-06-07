package gra.GraWlasciwa;

import gra.ElementyPomocnicze.Ekwipunek;
import gra.ElementyPomocnicze.TYP_POSIADACZA_EKWIPUNKU;
import gra.ElementyPomocnicze.Zagadka;
import gra.GraWlasciwa.Gra;
import gra.NPC.Boss;
import gra.NPC.Fabularny;
import gra.NPC.Handlarz;
import gra.NPC.Wrog;
import gra.RodzajeGracz.Gracz;
import gra.RodzajePrzedmiot.BronFizyczna;
import gra.RodzajePrzedmiot.BronMagiczna;
import gra.RodzajePrzedmiot.PrzedmiotFabularny;
import gra.RodzajePrzedmiot.PrzedmiotPozywienie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Wydarzenie {
    private final String nazwa;
    private final String opis;
    private final Gracz gracz;
    private final List<Fabularny> postacieFabularne;
    private final List<Wrog> wrogowie;
    private final Boss boss;
    private final Zagadka zagadka;

    public Boolean getCzyWykonana() {
        return czyWykonana;
    }

    public void setCzyWykonana(Boolean czyWykonana) {
        this.czyWykonana = czyWykonana;
    }

    private Boolean czyWykonana;

    public Wydarzenie(String nazwa, String opis, Gracz gracz, List<Fabularny> fabularne, int iluWrogow, Boss boss) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.gracz = gracz;
        this.postacieFabularne = fabularne;
        this.wrogowie = generujWrogow(iluWrogow);
        this.boss = boss;
        this.zagadka = null;
        this.czyWykonana = false;
    }

    public Wydarzenie(String nazwa, String opis, Gracz gracz, List<Fabularny> fabularne, List<Wrog> wrogowie, Boss boss) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.gracz = gracz;
        this.postacieFabularne = fabularne;
        this.wrogowie = wrogowie;
        this.boss = boss;
        this.zagadka = null;
        this.czyWykonana = false;
    }

    public Wydarzenie(String nazwa, String opis, Gracz gracz, List<Fabularny> fabularne, List<Wrog> wrogowie, Boss boss, Zagadka zagadka) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.gracz = gracz;
        this.postacieFabularne = fabularne;
        this.wrogowie = wrogowie;
        this.boss = boss;
        this.zagadka = zagadka;
        this.czyWykonana = false;
    }


    private List<Wrog> generujWrogow(int iluWrogow) {
        List<Wrog> tempWrogowie = new ArrayList<>();
        for(int i = 0; i < iluWrogow; i++) {
            Wrog wrog = new Wrog("generyczny wrog");
            tempWrogowie.add(wrog);
            // todo generowanie wrogow
        }
        return tempWrogowie;
    }

    private void rozpocznijWydarzenie() {
        int wyborGracza;

        System.out.println(nazwa);
        System.out.println(opis);

        // walka z kazdym zywym wrogiem pobocznym z przerwami na zmiane ekwipunku, nie da sie przerwac walki
        for(Wrog wrog: wrogowie) {
            if(wrog.getObecnePunktyZycia() > 0) {
                // przerwa na zmiane wyposazenia / uleczenie sie
                System.out.println("Przed Toba stoi " + wrog.getImie() + "! Jestes gotowy do walki (1), czy wolisz " +
                        "skorzystac z ekwipunku (2) przed walka?");
                wyborGracza = Gra.wczytajWyborGracza(2, false);

                if(wyborGracza == 2) {
                    Menu.menuEkwipunku();
                }

                System.out.println("O bogowie, walka!");

                /*
                if(walka(wrog)) {
                    wygranaGracza(wrog);
                } else {
                    Gra.przegrana();
                }

                 */
            }
        }

        // interakcja z kazdym bohaterem fabularnym w lokacji
        for(Fabularny fabularny: postacieFabularne) {
            System.out.println("Spotykasz " + fabularny.getImie());
            //todo fabularny.interakcja() ?
        }

        // walka z bossem o ile jest
        if(boss != null) {
            // todo boss powinien miec swoj wlasny "wstep" do walki w rodzaju
            // "w powietrzu czuc zapach siarki, slychac ciezkie kroki... nadchodzi smok jakistam!!!!!!!!!!!!!!
            // System.out.println(boss.get);
            /*
            if(!walka(boss)) {
                Gra.przegrana();
            } else {
                wygranaGracza(boss);
            }

             */
        }
    }

    private void wygranaGracza(Wrog wrog) {
        System.out.println("Udalo Ci sie pokonac przeciwnika!");
        Random rand = new Random();
        double pktDoswiadczenia;
        if(wrog instanceof Boss){
            System.out.println("Specjalne napisy do bossa");
            pktDoswiadczenia = gracz.getPunktyDoswiadczenia() + 100.0 + 40*rand.nextDouble();
            gracz.setPunktyDoswiadczenia(pktDoswiadczenia);
        }else {
            gracz.setPunktyDoswiadczenia(gracz.getPunktyDoswiadczenia() + 80 + rand.nextInt(40));
            pktDoswiadczenia = gracz.getPunktyDoswiadczenia() + 10.0 + 30*rand.nextDouble();
        }
        System.out.println("Zdobyłes " + pktDoswiadczenia + "punktow doswiadczenia");

        System.out.println("W truchle znajdujesz:");
        if(wrog.getEkwipunek().isEmpty()) {
            System.out.println("Tym razem nic :/");
            return;
        }
        System.out.println("Przedmioty wroga:");
        if(!wrog.getEkwipunek().getEkwipunekPozywienie().isEmpty()) {
            System.out.println("Pozywienie:");
            for(PrzedmiotPozywienie p: wrog.getEkwipunek().getEkwipunekPozywienie()) {
                System.out.println(p.getNazwa());
            }
        }
        if(!wrog.getEkwipunek().getEkwipunekBronFizyczna().isEmpty()) {
            System.out.println("Bron fizyczna:");
            for(BronFizyczna bron: wrog.getEkwipunek().getEkwipunekBronFizyczna()) {
                System.out.println(bron.getNazwa());
            }
        }
        if(!wrog.getEkwipunek().getEkwipunekBronMagiczna().isEmpty()) {
            System.out.println("Bron magiczna:");
            for(BronMagiczna bron: wrog.getEkwipunek().getEkwipunekBronMagiczna()) {
                System.out.println(bron.getNazwa());
            }
        }
        if(wrog instanceof Boss && !wrog.getEkwipunek().getEkwipunekFabularne().isEmpty()){
            System.out.println("Przedmioty fabularne:");
            for(PrzedmiotFabularny p: wrog.getEkwipunek().getEkwipunekFabularne()) {
                System.out.println(p.getNazwa());
            }
        }
        polaczEkwipunki(gracz.getEkwipunek(),wrog.generujEkwipunek());
    }

    public void polaczEkwipunki(Ekwipunek ekwipunekGracza, Ekwipunek ekwipunekPrzeciwnika){
        if(!ekwipunekPrzeciwnika.getEkwipunekPozywienie().isEmpty()) {
            ekwipunekPrzeciwnika.getEkwipunekPozywienie().forEach(ekwipunekGracza::wlozPozywienie);
        }
        if(!ekwipunekPrzeciwnika.getEkwipunekBronFizyczna().isEmpty()) {
            ekwipunekPrzeciwnika.getEkwipunekBronFizyczna().forEach(ekwipunekGracza::wlozBronFizyczna);
        }
        if(!ekwipunekPrzeciwnika.getEkwipunekBronMagiczna().isEmpty()) {
            ekwipunekPrzeciwnika.getEkwipunekBronMagiczna().forEach(ekwipunekGracza::wlozBronMagiczna);
        }
        if(ekwipunekPrzeciwnika.getTYP()==TYP_POSIADACZA_EKWIPUNKU.BOSS
                &&!ekwipunekPrzeciwnika.getEkwipunekBronFizyczna().isEmpty()) {
            ekwipunekPrzeciwnika.getEkwipunekFabularne().forEach(ekwipunekGracza::wlozFabularne);
        }
    }

    public void zagadka() {
        this.zagadka.wywolajZagadke(gracz);
    }

    public Zagadka getZagadka() {
        return zagadka;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public Gracz getGracz() {
        return gracz;
    }

    public List<Fabularny> getPostacieFabularne() {
        return postacieFabularne;
    }


    public List<Wrog> getWrogowie() {
        return wrogowie;
    }

    public Boss getBoss() {
        return boss;
    }
}
