package gra.GraWlasciwa;

import gra.ElementyPomocnicze.KolorTekstu;
import gra.RodzajeGracz.Gracz;
import java.io.IOException;
import java.util.*;

public class Gra {
    private static Scanner in;
    private static Gra instance;
    private Gracz gracz;
    private List<Lokacja> lokacje;

    private int lokalizacjaGracza;

    private Gra() {
        System.out.println("Zainicjalowana GRA");
    }

    public static void main(String[] args) {
        Gra.getInstance().granko();
    }

    public static Gra getInstance() {
        if (instance == null) {
            instance = new Gra();
        }
        return instance;
    }

    public static void wygrana() {
        KolorTekstu.printCyan("Udalo Ci sie pokonac zlego Czarnoksieznika! Gratulacje!");
        KolorTekstu.printCyan("To juz koniec Twojej przygody!");
        System.exit(0);
    }

    public static void przegrana() {
        KolorTekstu.printCyan("Nie zyjesz! Koniec gry! Powodzenia nastepnym razem.");
        System.exit(0);
    }

    public static int wczytajWyborGracza(int liczbaOpcji, boolean mozliwoscPowrotu) {
        int wybor;
        while (true) {
            System.out.print("Twoj wybor: ");
            wybor = in.nextInt();
            if (wybor == 0 && mozliwoscPowrotu) {
                return 0;
            } else if (1 <= wybor && wybor <= liczbaOpcji) {
                return wybor;
            } else {
                System.out.println("Nieprawidlowy numer instrukcji");
            }
        }
    }

    public static void wyczyscTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ignored) {
        }
    }

    public void granko() {
        in = new Scanner(System.in);
        lokalizacjaGracza = 0;

        wyczyscTerminal();
        KolorTekstu.printCyan("Czarnoksieznik z Krainy Koz\n");

        KolorTekstu.printZolty("1.Rozpocznij nowa gre \n2.Pokaz instrukcje");
        int wyborGracza = wczytajWyborGracza(2, false);

        if (wyborGracza == 2) {
            pokazInstrukcje();
            System.out.println("1.Kontynuuj");
           wczytajWyborGracza(1, false);
        }

        KolorTekstu.printZolty("Wybierz swoja klase postaci:\n1.Wojownik\n2.Mag\n3.Kaplan");
        wyborGracza = wczytajWyborGracza(3, false);

        if (!InicjalizacjaGry.inicjalizacjaGry(wyborGracza)) {
            System.out.println("Blad podczas inicjalizacji gry!");
            System.exit(1);
        }

        if (rozpocznijGre()) {
            wygrana();
        } else {
            przegrana();
        }
    }

    private void pokazInstrukcje() {
        wyczyscTerminal();
        KolorTekstu.printZolty("To jest instrukcja");
    }

    private void rozpocznijWydarzenie(Wydarzenie wydarzenie) {
        if (wydarzenie.getCzyWykonana()) {
            return;
        }

        KolorTekstu.printCyan(wydarzenie.getNazwa());
        KolorTekstu.printCyan(wydarzenie.getOpis());

        if (wydarzenie.getWrogowie() != null) {
            wydarzenie.getWrogowie().forEach(wrog ->
                    Walka.walka(gracz, wrog)
            );
        }

        wydarzenie.setCzyWykonana(true);
    }

    private void rozpocznijWydarzenieFabularne(Wydarzenie wydarzenie) {

        if(wydarzenie.getCzyWykonana()) {
            return;
        }
        rozpocznijWydarzenie(wydarzenie);

        if (wydarzenie.getPostacieFabularne() != null) {
            wydarzenie.getPostacieFabularne().forEach(postac -> {
                gracz.getEkwipunek().wlozDoEkwipunku(postac.podarujLosowyPrzedmiotNieFabularny());

                if (postac.isCzyPosiadaPrzedmiotFabularny()) {
                    gracz.getEkwipunek().wlozDoEkwipunku(postac.podarujPrzedmiotFabularny());
                }
            });
        }

        if (wydarzenie.getZagadka() != null) {
            wydarzenie.zagadka();
        }

        if (wydarzenie.getBoss() != null) {
            Walka.walka(gracz, wydarzenie.getBoss());
        }

        wydarzenie.setCzyWykonana(true);
    }

    private boolean rozpocznijGre() {
        do {
            KolorTekstu.printCyan(this.lokacje.get(lokalizacjaGracza).getOpis());

            if (lokacje.get(lokalizacjaGracza).getWydarzeniaPoboczne() != null) {
                lokacje.get(lokalizacjaGracza).getWydarzeniaPoboczne().forEach(this::rozpocznijWydarzenie);
            }

            if (lokacje.get(lokalizacjaGracza).getWydarzenieFabularne() != null &&
                    !lokacje.get(lokalizacjaGracza).getWydarzenieFabularne().getCzyWykonana()) {
                rozpocznijWydarzenieFabularne(lokacje.get(lokalizacjaGracza).getWydarzenieFabularne());
            }

            while (Menu.menuGlowne());

        } while (lokalizacjaGracza != 20);
        return true;
    }

    public int getLokalizacjaGracza() {
        return lokalizacjaGracza;
    }

    public void setLokalizacjaGracza(int lokalizacjaGracza) {
        this.lokalizacjaGracza = lokalizacjaGracza;
    }

    public Gracz getGracz() {
        return gracz;
    }

    public void setGracz(Gracz g) {
        this.gracz = g;
    }

    public void setLokacje(List<Lokacja> lokacje) {
        this.lokacje = lokacje;
    }

    public List<Lokacja> getLokacje() {
        return lokacje;
    }
}
