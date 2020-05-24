package gra;

import java.util.Scanner;

public class Gra {
    // tylko jedna instancja gry - singleton
    private Scanner in;
    private int wyborGracza;

    private static Gra instance;
    private Gracz gracz;
    private Lokacja[] lokacje;

    private Gra() {
        in = new Scanner(System.in);

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
        lokacje = inicjalizacjaLokacji();
        //inicjalizacjaBossow();
        inicjalizacjaWydarzenFabularnych();


        return true;
    }


    private boolean rozpocznijGre() {
        return false;
    }

    private void inicjalizacjaWydarzenFabularnych() {
        // nazywa wydarzenia fabularnego (obiektu) - nazwa + podloga +
        // kolejny numer wg postepu fabuly

        Wydarzenie wprowadzenie_1 = new Wydarzenie("Wprowadzenie",
                "\"Jezeli Krol dalej bedzie nakladal na nas tak wysokie podatki, to juz nigdy nie wyjdziemy" +
                        " z kryzysu! Mam tylko nadzieje, ze nie zamkna naszego zakladu, to dopiero bylaby" +
                        " katastrofa...\" myslal Roman wracajac do domu po kolejnym ciezkim dniu pracy jako" +
                        "ciesla. Wraca do domu a tam zony nimo." );

        Wydarzenie spotkanieNaBajkowejPolanie_2;
    }

    private Lokacja[] inicjalizacjaLokacji() {

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
    }
}
