package gra;

import java.util.*;

public class Gra {
    // tylko jedna instancja gry - singleton
    private static Scanner in;
    private int wyborGracza;
    private Map<String,Wydarzenie> wydarzenia;
    private Map<String, Lokacja> lokacje;
    private static Gra instance;
    private Gracz gracz;


    public static Scanner getIn() {
        return in;
    }

    public Map<String, Wydarzenie> getWydarzenia() {
        return wydarzenia;
    }

    public Map<String, Lokacja> getLokacje() {
        return lokacje;
    }

    private Gra() {
        in = new Scanner(System.in);
        wydarzenia = new HashMap<>();
        lokacje = new HashMap<>();
        System.out.println("Czarnoksieznik z Krainy Koz\n");
        System.out.println("1. Rozpocznij nowa gre \n 2. Pokaz instrukcje");
        wyborGracza = in.nextInt();

        if(wyborGracza == 1) {
            if(!inicjalizacjaGry()) {
                System.out.println("Blad podczas inicjalizacji gry!");
                System.exit(1);
            }

            if(rozpocznijGre()) {
                System.out.println("Gratulacje, udalo Ci sie ukonczyc gre!");
            } else {
                System.out.println("Powodzenia nastepnym razem!");
            }

        } else if(wyborGracza == 2) {
            pokazInstrukcje();
        } else {
            System.out.println("Nieprawidlowy numer instrukcji");
        }
    };


    public static Gra getInstance() {
        if(instance == null) {
            instance = new Gra();
        }
        return instance;
    }

    private void pokazInstrukcje() {
        System.out.println("To jest instrukcja");
    }

    private boolean inicjalizacjaGry() {
        inicjalizacjaLokacji();
        //inicjalizacjaBossow();
        inicjalizacjaWydarzenFabularnych();


        return true;
    }


    private boolean rozpocznijGre() {
        return false;
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

        Wydarzenie wprowadzenie_1 = new Wydarzenie("Wprowadzenie",
                "\"Jezeli Krol dalej bedzie nakladal na nas tak wysokie podatki, to juz nigdy nie wyjdziemy" +
                        " z kryzysu! Mam tylko nadzieje, ze nie zamkna naszego zakladu, to dopiero bylaby" +
                        " katastrofa...\" myslal Roman wracajac do domu po kolejnym ciezkim dniu pracy jako" +
                        "ciesla. Wraca do domu a tam zony nimo." );
        wydarzenia.put(wprowadzenie_1.getNazwa(),wprowadzenie_1);

        Wydarzenie spotkanieNaBajkowejPolanie_2 = new Wydarzenie("Spotkanie na bajkowej polanie",
                "opis");
        wydarzenia.put(spotkanieNaBajkowejPolanie_2.getNazwa(), spotkanieNaBajkowejPolanie_2);
    }

    private void inicjalizacjaLokacji() {

        // Lokacja startowa - tylko do wywolania wydarzenia fabularnego
        // wprowadzenie_1

        // Lokacja lokacjaStartowa = new Lokacja("Lokacja startowa", );

        // Chalupka nad urwista dolina

        /*
        (String nazwa,
                   List<String> opisy,
                   Wydarzenie fabularne,
                   List<Wydarzenie> poboczne,
                   List<Integer> sasiednie)
         */
    /*
            Lokacja lokacja0 = new Lokacja("Chałpka nad urwistą doliną",
                Arrays.asList("Znajdujesz się w swojej chałpce, dookoła panuje straszny bałagan, wszędzie na podłodze walają" +
                        " się szczątki tego co jeszcze przed chwilą było Twoim dawnym mieszkaniem. Nadszedł najwyższy" +
                        " czas by wyruszyć w drogę i uratować Twoją ukochaną małżonkę. Uzbrojony w kijek i parę złotych monet" +
                        " jesteś gotów by podjąć wyzwanie Czarnoksiężnikowi."),
                new Wydarzenie("fabularne", "opis"),
                Arrays.asList(new Wydarzenie("w1","opis"),
                Arrays.asList(1,2,3)
                ));
     */


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
        //return new Lokacja[1];
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
}
