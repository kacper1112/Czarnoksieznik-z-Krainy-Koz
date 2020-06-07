package gra.GraWlasciwa;

import gra.NPC.Boss;
import gra.NPC.Wrog;
import gra.RodzajeGracz.Gracz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Walka {

    static Random random = new Random();

    static List<String > komentarze(){
        return new ArrayList<>();
    }

    private static int whoBegin(){
        return random.nextInt(2);
    }

    public static boolean walka(Gracz gracz, Wrog wrog) {
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

                wyborGracza = Gra.wczytajWyborGracza(3, false);
                if(wyborGracza == 1) {
                    Menu.menuEkwipunku();
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

    static boolean walkaZWrogiem(Gracz gracz, Wrog wrog){
        if(whoBegin() == 0){
            while (gracz.getObecnePunktyZycia() > 0 && wrog.getObecnePunktyZycia()>0){
                gracz.otrzymajObrazenia(wrog.zadajObrazenia());
                wrog.otrzymajObrazenia(gracz.zadajObrazenia());
            }
        }else {
            while (gracz.getObecnePunktyZycia() > 0 && wrog.getObecnePunktyZycia()>0){
                wrog.otrzymajObrazenia(gracz.zadajObrazenia());
                gracz.otrzymajObrazenia(wrog.zadajObrazenia());
            }
        }

        if(gracz.getObecnePunktyZycia() > 0) {
            gracz.getEkwipunek().dodajEkwipunek(wrog.getEkwipunek());
        } else {
            System.out.println("Zostales zabity");
        }

        return gracz.getObecnePunktyZycia() > 0;
    }

    static boolean walkaZBossem(Gracz gracz, Boss boss){
        if(whoBegin() == 1){
            while (gracz.getObecnePunktyZycia() > 0 && boss.getObecnePunktyZycia()>0){
                gracz.otrzymajObrazenia(boss.zadajObrazenia());
                boss.otrzymajObrazenia(gracz.zadajObrazenia());
            }
        }else {
            while (gracz.getObecnePunktyZycia() > 0 && boss.getObecnePunktyZycia()>0){
                boss.otrzymajObrazenia(gracz.zadajObrazenia());
                gracz.otrzymajObrazenia(boss.zadajObrazenia());
            }
        }

        if(gracz.getObecnePunktyZycia() > 0) {
            gracz.getEkwipunek().dodajEkwipunek(boss.getEkwipunek());
        } else {
            System.out.println("Zostales zabity");
        }

        return gracz.getObecnePunktyZycia() > 0;
    }
}
