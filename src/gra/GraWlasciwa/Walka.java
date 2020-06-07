package gra.GraWlasciwa;

import gra.ElementyPomocnicze.KolorTekstu;
import gra.NPC.Wrog;
import gra.RodzajeGracz.Gracz;

public class Walka {


    public static void walka(Gracz gracz, Wrog wrog) {
        int wyborGracza;
        boolean walkaTrwa = true;
        double obrazenia;
        // true - gracz, false - wrog

        Gra.wyczyscTerminal();

        boolean kolejGracza = (Math.random() < 0.5);
        if (kolejGracza) {
            KolorTekstu.printCzerwony("Atakujesz pierwszy!");
        } else {
            KolorTekstu.printCzerwony("Pierwszy atakuje Twoj przeciwnik!");
        }

        while (walkaTrwa) {
            if (kolejGracza) {
                KolorTekstu.printZolty("Co chcesz zrobic?\n1.Skorzystaj z ekwipunku\n2.Zaatakuj przeciwnika " +
                        "korzystajac z " + gracz.getEkwipunek().getWyekwipowanaBron() + "\n" +
                        "3.Sprobuj wykonac mocny atak");

                wyborGracza = Gra.wczytajWyborGracza(3, false);
                if (wyborGracza == 1) {
                    Menu.menuEkwipunku();
                    // menu wyboru przedmiotu z ekwipunku
                } else if (wyborGracza == 2) {
                    if (Math.random() < gracz.getSzansaNaNatychmiastoweZabicie()) {
                        KolorTekstu.printCzerwony("O niebiosa! Twoj atak byl tak mocny, ze przeciwnik zginal w jednym" +
                                "uderzeniu!");
                        walkaTrwa = false;
                        wrog.otrzymajObrazenia(wrog.getMaksymalnePunktyZycia());
                    } else {
                        obrazenia = gracz.zadajObrazenia();
                        KolorTekstu.printCzerwony("Atakujesz wroga za " + obrazenia + " punktow!");
                        wrog.otrzymajObrazenia(obrazenia);
                        KolorTekstu.printCzerwony("Wrog ma teraz " + wrog.getObecnePunktyZycia() + "/" +
                                wrog.getMaksymalnePunktyZycia() + " punktow zycia.");
                    }
                } else if (wyborGracza == 3) {
                    if (Math.random() < gracz.getSzansaNaNatychmiastoweZabicie()) {
                        KolorTekstu.printCzerwony("O niebiosa! Twoj atak byl tak mocny, ze przeciwnik zginal w jednym" +
                                "uderzeniu!");
                        walkaTrwa = false;
                        wrog.otrzymajObrazenia(wrog.getMaksymalnePunktyZycia());
                    } else {
                        obrazenia = gracz.zadajMocneObrazenia();
                        KolorTekstu.printCzerwony("Atakujesz wroga za " + obrazenia + " punktow!");
                        wrog.otrzymajObrazenia(obrazenia);
                        KolorTekstu.printCzerwony("Wrog ma teraz " + wrog.getObecnePunktyZycia() + "/" +
                                wrog.getMaksymalnePunktyZycia() + " punktow zycia.");
                    }
                }

            } else {
                obrazenia = wrog.zadajObrazenia();
                KolorTekstu.printCzerwony(wrog.getImie() + " zadaje Ci " + obrazenia + " punktow obrazen!");
                gracz.otrzymajObrazenia(obrazenia);
                KolorTekstu.printCzerwony("Masz teraz " + gracz.getObecnePunktyZycia() + "/" +
                        gracz.getMaksymalnePunktyZycia() + " punktow zycia.");
            }

            if (gracz.getObecnePunktyZycia() <= 0 || wrog.getObecnePunktyZycia() <= 0) {
                walkaTrwa = false;
            }

            kolejGracza = !kolejGracza;
        }
        if (gracz.getObecnePunktyZycia() > 0) {
            gracz.getEkwipunek().dodajEkwipunek(wrog.getEkwipunek());
            KolorTekstu.printCzerwony("Udalo Ci sie zwyciezyc w walce!");
            KolorTekstu.printCzerwony("Podnosisz przedmioty przecinika");
        } else {
            KolorTekstu.printCzerwony("Zostales zabity");
        }
    }
}
