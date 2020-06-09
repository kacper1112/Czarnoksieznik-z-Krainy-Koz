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
        KolorTekstu.printCyan("Udalo Ci sie pokonac zlego Czarnoksieznika! Gratulacje!\n" + "To juz koniec Twojej przygody!");
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
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ignored) {}
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
            wyczyscTerminal();
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
        KolorTekstu.printFioletowy("Malzonka ciesli Romana zostala porwana przez zlego Czarnoksieznika z Krainy" +
                " Koz! Bohater musi ja uratowac z rak oprawcy. Zeby tego dokonac musi zdobyc najpierw odnalezc w swiecie" +
                " gry 3 przepustki do 3 roznych bossow! Po otwarciu sobie drogi do bossow i pokonaniu ich, gracz moze" +
                " rozpoczac walke finalowa - z samym Czarnoksieznikiem!");
        System.out.println();
        KolorTekstu.printFioletowy("Istnieja 3 klasy postaci - Wojownik, Mag i Kaplan. Kazda z nich ma swoje" +
                " umiejetnosci specjalne i moze poslugiwac sie innym rodzajem broni - odpowiednio bronia fizyczna," +
                " magiczna, lub oboma typami.");
        System.out.println();
        KolorTekstu.printFioletowy("Walka w grze przebiega turowo, podczas swojej kolejki gracz moze skorzystac" +
                " z ekwipunku (aby zmienic bron lub uleczyc sie), zaatakowac standardowym atakiem lub sprobowac silnego" +
                " ataku (nie jest gwarantowane jego powodzenie!). Bron zuzywa sie z kazdym ciosem, dlatego warto ciagle" +
                " szukac nowego sprzetu.");
        System.out.println();
        KolorTekstu.printFioletowy("Niektore z przedmiotow posiadaja specjalne atrybuty, ktore daja bohaterowi bonusy" +
                " do statystyk. Najmocniejsze z przedmiotow daja tez szanse na zabicie przeciwnika w jednym ciosie!");
        System.out.println();
        KolorTekstu.printZolty("Szczegolowa instrukcja dostepna w pliku README!");
    }

    private void rozpocznijWydarzenie(Wydarzenie wydarzenie) {
        if (wydarzenie.getCzyWykonana()) {
            return;
        }

        KolorTekstu.printCyan(wydarzenie.getNazwa());
        KolorTekstu.printCyan(wydarzenie.getOpis());

        if (wydarzenie.getWrogowie() != null) {
            wydarzenie.getWrogowie().forEach(wrog -> Walka.walka(gracz, wrog));
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
            Gra.wyczyscTerminal();

            // najpierw wydarzenia poboczne z wrogami
            if (lokacje.get(lokalizacjaGracza).getWydarzeniaPoboczne() != null) {
                lokacje.get(lokalizacjaGracza).getWydarzeniaPoboczne().forEach(wydarzenie -> {
                    if(!wydarzenie.getWrogowie().isEmpty()) {
                        rozpocznijWydarzenie(wydarzenie);
                    }
                });
            }

            // potem docieramy do lokacji
            KolorTekstu.printCyan(this.lokacje.get(lokalizacjaGracza).getOpis());
            System.out.println();

            // potem pozostale poboczne
            if (lokacje.get(lokalizacjaGracza).getWydarzeniaPoboczne() != null) {
                lokacje.get(lokalizacjaGracza).getWydarzeniaPoboczne().forEach(this::rozpocznijWydarzenie);
            }

            // potem fabularne
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
