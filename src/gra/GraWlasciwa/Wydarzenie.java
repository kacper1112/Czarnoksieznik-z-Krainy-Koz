package gra.GraWlasciwa;

import gra.GraWlasciwa.Gra;
import gra.NPC.Boss;
import gra.NPC.Fabularny;
import gra.NPC.Handlarz;
import gra.NPC.Wrog;
import gra.RodzajeGracz.Gracz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Wydarzenie {
    private final String nazwa;
    private final String opis;
    private final Gracz gracz;
    private final List<Fabularny> postacieFabularne;
    private final List<Handlarz> handlarze;
    private final List<Wrog> wrogowie;
    private final Boss boss;

    public Wydarzenie(String nazwa, String opis, Gracz gracz, List<Fabularny> fabularne, List<Handlarz> handlarze, int iluWrogow, Boss boss) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.gracz = gracz;
        this.postacieFabularne = fabularne;
        this.handlarze = handlarze;
        this.wrogowie = generujWrogow(iluWrogow);
        this.boss = boss;
    }

    public Wydarzenie(String nazwa, String opis, Gracz gracz, List<Fabularny> fabularne, List<Handlarz> handlarze, List<Wrog> wrogowie, Boss boss) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.gracz = gracz;
        this.postacieFabularne = fabularne;
        this.handlarze = handlarze;
        this.wrogowie = wrogowie;
        this.boss = boss;
    }

    public Wydarzenie(String nazwa, String opis, Gracz gracz, int iluWrogow) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.gracz = gracz;
        this.postacieFabularne = null;
        this.handlarze = null;
        this.wrogowie = generujWrogow(iluWrogow);
        this.boss = null;
    }

    public Wydarzenie(String nazwa, String opis, Gracz gracz, List<Fabularny> fabularne, int iluWrogow) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.gracz = gracz;
        this.postacieFabularne = fabularne;
        this.handlarze = null;
        this.wrogowie = generujWrogow(iluWrogow);
        this.boss = null;
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
                wyborGracza = Gra.wczytajWyborGracza(2);

                if(wyborGracza == 2) {
                    gracz.menuEkwipunku();
                }

                System.out.println("O bogowie, walka!");

                if(walka(wrog)) {
                    wygranaGracza(wrog);
                } else {
                    Gra.przegrana();
                }
            }
        }

        // interakcja z kazdym bohaterem fabularnym w lokacji
        for(Fabularny fabularny: postacieFabularne) {
            System.out.println("Spotykasz " + fabularny.getImie());
            // todo fabularny.interakcja() ?
        }

        // walka z bossem o ile jest
        if(boss != null) {
            // todo boss powinien miec swoj wlasny "wstep" do walki w rodzaju
            // "w powietrzu czuc zapach siarki, slychac ciezkie kroki... nadchodzi smok jakistam!!!!!!!!!!!!!!
            // System.out.println(boss.get);
            if(!walka(boss)) {
                Gra.przegrana();
            } else {
                wygranaGracza(boss);
            }
        }
    }


    private void wygranaGracza(Wrog wrog) {
        // todo levelup, zbieranie lootu z wroga
        System.out.println("Udalo Ci sie pokonac przeciwnika!");
        System.out.println("W truchle znajdujesz:");

    }

    private void wygranaGracza(Boss wrog) {

    }

    private boolean walka(Wrog wrog) {
        int wyborGracza;
        boolean walkaTrwa = true;
        double obrazenia;
        // true - gracz, false - wrog
        boolean kolejGracza = (Math.random() < 0.5);
        if(kolejGracza) {
            System.out.println("Walke rozpoczyna Twoj przeciwnik!");
        } else {
            System.out.println("Rozpoczynasz walke!");
        }

        while(walkaTrwa) {
            if(kolejGracza) {
                System.out.println("Co chcesz zrobic?\n1.Skorzystaj z ekwipunku\n2.Zaatakuj przeciwnika" +
                        "korzystajac z " + gracz.getEkwipunek().getWyekwipowanaBron() + "\n" +
                        "3.Sprobuj wykonac atak specjalny");

                wyborGracza = Gra.wczytajWyborGracza(3);
                if(wyborGracza == 1) {
                    System.out.println(gracz.getEkwipunek().toString());
                    // menu wyboru przedmiotu z ekwipunku
                } else if(wyborGracza == 2) {
                    obrazenia = gracz.zadajObrazenia();
                    System.out.println("Atakujesz wroga za " + obrazenia + " punktow!");
                    wrog.otrzymajObrazenia(obrazenia);
                    System.out.println("Wrog ma teraz " + wrog.getObecnePunktyZycia() + "/" +
                            wrog.getMaksymalnePunktyZycia() + " punktow zycia.");
                } else if(wyborGracza == 3) {
                    obrazenia = gracz.zadajObrazeniaSpecjalne();
                    System.out.println("Atakujesz wroga za " + obrazenia + " punktow!");
                    wrog.otrzymajObrazenia(obrazenia);
                    System.out.println("Wrog ma teraz " + wrog.getObecnePunktyZycia() + "/" +
                            wrog.getMaksymalnePunktyZycia() + " punktow zycia.");
                }

            } else {
                obrazenia = wrog.zadajObrazenia();
                System.out.println(wrog.getImie() + " zadaje Ci " + obrazenia + "punktow obrazen!");
                gracz.otrzymajObrazenia(obrazenia);
                System.out.println("Masz teraz " + gracz.getObecnePunktyZycia() + "/" +
                        gracz.getMaksymalnePunktyZycia() + " punktow zycia.");
            }

            if(gracz.getObecnePunktyZycia() <= 0 || wrog.getObecnePunktyZycia() <= 0) {
                walkaTrwa = false;
            }

            kolejGracza = !kolejGracza;
        }
        return !kolejGracza;
    }
}
