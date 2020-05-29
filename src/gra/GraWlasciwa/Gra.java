package gra.GraWlasciwa;

import gra.NPC.Boss;
import gra.NPC.Fabularny;
import gra.NPC.Handlarz;
import gra.NPC.Wrog;
import gra.RodzajeGracz.Gracz;
import gra.RodzajeGracz.Kaplan;
import gra.RodzajeGracz.Mag;
import gra.RodzajeGracz.Wojownik;
import gra.RodzajePrzedmiot.PrzedmiotFabularny;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Gra{
    // tylko jedna instancja gry - singleton
    private static Scanner in;
    private int wyborGracza;

    private static Gra instance;
    private Gracz gracz;
    private List<Lokacja> lokacje;

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

    public int menuPodstawowe(){
        System.out.println("1. Pokaż ekwipunek\n" +
                "2. Przejdź do innej lokalizacji\n");
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
                        "ciesla. Wraca do domu a tam zony nimo.", gracz, null, null, 0, null);

        Wydarzenie spotkanieNaBajkowejPolanie_2;
    }

    private List<Lokacja> inicjalizacjaLokacji() {

        List<Lokacja> lokacjeTMP = new ArrayList<>();
        /*
        (String nazwa,
                   String opis,
                   Wydarzenie fabularne,
                   Wydarzenie[] poboczne,
                   int[] sasiednie)
         */
        // numery lokacji w tablicy lokacje
        //  0
        lokacjeTMP.add(new Lokacja("Chałpka nad urwistą doliną",
                "Znajdujesz się w swojej chałpce, " +
                "dookoła panuje straszny bałagan, wszędzie na podłodze walają się szczątki" +
                " tego co jeszcze przed chwilą było Twoim dawnym mieszkaniem. Nadszedł najwyższy" +
                " czas by wyruszyć w drogę i uratować Twoją ukochaną małżonkę. Uzbrojony w kijek i parę" +
                " złotych monet jesteś gotów by podjąć wyzwanie Czarnoksiężnikowi. Wybierz co chcesz zrobić.\n",
                new Wydarzenie("Lokacja startowa", "Gracz zaczyna gre, znajduje sie w lokacji startowej", gracz, null, null, 0, null),
                null,
                new int[] {1}
                ));

        // 1
        lokacjeTMP.add(new Lokacja("Bajkowa polana",
                "Dochodzisz do bajkowej polany, księżyc rozpościera" +
                " się na ciemnym niebie, panuje północ. Rozglądasz się dookoła jednak po czarnoksiężniku ani widu ani słychu." +
                " Czujesz napływającą do głowy gorycz i rozczarowanie, czujesz się oszukany, jednak zarazem jeszcze mocniej " +
                "zmotywowany. Uświadamiasz sobie, że sam musisz obrać cel swoich kolejnych poszukiwań.\n",
                new Wydarzenie(
                        "List od Czarnoksieznika",
                        "Gracz otrzymuje list od czarnoksieznika, ktory wprowadza go do gry i wskazuje co ma dalej robic",
                        gracz,
                        null,
                        null,
                        0,
                        null),
                new Wydarzenie[] {
                        new Wydarzenie(
                                "Walka z zajacem",
                                "Na polanie pojawia sie zajac, gracz moze z nim walczyc, wprowadzenie do walki",
                                gracz,
                                null,
                                null,
                                Arrays.asList(new Wrog("Zajac", 10, 10)), null)},
                new int[] {2, 8}
                ));

        // 3
        lokacjeTMP.add(new Lokacja("Miasto",
                "Docierasz do majestatycznego miasta Mysłowice, wszędzie panuje zamęt, na ulicach " +
                        "widać pełno mieszkańców, którzy w świetle dzisiejszego słonecznego dnia wyszli na ulicę " +
                        "zaczerpnąć trochę świeżego powietrza. Na ulicach swoje kramiki rozstawili tutejsi handlarze, " +
                        "którzy energicznym wymachywaniem swoich rąk zachęcają Cię do skorzystania z ich ofert. W jednej" +
                        " z uliczek napotykasz żebraka, który przygląda CI się uważnie swoim tajemniczym wzrokiem\n",
                new Wydarzenie(
                        "Spotkanie z zebrakiem",
                        "Gracz spotyka zebraka, ktory przekazuje mu wiadomosc od czarnoksieznika",
                        gracz,
                        Arrays.asList(new Fabularny("Zebrak", true, new PrzedmiotFabularny(false,
                                "Moj najdrozszy Romanie z ogromna nostalgia wspominam nasze minione porachunki, " +
                                        "niestety musze Cie zawiadomic, ze niestety nie moglem pojawic sie na naszym ostatnim spotkaniu na Bajkowej Polanie. " +
                                        "Twoja ukochana jest wciaz cala i zdrowa, jednak jej spotkanie nie bedzie takie proste. Odnajdziesz mnie za pomoca trzech Tajemniczych Kluczy ktore " +
                                        "mozesz zdobyc rozprawiajac sie z trzema bossami. Do zobaczenia\n", 0))),
                        null,
                        0,
                        null
                ),
                new Wydarzenie[] {
                        new Wydarzenie("Spotkanie z handlarzem w miescie", "Gracz spotyka miejskiego handlarza, ktory pokazuje mu co ma pod swoim szynkwasem", gracz, null,
                                List.of(new Handlarz("Miejski handlarz")), 0, null)
                },
                // todo dodac lokajce z bossem
                new int[1]
        ));

//        // 4
//        lokacjeTMP.add(new Lokacja("Czarny szczyt",
//                "Po długiej wędrówce docierasz na szczyt, przed Tobą rozpościera się niesamowity widok. " +
//                        "Na swojej drodze jednak napotykasz rozwścieczonego wilka, który nie jest zadowolony z " +
//                        "Twojej obecności. Wyciągasz swoją broń i przygotowujesz się na najgorszę, rozpoczyna się walka.",
//                ));
//
//        // 5
//        lokacjeTMP.add(new Lokacja("Magiczna przystań",
//                "Docierasz do niesamowitego miejsca w powietrzu czuć unoszącą się tutaj magię. Czujesz dziwną radość" +
//                        " i niepokój zarazem, pod ogromnym drzewem zauważasz zamknięta skrzynię, na wieku której widać" +
//                        " wyrytą inskrypcję, brzmiącą następująco\n",
//                ));
//
//        // 6
//        lokacjeTMP.add(new Lokacja("Miasteczko Ravelholm",
//                "Przekraczasz mury miasteczka Ravelholm, we wsi panuje dziwna cisza, po środku, przy ogromnej studni" +
//                        " znajduje się tajemnicza postać. Podchodzisz do niej, a ona okazuje się być tutejszym handlarzem",
//                ));
//
//        // 7
//        lokacjeTMP.add(new Lokacja("Grota bębniarza",
//                "Wchodzisz do olbrzymiej jaskini, płomienie dogasającego ogniska rzucają lekką poświatę na ściany" +
//                        " pomieszczenia. Podchodzisz bliżej by ogrzać swoje zziębnięte ręce i zauważasz ogromnego trolla" +
//                        " w głębi jaskini, który bez zastnowienia rzuca się na Ciebie",
//                ));
//
//        // 8
//        lokacjeTMP.add(new Lokacja("Żebrowe wzgórze",
//                "Docierasz do rozległego wzgórza, którego widok rozpościera się na całą krainę. W oddali widać" +
//                        " majaczącą się postać, która po dłuższej obserwacji okazuje się być Pradawnym Strażnikiem tego " +
//                        "miejsca, który nie pozwala Ci przejść. Musisz dostać się dalej, nie pozostaje Ci więc nic innego" +
//                        " jak walka ze zbrojnym",
//                ));
//
//        // 9
//        lokacjeTMP.add(new Lokacja("Zdradliwe urwisko",
//                "Docierasz do urwiska, starasz się nawet nie patrzeć w dół, pamiętając jednocześnie, że musisz " +
//                        "dostać się do Gniazda Harpii, aby zdobyć kolejną z przepustek. Dotarłszy do gniazda, już masz " +
//                        "sięgać po jedno z jaj, w którym z pewnością znajduje się przepustka, gdy nagle z nieba naciera na" +
//                        " Ciebie Królowa Harpii",
//                ));
//
//        // 10
//        lokacjeTMP.add(new Lokacja("Orla przepaść",
//                "Przemierzasz most łączący krainę światła i cienia, który pozwala bezpiecznie przejść nad niekończącą" +
//                        " się przepaścią, na Twojej drodze staje Gnomi Wojownik, który wymachując swoją pałką, ślini się " +
//                        "na Twój widok",
//                ));
//
//        // 11
//        lokacjeTMP.add(new Lokacja("Wysoka Brama",
//                "Docierasz do ogromnych wrót, które wystają ponad chmury, widać na nich zapisaną elfickim językiem " +
//                        "inskrypcję",
//                ));

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
        return lokacjeTMP;
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