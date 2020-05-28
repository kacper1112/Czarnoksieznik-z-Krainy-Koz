package gra.GraWlasciwa;

import gra.NPC.Boss;
import gra.RodzajeGracz.Gracz;
import gra.RodzajeGracz.Kaplan;
import gra.RodzajeGracz.Mag;
import gra.RodzajeGracz.Wojownik;
import gra.RodzajePrzedmiot.PrzedmiotFabularny;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gra{
    // tylko jedna instancja gry - singleton
    private static Scanner in;
    private int wyborGracza;

    private static Gra instance;
    private Gracz gracz;
    private Lokacja[] lokacje;

    public static void main(String[] args) {
        getInstance();
    }

    private Gra() {
        in = new Scanner(System.in);

        System.out.println("Czarnoksieznik z Krainy Koz\n");
        System.out.println("1.Rozpocznij nowa gre \n2.Pokaz instrukcje");
        wyborGracza = wczytajWyborGracza(2);

        if(wyborGracza == 2) {
            pokazInstrukcje();
            System.out.println("1.Kontynuuj");
            wyborGracza = wczytajWyborGracza(1);
        }

        System.out.println("Wybierz swoja klase postaci:\n1.Wojownik\n2.Mag\n3.Kaplan");
        wyborGracza = wczytajWyborGracza(3);

        if(!inicjalizacjaGry(wyborGracza)) {
            System.out.println("Blad podczas inicjalizacji gry!");
            System.exit(1);
        }

        if(rozpocznijGre()) {
            wygrana();
        } else {
            przegrana();
        }
    }


    public static Gra getInstance() {
        if(instance == null) {
            instance = new Gra();
        }
        return instance;
    }

    private void pokazInstrukcje() {
        System.out.println("To jest instrukcja");
    }

    private boolean inicjalizacjaGry(int klasaPostaci) {
        inicjalizacjaBossow();
        inicjalizacjaWydarzenFabularnych();
        lokacje = inicjalizacjaLokacji();

        switch(klasaPostaci){
            case 1:
                gracz = new Wojownik();
                break;
            case 2:
                gracz = new Mag();
                break;
            case 3:
                gracz = new Kaplan();
                break;
        }

        return true;
    }


    private boolean rozpocznijGre() {
        return false;
    }

    public static void wygrana() {
        System.out.println("Udalo Ci sie pokonac zlego Czarnoksieznika! Gratulacje!");
        System.out.println("To juz koniec Twojej przygody!");
        System.exit(0);
    }

    public static void przegrana() {
        System.out.println("Nie zyjesz! Koniec gry! Powodzenia nastepnym razem.");
        System.exit(0);
    }

    public static int wczytajWyborGracza(int liczbaOpcji) {
        int wybor;
        while(true) {
            System.out.print("Twoj wybor: ");
            wybor = in.nextInt();
            if(1 <= wybor && wybor <= liczbaOpcji) {
                return wybor;
            } else {
                System.out.println("Nieprawidlowy numer instrukcji");
            }
        }
    }

    public int menu0(){
        System.out.println("1. Pokaż ekwipunek\n" +
                "2. Zmień broń\n" +
                "3. Użyj pożywienia\n" +
                "4. Użyj przedmiotu fabularnego\n" +
                "5. Pokaż listę dostępnych lokalizacji\n" +
                "6. Przejdź do innej lokalizacji\n");
        return in.nextInt();
    }

    private void inicjalizacjaWydarzenFabularnych() {
        // nazywa wydarzenia fabularnego (obiektu) - nazwa + podloga +
        // kolejny numer wg postepu fabuly

        //Wydarzenie walka

        Wydarzenie wprowadzenie_1 = new Wydarzenie("Wprowadzenie",
                "\"Jezeli Krol dalej bedzie nakladal na nas tak wysokie podatki, to juz nigdy nie wyjdziemy" +
                        " z kryzysu! Mam tylko nadzieje, ze nie zamkna naszego zakladu, to dopiero bylaby" +
                        " katastrofa...\" myslal Roman wracajac do domu po kolejnym ciezkim dniu pracy jako" +
                        "ciesla. Wraca do domu a tam zony nimo.", gracz, null, 0, null);

        Wydarzenie spotkanieNaBajkowejPolanie_2;
    }

    private Lokacja[] inicjalizacjaLokacji() {

        List<Lokacja> lokacjeTMP = new ArrayList<>();
        /*
        (String nazwa,
                   String opis,
                   Wydarzenie fabularne,
                   Wydarzenie[] poboczne,
                   int[] sasiednie)
         */
        lokacjeTMP.add(new Lokacja("Chałpka nad urwistą doliną", "Znajdujesz się w swojej chałpce, " +
                "dookoła panuje straszny bałagan, wszędzie na podłodze walają się szczątki" +
                " tego co jeszcze przed chwilą było Twoim dawnym mieszkaniem. Nadszedł najwyższy" +
                " czas by wyruszyć w drogę i uratować Twoją ukochaną małżonkę. Uzbrojony w kijek i parę" +
                " złotych monet jesteś gotów by podjąć wyzwanie Czarnoksiężnikowi. Wybierz co chcesz zrobić.",
                ));

        lokacjeTMP.add(new Lokacja("Bajkowa polana", "Dochodzisz do bajkowej polany, księżyc rozpościera" +
                " się na ciemnym niebie, panuje północ. Rozglądasz się dookoła jednak po czarnoksiężniku ani widu ani słychu." +
                " Czujesz napływającą do głowy gorycz i rozczarowanie, czujesz się oszukany, jednak zarazem jeszcze mocniej zmotywowany." +
                " Uświadamiasz sobie, że sam musisz obrać cel swoich kolejnych poszukiwań.",));

        // Lokacja startowa - tylko do wywolania wydarzenia fabularnego
        // wprowadzenie_1

        // Lokacja lokacjaStartowa = new Lokacja("Lokacja startowa", );

        // Chalupka nad urwista dolina

        // Bajkowa polana

        // Miasto (Mysłowice)

        // Czarny Szczyt

        // Magiczna Przystan

        // Miasteczko Ravelholm

        // Grota Bebniarza

        // Przedpola Azgardu

        // Wzniesienie Marmonda

        // Krzywa Wieża

        // Zebrowe Wzgorze

        // Zdradliwe Urwisko

        // Orla Przepasc

        // Wysoka Brama

        // Wieza Czarnoksieznika z Koz (lokacja koncowa)
        return (Lokacja[]) lokacjeTMP.toArray();
    }

    private void inicjalizacjaBossow(){
        Boss czarnoksieznik = new Boss("Czarnoksieznik z Krainy Koz", new PrzedmiotFabularny(
                false,
                "ksiezniczka znajduje sie.....",
                100
        ));

        Boss ork = new Boss("Dis", new PrzedmiotFabularny(
           false,
           "po przejsciu przez bagna..",
                100
        ));

        Boss smok = new Boss("Smok", new PrzedmiotFabularny(
                false,
                "po przez zdradliwe urwisko...",
                100
        ));
    }

    //todo - walke obsluguje lokacja, przekazujemy jej gracza jako argument a wroga juz ma

}
