package gra.GraWlasciwa;

import gra.ElementyPomocnicze.KolorTekstu;
import gra.NPC.Boss;
import gra.NPC.Wrog;
import gra.RodzajeGracz.Gracz;

public class Walka {

    public static void walka(Gracz gracz, Wrog wrog) {
        int wyborGracza;
        boolean walkaTrwa = true;
        double obrazenia;

        System.out.println();

        boolean kolejGracza = (Math.random() < 0.5);
        if (kolejGracza) {
            KolorTekstu.printCzerwony("Atakujesz pierwszy!");
        } else {
            KolorTekstu.printCzerwony("Pierwszy atakuje " + wrog.getImie() +" !");
        }

        while (walkaTrwa) {
            if (kolejGracza) {
                KolorTekstu.printZolty("Co chcesz zrobic?\n1.Skorzystaj z ekwipunku\n2.Zaatakuj przeciwnika " +
                        "korzystajac z " + gracz.getEkwipunek().getWyekwipowanaBron() + "\n" +
                        "3.Sprobuj wykonac mocny atak");

                wyborGracza = Gra.wczytajWyborGracza(3, false);
                System.out.println();
                if (wyborGracza == 1) {
                    Menu.menuEkwipunku();
                    Gra.wyczyscTerminal();
                    // menu wyboru przedmiotu z ekwipunku
                } else if (wyborGracza == 2) {
                    if (Math.random() < gracz.getSzansaNaNatychmiastoweZabicie()) {
                        KolorTekstu.printCzerwony("O niebiosa! Twoj atak byl tak mocny, ze przeciwnik zginal w jednym" +
                                "uderzeniu!");
                        walkaTrwa = false;
                        wrog.otrzymajObrazenia(wrog.getMaksymalnePunktyZycia());
                    } else {
                        obrazenia = gracz.zadajObrazenia();
                        KolorTekstu.printCzerwony("Atakujesz wroga za " + String.format("%1.2f", obrazenia) + " punktow!");
                        wrog.otrzymajObrazenia(obrazenia);
                        KolorTekstu.printCzerwony("Wrog ma teraz " + String.format("%1.2f", wrog.getObecnePunktyZycia()) + "/" +
                                wrog.getMaksymalnePunktyZycia() + " punktow zycia.");
                    }
                } else if (wyborGracza == 3) {
                    if (Math.random() < gracz.getSzansaNaNatychmiastoweZabicie()) {
                        KolorTekstu.printCzerwony("O niebiosa! Twoj atak byl tak mocny, ze przeciwnik zginal w jednym " +
                                "uderzeniu!");
                        walkaTrwa = false;
                        wrog.otrzymajObrazenia(wrog.getMaksymalnePunktyZycia());
                    } else {
                        obrazenia = gracz.zadajMocneObrazenia();
                        KolorTekstu.printCzerwony("Atakujesz wroga za " + String.format("%1.2f", obrazenia) + " punktow!");
                        wrog.otrzymajObrazenia(obrazenia);
                        KolorTekstu.printCzerwony("Wrog ma teraz " + String.format("%1.2f", wrog.getObecnePunktyZycia()) + "/" +
                                wrog.getMaksymalnePunktyZycia() + " punktow zycia.");
                    }
                }

            } else {
                obrazenia = wrog.zadajObrazenia();
                KolorTekstu.printCzerwony(wrog.getImie() + " atakuje za " + String.format("%1.2f", obrazenia) + " punktow obrazen!");
                double otrzymaneObrazenia = gracz.otrzymajObrazenia(obrazenia);

                if (otrzymaneObrazenia < 0) {
                    KolorTekstu.printZielony("Atak wroga leczy Cie!");
                } else if(otrzymaneObrazenia == 0) {
                    KolorTekstu.printZielony("Wykonales unik, nie otrzymujesz obrazen!");
                } else {
                    KolorTekstu.printCzerwony("Ostatecznie otrzymujesz " + String.format("%1.2f", otrzymaneObrazenia) + " punktow obrazen. Masz teraz " +
                            gracz.getObecnePunktyZycia() + "/" + gracz.getMaksymalnePunktyZycia() + " punktow zycia");
                }
            }
            if (gracz.getObecnePunktyZycia() <= 0 || wrog.getObecnePunktyZycia() <= 0) {
                walkaTrwa = false;
            }

            kolejGracza = !kolejGracza;
        }
        if (gracz.getObecnePunktyZycia() > 0) {
            wygranaGracza(wrog);
        } else {
            KolorTekstu.printCzerwony("Zostales zabity");
            Gra.przegrana();
        }
    }

    private static void wygranaGracza(Wrog wrog) {
        System.out.println("Udalo Ci sie pokonac przeciwnika!");

        double pktDoswiadczenia;
        if (wrog instanceof Boss) {
            System.out.println("Udalo Ci sie pokonac jednego z 3 bossow!");
            pktDoswiadczenia = 100.0 + 80 * Math.random();
        } else {
            pktDoswiadczenia = 100.0 + 30 * Math.random();
        }
        Gra.getInstance().getGracz().zwiekszPunktyDoswiadczenia(pktDoswiadczenia);
        System.out.println("Zdobyłes " + String.format("%1.2f", pktDoswiadczenia) + " punktow doswiadczenia");

        if(Gra.getInstance().getGracz().getPunktyDoswiadczenia() >= 300) {
            Gra.getInstance().getGracz().zwiekszLevel();
        }

        System.out.println("W truchle " + wrog.getImie() + " znajdujesz:");
        if (wrog.getEkwipunek().isEmpty()) {
            System.out.println("Tym razem nic :/");
            return;
        }

        wrog.getEkwipunek().pokazEkwipunek();
        Gra.getInstance().getGracz().getEkwipunek().dodajEkwipunek(wrog.getEkwipunek());
        System.out.println("Wybierz 1 aby kontynuowac");
        Gra.wczytajWyborGracza(1, false);
        Gra.wyczyscTerminal();
    }

}
