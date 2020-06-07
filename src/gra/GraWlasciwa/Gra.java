package gra.GraWlasciwa;

import gra.ElementyPomocnicze.KolorTekstu;
import gra.ElementyPomocnicze.Zagadka;
import gra.NPC.Boss;
import gra.NPC.Fabularny;
import gra.NPC.Handlarz;
import gra.NPC.Wrog;
import gra.RodzajeGracz.Gracz;
import gra.RodzajeGracz.Kaplan;
import gra.RodzajeGracz.Mag;
import gra.RodzajeGracz.Wojownik;
import gra.RodzajePrzedmiot.BronMagiczna;
import gra.RodzajePrzedmiot.PrzedmiotFabularny;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Gra {
    // tylko jedna instancja gry - singleton
    private static Scanner in;
    private int wyborGracza;

    private static Gra instance;
    private Gracz gracz;
    private List<Lokacja> lokacje;

    private List<Wydarzenie> wydarzeniaPoboczne;

    private int lokalizacjaGracza;

    public static void main(String[] args) {
        Gra.getInstance().granko();
    }

    private Gra() {
        System.out.println("Zainicjalowana GRA");
    }

    public void granko(){
        in = new Scanner(System.in);
        lokalizacjaGracza = 0;

        wyczyscTerminal();
        KolorTekstu.printCyan("Czarnoksieznik z Krainy Koz\n");

        KolorTekstu.printZolty("1.Rozpocznij nowa gre \n2.Pokaz instrukcje");
        wyborGracza = wczytajWyborGracza(2, false);

        if (wyborGracza == 2) {
            pokazInstrukcje();
            System.out.println("1.Kontynuuj");
            wyborGracza = wczytajWyborGracza(1, false);
        }

        wyczyscTerminal();
        KolorTekstu.printZolty("Wybierz swoja klase postaci:\n1.Wojownik\n2.Mag\n3.Kaplan");
        wyborGracza = wczytajWyborGracza(3, false);

        if (!inicjalizacjaGry(wyborGracza)) {
            System.out.println("Blad podczas inicjalizacji gry!");
            System.exit(1);
        }

        if (rozpocznijGre()) {
            wygrana();
        } else {
            przegrana();
        }
    }

    public static Gra getInstance() {
        if (instance == null) {
            instance = new Gra();
        }
        return instance;
    }

    private void pokazInstrukcje() {
        wyczyscTerminal();
        KolorTekstu.printZolty("To jest instrukcja");
    }

    private boolean inicjalizacjaGry(int klasaPostaci) {
        switch (klasaPostaci) {
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
        Menu.setGracz(gracz);
        inicjalizacjaWydarzenFabularnych();
        wydarzeniaPoboczne = new ArrayList<>();
        wydarzeniaPoboczne.add(new Wydarzenie("Walka z zajacem",
                "Na polanie pojawia sie zajac, gracz moze z nim walczyc, wprowadzenie do walki", gracz, null,
                Arrays.asList(new Wrog("Zajac", 10, 10)), null));
        lokacje = inicjalizacjaLokacji();
        Menu.setLokacje(lokacje);

        return true;
    }

    private boolean rozpocznijGre() {
        while (true) {
            KolorTekstu.printCyan(this.lokacje.get(lokalizacjaGracza).getOpis());
            if(this.lokacje.get(lokalizacjaGracza).getWydarzeniaPoboczne() != null) {
                this.lokacje.get(lokalizacjaGracza).getWydarzeniaPoboczne().forEach(wydarzenie -> {
                    if(!wydarzenie.getCzyWykonana()) {

                        KolorTekstu.printCyan(wydarzenie.getNazwa());
                        KolorTekstu.printCyan(wydarzenie.getOpis());
                        if (wydarzenie.getPostacieFabularne() != null) {
                            wydarzenie.getPostacieFabularne().forEach(postac -> {
                                postac.podarujLosowyPrzedmiotNieFabularny();
                                if (postac.isCzyPosiadaPrzedmiotFabularny()) {
                                    gracz.getEkwipunek().wlozFabularne(postac.podarujPrzedmiotFabularny());
                                }
                            });
                        }
                        if (wydarzenie.getZagadka() != null) {
                            wydarzenie.zagadka();
                        }
                        if (wydarzenie.getWrogowie() != null) {
                            wydarzenie.getWrogowie().forEach(wrog -> {
                                Walka.walka(gracz, wrog);
                            });
                        }
                        if (wydarzenie.getBoss() != null) {
                            Walka.walka(gracz, wydarzenie.getBoss());
                        }

                        wydarzenie.setCzyWykonana(true);
                    }
                });
            }

            if(this.lokacje.get(lokalizacjaGracza).getWydarzenieFabularne() != null && !this.lokacje.get(lokalizacjaGracza).getWydarzenieFabularne().getCzyWykonana()) {
                KolorTekstu.printCyan(this.lokacje.get(lokalizacjaGracza).getWydarzenieFabularne().getNazwa());
                KolorTekstu.printCyan(this.lokacje.get(lokalizacjaGracza).getWydarzenieFabularne().getOpis());
                if(this.lokacje.get(lokalizacjaGracza).getWydarzenieFabularne().getPostacieFabularne() != null) {
                    this.lokacje.get(lokalizacjaGracza).getWydarzenieFabularne().getPostacieFabularne().forEach(postac -> {
                        postac.podarujLosowyPrzedmiotNieFabularny();
                        if(postac.isCzyPosiadaPrzedmiotFabularny()) {
                            gracz.getEkwipunek().wlozFabularne(postac.podarujPrzedmiotFabularny());
                        }
                    });
                }
                if(this.lokacje.get(lokalizacjaGracza).getWydarzenieFabularne().getZagadka() != null) {
                    this.lokacje.get(lokalizacjaGracza).getWydarzenieFabularne().zagadka();
                }
                if(this.lokacje.get(lokalizacjaGracza).getWydarzenieFabularne().getWrogowie() != null) {
                    this.lokacje.get(lokalizacjaGracza).getWydarzenieFabularne().getWrogowie().forEach(wrog -> {
                        Walka.walka(gracz, wrog);
                    });
                }
                if(this.lokacje.get(lokalizacjaGracza).getWydarzenieFabularne().getBoss() != null) {
                    Walka.walka(gracz, this.lokacje.get(lokalizacjaGracza).getWydarzenieFabularne().getBoss());
                }

                this.lokacje.get(lokalizacjaGracza).getWydarzenieFabularne().setCzyWykonana(true);
            }

            while(Menu.menuGlowne()) {

            }

            if(lokalizacjaGracza == 20) {
                break;
            }

        }
        return true;
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
            if(wybor == 0 && mozliwoscPowrotu) {
                return 0;
            } else if (1 <= wybor && wybor <= liczbaOpcji) {
                return wybor;
            } else {
                System.out.println("Nieprawidlowy numer instrukcji");
            }
        }
    }

    private void inicjalizacjaWydarzenFabularnych() {
        // nazywa wydarzenia fabularnego (obiektu) - nazwa + podloga +
        // kolejny numer wg postepu fabuly

        // Wydarzenie walka

        Wydarzenie wprowadzenie_1 = new Wydarzenie("Wprowadzenie",
                "\"Jezeli Krol dalej bedzie nakladal na nas tak wysokie podatki, to juz nigdy nie wyjdziemy"
                        + " z kryzysu! Mam tylko nadzieje, ze nie zamkna naszego zakladu, to dopiero bylaby"
                        + " katastrofa...\" myslal Roman wracajac do domu po kolejnym ciezkim dniu pracy jako"
                        + "ciesla. Wraca do domu a tam zony nimo.",
                gracz, null, 0, null);

        Wydarzenie spotkanieNaBajkowejPolanie_2;
    }

    private List<Lokacja> inicjalizacjaLokacji() {

        List<Lokacja> lokacjeTMP = new ArrayList<>();
        /*
         * (String nazwa, String opis, Wydarzenie fabularne, Wydarzenie[] poboczne,
         * int[] sasiednie)
         */
        // numery lokacji w tablicy lokacje
        // 0
        lokacjeTMP.add(new Lokacja("Chałpka nad urwistą doliną", "Stary domek Romana i jego ukochanej.",
                new Wydarzenie("Lokacja startowa", "Znajdujesz się w swojej chałpce, "
                        + "dookoła panuje straszny bałagan, wszędzie na podłodze walają się szczątki"
                        + " tego co jeszcze przed chwilą było Twoim dawnym mieszkaniem. Nadszedł najwyższy"
                        + " czas by wyruszyć w drogę i uratować Twoją ukochaną małżonkę. Uzbrojony w kijek i parę"
                        + " złotych monet jesteś gotów by podjąć wyzwanie Czarnoksiężnikowi. Wybierz co chcesz zrobić.\n", gracz, null, 0, null),
                null, List.of( 1 ), null));

        // 1
        lokacjeTMP.add(new Lokacja("Bajkowa polana", "Bajkowa polana, księżyc w nowiu.",
                new Wydarzenie("List od Czarnoksieznika",
                        "Dochodzisz do bajkowej polany, księżyc rozpościera"
                                + " się na ciemnym niebie, panuje północ. Rozglądasz się dookoła jednak po czarnoksiężniku ani widu ani słychu."
                                + " Czujesz napływającą do głowy gorycz i rozczarowanie, czujesz się oszukany, jednak zarazem jeszcze mocniej "
                                + "zmotywowany. Uświadamiasz sobie, że sam musisz obrać cel swoich kolejnych poszukiwań.\n",
                        gracz, null, 0, null),
                List.of(new Wydarzenie("Walka z zajacem",
                        "Na polanie pojawia sie zajac, gracz moze z nim walczyc, wprowadzenie do walki", gracz, null,
                        Arrays.asList(new Wrog("Zajac", 10, 10)), null)),
                List.of( 2, 3, 7 ), null));

        // 2
        lokacjeTMP.add(new Lokacja("Miasto",
                "Docierasz do majestatycznego miasta Mysłowice, wszędzie panuje zamęt, na ulicach "
                        + "widać pełno mieszkańców, którzy w świetle dzisiejszego słonecznego dnia wyszli na ulicę "
                        + "zaczerpnąć trochę świeżego powietrza. Na ulicach swoje kramiki rozstawili tutejsi handlarze, "
                        + "którzy energicznym wymachywaniem swoich rąk zachęcają Cię do skorzystania z ich ofert.",
                new Wydarzenie("Spotkanie z zebrakiem",
                        "W jednej z uliczek napotykasz żebraka, który przygląda Ci się uważnie swoim tajemniczym wzrokiem \n", gracz,
                        Arrays.asList(new Fabularny("Zebrak", true, new PrzedmiotFabularny("List", "List od czarnoksieznika", 100,50, false,
                                "Moj najdrozszy Romanie z ogromna nostalgia wspominam nasze minione porachunki, "
                                        + "niestety musze Cie zawiadomic, ze niestety nie moglem pojawic sie na naszym ostatnim spotkaniu na Bajkowej Polanie. "
                                        + "Twoja ukochana jest wciaz cala i zdrowa, jednak jej spotkanie nie bedzie takie proste. Odnajdziesz mnie za pomoca trzech Tajemniczych Kluczy ktore "
                                        + "mozesz zdobyc rozprawiajac sie z trzema bossami. Do zobaczenia\n",
                                0))),
                         0, null),
                List.of( new Wydarzenie(
                        "Spotkanie z handlarzem w miescie",
                        "Gracz spotyka miejskiego handlarza, ktory pokazuje mu co ma pod swoim szynkwasem",
                        gracz,
                        null,
                         0, null),
                        new Wydarzenie("Walka z bandyta", "Na swojej drodze napotykasz ulicznego zawadiake, ktory pragnie pokazac Ci gdzie raki zimuja",
                                gracz,
                                null,
                                List.of(new Wrog("Bandyta", 20, 20)), null)),
                        List.of( 1, 11, 12, 13 ), new Handlarz("Miejski handlarz") )
                );

        // // 3
        lokacjeTMP.add(new Lokacja("Czarny szczyt", "Po długiej wędrówce docierasz na szczyt, przed Tobą rozpościera się niesamowity widok.", null,
                List.of(new Wydarzenie("Walka z wilkiem",
                        "Na swojej drodze jednak napotykasz rozwścieczonego wilka, który nie jest zadowolony z " +
                        "Twojej obecności. Wyciągasz swoją broń i przygotowujesz się na najgorszę, rozpoczyna się walka.", gracz, null,
                        Arrays.asList(new Wrog("Wilk", 40, 30)), null)), List.of(1, 4, 5, 6), null));
        //
        // // 4
        // lokacjeTMP.add(new Lokacja("Magiczna przystań",
        // "Docierasz do niesamowitego miejsca w powietrzu czuć unoszącą się tutaj
        // magię. Czujesz dziwną radość" +
        // " i niepokój zarazem, pod ogromnym drzewem zauważasz zamknięta skrzynię, na
        // wieku której widać" +
        // " wyrytą inskrypcję, brzmiącą następująco\n",
        // ));
        lokacjeTMP.add(new Lokacja("Magiczna przystan", "Docierasz do niesamowitego miejsca w powietrzu czuć unoszącą się tutaj magię. Czujesz dziwną radość i niepokój zarazem",
                        new Wydarzenie("Zagadka na wieku skrzyni", "Pod ogromnym drzewem zauważasz zamknięta skrzynię, na wieku której widać wyrytą inskrypcję, brzmiącą następująco",
                                gracz, null, null, null, new Zagadka("Co jest lepsze od wszystkich bogów i gorsze od unicestwienia duszy? Umarli jedzą to cały czas, a pożywiający się tym żywi powoli umierają", "Nic", new PrzedmiotFabularny(
                                        "Przepustka 1", "Przepustka do boss'a 1", 0, 0, false, "Uzyj, aby wejsc do boss'a 1", 10
                        ))), List.of(new Wydarzenie("Walka z zywiolakiem",
                        "Rzuca sie na Ciebie wsciekly zywiolak, wyciagasz bron i stajesz do walki.", gracz, null,
                        Arrays.asList(new Wrog("Zywiolak", 100, 50)), null)), List.of(3, 5, 6), null
                        ));
        //
        // // 5
        lokacjeTMP.add(new Lokacja("Miasteczko Ravelholm", "Przekraczasz mury miasteczka Ravelholm, we wsi panuje dziwna cisza", null,
                List.of(
                new Wydarzenie(
                        "Spotkanie z handlarzem w miescie",
                        "Po środku, przy ogromnej studni znajduje się tajemnicza postać, Podchodzisz do niej, a ona " +
                                "okazuje się być tutejszym handlarzem.",
                        gracz,
                        null,
                         0,
                        null)),
                List.of(3, 4, 6),
                new Handlarz("Miejski handlarz")));
        //
        // // 6
        lokacjeTMP.add(new Lokacja("Grota bebniarza", "Wchodzisz do olbrzymiej jaskini, płomienie dogasającego ogniska rzucają lekką poświatę na ściany pomieszczenia.",
                new Wydarzenie("Walka z trollem", "Podchodzisz bliżej by ogrzać swoje zziębnięte ręce i zauważasz ogromnego trolla w głębi jaskini, który bez zastnowienia rzuca się na Ciebie.", gracz,
                        null,  List.of(new Wrog("Troll", 120, 60)), null), null, List.of(3, 4, 5), null));
        //
        // // 7
        // lokacjeTMP.add(new Lokacja("Żebrowe wzgórze",
        // "Docierasz do rozległego wzgórza, którego widok rozpościera się na całą
        // krainę. W oddali widać" +
        // " majaczącą się postać, która po dłuższej obserwacji okazuje się być
        // Pradawnym Strażnikiem tego " +
        // "miejsca, który nie pozwala Ci przejść. Musisz dostać się dalej, nie
        // pozostaje Ci więc nic innego" +
        // " jak walka ze zbrojnym",
        // ));
        lokacjeTMP.add(new Lokacja("Zebrowe wzgorze", "Docierasz do rozległego wzgórza, którego widok rozpościera się na całą krainę",
                new Wydarzenie("Walka ze straznikiem",
                        "W oddali widać majaczącą się postać, " +
                                "która po dłuższej obserwacji " +
                                "okazuje się być Pradawnym Strażnikiem " +
                                "tego miejsca, który nie pozwala Ci przejść. " +
                                "Musisz dostać się dalej, nie pozostaje Ci więc nic " +
                                "innego jak walka ze zbrojnym", gracz, null, List.of(new Wrog("Straznik", 30, 10)), null), List.of(
                                        new Wydarzenie("Walka z orlem", "Nie wiadomo skad pikuje na Ciebie olbrzymi orzel.", gracz, null,  List.of(new Wrog("Orzel", 20, 10)), null)
        ), List.of(1, 8, 9, 10), null));
        //
        // // 8
        // lokacjeTMP.add(new Lokacja("Zdradliwe urwisko",
        // "Docierasz do urwiska, starasz się nawet nie patrzeć w dół, pamiętając
        // jednocześnie, że musisz " +
        // "dostać się do Gniazda Harpii, aby zdobyć kolejną z przepustek. Dotarłszy do
        // gniazda, już masz " +
        // "sięgać po jedno z jaj, w którym z pewnością znajduje się przepustka, gdy
        // nagle z nieba naciera na" +
        // " Ciebie Królowa Harpii",
        // ));
        lokacjeTMP.add(new Lokacja("Zdradliwe urwisko", "Docierasz do urwiska, starasz sie nawet nie patrzec w dol, pamietajac jednoczesnie, że musisz dostac sie do Gniazda Harpii, aby zdobyc kolejna z przepustek",
                new Wydarzenie("Walka z krolowa harpii", "Dotarlszy do gniazda, juz masz siegac po jedno z jaj, w ktorym z pewnoscia znajduje sie przepustka, gdy nagle z nieba naciera na Ciebie Krolowa Harpii",
                        gracz, null, List.of(new Wrog("Krlowa Harpii", 20, 10, new PrzedmiotFabularny(
                        "Przepustka 2", "Przepustka do boss'a 2", 0, 0, false, "Uzyj, aby wejsc do boss'a 2", 10
                ))), null, null), null, List.of(1, 9, 10), null));
        //
        // // 9
        // lokacjeTMP.add(new Lokacja("Orla przepaść",
        // "Przemierzasz most łączący krainę światła i cienia, który pozwala bezpiecznie
        // przejść nad niekończącą" +
        // " się przepaścią, na Twojej drodze staje Gnomi Wojownik, który wymachując
        // swoją pałką, ślini się " +
        // "na Twój widok",
        // ));
        lokacjeTMP.add(new Lokacja("Orla przepasc", "Przemierzasz most laczacy kraine swiatla i cienia, ktory pozwala bezpiecznie przejsc nad niekonczaca sie przepascia",
                new Wydarzenie("Walka z Gnomim Wojownikiem", "Na Twojej drodze staje Gnomi Wojownik, ktory wymachujac swoja palka, slini sie na Twoj widok",
                        gracz,
                        null,
                        List.of(new Wrog("Gnomi Wojownik", 190, 90)), null, null),
                null, List.of(7, 8, 10), null));
        //
        // // 10
        // lokacjeTMP.add(new Lokacja("Wysoka Brama",
        // "Docierasz do ogromnych wrót, które wystają ponad chmury, widać na nich
        // zapisaną elfickim językiem " +
        // "inskrypcję",
        // ));
        lokacjeTMP.add(new Lokacja("Wysoka brama", "Docierasz do ogromnych wrot, ktore wystaja ponad chmury", new Wydarzenie(
                "Zagadka na elfich wrotach", "Na wrotach widac zapisana elfickim jezykiem inskrypcje", gracz, null, null, null, new Zagadka("Syc mnie, a bede zyl. Napoj mnie, a umre. Czym jestem?", "Ogniem", new PrzedmiotFabularny(
                "Przepustka 3", "Przepustka do boss'a 3", 0, 0, false, "Uzyj, aby wejsc do boss'a 3", 10
        ))
        ), null, List.of(7, 8, 9), null));

        // 11
        lokacjeTMP.add(
                new Lokacja(
                        "Przedpola Asgardu",
                        "Docierasz do przedpol Azgardu",
                        new Wydarzenie(
                                "Walka z bossem Mefisto",
                                "Osiagnales swoj cel, walczysz ze straszliwym Mefisto.",
                                gracz,
                                null,
                                null,
                                new Boss("Mefisto", 500, 170, new PrzedmiotFabularny("Przepustka do czarnoksieznika 1", "Przepustka pozwala sie dostac do czarnoksieznika", 0, 0, false, "Uzyj, aby wejsc do czarnoksieznika", 10)),
                                null

        ), null, List.of(12, 13, 2, 14), null));

        // 12
        lokacjeTMP.add(
                new Lokacja(
                        "Wzniesienie Marmonda",
                        "Docierasz do wzniesienia Marmonda",
                        new Wydarzenie(
                                "Walka z bossem Andariel",
                                "Osiagnales swoj cel, walczysz ze straszliwym Andarielem.",
                                gracz,
                                null,
                                null,
                                new Boss("Andariel ", 700, 200, new PrzedmiotFabularny("Przepustka do czarnoksieznika 2", "Przepustka pozwala sie dostac do czarnoksieznika", 0, 0, false, "Uzyj, aby wejsc do czarnoksieznika", 10)),
                                null

                        ), null, List.of(11, 13, 2, 14), null));

        // 13
        lokacjeTMP.add(
                new Lokacja(
                        "Krzywa wieza",
                        "Docierasz do krzywej Wiezy",
                        new Wydarzenie(
                                "Walka z bossem Beliar",
                                "Osiagnales swoj cel, walczysz ze straszliwym Beliarem.",
                                gracz,
                                null,
                                null,
                                new Boss("Beliar", 1000, 300, new PrzedmiotFabularny("Przepustka do czarnoksieznika 3", "Przepustka pozwala sie dostac do czarnoksieznika", 0, 0, false, "Uzyj, aby wejsc do czarnoksieznika", 10)),
                                null

                        ), null, List.of(11, 12, 2, 14), null));

        lokacjeTMP.add(new Lokacja("Wrota Beliara", "Docierasz do kresu swojej wedrwoki, ogladasz sie wokol siebie, ale wszedzie panuje chaos...",
                new Wydarzenie("Walka z Czarnoksieznikiem", "Stajesz na przeciw Czarnoskieznika", gracz, null, null, new Boss(
                        "Czarnoksieznik z Krainy Koz", 1000, 500, null
                ))
                , null, List.of(11, 12, 13),null));

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

    public int getLokalizacjaGracza() {
        return lokalizacjaGracza;
    }

    public void setLokalizacjaGracza(int lokalizacjaGracza) {
        this.lokalizacjaGracza = lokalizacjaGracza;
    }

    public Gracz getGracz() {
        return gracz;
    }

    public static void wyczyscTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ignored) {}
    }

}
